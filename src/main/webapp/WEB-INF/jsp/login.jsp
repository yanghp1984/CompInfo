<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>小春论坛登录</title>
    <base href="<%=basePath%>">
</head>
<body>
<a href="index.form">INDEX.FORM</a><br>
<c:if test="${!empty error}">
    <font color="red"><c:out value="${error}"/></font>
</c:if>
<form action="loginCheck.form" method="post">
    <table>
        <tr>
            <td><label>用户名：</label></td>
            <td><input type="text" name="userName"></td>
        </tr>
        <tr>
            <td><label>密&nbsp码：</label></td>
            <td><input type="text" name="password"></td>
        </tr>
        <tr>
            <td><input type="submit" value="登录"></td>
            <td><input type="reset" value="重置"></td>
        </tr>
    </table>
</form>
</body>
</html>
