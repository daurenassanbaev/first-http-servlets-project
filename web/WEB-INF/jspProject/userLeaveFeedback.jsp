<%--
  Created by IntelliJ IDEA.
  User: daurenassanbaev
  Date: 20.07.2024
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Leave Feedback</title>
</head>
<body>
<table border="1">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Bio</td>
        <td>Contact Phone</td>
        <td>Contact Email</td>
        <td>Photo</td>
    </tr>
    <tr>
        <td>${requestScope.trainer.id}</td>
        <td>${requestScope.trainer.name}</td>
        <td>${requestScope.trainer.bio}</td>
        <td>${requestScope.trainer.contactPhone}</td>
        <td>${requestScope.trainer.contactEmail}</td>
        <td>
            <img src="${pageContext.request.contextPath}/images/${requestScope.trainer.photo}" alt="null"
                 style="max-width: 100px; max-height: 100px;">
        </td>
    </tr>
</table>
<br>
<form action="${pageContext.request.contextPath}/userFeedbackSubmit" method="post">
    <label for="rating">Rating:
        <select name="rating" id="rating" required>
            <option value="">Select rating</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select>
    </label>
    <br>
    <label for="comment">Comment:
        <input type="text" name="comment" id="comment">
    </label>
    <input type="hidden" name="trainerId" value="${requestScope.trainer.id}">
    <button type="submit">
        Leave
    </button>
</body>
</html>
