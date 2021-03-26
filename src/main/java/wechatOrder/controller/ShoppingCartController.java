package wechatOrder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import wechatOrder.exception.NotAcceptableException;
import wechatOrder.exception.SomeParamIsNullException;
import wechatOrder.exception.UnAuthorizedException;
import wechatOrder.po.*;
import wechatOrder.service.ProductService;
import wechatOrder.service.SpecificationService;
import wechatOrder.util.RedisUtil;
import wechatOrder.util.SecurityUtils;
import wechatOrder.util.SerializableUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 这是购物车模块的控制器
 * 操作购物车实质上是在操作session中存的一个Map<Integer商品规格id,Integer此规格商品数量> cart;
 * 在取出购物车中数据时,返回给小程序一个List
 *
 * @author JJ
 * @date 2019/12/1 - 12:09
 */
@RequestMapping("/cart")
@Controller
public class ShoppingCartController {

    @Autowired
    private ProductService productService;
    @Autowired
    private SpecificationService specificationService;
    private static final Logger logger= LoggerFactory.getLogger(ShoppingCartController.class);


    /**
     * 用于购物车页面中,将购物车中每种规格商品(或者数量已修改的商品)的specificationId和数量上传
     * 将传进来的数量作为购物车中该商品的数量
     * cart[specificationId]=num
     *
     * @param cartCarrier
     */
    @RequestMapping("/modifyGoodsNum")
    public ResponseEntity modifyGoodsNumInCart(ShoppingCartCarrier cartCarrier, HttpServletRequest req) throws Exception {


        if (cartCarrier == null) {
            throw new SomeParamIsNullException("cartCarrier为空");
        }
        Map<Integer, Integer> specificationIdAndNum = cartCarrier.getCart();//接收传进来的specificationId和对应的数量
        if (specificationIdAndNum == null) {
            throw new SomeParamIsNullException("cartCarrier中取不到Map");
        }

//        HttpSession session = req.getSession(false);
//
//        if (session == null) {
//            throw new UnAuthorizedException();
//        }
//
//        User user = (User) session.getAttribute("user");
//        if (user == null) {
//            throw new UnAuthorizedException("session中获取不到user信息");
//        }
        User user = (User) SecurityUtils.getCurrentUser();

        //获取登录的用户的id
        Integer userId = user.getId();
        //从redis中获取购物车信息
        Jedis jedis = RedisUtil.getJedis();
        byte[] car = jedis.get(SerializableUtils.toBytes(userId));
        //反序列化重新获取cart对象
        Cart cart = (Cart) SerializableUtils.toObject(car);
        Map<Integer, CartItem> cartMap = cart.getCartMap();

        Iterator<Integer> iterator = specificationIdAndNum.keySet().iterator();
        while (iterator.hasNext()) {//遍历要修改的商品
            Integer specificationId = iterator.next();
            if (cartMap.containsKey(specificationId)) {//如果购物车中有此商品
                CartItem cartItem = cartMap.get(specificationId);//将购物车中此商品先取出
                cartItem.setNum(specificationIdAndNum.get(specificationId));//把该商品的数量修改为传进来的数量
                cartItem.setTotalPrice(specificationIdAndNum.get(specificationId) * cartItem.getUnitPrice());//计算得到并修改购物车中该商品的总价
                cartMap.put(specificationId, cartItem);//其实这步好像可以省略,因为从map中取出的是相关映射
            }
        }
        cart.setCartMap(cartMap);
        //更新购物车中所有商品的总价钱
        double allPrice = this.getAllPrice(cartMap);
        cart.setAllPrice(allPrice);
        //重新存入redis
        byte[] cartByte = SerializableUtils.toBytes(cart);
        byte[] userIdBs = SerializableUtils.toBytes(userId);
        //注意用set(byte[],byte[])
        jedis.set(userIdBs, cartByte);
        jedis.close();
        return new ResponseEntity("修改成功",HttpStatus.OK);
//        //尝试从session中取出购物车
//        ShoppingCartCarrier cartCarrierInSession = (ShoppingCartCarrier) session.getAttribute("cart");
//        if (cartCarrierInSession == null) {//如果session中没有购物车则创建一个
//            session.setAttribute("cart", cartCarrier);
//        } else {
//            //如果session中已有购物车
//            Map<Integer, Integer> cartInSession = cartCarrierInSession.getCart();//取出session购物车
//            //判断传进来的某些规格的商品在不在购物车中,如果不在则添加,如果已存在则增加购物车中该种规格商品的数量
//
//            //获取传进来的购物车
//            Map<Integer, Integer> cart = cartCarrier.getCart();
//            Set<Integer> keySet = cart.keySet();
//            Iterator<Integer> iterator = keySet.iterator();
//            if(iterator.hasNext()){//遍历cartCarrier
//                Integer cartKey = iterator.next();
//                boolean isContainsKey = cartInSession.containsKey(cartKey);//判断session购物车中是否有此种规格的商品
//                if(isContainsKey){//如果购物车中已有该规格的商品
//                    Integer num = cart.get(cartKey);//获取传进来的该规格商品的数量
//                    cartInSession.put(cartKey,num);//将session购物车中该规格商品的数量修改
//                }else{//如果购物车中没有该规格的商品
//
//                }
//            }
//        }/
//        更新购物车
//        session.setAttribute("cart",cartCarrier.getCart());
    }

    /**
     * 向小程序返回购物车中的数据
     */
    @RequestMapping("/get")
    @ResponseBody
    public Map getCart(HttpServletRequest req) {
        Map map = new HashMap();

//        HttpSession session = req.getSession(false);
//        if (session == null) {
//            throw new UnAuthorizedException();
//        }
//        //获取登录的用户id
//        User user = (User) session.getAttribute("user");
        User user = (User) SecurityUtils.getCurrentUser();
        Integer userId = user.getId();
        //从redis中获取购物车信息
        Jedis jedis = RedisUtil.getJedis();
        byte[] car = jedis.get(SerializableUtils.toBytes(userId));
        //反序列化重新获取cart对象
        if (car != null) {
            Cart cart = (Cart) SerializableUtils.toObject(car);
            map.put("cart", cart);
        } else {
            map.put("cart", null);
        }

        jedis.close();//释放资源

        return map;
//
//        Map<Integer,Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
//
//        if(cart==null){//如果购物车不存在或购物车为空,则返回null
//            return null;
//        }
////
//        //将cart传给service以得到购物车中商品的信息
//        //此处每次要查看购物车时都要从数据库中查找,感觉不太妥,
//        // 应该将查出的数据放在缓存中,每次查购物车时就从缓存中取,
//        //增删改数据时再操作数据库
//        List<CartItem> goodsInCart = specificationService.getGoodsInCart(cart);
//
//
//        return goodsInCart
    }

    /**
     * 当用户在商品详情页面点击"添加到购物车"时调用此接口
     *
     * @param specificationId
     * @param num             在商品详情页面点击"添加到购物车"时,所选择的添加数量
     * @param req
     */
    @RequestMapping("/addGoods")
    public ResponseEntity<String> addGoodsToCart(Integer specificationId, Integer num, HttpServletRequest req) {
        if (specificationId == null || num == null) {
            throw new SomeParamIsNullException("specificationId或num为空");
        }
        if (num == null || num <= 0) {
            return new ResponseEntity("添加的数量有问题",HttpStatus.NOT_ACCEPTABLE);
        }

//        HttpSession session = req.getSession(false);
//
//        //判断是否登录或登录态是否过期
//        if (session == null) {
//            throw new UnAuthorizedException("请先登录");
//        }


        //判断要添加的该种规格商品在数据库中存不存在
        Specification specification = specificationService.getSpecificationById(specificationId);
        if (specification == null) {//如果数据库中不存在specificationId指定的商品,则抛出异常
            logger.warn("userid为%d的用户尝试添加specificationId为%d的规格商品进入购物车,而该规格商品不存在");
            throw new NotAcceptableException(">>>规格为[" + specification + "]的商品不存在");
        }

        Map specificationIdAndNum = new HashMap<Integer, Integer>();
        specificationIdAndNum.put(specificationId, num);
        List<CartItem> cartItems = specificationService.getCartItems(specificationIdAndNum);//将要放入购物车中的商品的信息先查出来
        CartItem cartItem = cartItems.get(0);

        //从session中获取登陆者信息,存入redis中的key是serId,值是cart对象
//        User user = (User) session.getAttribute("user");
//        if (user == null) {
//            throw new UnAuthorizedException("session中获取不到user信息");
//        }
        User user = (User) SecurityUtils.getCurrentUser();


        Integer userId = user.getId();
        //从redis中获取购物车信息
        Jedis jedis = RedisUtil.getJedis();
        byte[] userIdBs = SerializableUtils.toBytes(userId);
        byte[] sCart = jedis.get(userIdBs);
        Cart cart = null;
        //判断购物车是否存在
        if (sCart == null) {
            //购物车不存在,先创建购物车
            cart = new Cart();

            //Integer specificationId,CartItem 购物项
            Map<Integer, CartItem> cartMap = new HashMap();
            cartMap.put(specificationId, cartItem);

            cart.setCartMap(cartMap);
            cart.setAllPrice(this.getAllPrice(cartMap));
        } else {
            //购物车已经存在,反序列化获取这个购物车
            cart = (Cart) SerializableUtils.toObject(sCart);
            //判断 当前的商品之前是否买过  购物车的数据在Map集合中 判断 map的key是否存在
            if (cart.getCartMap().containsKey(specificationId)) {//之前已经买过
                //使用key获取已经存在的购物项  直接修改购物项的数量和totalPrice即可
                CartItem oldCartItem = cart.getCartMap().get(specificationId);
                //得到该规格商品的之前数量
                Integer oldNum = oldCartItem.getNum();
                Integer newNum = oldNum + num;//添加该规格商品后的数量
                oldCartItem.setNum(newNum);
                oldCartItem.setTotalPrice(newNum * cartItem.getUnitPrice());
                //将修改后的购物项存入购物车的Map中
                cart.getCartMap().put(specificationId, oldCartItem);
            } else {
                //之前没买过
                //添加全新的购物项
                cart.getCartMap().put(specificationId, cartItem);
            }
            //计算总价钱 并set进购物车
            double allPrice = this.getAllPrice(cart.getCartMap());
            cart.setAllPrice(allPrice);
        }

        //将购物车序列化之后存入redis ,存入redis中的key是序列化后的userId, 值是序列化后的cart
        byte[] cartBytes = SerializableUtils.toBytes(cart);
        //这个地方要注意不要用set(String,String) 要用set(byte[] ,byte[])
        jedis.set(userIdBs, cartBytes);
        jedis.close();//释放资源

        return new ResponseEntity("添加成功",HttpStatus.OK);
//        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
//
//        if(cart==null){//购物车不存在,则创建购物车,把此规格的商品添加到购物车中
//            HashMap<Integer, Integer> newCart = new HashMap<Integer, Integer>();
//            newCart.put(specificationId,num);
//            session.setAttribute("cart",newCart);
//            return;
//        }else{//如果购物车不是空的
//            boolean isContainsKey = cart.containsKey(specificationId);
//            if(isContainsKey){//如果购物车中已有此规格商品
//                Integer numBefor = cart.get(specificationId);//得到此规格商品的数量
//                cart.put(specificationId,numBefor+num);//加上新增的此规格商品的数量
//            }else{//如果购物车中没有此规格的商品
//                cart.put(specificationId,num);//则直接把此规格的商品添加到购物车中
//            }
//        }
    }

    /**
     * @param specificationIds 一个数组或list,里面有购物车中要删除的某种规格商品的id
     * @return
     */
    @RequestMapping("/deleteCartItemsBySpecificationId")
    @ResponseBody
    public void deleteCartItemsBySpecification(Integer[] specificationIds) throws Exception {

        if (specificationIds == null) {
            throw new SomeParamIsNullException("specificationId不能为空");
        }

//        HttpSession session = req.getSession(false);
//
//        if (session == null) {
//            throw new UnAuthorizedException();
//        }
//
//        User user = (User) session.getAttribute("user");
//        if (user == null) {
//            throw new UnAuthorizedException("session中获取不到user信息");
//        }
        User user = (User) SecurityUtils.getCurrentUser();

        //获取登录的用户的id
        Integer userId = user.getId();
        //从redis中获取购物车信息
        Jedis jedis = RedisUtil.getJedis();

        byte[] car = jedis.get(SerializableUtils.toBytes(userId));
        //反序列化重新获取cart对象
        Cart cart = (Cart) SerializableUtils.toObject(car);
        //获取cartMap
        Map<Integer, CartItem> cartMap = cart.getCartMap();
        //遍历要被删除的商品规格id
        for (Integer specificationId : specificationIds) {
            if (cartMap.containsKey(specificationId)) {//如果购物车中有该商品则删除
                cartMap.remove(specificationId);
            }
        }
        //更新购物车
        cart.setCartMap(cartMap);
        cart.setAllPrice(this.getAllPrice(cartMap));
        //重新存入redis
        byte[] cartByte = SerializableUtils.toBytes(cart);
        byte[] userIdBs = SerializableUtils.toBytes(userId);
        //注意用set(byte[],byte[])
        jedis.set(userIdBs, cartByte);
        jedis.close();//释放资源

//
//        //得到session购物车
//        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
//
//        if(cart==null){
//            throw new CartIsNotExistException();
//        }
//
//        for(int i=0;i<specificationId.length;i++){
//            if(cart.containsKey(specificationId[i])){//如果session购物车中含有要被删除的商品,则将该商品从购物车中移除
//                Integer remove = cart.remove(specificationId);
//            }
//        }
//
//        session.setAttribute("cart",cart);
    }

    @RequestMapping("/emptyCart")
    public void emptyCart(HttpServletRequest req) {
//        HttpSession session = req.getSession(false);
//
//        if (session == null) {
//            throw new UnAuthorizedException();
//        }
//
//        User user = (User) session.getAttribute("user");
//        if (user == null) {
//            throw new UnAuthorizedException("session中获取不到user信息");
//        }
        User user = (User) SecurityUtils.getCurrentUser();

        //获取登录的用户的id
        Integer userId = user.getId();
        //从redis中获取购物车信息
        Jedis jedis = RedisUtil.getJedis();
        //清空购物车  直接删除redis中对应的key
        jedis.del(SerializableUtils.toBytes(userId));

    }

    /**
     * 此方法用于得到购物车中所有商品的全部价钱
     *
     * @return
     */
    private double getAllPrice(Map<Integer, CartItem> cartMap) {
        Iterator<Integer> iterator = cartMap.keySet().iterator();
        double allPrice = 0;
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            CartItem cartItem = cartMap.get(key);
            Double totalPrice = cartItem.getTotalPrice();
            allPrice += totalPrice;
        }
        return allPrice;
    }
}
