
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>

    <%@ include file="header.jsp"%>
    <div id="locale">
        <form action="${pageContext.request.contextPath}/locale" method="post">
            <button type="submit" name="lang" value="ru_RU">RU</button>
            <button type="submit" name="lang" value="en_US">EN</button>
        </form>
    </div>
    <fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : (param.lang != null ? param.lang : 'en_US')}"/>
    <fmt:setBundle basename="translations"/>
    <title><fmt:message key="page.register.login"/></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="email"><fmt:message key="page.login.email"/>:
        <input type="text" name="email" id="email" required>
    </label>
    <br>
    <label for="password"><fmt:message key="page.login.password"/>:
        <input type="password" name="password" id="password" required>
    </label>
    <br>
    <button type="submit">
        <fmt:message key="page.register.login"/>
    </button>
    <a href="${pageContext.request.contextPath}/registration">
        <button type="button"><fmt:message key="page.login.register.button"/></button>
    </a>
    <c:if test="${param.error != null}">
        <div style="color:red">
            <span>
                <fmt:message key="page.login.register.error"/>
            </span>
        </div>
    </c:if>
</form>

</body>
</html>
