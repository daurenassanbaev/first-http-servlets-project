<%--
  Created by IntelliJ IDEA.
  User: daurenassanbaev
  Date: 16.07.2024
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>User Page</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/userView">
        <button type="button">View trainers' schedules</button>
    </a>
    <br>
    <a href="${pageContext.request.contextPath}/userBookingTraining">
        <button type="button">Booking training</button>
    </a>
    <br>
    <a href="${pageContext.request.contextPath}/userViewBookings">
        <button type="button">View bookings</button>
    </a>
    <br>
    <a href="${pageContext.request.contextPath}/userReviewRatings">
        <button type="button">Reviews and ratings</button>
    </a>

</body>
</html>
