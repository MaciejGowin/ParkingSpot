<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<body>

<h1>Wybrana rezerwacja jest niepoprawna</h1>

<p>Błąd: ${error}</p>

<div><a href="${contextPath}/profile">Powrót do strony głównej</a></div>

</body>
</html>
