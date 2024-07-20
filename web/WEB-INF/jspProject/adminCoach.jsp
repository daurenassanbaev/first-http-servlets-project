<%--
  Created by IntelliJ IDEA.
  User: daurenassanbaev
  Date: 17.07.2024
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp"%>
    <title>Coach schedule management</title>
</head>
<body>
    <h1><b>Trainer control panel</b></h1>
    <a href="${pageContext.request.contextPath}/adminCoach/adminCreateTrainer">
        <button type="button">Create a trainer profile</button>
    </a>
    <br>
    <a href="${pageContext.request.contextPath}/adminCoach/adminViewAll">
        <button type="button">View all trainers</button>
    </a>
    <br>
    <h1><b>Schedule control panel</b></h1>
    <a href="${pageContext.request.contextPath}/adminCoach/adminCreateSchedule">
        <button type="button">Create a schedule</button>
    </a>
    <br>
    <a href="${pageContext.request.contextPath}/adminCoach/adminViewAllSchedules">
        <button type="button">View all schedules</button>
    </a>
</body>
</html>
