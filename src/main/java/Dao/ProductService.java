package main.java.Dao;

import main.java.Utils.JDBCUtils;
import main.java.domain.Products;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProductService {
    public static Products FindByid(String id){
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select * from product where id=?";
        try {
            Products product =template.queryForObject(sql,
                    new BeanPropertyRowMapper<Products>(Products.class),id);
            return product;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
