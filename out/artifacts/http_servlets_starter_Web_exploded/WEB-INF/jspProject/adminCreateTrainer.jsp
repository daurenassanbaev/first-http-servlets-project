<%--
  Created by IntelliJ IDEA.
  User: daurenassanbaev
  Date: 17.07.2024
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Trainer</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/adminCoach/adminCreateTrainer" method="post" enctype="multipart/form-data">
        <label for="name">Name:
            <input type="text" name="name" id="name" required>
        </label>
        <br>
        <label for="bio">Bio:</label>
        <textarea name="bio" id="bio" rows="10" cols="20" required></textarea>

        <br>
        <label for="contact_phone">Contact Phone:
            <input type="text" name="contact_phone" id="contact_phone" required>
        </label>
        <br>
        <label for="contact_email">Contact Email:
            <input type="text" name="contact_email" id="contact_email" required>
        </label>
        <br>
        <label for="photo">Photo:
            <input type="file" name="photo" id="photo" required>
        </label>
        <br>
        <button type="submit">
            Create a trainer profile
        </button>
        <c:if test="${not empty requestScope.error}">
            <div style="color: red">
                <span>${requestScope.error}</span>
            </div>
        </c:if>
    </form>

</body>
</html>
