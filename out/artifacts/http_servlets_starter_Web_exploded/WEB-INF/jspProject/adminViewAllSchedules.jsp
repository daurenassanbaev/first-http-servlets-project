<%--
  Created by IntelliJ IDEA.
  User: daurenassanbaev
  Date: 17.07.2024
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <form action="${pageContext.request.contextPath}/adminCoach" method="get">
        <button type="submit">Coach schedule management</button>
    </form>
    <title>All schedules</title>
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
                <form action="${pageContext.request.contextPath}/adminCoach/adminViewAllSchedules/adminDeleteSchedule" method="post">
                    <input type="hidden" name="scheduleId" value="${schedule.id}">
                    <input type="submit" value="Delete">
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/adminCoach/adminViewAllSchedules/adminEditSchedule" method="get">
                    <input type="hidden" name="scheduleId" value="${schedule.id}">
                    <input type="submit" value="Edit">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
