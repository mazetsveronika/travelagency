<%--
  Created by IntelliJ IDEA.
  User: veron
  Date: 31.05.2022
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <%@ include file="include.jsp" %>
    <style type="text/css"><%@include file="/resources/css/style.css"%></style>
    <fmt:setLocale value="${sessionScope.localization}"/>
    <fmt:setBundle basename="localization.local" var="local"/>
    <fmt:bundle basename="localization">
        <fmt:message key="local.welcom" var="welcom"/>
        <fmt:message key="local.contact" var="contact"/>
    </fmt:bundle>
</head>
<body>
<header>
    <div>
        <div>
            <a href="index.jsp"><img src="resources/image/logotype.png" alt="logotype" class="logo"/></a>
        </div>
        <div class="welcom">
            <h3>${welcom}</h3>
        </div>
        <div>
            <ul id="contact-info" class="contact-info">
                <li>${contact}</li>
                <li>&#9990; +375 29 309 77 77</li>
            </ul>
        </div>
    </div>
</header>
</body>
</html>