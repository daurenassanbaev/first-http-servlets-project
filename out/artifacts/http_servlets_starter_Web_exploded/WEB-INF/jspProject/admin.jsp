<%--
  Created by IntelliJ IDEA.
  User: daurenassanbaev
  Date: 16.07.2024
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp"%>
    <title>Admin</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/adminCoach">
        <button type="button">Coach schedule management</button>
    </a>
    <br>
    <a href="${pageContext.request.contextPath}/adminUserManagement">
        <button type="button">User Management</button>
    </a>
    <br>
    <a href="${pageContext.request.contextPath}/adminBookingManagement">
        <button type="button">Booking Management</button>
    </a>
    <br>

    <a href="${pageContext.request.contextPath}/adminReviewManagement">
        <button type="button">Review and ratings management</button>
    </a>


</body>
</html>