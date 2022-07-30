<%--
  Created by IntelliJ IDEA.
  User: veron
  Date: 02.06.2022
  Time: 09:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <%@include file="header.jsp" %>
    <%@ include file="include.jsp" %>
    <style type="text/css"><%@include file="/resources/css/style.css"%></style>
    <fmt:setLocale value="${sessionScope.localization}"/>
    <fmt:setBundle basename="localization.local" var="local"/>
    <fmt:bundle basename="localization">
        <fmt:message key="local.errorMsg" var="errorMsg"/>
    </fmt:bundle>
</head>
<body>
<div class = "error">
    <h2 style="color: #ff0026; font-weight: bold">${errorMsg}</h2>
    <h3 style="color: #ff0026; font-weight: bold">${error}</h3>
</div>





<html>