<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<body>

<h1>Strona główna</h1>

<div><b>Witaj ${username}</b></div>
<br>
<div>Wybierz datę rezerwacji w formacie YYYY-MM-DD</div>
<br>

<div>
    <form:form action="${contextPath}/reservation" method="POST" modelAttribute="reservationForm">
        <div><label for="date"> Data: <form:input type="text" path="date" required="required" placeholder="yyyy-MM-dd"/> </label></div>
        <br>
        <div><input type="submit" value="Zarezerwuj"/></div>
    </form:form>
</div>
<br>

<div>
    <form action="${contextPath}/reservations" method="GET">
        <div><input type="submit" value="Zarezerwowane terminy"/></div>
    </form>
</div>
<br>

<div>
    <form action="${contextPath}/my-reservations" method="GET">
        <div><input type="submit" value="Moje rezerwacje"/></div>
    </form>
</div>
<br>

<div>
    <form action="/logout" method="POST">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div><input type="submit" value="Wyloguj"/></div>
    </form>
</div>

</body>
</html>
