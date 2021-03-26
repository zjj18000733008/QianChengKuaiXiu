package wechatOrder.po;

import java.util.Map;

/**
 * @author JJ
 * @date 2019/12/3 - 21:24
 */
public class ShoppingCartCarrier {

    private Map<Integer,Integer> cart;//Integer: specificationId,Integer: num

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cart=" + cart +
                '}';
    }

    public Map<Integer, Integer> getCart() {
        return cart;
    }

    public void setCart(Map<Integer, Integer> cart) {
        this.cart = cart;
    }
}
