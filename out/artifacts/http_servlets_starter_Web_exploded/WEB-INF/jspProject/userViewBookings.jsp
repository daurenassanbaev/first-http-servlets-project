<%--
  Created by IntelliJ IDEA.
  User: daurenassanbaev
  Date: 17.07.2024
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp"%>
    <title>View bookings</title>
</head>
<body>
<table border="1">
    <tr>
        <td>Id</td>
        <td>Trainer Name</td>
        <td>Program Name</td>
        <td>Date</td>
        <td>Start time</td>
        <td>End time</td>
        <td>Location</td>
    </tr>
    <c:forEach var="schedule" items="${requestScope.schedules}">
        <tr>
            <td>${schedule.id}</td>
            <td>${schedule.trainerName}</td>
            <td>${schedule.programName}</td>
            <td>${schedule.date}</td>
            <td>${schedule.startTime}</td>
            <td>${schedule.endTime}</td>
            <td>${schedule.location}</td>
            <td>
                <form action="${pageContext.request.contextPath}/userViewBookings/userCancelBooking" method="post">
                    <input type="hidden" name="bookingId" value="${schedule.bookingId}">
                    <input type="submit" value="Cancel">
                </form>
            </td>

        </tr>
    </c:forEach>
</table>

</body>
</html>
