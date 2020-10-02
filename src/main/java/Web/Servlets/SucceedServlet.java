package main.java.Web.Servlets;


import main.java.domain.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/SucceedServlet")
public class SucceedServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");     //设置编码
        HttpSession session =req.getSession();
        String SessionIdStr = session.getId();
        Cookie SessionIDCok = new Cookie("SessionID",SessionIdStr);
        SessionIDCok.setMaxAge(60*60*24*30);                                   //存储登录状态
        resp.addCookie(SessionIDCok);
        req.getServletContext().setAttribute("SessionID_Value",SessionIdStr);

        Cookie[] cookies=req.getCookies();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String str_date = sdf.format(date);                                     //刷新当前时刻
        str_date = URLEncoder.encode(str_date,"utf-8");
        if (cookies!=null&&cookies.length>0){
            for(Cookie cookie:cookies){
                String name = cookie.getName();
                if ("lastTime".equals(name)){
                    String value = cookie.getValue();
                    value=URLDecoder.decode(value,"utf-8");
                    resp.getWriter().write("<h1>欢迎回来，您上次的访问时间是:  "+value+"</h1>");
                    cookie.setValue(str_date);
                    cookie.setMaxAge(60*60*24*30);
                    resp.addCookie(cookie);

                    break;
                }
            }
        }
        if (cookies==null||cookies.length==0){
            resp.getWriter().write("<h1>您好，欢迎首次访问</h1>");
            Cookie cookie = new Cookie("lastTime",str_date);
            cookie.setMaxAge(60*60*24*30);
            resp.addCookie(cookie);

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}