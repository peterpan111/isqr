<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html lang="en">

<jsp:include page="../fragments/headTag.jsp"/>


<body>
<script>
    $(function () {
        $("#date").datepicker({ dateFormat: 'yy/mm/dd'});
    });
</script>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>
    <h2><c:if test="${statistic['new']}">New </c:if>Visit</h2>

    <b>Pet</b>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Birth Date</th>
            <th>Type</th>
            <th>Owner</th>
        </tr>
        </thead>
        <tr>
            <td><c:out value="${statistic.response.name}"/></td>
            <td><joda:format value="${statistic.response.birthDate}" pattern="yyyy/MM/dd"/></td>
            <td><c:out value="${statistic.response.type.name}"/></td>
            <td><c:out value="${statistic.response.service.firstName} ${statistic.response.service.lastName}"/></td>
        </tr>
    </table>

    <form:form modelAttribute="statistic">
        <div class="control-group">
            <label class="control-label">Date </label>

            <div class="controls">
                <form:input path="date"/>
                <span class="help-inline"><form:errors path="date"/></span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Description </label>

            <div class="controls">
                <form:input path="description"/>
                <span class="help-inline"><form:errors path="description"/></span>
            </div>
        </div>
        <div class="form-actions">
            <input type="hidden" name="petId" value="${statistic.response.id}"/>
            <button type="submit">Add Visit</button>
        </div>
    </form:form>

    <br/>
    <b>Previous Visits</b>
    <table style="width: 333px;">
        <tr>
            <th>Date</th>
            <th>Description</th>
        </tr>
        <c:forEach var="statistic" items="${statistic.response.statistics}">
            <c:if test="${!statistic['new']}">
                <tr>
                    <td><joda:format value="${statistic.date}" pattern="yyyy/MM/dd"/></td>
                    <td><c:out value="${statistic.description}"/></td>
                </tr>
            </c:if>
        </c:forEach>
    </table>

</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>

</html>
