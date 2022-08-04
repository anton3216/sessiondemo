package com.qingqiao.servlet;

import com.qingqiao.entity.User;
import com.qingqiao.service.UserService;
import com.qingqiao.service.impl.UserServiceImpl;
import com.qingqiao.utils.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String m = request.getParameter("m");

        if ("list".equals(m)) {
            queryAll(request, response);
        } else if ("login".equals(m)) {
            login(request, response);
        } else if ("logout".equals(m)) {
            request.getSession().invalidate();
            response.sendRedirect("login.jsp");
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        User user = userService.login(username, password);
        HttpSession session = request.getSession();
        if (user != null) {
            if ("yes".equals(remember)) {
                // cookie不支持汉字 所以进行URLEncoder编码
                String username_encode = URLEncoder.encode(username, StandardCharsets.UTF_8);
                String password_encode = URLEncoder.encode(password, StandardCharsets.UTF_8);
                // 创建cookie
                Cookie username_cookie = new Cookie("username", username_encode);
                Cookie password_cookie = new Cookie("password", password_encode);
                // 设置过期时间 七天
                username_cookie.setMaxAge(60 * 60 * 24 * 7);
                password_cookie.setMaxAge(60 * 60 * 24 * 7);
                // 将cookie响应到客户端
                response.addCookie(username_cookie);
                response.addCookie(password_cookie);
            }
            session.setAttribute("login_error", "");
            session.setAttribute("login_info", user);
            response.sendRedirect("user?m=list");
        } else {
            session.setAttribute("login_error", "用户名或密码错误");
            response.sendRedirect("login.jsp");
        }
    }

    private void queryAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mohu = request.getParameter("mohu");
        mohu = mohu == null ? "" : mohu;
        byte[] bytes = mohu.getBytes(StandardCharsets.ISO_8859_1);
        mohu = new String(bytes, StandardCharsets.UTF_8);

        String currentPage = request.getParameter("currentPage");
        int count = userService.getCount(mohu);

        PageUtil pageUtil = new PageUtil(5, currentPage, count);

        List<User> users = userService.queryAll(pageUtil,mohu);

        request.setAttribute("list", users);
        request.setAttribute("page", pageUtil);
        request.setAttribute("mohu", mohu);

        request.getRequestDispatcher("user/list.jsp").forward(request, response);
    }
}
