package wechatOrder.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wechatOrder.dao.*;
import wechatOrder.po.*;
import wechatOrder.po.vo.QueryOrdersForStaffVo;
import wechatOrder.po.vo.OrderItemVo;
import wechatOrder.po.vo.OrderVO;
import wechatOrder.po.vo.QueryMyOrdersVo;
import wechatOrder.service.OrderService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author JJ
 * @date 2019/12/7 - 11:24
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderitemMapper orderitemMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private SpecificationMapper specificationMapper;
    @Autowired
    private StaffMapper staffMapper;
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderVO saveOrderAndOrderItems(OrderVO orderVO) {

        //生成订单的时间
        orderVO.setOrderTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        orderVO.setState("0");//设置订单状态为未支付
        String orderId = UUID.randomUUID().toString().replace("-", "");//生成32位订单号,因为小程序中out_trade_no需要是32位的
        orderVO.setId(orderId);//设置订单号

        orderMapper.saveOrder(orderVO);//插入后,得到返回的id?
        List<Orderitem> orderItems = orderVO.getOrderItems();
        for (Orderitem orderItem : orderItems) {
            orderItem.setOrderId(orderId);
            orderitemMapper.saveOrderItem(orderItem);
        }

        return orderVO;
    }

    @Override
    public List<QueryOrdersForStaffVo> queryOrderForStaff(Map map) {
        List<QueryOrdersForStaffVo> queryOrdersForStaffVos = orderMapper.queryAllOrdersWithAddress(map);
        for (QueryOrdersForStaffVo queryOrdersForStaffVo : queryOrdersForStaffVos) {
            //给queryOrdersForStaffVo封装数据
            this.packaging(queryOrdersForStaffVo);
        }
        return queryOrdersForStaffVos;
    }

    @Override
    public QueryOrdersForStaffVo getOrderForStaff(String orderId) {
        QueryOrdersForStaffVo orderWithAddressByOrderId = orderMapper.getOrderWithAddressByOrderId(orderId);
        this.packaging(orderWithAddressByOrderId);
        return orderWithAddressByOrderId;
    }

    /**
     * 此方法用于给queryOrdersForStaffVo封装数据
     *
     * @param queryOrdersForStaffVo
     * @return
     */
    private QueryOrdersForStaffVo packaging(QueryOrdersForStaffVo queryOrdersForStaffVo) {
        //给返回的数据增加配送员姓名
        Integer deliverymanId = queryOrdersForStaffVo.getDeliverymanId();
        String deliveryman = "未指定取/送人员";
        if (deliverymanId != null) {
            StaffExample staffExample = new StaffExample();
            staffExample.createCriteria().andIdEqualTo(deliverymanId);
            List<Staff> staff = staffMapper.selectByExample(staffExample);
            if (staff.size() > 0) {
                deliveryman = staff.get(0).getRealName();
            } else {
                logger.warn("根据deliverymanId:" + deliverymanId + "找不到指定员工!!!");
            }
        }
        queryOrdersForStaffVo.setDeliveryman(deliveryman);
        //给返回的数据增加订单状态的文字描述
        queryOrdersForStaffVo.setState(OrderServiceImpl.transformState(queryOrdersForStaffVo.getState()));

        return queryOrdersForStaffVo;
    }

    /**
     * 这个方法用于将数字形式的state转换为相应的文字描述
     *
     * @param num
     * @return
     */
    private static String transformState(String num) {
        if ("10".equals(num)) {
            return "派送员已送达且用户已确认收货";
        } else if ("9".equals(num)) {
            return "用户已确认收货";
        } else if ("8".equals(num)) {
            return "派送员已送达";
        } else if ("7".equals(num)) {
            return "派送员正在派送";
        } else if ("6".equals(num)) {
            return "用户已确认出件且派送员已确认取件";
        } else if ("5".equals(num)) {
            return "用户已确认出件";
        } else if ("4".equals(num)) {
            return "派送员已确认取件";
        } else if ("3".equals(num)) {
            return "派送员正在取件";
        } else if ("2".equals(num)) {
            return "已支付,已指派";
        } else if ("1".equals(num)) {
            return "已支付,未被指派";
        } else if ("0".equals(num)) {
            return "未支付";
        } else if ("-1".equals(num)) {
            return "已取消";
        } else if ("-2".equals(num)) {
            return "申请退款";
        }
        return "异常,请联系管理员";
    }

    @Override
    public List<QueryMyOrdersVo> queryAllOrdersForUser(Integer userId, String orderState) {
        QueryMyOrdersVo queryMyOrdersVo;
        ArrayList<QueryMyOrdersVo> queryMyOrdersVoList = new ArrayList<QueryMyOrdersVo>();
        //先查询出指定用户的全部可见订单
        OrderExample orderExample = new OrderExample();
        ArrayList<String> stateNotIn = new ArrayList<String>();
        stateNotIn.add("-1");
        stateNotIn.add("-3");
        OrderExample.Criteria criteria = orderExample.createCriteria().andStateNotIn(stateNotIn).andUserIdEqualTo(userId);
        if (orderState != null && !"".equals(orderState)) {//如果传入了订单状态,说明不是查询该用户的全部订单,而是查询该用户指定状态的订单
            criteria.andStateEqualTo(orderState);
        }
        List<Order> orders = orderMapper.selectByExample(orderExample);//得到指定用户的全部可见订单
        for (Order order : orders) {
            queryMyOrdersVo = new QueryMyOrdersVo();
            List orderItemVos = new ArrayList<OrderItemVo>();

            String orderId = order.getId();
            Double goodsAmount = order.getGoodsAmount();
            Double freightCharge = order.getFreightCharge();
            Double actualAmount = order.getActualAmount();
            String state = order.getState();
            queryMyOrdersVo.setOrderId(orderId);
            queryMyOrdersVo.setGoodsAmount(goodsAmount);
            queryMyOrdersVo.setFreightCharge(freightCharge);
            queryMyOrdersVo.setActualAmount(actualAmount);
            queryMyOrdersVo.setState(state);

            Integer deliverymanId = order.getDeliverymanId();
            String staffRealName = "未指定取/送人员";
            String staffPhone = "";
            if (deliverymanId != null) {//如果已指派配送人员
                StaffExample staffExample = new StaffExample();
                staffExample.createCriteria().andIdEqualTo(deliverymanId);
                List<Staff> staffList = staffMapper.selectByExample(staffExample);

                if (staffList.size() > 0) {
                    Staff staff = staffList.get(0);
                    staffRealName = staff.getRealName();
                    staffPhone = staff.getPhone();
                }
            }

            queryMyOrdersVo.setDeliveryman(staffRealName);
            queryMyOrdersVo.setDeliverymanPhone(staffPhone);

            //根据该订单的id查询出所有订单项
            OrderitemExample orderitemExample = new OrderitemExample();
            orderitemExample.createCriteria().andOrderIdEqualTo(orderId);
            List<Orderitem> orderitems = orderitemMapper.selectByExample(orderitemExample);//得到该订单的所有订单项
            //遍历所有的订单项
            for (Orderitem orderitem : orderitems) {
                OrderItemVo orderItemVo = new OrderItemVo();
                //将订单项中的数据转存入orderItemVo中
                orderItemVo.setId(orderitem.getId());
                orderItemVo.setOrderId(orderitem.getOrderId());
                orderItemVo.setBuynum(orderitem.getBuynum());
                orderItemVo.setSpecificationId(orderitem.getSpecificationId());
                orderItemVo.setUnitPrice(orderitem.getUnitPrice());
                //查询并设置该订单项对应规格商品的名称,图片
                SpecificationExample specificationExample = new SpecificationExample();
                specificationExample.createCriteria().andIdEqualTo(orderitem.getSpecificationId());
                List<Specification> specifications = specificationMapper.selectByExampleWithBLOBs(specificationExample);
                Specification specification = specifications.get(0);
                String img = specification.getImg();
                String name = specification.getName();
                orderItemVo.setImg(img);
                orderItemVo.setSpecificationName(name);
                //查询并设置该订单项对应商品的名称
                ProductExample productExample = new ProductExample();
                productExample.createCriteria().andIdEqualTo(specification.getProductId());
                List<Product> products = productMapper.selectByExample(productExample);
                Product product = products.get(0);
                String productName = product.getProductName();
                Integer productId = product.getId();
                orderItemVo.setProductName(productName);
                orderItemVo.setProductId(productId);
                //将orderItemVo存入List
                orderItemVos.add(orderItemVo);

            }
            //将orderItemVos存入queryMyOrdersVo
            queryMyOrdersVo.setOrderItemVos(orderItemVos);
            queryMyOrdersVoList.add(queryMyOrdersVo);
        }
        return queryMyOrdersVoList;
    }

    @Override
    public List<OrderItemVo> getOrderItemsByOrderId(String orderId) {

        ArrayList<OrderItemVo> orderItemVos = new ArrayList<OrderItemVo>();
        OrderItemVo orderItemVo = new OrderItemVo();

        //根据orderid查询出所有订单项
        OrderitemExample orderitemExample = new OrderitemExample();
        orderitemExample.createCriteria().andOrderIdEqualTo(orderId);
        List<Orderitem> orderitems = orderitemMapper.selectByExample(orderitemExample);
        //遍历所有订单项
        for (Orderitem orderitem : orderitems) {
            //将orderitem中的数据填充至orderItemVo
            orderItemVo.setId(orderitem.getId());
            orderItemVo.setOrderId(orderitem.getOrderId());
            orderItemVo.setSpecificationId(orderitem.getSpecificationId());
            orderItemVo.setBuynum(orderitem.getBuynum());
            orderItemVo.setUnitPrice(orderitem.getUnitPrice());
            //根据specificationId查询出规格商品的信息
            Integer specificationId = orderitem.getSpecificationId();
            SpecificationExample specificationExample = new SpecificationExample();
            specificationExample.createCriteria().andIdEqualTo(specificationId);
            List<Specification> specifications = specificationMapper.selectByExampleWithBLOBs(specificationExample);
            if (specifications.size() > 0) {
                Specification specification = specifications.get(0);
                String img = specification.getImg();
                String specificationName = specification.getName();
                Integer productId = specification.getProductId();
                ProductWithBLOBs productWithBLOBs = productMapper.selectByPrimaryKey(productId);
                orderItemVo.setProductId(productId);
                orderItemVo.setImg(img);
                orderItemVo.setSpecificationName(specificationName);
                orderItemVo.setProductName(productWithBLOBs.getProductName());
            } else {
                logger.warn("根据specificationId查询不到specification" +
                        "orderId:" + orderId + ",orderitemId:" + orderitem.getId() + ",specificationId" + specificationId);
            }
            orderItemVos.add(orderItemVo);
        }

        return orderItemVos;
    }

    @Override
    public Integer appointOrderToDeliveryman(String orderId, Integer staffId) {
        Order order = new Order();
        order.setId(orderId);
        order.setDeliverymanId(staffId);
        order.setState("2");
        Integer result = orderMapper.updateByPrimaryKeySelective(order);
        return result;
    }

    @Override
    public Order getOrderById(String id) {
        return orderMapper.getOrderById(id);
    }

    @Override
    public void updateOrderById(Order order) {
        orderMapper.updateOrderById(order);
    }

    @Override
    public List<Orderitem> queryOrderItemsByOrderId(String orderid) {
        return orderitemMapper.queryOrderItemsByOrderId(orderid);
    }
}
