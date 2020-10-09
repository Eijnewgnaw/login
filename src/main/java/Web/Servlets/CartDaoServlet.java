package main.java.Web.Servlets;

import main.java.Utils.JDBCUtils;
import net.sf.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/CartDaoServlet")
public class CartDaoServlet extends HttpServlet {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    String quantity_change ="update Products set quantity = ? where id =?";     //库存增减
    String price_change = "update Products set price = ? where id =?";     //价格增减
    String show = "Select * from Products";       //管理员查库存
    String alter_a = "insert into Products(name,price,quantity)values(?,?,?)";
    String alter_d = "delete from Products where id =?";        //管理员改动商品

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map map = template.queryForMap(show);
        resp.getWriter().print(JSONObject.fromObject(map));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int price = Integer.parseInt(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        template.update(alter_a,name,price,quantity);
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        template.update(alter_d,id);
        doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String choice = req.getParameter("choice");
        int id = Integer.parseInt(req.getParameter("id"));
        if("A".equals(choice)){
            String quantity = req.getParameter("quantity");
            template.update(quantity_change,quantity,id);
        }else {
            String price = req.getParameter("price");
            template.update(price_change,price,id);
        }
        doGet(req, resp);
    }
}
