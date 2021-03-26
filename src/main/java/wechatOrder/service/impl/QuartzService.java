package wechatOrder.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wechatOrder.dao.OrderMapper;
import wechatOrder.dao.OrderitemMapper;
import wechatOrder.dao.SpecificationMapper;
import wechatOrder.po.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author JJ
 * @date 2020/1/29 - 13:46
 */
@Service
public class QuartzService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderitemMapper orderitemMapper;
    @Autowired
    private SpecificationMapper specificationMapper;
    private static final Logger log= LoggerFactory.getLogger(QuartzService.class);

    public void deleteTimeoutOrder(){
        log.info("开始执行删除超时未支付订单...");
        Date currentDate = new Date(System.currentTimeMillis());//当前时间

        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andStateEqualTo("0");
        List<Order> orders = orderMapper.selectByExample(orderExample);
        for (Order order : orders) {
            String orderTime = order.getOrderTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = simpleDateFormat.parse(orderTime);//下单时间
                if((currentDate.getTime()-date.getTime())>15*60*1000){//未支付订单超过15分钟,则删除该订单并恢复库存
                    //查询出该订单的所有订单项
                    OrderitemExample orderitemExample = new OrderitemExample();
                    orderitemExample.createCriteria().andOrderIdEqualTo(order.getId());
                    List<Orderitem> orderitems = orderitemMapper.selectByExample(orderitemExample);

                    for (Orderitem orderitem : orderitems) {
                        Integer buynum = orderitem.getBuynum();
                        this.addInventory(orderitem.getSpecificationId(), buynum);//恢复商品库存数
                        //删除该订单项
                        orderitemMapper.deleteByExample(orderitemExample);
                    }
                    //删除该订单
                    orderMapper.deleteByPrimaryKey(order.getId());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 增加库存
     * @param specificationId
     * @param num
     * @return
     */
    private boolean addInventory(Integer specificationId,Integer num){
        Specification specification = specificationMapper.selectByPrimaryKey(specificationId);
        Integer inventory = specification.getInventory();
        specification.setInventory(inventory+num);
        int result = specificationMapper.updateByPrimaryKeySelective(specification);
        if(result==1){
            return true;
        }else{
            return false;
        }
    }

}
