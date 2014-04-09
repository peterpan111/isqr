<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>


<html lang="ru">

<jsp:include page="fragments/headTag.jsp"/>

<body>
<div class="container">
    <jsp:include page="fragments/bodyHeader.jsp"/>
    <h2><fmt:message key="Пожалуйста, авторизуйтесь"/></h2>
    <form action="http://192.168.11.12:8585/IS-QR/services/find.html" class="form-horizontal">
        <div class="control-group">
            <label class="control-label">Логин </label>
            <input type="text" size="40">
        </div>
        <div class="control-group" id="lastName">
            <label class="control-label">Пароль</label>
            <input type="password" size="40">
        </div>
        <input type="submit" align="center" value="Войти">
    </form>
    <spring:url value="/resources/images/home.gif" htmlEscape="true" var="petsImage"/>
    <img src="${petsImage}"/>

    <jsp:include page="fragments/footer.jsp"/>

</div>
</body>

</html>
