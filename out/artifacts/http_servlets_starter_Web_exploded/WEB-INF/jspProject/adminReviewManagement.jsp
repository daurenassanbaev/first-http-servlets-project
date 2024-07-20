<%--
  Created by IntelliJ IDEA.
  User: daurenassanbaev
  Date: 17.07.2024
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp"%>
    <title>Admin review management</title>
</head>
<body>
<h1>Reviews and ratings</h1>
<table border="1">
    <tr>
        <td>Id</td>
        <td>Username</td>
        <td>Trainer Name</td>
        <td>Rating</td>
        <td>Comment</td>
    </tr>
    <c:forEach var="review" items="${requestScope.reviews}">
        <tr>
            <td>${review.username}</td>
            <td>${review.reviewId}</td>
            <td>${review.trainerName}</td>
            <td>${review.rating}</td>
            <td>${review.comment}</td>
            <td>
                <form action="${pageContext.request.contextPath}/adminDeleteReview" method="post">
                    <input type="hidden" name="reviewId" value="${review.reviewId}">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
