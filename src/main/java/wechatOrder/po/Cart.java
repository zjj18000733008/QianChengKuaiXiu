package wechatOrder.po;

import java.io.Serializable;
import java.util.Map;

/**
 * @author JJ
 * @date 2019/12/4 - 20:12
 */
public class Cart implements Serializable {
    private Map<Integer,CartItem> cartMap;
    private Double AllPrice;//购物车中所有商品的总价

    public Map<Integer, CartItem> getCartMap() {
        return cartMap;
    }

    public void setCartMap(Map<Integer, CartItem> cartMap) {
        this.cartMap = cartMap;
    }

    public Double getAllPrice() {
        return AllPrice;
    }

    public void setAllPrice(Double allPrice) {
        AllPrice = allPrice;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartMap=" + cartMap +
                ", AllPrice=" + AllPrice +
                '}';
    }
}
