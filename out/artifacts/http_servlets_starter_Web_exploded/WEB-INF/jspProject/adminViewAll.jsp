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
    <title>All trainers</title>
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
            <td>Delete</td>
            <td>Edit</td>
        </tr>
        <c:forEach var="trainer" items="${requestScope.trainers}">
            <tr>
                <td>${trainer.id}</td>
                <td>${trainer.name}</td>
                <td>${trainer.bio}</td>
                <td>${trainer.contactPhone}</td>
                <td>${trainer.contactEmail}</td>
                <td>
                    <img src="${pageContext.request.contextPath}/images/${trainer.photo}" alt="null" style="max-width: 100px; max-height: 100px;">
                </td>
                <td>
                <form action="${pageContext.request.contextPath}/adminCoach/adminViewAll/adminDelete" method="post">
                    <input type="hidden" name="trainerId" value="${trainer.id}">
                    <input type="submit" value="Delete">
                </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/adminCoach/adminViewAll/adminEditTrainer" method="get">
                        <input type="hidden" name="trainerId" value="${trainer.id}">
                        <input type="submit" value="Edit">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
