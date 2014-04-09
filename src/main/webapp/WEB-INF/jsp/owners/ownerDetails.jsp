<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html lang="en">

<jsp:include page="../fragments/headTag.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>Информация об услуге</h2>

    <table class="table table-striped" style="width:600px;">
        <tr>
            <th>Название</th>
            <td><b><c:out value="${service.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Описание</th>
            <td><c:out value="${service.firstName}"/></td>
        </tr>
        <%--<tr>--%>
            <%--<th>City</th>--%>
            <%--<td><c:out value="${service.description}"/></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<th>Telephone</th>--%>
            <%--<td><c:out value="${service.telephone}"/></td>--%>
        <%--</tr>--%>
         <tr>
            <td> 
            	<spring:url value="{ownerId}/edit.html" var="editUrl">
                    <spring:param name="ownerId" value="${service.id}"/>
                </spring:url>
                <a href="${fn:escapeXml(editUrl)}" class="btn btn-info">Редактировать услугу</a></td>
            <td>
            	<spring:url value="{ownerId}/responses/new.html" var="addUrl">
                    <spring:param name="ownerId" value="${service.id}"/>
                </spring:url>
                <a href="${fn:escapeXml(addUrl)}"  class="btn btn-success">Добавить оценку</a></td>
        </tr>
    </table>

    <h2>Оценки</h2>

    <c:forEach var="response" items="${service.responses}">
        <table class="table" style="width:600px;">
            <tr>
                <td valign="top" style="width: 220px;">
                    <dl class="dl-horizontal">
                        <dt>Оценка</dt>
                        <dd><c:out value="${response.name}"/></dd>
                        <%--<dt>Birth Date</dt>--%>
                        <%--<dd><joda:format value="${response.birthDate}" pattern="yyyy-MM-dd"/></dd>--%>
                        <%--<dt>Комментарий</dt>--%>
                        <%--<dd><c:out value="${response.type.name}"/></dd>--%>
                    </dl>
                </td>
                <td valign="top">
                    <table class="table-condensed">
                        <thead>
                        <%--<tr>--%>
                            <%--<th>Statistic Date</th>--%>
                            <%--<th>Description</th>--%>
                        <%--</tr>--%>
                        <%--</thead>--%>
                        <%--<c:forEach var="statistic" items="${response.statistics}">--%>
                            <%--<tr>--%>
                                <%--<td><joda:format value="${statistic.date}" pattern="yyyy-MM-dd"/></td>--%>
                                <%--<td><c:out value="${statistic.description}"/></td>--%>
                            <%--</tr>--%>
                        <%--</c:forEach>--%>
                        <tr>
                            <td> 
                            	<spring:url value="/services/{ownerId}/responses/{petId}/edit" var="petUrl">
			                        <spring:param name="ownerId" value="${service.id}"/>
			                        <spring:param name="petId" value="${response.id}"/>
			                    </spring:url>
			                    <a href="${fn:escapeXml(petUrl)}">Редактировать оценку</a>
			                </td>
                            <%--<td>--%>
			                    <%--<spring:url value="/services/{ownerId}/responses/{petId}/statistics/new" var="visitUrl">--%>
			                        <%--<spring:param name="ownerId" value="${service.id}"/>--%>
			                        <%--<spring:param name="petId" value="${response.id}"/>--%>
			                    <%--</spring:url>--%>
			                    <%--<a href="${fn:escapeXml(visitUrl)}">Add Statistic</a>--%>
                            <%--</td>--%>
                       	</tr>
                    </table>
                </td>
            </tr>
        </table>
    </c:forEach>

    <jsp:include page="../fragments/footer.jsp"/>

</div>

</body>

</html>
