<%--
  Created by IntelliJ IDEA.
  User: veron
  Date: 02.06.2022
  Time: 09:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>View vouchers</title>
    <%@include file="header.jsp" %>
    <%@ include file="include.jsp" %>
    <style type="text/css"><%@include file="/resources/css/style.css"%></style>
    <fmt:setLocale value="${sessionScope.localization}"/>
    <fmt:setBundle basename="localization.local" var="local"/>
    <fmt:bundle basename="localization">
        <fmt:message key="local.country" var="country"/>
        <fmt:message key="local.dateFrom" var="dateFrom"/>
        <fmt:message key="local.dateTo" var="dateTo"/>
        <fmt:message key="local.tour.type" var="tourtype"/>
        <fmt:message key="local.tour.price" var="tourprice"/>
        <fmt:message key="local.tour.hot" var="tourhot"/>
        <fmt:message key="local.hotel.name" var="hotelname"/>
        <fmt:message key="local.hotel.pricePerDay" var="hotelpricePerDay"/>
        <fmt:message key="local.transport" var="transport"/>
    </fmt:bundle>
</head>
<body>
<div align="center" style="margin-top: 50px; margin-bottom: 150px">
    <table border="1"  style="border: 3px ridge #030101">
        <tr align="center" style="font-weight: bold; ">
            <td>№</td>
            <td>${country}</td>
            <td>${dateFrom}</td>
            <td>${dateTo}</td>
            <td>${tourtype}</td>
            <td>${tourprice}</td>
            <td>${tourhot}</td>
            <td>${hotelname}</td>
            <td>${hotelpricePerDay}</td>
            <td>${transport}</td>
        </tr>
        <c:forEach var="voucher" items="${vouchers}" varStatus="status">
            <tr>
                <td><c:out value="${status.count}"/></td>
                <td><c:out value="${voucher.country}"/></td>
                <td><c:out value="${voucher.dateFrom}"/></td>
                <td><c:out value="${voucher.dateTo}"/></td>
                <td><c:out value="${voucher.tour.type}"/></td>
                <td><c:out value="${voucher.tour.price}"/></td>
                <td><c:out value="${voucher.tour.hot}"/></td>
                <td><c:out value="${voucher.hotel.name}"/></td>
                <td><c:out value="${voucher.hotel.pricePerDay}"/></td>
                <td><c:out value="${voucher.transport}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
<div style="margin-bottom: 150px">
    <h2 align="center" style="color: #ff0026; font-weight: bold">${error}</h2>
</div>
<div style="margin-top: 200px; margin-bottom: 500px">
</div>
<%@ include file="footer.jsp" %>
</body>
</html>