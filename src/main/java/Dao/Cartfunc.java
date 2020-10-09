package main.java.Dao;

import main.java.Utils.JDBCUtils;
import main.java.domain.Cart;
import main.java.domain.Products;
import main.java.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cartfunc {
    private double final_total;     //总计
    private Map<String, Cart> cartMap = new HashMap<String, Cart>();

    public double getFinal_total() {
        return final_total;
    }

    public void setFinal_total(double final_total) {
        this.final_total = final_total;
    }

    public Collection<Cart> getCartMap() {
        return cartMap.values();            //返回购物项的集合
    }

    public void setCartMap(Map<String, Cart> cartMap) {
        this.cartMap = cartMap;
    }

    public void addCart(Cart cart){
        String id =cart.getProduct().getId();
        if (cartMap.containsKey(id)){
            Cart oldcart = cartMap.get(id);
            oldcart.setCount(oldcart.getCount()+ cart.getCount());
        }else {
            cartMap.put(id,cart);
        }
        final_total += cart.getTotal();
    }

    public void remove(String id){
        Cart cart = cartMap.remove(id);
        final_total -= cart.getTotal();
    }
}

