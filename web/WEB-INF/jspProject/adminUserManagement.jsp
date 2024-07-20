<%--
  Created by IntelliJ IDEA.
  User: daurenassanbaev
  Date: 17.07.2024
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp"%>
    <title>User management</title>
</head>
<body>
<table border="1">
    <tr>
        <td>Id</td>
        <td>Username</td>
        <td>Password</td>
        <td>Email</td>
        <td>Role</td>
        <td>Action</td>
    </tr>
    <c:forEach var="user" items="${requestScope.users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.email}</td>
            <td>${user.role}</td>
            <td>
                <form action="${pageContext.request.contextPath}/adminUserManagement/adminDeleteUser" method="post">
                    <input type="hidden" name="userId" value="${user.id}">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
