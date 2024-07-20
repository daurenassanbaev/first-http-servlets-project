<%--
  Created by IntelliJ IDEA.
  User: daurenassanbaev
  Date: 17.07.2024
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create Schedule</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/adminCoach/adminCreateSchedule" method="post">
    <label for="name">Name:
        <input type="text" name="name" id="name" required>
    </label>
    <br>
    <label for="trainerId">Choose trainer:</label>
    <select id="trainerId" name="trainer_id">
        <c:forEach var="trainer" items="${requestScope.trainers}">
            <option value="${trainer.id}">${trainer.name} : ${trainer.bio}</option>
        </c:forEach>
    </select>

    <br>
    <label for="date">Choose date:</label>
    <input type="date" id="date" name="date" required>
    <br>
    <label for="start_time">Choose start time:</label>
    <input type="time" id="start_time" name="start_time" required>
    <br>
    <label for="end_time">Choose end time:</label>
    <input type="time" id="end_time" name="end_time" required>
    <br>
    <label for="location">Location:
        <input type="text" name="location" id="location" required>
    </label>
    <br>
    <button type="submit">
        Create a schedule
    </button>
    <c:if test="${not empty requestScope.error}">
        <div style="color: red">
            <span>${requestScope.error}</span>
        </div>
    </c:if>
</form>

</body>
</html>
