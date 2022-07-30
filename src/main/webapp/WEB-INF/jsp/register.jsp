<%--
  Created by IntelliJ IDEA.
  User: veron
  Date: 02.06.2022
  Time: 09:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <%@include file="header.jsp" %>
    <%@ include file="include.jsp" %>
    <style type="text/css"><%@include file="/resources/css/style.css"%></style>
    <fmt:setLocale value="${sessionScope.localization}"/>
    <fmt:setBundle basename="localization.local" var="local"/>
    <fmt:bundle basename="localization">
        <fmt:message key="local.registrMessage" var="registrMessage"/>
        <fmt:message key="local.name" var="name"/>
        <fmt:message key="local.surname" var="surname"/>
        <fmt:message key="local.money" var="money"/>
        <fmt:message key="local.email" var="email"/>
        <fmt:message key="local.login" var="login"/>
        <fmt:message key="local.password" var="password"/>
        <fmt:message key="local.send" var="send"/>
    </fmt:bundle>
</head>
<body>
<div class="wrapper" >
    <div class="content">
        <form id="regForm" action="Controller" method="post">
            <input type="hidden" name="command" value="register" />
            <input type="hidden" name="userRole" value="client" />
            <table id="registration-table">
                <tr>
                    <td>
                    </td>
                    <td>
                        <p align="center" style="color: #030101; font-weight: bold">${registrMessage}</p>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="name" >${name}</form:label>
                    </td>
                    <td>
                        <input type="text" name="name" value="name" maxlength="15" required="required" title="${name}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="surname">${surname}</form:label>
                    </td>
                    <td>
                        <input type="text" name="surname" value="surname" maxlength="15" required="required" title="${surname}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="money">${money}</form:label>
                    </td>
                    <td>
                        <input type="text" name="money" value="0.00" required="required" title="${money}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="email">${email}</form:label>
                    </td>
                    <td>
                        <input type="text" name="email" value="email@mail.ru" required="required" title="${email}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="login">${login}</form:label>
                    </td>
                    <td>
                        <input type="text" name="login" value="login" maxlength="10" required="required" title="${login}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="password">${password}</form:label>
                    </td>
                    <td>
                        <input type="text" name="password" value="password" maxlength="10" required="required" title="${password}"/>
                    </td>
                </tr>
                <tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" class="btn btn-success" value="${send}"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>