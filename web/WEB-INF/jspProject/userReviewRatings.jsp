<%--
  Created by IntelliJ IDEA.
  User: daurenassanbaev
  Date: 17.07.2024
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="header.jsp"%>
    <title>Reviews and ratings</title>
</head>
<body>
<h1>My trainings</h1>
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
            <c:if test="${empty requestScope.check}">
                <td>
                    <form action="${pageContext.request.contextPath}/userReviewRatings/userLeaveFeedback" method="post">
                        <input type="hidden" name="trainerId" value="${schedule.trainerId}">
                        <input type="submit" value="Leave feedback">
                    </form>
                </td>
            </c:if>


        </tr>
    </c:forEach>
</table>

<br>
<h1>Reviews and ratings</h1>
<table border="1">
    <tr>
        <td>Id</td>
        <td>Trainer Name</td>
        <td>Rating</td>
        <td>Comment</td>
    </tr>
    <c:forEach var="review" items="${requestScope.reviews}">
        <tr>
            <td>${review.reviewId}</td>
            <td>${review.trainerName}</td>
            <td>${review.rating}</td>
            <td>${review.comment}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
