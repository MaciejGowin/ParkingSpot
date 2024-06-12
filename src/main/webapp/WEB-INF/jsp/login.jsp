<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<body>

<h1>Logowanie</h1>

<form action="/login" method="POST">
    <div><label> Login: <input type="text" name="username"/> </label></div>
    <br>
    <div><label> Hasło: <input type="password" name="password"/> </label></div>
    <br>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <br>
    <div><input type="submit" value="Zaloguj"/></div>
</form>

</body>
</html>
