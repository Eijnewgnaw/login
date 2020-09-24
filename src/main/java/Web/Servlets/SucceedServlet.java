package main.java.Web.Servlets;


import main.java.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/SucceedServlet")
public class SucceedServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getAttribute("user");
        if (user != null) {
            resp.setContentType("text/html;charset=utf-8");     //设置编码
            resp.getWriter().write("Login Succeed!" + user.getUsername() + ",Welcome!!!");
        }
        Cookie[] cookies=req.getCookies();
        boolean flag = false;
        if (cookies!=null&&cookies.length>0){
            for(Cookie cookie:cookies){
                String name = cookie.getName();
                if ("lastTime".equals(name)){
                    flag=true;
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    String str_date = sdf.format(date);
                    URLEncoder.encode(str_date,"utf-8");
                    cookie.setValue(str_date);
                    cookie.setMaxAge(60*60*24*30);
                    resp.addCookie(cookie);
                    String value = cookie.getValue();
                    value=URLDecoder.decode(value,"utf-8");
                    resp.getWriter().write("<h1>欢迎回来，您上次的访问时间是:  "+value+"</h1>");
                    break;
                }
            }
        }
        if (cookies==null||cookies.length==0||flag==false){
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String str_date = sdf.format(date);
            str_date=URLEncoder.encode(str_date,"utf-8");
            Cookie cookie = new Cookie("lastTime",str_date);
            cookie.setMaxAge(60*60*24*30);
            resp.addCookie(cookie);
            resp.getWriter().write("<h1>您好，欢迎首次访问</h1>");
        }
    }
}