<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        function logout(){
            location = "user?m=logout";
        }
    </script>
</head>
<body>
    <!-- 按照作用域范围从小到大查找 -->
    <!-- page request session application -->
    当前页面访问人数 : ${count}
    <input type="button" onclick="logout()" value="注销">
</body>
</html>
