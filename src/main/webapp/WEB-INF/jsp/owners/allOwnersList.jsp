<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html lang="en">

<jsp:include page="../fragments/headTag.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/logo.jsp"/>
    <h2>Пожалуйста, выберите оцениваемую услугу</h2>
    
    <datatables:table id="services" data="${selections}" cdn="true" row="service" theme="bootstrap2"
                      cssClass="table table-striped" paginate="false" info="false" export="pdf">
        <datatables:column title="Название" cssStyle="width: 150px;" display="html">
            <spring:url value="/resp/{ownerId}.html" var="ownerUrl">
                <spring:param name="ownerId" value="${service.id}"/>
            </spring:url>
            <a href="${fn:escapeXml(ownerUrl)}"><c:out value="${service.lastName}"/></a>
        </datatables:column>
        <%--<datatables:column title="Услуга" display="pdf">--%>
            <%--<c:out value="${service.firstName} ${service.lastName}"/>--%>
        <%--</datatables:column>--%>
        <%--<datatables:column title="Address" property="name" cssStyle="width: 200px;"/>--%>
        <%--<datatables:column title="City" property="description"/>--%>
        <%--<datatables:column title="Telephone" property="telephone"/>--%>
        <%--<datatables:column title="Оценки" cssStyle="width: 100px;">--%>
            <%--<c:forEach var="response" items="${service.responses}">--%>
                <%--<c:out value="${response.name}"/>--%>
            <%--</c:forEach>--%>
        <%--</datatables:column>--%>
        <%--<datatables:export type="pdf" cssClass="btn btn-small" />--%>
    </datatables:table>
    
    <jsp:include page="../fragments/footer.jsp"/>

</div>
</body>

</html>
