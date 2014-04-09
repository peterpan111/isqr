<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html lang="ru">

<jsp:include page="../fragments/headTag.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>Найти услугу</h2>

    <spring:url value="/services.html" var="formUrl"/>
    <form:form modelAttribute="service" action="${fn:escapeXml(formUrl)}" method="get" class="form-horizontal"
               id="search-service-form">
        <fieldset>
            <div class="control-group" id="lastName">
                <label class="control-label">Описание </label>
                <form:input path="lastName" size="30" maxlength="80"/>
                <span class="help-inline"><form:errors path="*"/></span>
            </div>
            <div class="form-actions">
                <button type="submit">Найти услугу</button>
            </div>
        </fieldset>
    </form:form>

    <br/>
    <a href='<spring:url value="/services/new" htmlEscape="true"/>'>Добавить услугу</a>

    <jsp:include page="../fragments/footer.jsp"/>

</div>
</body>

</html>
