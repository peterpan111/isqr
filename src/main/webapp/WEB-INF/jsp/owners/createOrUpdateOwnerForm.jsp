<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>


<html lang="en">

<jsp:include page="../fragments/headTag.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>
    <c:choose>
        <c:when test="${service['new']}"><c:set var="method" value="post"/></c:when>
        <c:otherwise><c:set var="method" value="put"/></c:otherwise>
    </c:choose>

    <h2>
        <c:if test="${service['new']}">Новая </c:if> услуга
    </h2>
    <form:form modelAttribute="service" method="${method}" class="form-horizontal" id="add-service-form">
        <petclinic:inputField label="Название" name="lastName"/>
        <petclinic:inputField label="Описание" name="firstName"/>
        <%--<isqr:inputField label="Address" name="name"/>--%>
        <%--<isqr:inputField label="City" name="description"/>--%>
        <%--<isqr:inputField label="Telephone" name="telephone"/>--%>

        <div class="form-actions">
            <c:choose>
                <c:when test="${service['new']}">
                    <button type="submit">Добавить услугу</button>
                </c:when>
                <c:otherwise>
                    <button type="submit">Обновить услугу</button>
                </c:otherwise>
            </c:choose>
        </div>
    </form:form>
</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>

</html>
