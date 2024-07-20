<%--
  Created by IntelliJ IDEA.
  User: daurenassanbaev
  Date: 17.07.2024
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp"%>
    <title>Booking training</title>
</head>
<body>
<table border="1">
    <tr>
        <td>Id</td>
        <td>Trainer Id</td>
        <td>Name</td>
        <td>Date</td>
        <td>Start time</td>
        <td>End time</td>
        <td>Location</td>
        <td>Action</td>
    </tr>
    <c:forEach var="schedule" items="${requestScope.schedules}">
        <tr>
            <td>${schedule.id}</td>
            <td>${schedule.trainerId}</td>
            <td>${schedule.name}</td>
            <td>${schedule.date}</td>
            <td>${schedule.startTime}</td>
            <td>${schedule.endTime}</td>
            <td>${schedule.location}</td>
            <td>
                <form action="${pageContext.request.contextPath}/userBookingTraining/userBookTrainer" method="post">
                    <input type="hidden" name="scheduleId" value="${schedule.id}">
                    <input type="submit" value="Book">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
