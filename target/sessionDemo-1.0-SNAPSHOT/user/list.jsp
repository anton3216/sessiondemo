<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="<%= request.getContextPath()%>/js/jQuery-v3.6.0.js"></script>
    <script>
        $(function (){
            //
            let si = setInterval('getDate()',1000)
        // $("#stop").click(function (){
        //     clearInterval(si);
        // });
        });

        function getDate(){
            let d = new Date();
            let year = d.getFullYear();
            let month = d.getMonth() + 1;
            month = month < 10 ? '0' + month : month;
            let day = d.getDate();
            day = day < 10 ? '0' + day : day;

            let hours = d.getHours();
            hours = hours < 10 ? '0' + hours : hours;
            let min = d.getMinutes();
            min = min < 10 ? '0' + min : min;
            let sec = d.getSeconds();
            sec = sec < 10 ? '0' + sec : sec;

            let fmtDate = year + "年" + month + "月" + day + "日 " + hours + ":" + min + ":" + sec;
            $("#date").text(fmtDate);
        }
    </script>
</head>
<body>
<%--<input type="button" value="stop" id="stop">--%>
    欢迎${sessionScope.login_info.username}登录,当前时间是 : <span id="date"></span>
    <table>
        <tr>
            <th>
                <input type="checkbox" id="ck">
            </th>
            <th>编号</th>
            <th>用户名</th>
            <th>密码</th>
            <th>性别</th>
            <th>爱好</th>
            <th>
                操作 |
                <input type="button" value="添加" id="add">
            </th>
        </tr>
        <c:forEach items="${list}" var="u">
            <tr>
                <td>
                    <input type="checkbox" class="cks" value="${u.id}">
                </td>
                <td>${u.id}</td>
                <td>${u.username}</td>
                <td>${u.password}</td>
                <td>${u.sex}</td>
                <td>${u.hobby}</td>
                <td>
                    <input type="button" value="修改" class="update">
                    <input type="hidden"  value="${u.id}">
                    <input type="button" value="删除" class="delete">
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="10">
                <input type="button" value="全选" id="selectAll">
                <input type="button" value="全不选" id="selectNone">
                <input type="button" value="反选" id="fan">
                <input type="button" value="批量删除" id="plsc">
            </td>
        </tr>
        <tr>
            <td colspan="10">
                <input type="button" value="首頁">
                <input type="button" value="上一页">
                <input type="button" value="下一页">
                <input type="button" value="尾页">
            </td>
        </tr>
    </table>
</body>
</html>
