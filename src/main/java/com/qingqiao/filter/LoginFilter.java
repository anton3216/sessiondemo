package com.qingqiao.filter;

import com.qingqiao.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        // 当前访问的地址
        String requestURI = request.getRequestURI();
        // 接收参数
        String m = request.getParameter("m");
        User loginInfo = (User) session.getAttribute("login_info");
        if (loginInfo != null || requestURI.contains("login.jsp") || "login".equals(m)) {
            filterChain.doFilter(request, response);
        }else{
            session.setAttribute("login_error", "请先登录");
            // /sessionDemo
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/login.jsp");
        }

    }


}
