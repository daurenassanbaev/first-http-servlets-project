
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Trainer</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/adminCoach/adminViewAll/adminEditTrainer" method="post" enctype="multipart/form-data">
    <label for="name">Name:
        <input type="text" name="name" id="name" value="${requestScope.trainer.name}">
    </label>
    <br>
    <label for="bio">Bio:
        <textarea name="bio" id="bio" rows="10" cols="20" required>${requestScope.trainer.bio}</textarea>
    </label>

    <br>
    <label for="contact_phone">Contact Phone:
        <input type="text" name="contact_phone" id="contact_phone" value="${requestScope.trainer.contactPhone}" required>
    </label>
    <br>
    <label for="contact_email">Contact Email:
        <input type="text" name="contact_email" id="contact_email" value="${requestScope.trainer.contactEmail}" required>
    </label>
    <br>
    <label for="photo">Photo:
        <input type="file" name="photo" id="photo" required>
        </label>
    <br>
    <span>Previous photo</span>
    <img src="${pageContext.request.contextPath}/images/${requestScope.trainer.photo}" alt="null" style="max-width: 100px; max-height: 100px;">
    <br>
    <button type="submit">
        Update a trainer profile
    </button>
    <c:if test="${not empty requestScope.error}">
        <div style="color: #d00606">
            <span>${requestScope.error}</span>
        </div>
    </c:if>
</form>

</body>
</html>
