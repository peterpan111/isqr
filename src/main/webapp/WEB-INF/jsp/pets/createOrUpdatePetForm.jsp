<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>


<html lang="en">

<jsp:include page="../fragments/headTag.jsp"/>
<body>

<script>
    $(function () {
        $("#birthDate").datepicker({ dateFormat: 'yy/mm/dd'});
    });
</script>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>
    <c:choose>
        <c:when test="${response['new']}">
            <c:set var="method" value="post"/>
        </c:when>
        <c:otherwise>
            <c:set var="method" value="put"/>
        </c:otherwise>
    </c:choose>

    <h2>
        <c:if test="${response['new']}">Новая </c:if>
        оценка
    </h2>

    <form:form modelAttribute="response" method="${method}"
               class="form-horizontal">
        <div class="control-group" id="service">
            <label class="control-label">Услуга </label>

            <c:out value="${response.service.lastName}"/>
        </div>
        <petclinic:inputField label="Название" name="name"/>
        <petclinic:inputField  label="Дата" name="birthDate"/>
        <div class="control-group">
            <petclinic:selectField name="type" label="Комментарий " names="${types}" size="5"/>
        </div>
        <div class="form-actions">
            <c:choose>
                <c:when test="${response['new']}">
                    <button type="submit">Добавить оценку</button>
                </c:when>
                <c:otherwise>
                    <button type="submit">Обновить оценку</button>
                </c:otherwise>
            </c:choose>
        </div>
    </form:form>
    <c:if test="${!response['new']}">
    </c:if>
    <jsp:include page="../fragments/footer.jsp"/>
</div>
</body>

</html>
