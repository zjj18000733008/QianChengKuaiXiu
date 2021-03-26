package wechatOrder.controller;


import com.sun.org.apache.xpath.internal.operations.Mod;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import wechatOrder.dao.OrderMapper;
import wechatOrder.job.QuartzJob;
import wechatOrder.po.*;
import wechatOrder.po.vo.OrderItemVo;
import wechatOrder.po.vo.QueryOrdersForStaffVo;
import wechatOrder.po.vo.OrderVO;
import wechatOrder.po.vo.QueryMyOrdersVo;
import wechatOrder.service.OrderService;
import wechatOrder.service.impl.QuartzService;
import wechatOrder.service.SpecificationService;
import wechatOrder.util.SecurityUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JJ
 * @date 2019/12/6 - 21:58
 */
@RestController
@RequestMapping("/order")
@SuppressWarnings("all")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private SpecificationService specificationService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private QuartzService quartzService;
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    //    private QuartzService quartzService=quartzService = new QuartzService();
    private static SchedulerFactory schedulerFactory;
    private static Scheduler scheduler;

    static {
        schedulerFactory = new StdSchedulerFactory();
        try {
            scheduler = schedulerFactory.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 定时删除未支付超时订单
     *
     * @throws ParseException
     */
    @PostConstruct
    public void init() throws ParseException {
        log.info("开始初始化石英调度>>>");
        System.out.println(">>>");
        JobDetailImpl jobDetail = new JobDetailImpl();
        jobDetail.setJobClass(QuartzJob.class);
        jobDetail.setKey(new JobKey("job1", "group1"));
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("quartzService", quartzService);
        jobDetail.setJobDataMap(jobDataMap);

        try {
            CronTriggerImpl cronTrigger = new CronTriggerImpl();
            cronTrigger.setCronExpression("0 0/15 * * * ?");//每十五分钟执行一次
            cronTrigger.setName("trigger1");
            cronTrigger.setGroup("group1");
            scheduler.scheduleJob(jobDetail, cronTrigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成订单
     * <p>
     * 服务性商品和其他商品(普通商品,二手商品)不能在一个订单中
     * 所以在前端在发送生成订单请求之前要先调用/product/getById接口查看商品类型
     * 价格由前端发过来,后端进行校验,如果对不上则报错
     * <p>
     * 运费先就不考虑吧,先就传0
     * <p>
     * 这部分涉及高并发问题,技术还没到这个程度
     *
     * @param req
     * @param orderVo
     * @param result
     * @return
     */
    @RequestMapping("/save")
    public ResponseEntity save(HttpServletRequest req, @Validated OrderVO orderVo, BindingResult result) {
        /* 权限校验 begin  */
//        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute("user");
//        if (user == null) {
//            log.warn("用户未登录");
//            return new ResponseEntity("请先登录", HttpStatus.UNAUTHORIZED);
//        }

        User user = (User) SecurityUtils.getCurrentUser();
        if (result.hasErrors()) {
            ArrayList<String> errList = new ArrayList<String>();
//            return new ResponseEntity(result.getAllErrors(), HttpStatus.PRECONDITION_FAILED);
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError allError : allErrors) {
                errList.add(allError.getDefaultMessage());
            }
            return new ResponseEntity(errList,HttpStatus.PRECONDITION_REQUIRED);
        }
        /* end */


        /* 校验前端传来的该规格商品的金额与数据库中存储的金额是否相同 begin */
        Double goodsAmount = orderVo.getGoodsAmount();
        Double goodsAmountDB = 0d;
        List<Orderitem> orderItems = orderVo.getOrderItems();
        log.info("开始校验下单数是否超过库存数...");
        for (Orderitem orderItem : orderItems) {
            Integer specificationId = orderItem.getSpecificationId();
            log.debug("specificationId>>>" + specificationId);
            Specification specification = specificationService.getSpecificationById(specificationId);

            if (specification.getInventory() < orderItem.getBuynum()) {//如果下单数超过库存数则结束请求  //涉及并发问题,有待改善
                log.error("当前订单中此规格商品的下单数为%5d,而该规格商品的库存数为%5d", orderItem.getBuynum(), specification.getInventory());
                return new ResponseEntity("下单数量超过库存数", HttpStatus.BAD_REQUEST);
            }

            goodsAmountDB += specification.getCurrentPrice() * orderItem.getBuynum();
        }
        if (!goodsAmount.equals(goodsAmountDB)) {
            log.error("传入的商品总金额与计算后的商品总金额不一致,传入的商品总金额为" + goodsAmount + ",而计算后的商品总金额为" + goodsAmountDB);
            return new ResponseEntity("传入的商品总金额与计算后的商品总金额不一致", HttpStatus.CONFLICT);
        }
        /* end */
        log.info("结束校验...");

        Integer id = user.getId();//从session中取出用户的id
        log.info("用户id>>>", id);
        orderVo.setUserId(id);//设置用户id
        //将订单相关信息传入service,并得到要返回给前端的数据
        OrderVO orderVO = orderService.saveOrderAndOrderItems(orderVo);
        if (orderVO == null) {//如果orderVO为空,代表service中出现了异常(会回滚)
            return new ResponseEntity("数据库异常", HttpStatus.PRECONDITION_FAILED);
        }

        //若一切正常,则将该商品的库存数减少
        for (Orderitem orderItem : orderItems) {
            Integer specificationId = orderItem.getSpecificationId();
            Specification specification = specificationService.getSpecificationById(specificationId);
            int inventory = specification.getInventory() - orderItem.getBuynum();//计算得到下单后该规格商品的库存数
            if (inventory == 0) {//如果下单后该商品的库存数为零
                //修改该规格商品state为已售罄
                specification.setState("0");
            }
            specification.setInventory(inventory);
            specificationService.updateSpecificationById(specification);//更新库存数
        }

        return new ResponseEntity(orderVO, HttpStatus.OK);

    }

    /**
     * 用户下了单,但是还未支付时,可以取消订单
     * 取消订单后,要恢复下单商品的库存数
     * 取消的订单对管理员可见,对用户不可见
     *
     * @return
     */
    @RequestMapping("/cancel")
    public ResponseEntity cancel(@RequestParam(required = true) String orderId, HttpServletRequest req) {

//        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute("user");
//        if (user == null) {
//            return new ResponseEntity("请先登录", HttpStatus.UNAUTHORIZED);
//        }
        User user = (User) SecurityUtils.getCurrentUser();

        Order order = orderService.getOrderById(orderId);
        if (order.getUserId() != user.getId()) {//防止用户操作其他用户的订单
            log.warn("userId为" + user.getId() + "的用户尝试对orderId为:" + order.getId() + "的订单进行侵权操作!!");
            return new ResponseEntity("未授权", HttpStatus.UNAUTHORIZED);
        }
        if (!"0".equals(order.getState())) {//如果订单并非处于未支付状态
            //则用户不能取消订单
            return new ResponseEntity("此订单并非处于未支付状态,不能取消", HttpStatus.BAD_REQUEST);
        }
        order.setState("-1");//将订单状态修改为用户已取消
        orderService.updateOrderById(order);//更新数据库中信息

        //根据订单id查找到对应的订单项
        List<Orderitem> orderitems = orderService.queryOrderItemsByOrderId(order.getId());
        for (Orderitem orderitem : orderitems) {//遍历订单项
            Integer specificationId = orderitem.getSpecificationId();//取出规格商品id
            //根据规格商品id从数据库中找到规格商品信息
            Specification specificationDB = specificationService.getSpecificationById(specificationId);
            Integer inventoryDB = specificationDB.getInventory();//得到数据库中原来的库存数
            Integer buynum = orderitem.getBuynum();//得到订单中该规格商品的数量
            specificationDB.setInventory(inventoryDB + buynum);//恢复下单前的库存数
            specificationService.updateSpecificationById(specificationDB);
        }

        return new ResponseEntity("订单已取消", HttpStatus.OK);
    }

    /**
     * 用户下了单,且付了款,则可删除订单
     * 删除后,此订单只是对用户来说不可见,对管理员来说任然是可见的
     * <p>
     * 暂时不启用此接口
     *
     * @return
     */
    @RequestMapping("/delete")
    public ResponseEntity delete(@RequestParam(required = true) String orderId, HttpServletRequest req) {

//        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute("user");
//        if (user == null) {
//            return new ResponseEntity("请先登录", HttpStatus.UNAUTHORIZED);
//        }
        User user = (User) SecurityUtils.getCurrentUser();
        Order order = orderService.getOrderById(orderId);
        if (order.getUserId() != user.getId()) {//防止用户操作其他用户的订单
            log.warn("userId为%s的用户对orderId为%s的订单正在尝试未授权操作!!!");
            return new ResponseEntity("未授权", HttpStatus.UNAUTHORIZED);
        }
        if (!"1".equals(order.getState())) {//如果订单并非处于已支付状态
            //则用户不可删除订单
            return new ResponseEntity("此订单并非处于已支付状态,不能删除", HttpStatus.BAD_REQUEST);
        }

        order.setState("-2");//修改订单状态为已删除
        orderService.updateOrderById(order);

        return new ResponseEntity("订单删除成功", HttpStatus.OK);
    }

    /**
     * 用户查询自己的所有订单
     * 通过state可以查询用户已支付、未支付的账单
     * 排序条件:
     * servicing_time [asc,desc]   //服务时间
     * actual_amount [asc,desc]    //订单实际需支付的金额
     * order_time [asc,desc]       //下单时间
     *
     * @return
     */
    @RequestMapping("/user/query")
    public ResponseEntity queryForUser(String state, HttpServletRequest req) {
        List<QueryMyOrdersVo> queryMyOrdersVos = null;

//        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute("user");
//        if (user == null) {
//            return new ResponseEntity("请先登录", HttpStatus.UNAUTHORIZED);
//        }
        User user = (User) SecurityUtils.getCurrentUser();
        log.info("userId为" + user.getId() + "的用户正在查询自己的所有订单");
        queryMyOrdersVos = orderService.queryAllOrdersForUser(user.getId(), state);


        return new ResponseEntity(queryMyOrdersVos, HttpStatus.OK);
    }

    /**
     * 管理员/普通员工查询订单
     * 可选参数: state,pattern
     * 排序条件:
     * servicing_time [asc,desc]   //服务时间
     * actual_amount [asc,desc]    //订单实际需支付的金额
     * order_time [asc,desc]       //下单时间
     * <p>
     * 管理员能查看用户已"删除"的订单,与已付款订单属于一类
     *
     * @param order
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_USER,ROLE_STAFF,ROLE_ADMIN')")
    @RequestMapping("/staff/query")
    public ResponseEntity queryForAdmin(Order order, String[] orderby, HttpServletRequest req) {
        Map map = new HashMap();

//        HttpSession session = req.getSession();
//        Staff staff = (Staff) session.getAttribute("staff");
//        if (staff == null) {
//            return new ResponseEntity("请先登录", HttpStatus.UNAUTHORIZED);
//        }
        Staff staff = (Staff) SecurityUtils.getCurrentUser();
        if (orderby != null) {//如果排序条件不为空,则需校验一下排序条件
            for (String s : orderby) {
                boolean flag = false;
                //判断传来的排序条件是否符合要求
                if (!(s.contains("servicing_time") || s.contains("actual_amount") || s.contains("order_time"))) {//如果传来的排序条件不满足条件
                    flag = true;
                }
                if (flag) {
                    return new ResponseEntity("排序条件错误", HttpStatus.PRECONDITION_FAILED);
                }
            }
            map.put("orderby", orderby);
        }


        map.put("order", order);
        List<QueryOrdersForStaffVo> orderAddressVOs = orderService.queryOrderForStaff(map);
        return new ResponseEntity(orderAddressVOs, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER,ROLE_STAFF,ROLE_ADMIN')")
    @RequestMapping("/staff/query/page")
    public ModelAndView queryForAdminToPage(Order order, String[] orderby, HttpServletRequest req, ModelAndView mv) {
        Map map = new HashMap();

        if (orderby != null) {//如果排序条件不为空,则需校验一下排序条件
            for (String s : orderby) {
                boolean flag = false;
                //判断传来的排序条件是否符合要求
                if (!(s.contains("servicing_time") || s.contains("actual_amount") || s.contains("order_time"))) {//如果传来的排序条件不满足条件
                    flag = true;
                }
                if (flag) {
//                    return new ResponseEntity("排序条件错误", HttpStatus.PRECONDITION_FAILED);
                }
            }
            map.put("orderby", orderby);
        }
        map.put("order", order);
        List<QueryOrdersForStaffVo> orderAddressVOs = orderService.queryOrderForStaff(map);
        mv.addObject("orderAddressVos", orderAddressVOs);
        mv.setViewName("order-list");
        return mv;
    }

    /**
     * 根据订单id查询出详细的订单项
     * 当员工/管理员在查询出所有订单后,点击具体的一个订单时,调用此接口
     *
     * @param orderId
     * @param req
     * @return
     */
    @RequestMapping("/get")
    public ResponseEntity get(@RequestParam String orderId) {
        List<OrderItemVo> orderItemVoList = orderService.getOrderItemsByOrderId(orderId);
        return new ResponseEntity(orderItemVoList, HttpStatus.OK);
    }

    @RequestMapping("/get/page")
    public ModelAndView getToPage(@RequestParam String orderId,ModelAndView mv) {
        List<OrderItemVo> orderItemVoList = orderService.getOrderItemsByOrderId(orderId);
        QueryOrdersForStaffVo orderForStaff = orderService.getOrderForStaff(orderId);
        mv.addObject("orderItemVoList",orderItemVoList);
        mv.addObject("orderForStaff",orderForStaff);
        mv.setViewName("order-show");
        return mv;
    }

    @RequestMapping("/deliveryman/get/page")
    public ModelAndView deliverymanGetToPage(@RequestParam String orderId,ModelAndView mv) {
        List<OrderItemVo> orderItemVoList = orderService.getOrderItemsByOrderId(orderId);
        QueryOrdersForStaffVo orderForStaff = orderService.getOrderForStaff(orderId);
        Order order = orderMapper.selectByPrimaryKey(orderId);
        mv.addObject("orderItemVoList",orderItemVoList);
        mv.addObject("orderForStaff",orderForStaff);
        mv.addObject("order",order);
        mv.setViewName("deliveryman-order-show");
        return mv;
    }

    @RequestMapping("/modify")
    public ResponseEntity modify() {
        return null;
    }

    /**
     * 用户确认收货的接口
     * @return
     */
    @RequestMapping("/user/confirmReceived")
    public ResponseEntity confirmReceived(@RequestParam String orderId){
        //从数据库中查询出该订单
        Order order = orderMapper.selectByPrimaryKey(orderId);
        //判断当前用户是否是此订单的主人
        User user = (User) SecurityUtils.getCurrentUser();
        if(user.getId()!=order.getUserId()){
            return new ResponseEntity("未授权操作,您非此订单的主人",HttpStatus.UNAUTHORIZED);
        }
        //得到该订单状态
        String state = order.getState();
        boolean flag=false;
        //判断该订单的状态是否为7
        if("7".equals(state)){
            //是,修改订单状态为9
            order.setState("9");
            orderMapper.updateByPrimaryKeySelective(order);
            flag=true;
        }else if("8".equals(state)){//判断该订单状态是否为8
            //是,修改订单状态为10
            order.setState("10");
            orderMapper.updateByPrimaryKeySelective(order);
            flag=true;
        }else{

        }

        if(flag){
            return new ResponseEntity("OK",HttpStatus.OK);
        }else {
            return new ResponseEntity("当前不可确认收货",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 用户确认已取货的接口
     * @param orderId
     * @param staffId
     * @param req
     * @return
     */
    @RequestMapping("/user/confirmPicked")
    public ResponseEntity confirmPicked(String orderId){
        //从数据库中查询出该订单
        Order order = orderMapper.selectByPrimaryKey(orderId);
        //判断当前用户是否是此订单的主人
        User user = (User) SecurityUtils.getCurrentUser();
        if(user.getId()!=order.getUserId()){
            return new ResponseEntity("未授权操作,您非此订单的主人",HttpStatus.UNAUTHORIZED);
        }
        //得到该订单状态
        String state = order.getState();
        boolean flag=false;
        //判断该订单的状态是否为3
        if("3".equals(state)){
            //是,修改订单状态为5
            order.setState("5");
            orderMapper.updateByPrimaryKeySelective(order);
            flag=true;
        }else if("4".equals(state)){//判断该订单状态是否为4
            //是,修改订单状态为6
            order.setState("6");
            orderMapper.updateByPrimaryKeySelective(order);
            flag=true;
        }else{

        }

        if(flag){
            return new ResponseEntity("OK",HttpStatus.OK);
        }else {
            return new ResponseEntity("当前不可确认取货",HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * 分配订单
     * 暂时只有管理员具有分配订单的权利
     * 在分配订单前,先查询出所有员工(日后优化->查询出所有配送员...),然后传入订单号和员工id
     *
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DISPATCHER')")
    @RequestMapping("/dispatch")
    public ResponseEntity dispatchOrder(@RequestParam String orderId, @RequestParam Integer staffId, HttpServletRequest req) {

//        HttpSession session = req.getSession();
//        Staff staff = (Staff) session.getAttribute("staff");
//        if (staff == null) {
//            return new ResponseEntity("请先登录", HttpStatus.UNAUTHORIZED);
//        }
//        if (!"管理员".equals(staff.getRole())) {//如果当前员工不是管理员,则无法执行指派订单操作
//            log.warn("staffId为:" + staff.getId() + "的用户正在尝试越权操作!!!");
//            return new ResponseEntity("未授权", HttpStatus.UNAUTHORIZED);
//        }
        Staff staff = (Staff) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("staffId为:" + staff.getId() + "的员工将orderId为:" + orderId + "指派给staffId为" + staffId + "的员工");
        Integer result = orderService.appointOrderToDeliveryman(orderId, staffId);
        if (result == 1) {
            log.info("指派成功");
            return new ResponseEntity("指派成功", HttpStatus.OK);
        } else {
            log.info("指派失败");
            return new ResponseEntity("指派失败", HttpStatus.PRECONDITION_FAILED);
        }
    }

    /**
     * 派送人员查询自己需要派送的订单
     *
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_DELIVERYMAN','ROLE_PICKER')")
    @RequestMapping("/deliveryman/query")
    public ResponseEntity deliveryman(HttpServletRequest req) {
//        HttpSession session = req.getSession();
//        Staff staff = (Staff) session.getAttribute("staff");
//        if (staff == null) {
//            return new ResponseEntity("请先登录", HttpStatus.UNAUTHORIZED);
//        }
//        if(!"管理员".equals(staff.getRole()) && !"配送员".equals(staff.getRole())){
//            log.warn("staffId为"+staff.getId()+"的员工正在尝试未授权操作->查询需要派送的订单");
//            return new ResponseEntity("未授权",HttpStatus.UNAUTHORIZED);
//        }
        Staff staff = (Staff) SecurityUtils.getCurrentUser();
        HashMap map = new HashMap();
        Order order = new Order();
        order.setDeliverymanId(staff.getId());
        map.put("order", order);
        List<QueryOrdersForStaffVo> queryOrdersForStaffVos = orderService.queryOrderForStaff(map);
        return new ResponseEntity(queryOrdersForStaffVos, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_DELIVERYMAN','ROLE_PICKER')")
    @RequestMapping("/deliveryman/query/page")
    public ModelAndView deliverymanToPage(ModelAndView mv) {
//        HttpSession session = req.getSession();
//        Staff staff = (Staff) session.getAttribute("staff");
//        if (staff == null) {
//            return new ResponseEntity("请先登录", HttpStatus.UNAUTHORIZED);
//        }
//        if(!"管理员".equals(staff.getRole()) && !"配送员".equals(staff.getRole())){
//            log.warn("staffId为"+staff.getId()+"的员工正在尝试未授权操作->查询需要派送的订单");
//            return new ResponseEntity("未授权",HttpStatus.UNAUTHORIZED);
//        }
        Staff staff = (Staff) SecurityUtils.getCurrentUser();
        HashMap map = new HashMap();
        Order order = new Order();
        order.setDeliverymanId(staff.getId());
        map.put("order", order);
        List<QueryOrdersForStaffVo> queryOrdersForStaffVos = orderService.queryOrderForStaff(map);
        mv.addObject("orderAddressVos",queryOrdersForStaffVos);
        mv.setViewName("deliveryman-order-list");
        return mv;
    }

    /**
     * 配送人员改变订单状态
     *
     * @param req
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_DELIVERYMAN','ROLE_PICKER','ROLE_ADMIN')")
    @RequestMapping("/deliveryman/changeOrderState")
    public ResponseEntity changeOrderState(HttpServletRequest req, @RequestParam String state, @RequestParam String orderId) {
//        HttpSession session = req.getSession();
//        Staff staff = (Staff) session.getAttribute("staff");
//        if (staff == null) {
//            return new ResponseEntity("请先登录", HttpStatus.UNAUTHORIZED);
//        }
//        if(!"管理员".equals(staff.getRole()) && !"配送员".equals(staff.getRole())){
//            log.warn("staffId为"+staff.getId()+"的员工("+staff.getRole()+")正在尝试越权操作->改变订单状态为"+state);
//            return new ResponseEntity("未授权",HttpStatus.UNAUTHORIZED);
//        }

        //配送员只能将订单状态改变为指定类型
        //预设值配送人员可改变的订单状态
        Staff staff = (Staff) SecurityUtils.getCurrentUser();
        String[] preState = new String[]{"3", "4", "7", "8"};
        boolean flag = false;
        for (String s : preState) {
            if (s.equals(state)) {//如果要修改的状态是被允许的
                flag = true;
            }
        }
        if (!flag) {
            log.warn("staffId为" + staff.getId() + "员工正尝试越权操作->将订单状态改为" + state);
            return new ResponseEntity("您无权将订单状态修改为" + state, HttpStatus.UNAUTHORIZED);
        }

        //判断该订单的派送员是否是当前员工
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null) {
            log.warn("当前操作的orderId为" + orderId + "的订单不存在");
            return new ResponseEntity("当前操作的orderId为" + orderId + "的订单不存在", HttpStatus.PRECONDITION_FAILED);
        }
        if (!staff.getId().equals(order.getDeliverymanId())) {//如果不是
            log.warn("staffId为" + staff.getId() + "的员工正在尝试操作其未被分配到的订单orderId为" + orderId);
            return new ResponseEntity("您无权操作此订单", HttpStatus.UNAUTHORIZED);
        }

        Order order1 = new Order();
        order1.setId(orderId);
        int result=0;
        String msg="未知错误";

        if("3".equals(state)){//如果想将订单状态修改为3
            //需要判断当前订单是否处于2
            if("2".equals(order.getState())){
                order1.setState("3");
                result =orderMapper.updateByPrimaryKeySelective(order1);
                msg="修改成功";
            }else{
                msg="订单当前状态不可修改为'正在取件'";
            }
        }else if("4".equals(state)){//->4 派送员已确认取件
            //判断当前订单是否处于3->派送员正在取件
            if("3".equals(order.getState())){
                order1.setState("4");
                result=orderMapper.updateByPrimaryKeySelective(order1);
                msg="修改成功";
            }else if("5".equals(order.getState())){
                order1.setState("6");
                orderMapper.updateByPrimaryKeySelective(order1);
            }else{
                msg="订单当前状态不可修改为'派送员已确认取件'";
            }
        }else if("7".equals(state)){
            if(order.getState().equals("2") || order.getState().equals("4") || order.getState().equals("6")){
                order1.setState("7");
                result=orderMapper.updateByPrimaryKeySelective(order1);
                msg="修改成功";
            }else{
                msg="订单当前状态不可修改为'派送员正在派送'";
            }
        }else if("8".equals(state)){
            if("7".equals(order.getState())){
                order1.setState("8");
                result=orderMapper.updateByPrimaryKeySelective(order1);
                msg="修改成功";
            }else if("9".equals(order.getState())){
                order1.setState("10");
                result=orderMapper.updateByPrimaryKeySelective(order1);
                msg="修改成功";
            }else{
                msg="订单当前状态不可修改为'派送员已送达'";
            }
        }



        if (result == 1) {
            return new ResponseEntity(msg, HttpStatus.OK);
        } else {
            return new ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
