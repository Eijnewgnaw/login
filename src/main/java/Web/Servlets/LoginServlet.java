package main.java.Web.Servlets;


import main.java.Dao.UserDao;
import main.java.domain.User;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");      //设置编码

        String username = req.getParameter("username");
        String password = req.getParameter("password");     //获取请求参数
        String checkCode = req.getParameter("checkCode");

        HttpSession session = req.getSession();
        String checkCode_session=(String)session.getAttribute("code_session");

        if (checkCode_session.equalsIgnoreCase(checkCode)) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);         //获取User封装对象

            UserDao dao = new UserDao();
            User user1 = dao.login(user);       //调用UserDao的login方法

            if(user1==null){                                                            //判断并执行跳转
                req.getRequestDispatcher("/failedServlet").forward(req,resp);
            }else{
                req.setAttribute("user",user1);                                     //存储共享数据
                req.getRequestDispatcher("/SucceedServlet").forward(req,resp);      //跳转
            }
        }else{
            req.setAttribute("check_code_error","验证码错误");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
