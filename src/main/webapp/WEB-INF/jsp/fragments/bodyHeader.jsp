<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<spring:url value="/resources/images/banner-isqr.jpg" var="banner"/>
<spring:url value="/resources/images/kkclogo.gif" var="bannerkkc"/>
<span class="header">IS-QR</span><img src="${banner}"/>
<img src="${bannerkkc}"/>
<div class="navbar" style="width: 601px;">
    <div class="navbar-inner">
        <ul class="nav">
            <li style="width: 100px;"><a href="<spring:url value="/" htmlEscape="true" />"><i class="icon-home"></i>
                Home</a></li>
            <li style="width: 130px;"><a href="<spring:url value="/services/find.html" htmlEscape="true" />"><i
                    class="icon-search"></i>Найти услугу</a></li>
            <li style="width: 140px;"><a href="<spring:url value="/ownersAll.html" htmlEscape="true" />"><i
                    class="icon-th-list"></i> Отчеты</a></li>
            <li style="width: 90px;"><a href="<spring:url value="/ownersAll.html" htmlEscape="true" />"
                                        title="Страница пользователя"><i
                    class="icon-warning-sign"></i>Оценивать</a></li>
            <li style="width: 80px;"><a href="#" title="not available yet. Work in progress!!"><i
                    class=" icon-question-sign"></i> Help</a></li>
        </ul>
    </div>
</div>
	
