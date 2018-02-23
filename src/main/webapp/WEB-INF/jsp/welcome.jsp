<%--
  Created by IntelliJ IDEA.
  User: Hai
  Date: 2018/2/11
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>小春论坛</title>
</head>
<body>
<h2>${user.userName}, 欢迎您进入小春论坛</h2>
<table>
    <tr>
        <td>项目</td>
        <td>内容</td>
    </tr>
    <tr>
        <td>积分</td>
        <td>${user.credits}</td>
    </tr>
    <tr>
        <td>最近访问地址</td>
        <td>${user.lastVisitIp}</td>
    </tr>
    <tr>
        <td>最近访问时间</td>
        <td>${user.lastVisitTime}</td>
    </tr>
</table>
</body>
</html>
