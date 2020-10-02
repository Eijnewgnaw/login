package main.java.Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;

@WebFilter({"/SucceedServlet"})
public class loginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        boolean flag =false;
        String SessionID =(String) request.getServletContext().getAttribute("SessionID_Value");
        Cookie[] cookies=request.getCookies();
        if(cookies==null){
            request.getRequestDispatcher("/LoginServlet").forward(request,response);
        }else
        {
            for (Cookie c:cookies){
                if("SessionID".equals(c.getValue())){
                    flag = true;
                    request.getRequestDispatcher("/SucceedServlet").forward(request,response);
                    break;
                }
            }
            if(flag=false){
                request.getRequestDispatcher("/LoginServlet").forward(request,response);
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
