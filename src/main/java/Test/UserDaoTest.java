package main.java.Test;

import main.java.Dao.UserDao;
import main.java.domain.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void testLogin(){
        User loginuser = new User();
        loginuser.setUsername("Victor");
        loginuser.setPassword("03160420Victor");
        UserDao dao = new UserDao();
        User user1 = dao.login(loginuser);
        System.out.println(user1);
    }
}
