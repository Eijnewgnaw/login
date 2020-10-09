package main.java.Web.Servlets;

import com.sun.net.httpserver.Authenticator;
import main.java.Dao.Cartfunc;
import main.java.Dao.ProductService;
import main.java.domain.Cart;
import main.java.domain.Products;
import net.sf.json.JSONObject;

import javax.naming.spi.DirStateFactory;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.IOException;
import java.util.Collection;

public class CartServlet extends HttpServlet {

    //查
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cartfunc cartf = getCart(req,resp);
        Collection<Cart> carts = cartf.getCartMap();
        resp.getWriter().print(JSONObject.fromObject(carts));       //用json格式返回购物车数据
    }

    private Cartfunc getCart(HttpServletRequest request, HttpServletResponse response){
        Cartfunc cartf = (Cartfunc) request.getSession().getAttribute("cartf");
        if (cartf==null){
            cartf = new Cartfunc();
            request.getSession().setAttribute("cartf",cartf);
        }
        return cartf;
    }

    //增

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int count =Integer.parseInt(req.getParameter("count"));
        Products product = ProductService.FindByid(id);      //找到对应商品
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setCount(count);
        Cartfunc cartf = getCart(req, resp);        //获取购物车
        cartf.addCart(cart);                        //将商品加入购物车
        resp.getWriter().write("添加成功");
    }

    //删

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Cartfunc cartf = getCart(req, resp);
        cartf.remove(id);
        resp.getWriter().print(JSONObject.fromObject(cartf));
    }
}
