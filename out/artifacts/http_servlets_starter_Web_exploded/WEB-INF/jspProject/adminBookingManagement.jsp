<%--
  Created by IntelliJ IDEA.
  User: daurenassanbaev
  Date: 17.07.2024
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="header.jsp"%>
    <title>Booking Management</title>
</head>
<body>
<table border="1">
    <tr>
        <td>Booking Id</td>
        <td>Username</td>
        <td>Email</td>
        <td>Trainer name</td>
        <td>Program Name</td>
        <td>Date</td>
        <td>Start Time</td>
        <td>End Time </td>
        <td>Location</td>
        <td>Action</td>
    </tr>
    <c:forEach var="booking" items="${requestScope.bookings}">
        <tr>
            <td>${booking.bookingId}</td>
            <td>${booking.username}</td>
            <td>${booking.email}</td>
            <td>${booking.trainerName}</td>
            <td>${booking.programName}</td>
            <td>${booking.date}</td>
            <td>${booking.startTime}</td>
            <td>${booking.endTime}</td>
            <td>${booking.location}</td>
            <td>
                <form action="${pageContext.request.contextPath}/userViewBookings/userCancelBooking" method="post">
                    <input type="hidden" name="bookingId" value="${booking.bookingId}">
                    <input type="submit" value="Cancel">
                </form>
            </td>

        </tr>
    </c:forEach>
</table>

</body>
</html>
