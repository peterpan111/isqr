<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html lang="ru">
<head><meta http-equiv="refresh" content="1; url=http://192.168.11.12:8585/IS-QR/ownersAll"></head>
<jsp:include page="../fragments/headTag.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/logo.jsp"/>

    <h2>Спасибо Вам за оценку!</h2>

    <%--<spring:url value="/services.html" var="formUrl"/>--%>
    <%--<form:form modelAttribute="service" action="${fn:escapeXml(formUrl)}" method="get" class="form-horizontal"--%>
    <%--id="search-service-form">--%>
    <%--<fieldset>--%>
    <%--<div class="control-group" id="lastName">--%>
    <%--<label class="control-label">Описание </label>--%>
    <%--<form:input path="lastName" size="30" maxlength="80"/>--%>
    <%--<span class="help-inline"><form:errors path="*"/></span>--%>
    <%--</div>--%>
    <%--<div class="form-actions">--%>
    <%--<button type="submit">Найти услугу</button>--%>
    <%--</div>--%>
    <%--</fieldset>--%>
    <%--</form:form>--%>

    <%--<br/>--%>
    <%--<a href='<spring:url value="/services/new" htmlEscape="true"/>'>Добавить услугу</a>--%>

    <jsp:include page="../fragments/footer.jsp"/>

</div>
</body>

</html>


<%--<!DOCTYPE html> --%>

<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>--%>

<%--<html lang="en">--%>
<%--<jsp:include page="fragments/headTag.jsp"/>--%>

<%--&lt;%&ndash;<body>&ndash;%&gt;--%>
<%--&lt;%&ndash;<div class="container">&ndash;%&gt;--%>
    <%--&lt;%&ndash;<jsp:include page="fragments/bodyHeader.jsp"/>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<spring:url value="/resources/images/responses.png" var="petsImage"/>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<img src="${petsImage}"/>&ndash;%&gt;--%>

    <%--&lt;%&ndash;<h2>Something happened...</h2>&ndash;%&gt;--%>

    <%--&lt;%&ndash;<p>${exception.message}</p>&ndash;%&gt;--%>

    <%--&lt;%&ndash;<!-- Exception: ${exception.message}.&ndash;%&gt;--%>
		  	<%--&lt;%&ndash;<c:forEach items="${exception.stackTrace}" var="stackTrace"> &ndash;%&gt;--%>
				<%--&lt;%&ndash;${stackTrace} &ndash;%&gt;--%>
			<%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
	  	<%--&lt;%&ndash;-->&ndash;%&gt;--%>


    <%--&lt;%&ndash;<jsp:include page="fragments/footer.jsp"/>&ndash;%&gt;--%>

<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
<%--&lt;%&ndash;</body>&ndash;%&gt;--%>

<%--</html>--%>
