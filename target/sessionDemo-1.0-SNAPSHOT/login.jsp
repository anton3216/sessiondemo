<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%
        String username = "";
        String password = "";
        // jsp 脚本 可以编写java代码
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie c : cookies){
                if("username".equals(c.getName())){
                    username = URLDecoder.decode(c.getValue(), StandardCharsets.UTF_8);
                }
                if("password".equals(c.getName())){
                    password = URLDecoder.decode(c.getValue(), StandardCharsets.UTF_8);
                }
            }
            request.setAttribute("username",username);
            request.setAttribute("password",password);
        }
    %>
</head>
<body>
${sessionScope.login_error}
<form action="user?m=login" method="post">
    <table>
        <tr>
            <td>用户名:</td>
            <td>
                <input type="text" name="username" placeholder="请输入用户名" value="${requestScope.username}">
            </td>
        </tr>
        <tr>
            <td>密码:</td>
            <td>
                <input type="password" name="password" placeholder="请输入密码" value="${requestScope.password}">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit">
                <input type="checkbox" value="yes" name="remember">记住密码?
            </td>
        </tr>
    </table>
</form>
</body>
</html>
