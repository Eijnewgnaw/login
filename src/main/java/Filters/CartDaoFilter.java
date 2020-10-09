package main.java.Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/CartDaoServlet"})
public class CartDaoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        req.setCharacterEncoding("utf-8");      //设置编码
        String username = req.getParameter("username");
        String password = req.getParameter("password");     //获取请求参数
        if("2712350454@qq.com".equals(username)&&"03160420Victor".equals(password)){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            resp.getWriter().write("非管理员无权限操作");
            req.getRequestDispatcher("/LoginServlet").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
