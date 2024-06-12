<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<body>

<h1>Lista Moich Rezerwacji</h1>

<ul>
    <c:forEach var="myReservation" items="${userReservations}">
        <li>${myReservation.localDate}</li>
    </c:forEach>
</ul>

<div><a href="${contextPath}/profile">Wstecz</a></div>

</body>
</html>
