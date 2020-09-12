package Dao;
/*
操作数据库中User表
 */

import Utils.JDBCUtils;
import domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 登录方法
     * @param loginUser 只有用户民和密码
     * @return user包含用户全部数据
     */
    public User login(User loginUser){
        String sql = "select * from user where username = ? and password = ?";
        try {
            User user = template.queryForObject(sql,
                        new BeanPropertyRowMapper<User>(User.class),
                        loginUser.getUsername(),loginUser.getPassword());       //使用BeanPropertyRowMapper将数据库查询结果转换返回User对象
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
