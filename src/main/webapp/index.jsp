<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <%@include file="/WEB-INF/jsp/header.jsp" %>
    <%@include file="/WEB-INF/jsp/include.jsp" %>
    <style type="text/css"><%@include file="/resources/css/style.css"%></style>
    <fmt:setLocale value="${sessionScope.localization}"/>
    <fmt:setBundle basename="localization.local" var="local"/>
    <fmt:bundle basename="localization">
        <fmt:message key="local.welcom" var="welcom"/>
        <fmt:message key="local.register" var="register"/>
        <fmt:message key="local.logIn" var="logIn"/>
        <fmt:message key="local.logOut" var="logOut"/>
        <fmt:message key="local.loginMessage" var="loginMessage"/>
        <fmt:message key="local.registerMessage" var="registerMessage"/>
        <fmt:message key="local.en" var="en"/>
        <fmt:message key="local.ru" var="ru"/>
        <fmt:message key="local.viewAllVoucher" var="viewAllVoucher"/>
        <fmt:message key="local.Greece" var="Greece"/>
        <fmt:message key="local.Poland" var="Poland"/>
        <fmt:message key="local.Spain" var="Spain"/>
        <fmt:message key="local.Montenegro" var="Montenegro"/>
        <fmt:message key="local.Russia" var="Russia"/>
        <fmt:message key="local.Bulgaria" var="Bulgaria"/>
        <fmt:message key="local.beach" var="beach"/>
        <fmt:message key="local.shopping" var="shopping"/>
        <fmt:message key="local.excursion" var="excursion"/>
        <fmt:message key="local.fitness" var="fitness"/>
        <fmt:message key="local.weekend" var="weekend"/>
    </fmt:bundle>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <div align="center" style="margin-top: 100px; margin-bottom: 150px">
            <table width=800px border="1"  style="border:10px ridge #31f0da">
                <tr>
                    <td colspan="2">
                        <div class="locale">
                            <p style="font-weight: bold">
                                <a href="${pageContext.request.contextPath}/Controller?command=change_locale&localization=en">${en}</a> |
                                <a href="${pageContext.request.contextPath}/Controller?command=change_locale&localization=ru">${ru}</a>
                            </p>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <p align="center" style="color: #0C5A6AFF; font-weight: bold; font-size: 20px; font-style: normal">
                            <a href="Controller?command=view_all_vouchers" class="button">${viewAllVoucher}</a>
                        </p>
                        <p align="center" style="color: #0C5A6AFF; font-size: 18px; font-style: normal">
                            <a href="Controller?command=view_vouchers_by_country&country=Греция" class="button">${Greece}</a><a> ✈ </a>
                            <a href="Controller?command=view_vouchers_by_country&country=Польша" class="button">${Poland}</a><a> ✈ </a>
                            <a href="Controller?command=view_vouchers_by_country&country=Испания" class="button">${Spain}</a><a> ✈ </a>
                            <a href="Controller?command=view_vouchers_by_country&country=Черногория" class="button">${Montenegro}</a><a> ✈ </a>
                            <a href="Controller?command=view_vouchers_by_country&country=Россия" class="button">${Russia}</a><a> ✈ </a>
                            <a href="Controller?command=view_vouchers_by_country&country=Болгария" class="button">${Bulgaria}</a>
                        </p>
                        <p align="center" style="color: #0C5A6AFF; font-size: 18px; font-style: normal">
                            <a href="Controller?command=view_vouchers_by_tour_type&tourtype=Пляжный отдых" class="button">${beach}</a><a>  ✈  </a>
                            <a href="Controller?command=view_vouchers_by_tour_type&tourtype=Шоппинг" class="button">${shopping}</a><a>  ✈  </a>
                            <a href="Controller?command=view_vouchers_by_tour_type&tourtype=Экскурсия" class="button">${excursion}</a><a>  ✈  </a>
                            <a href="Controller?command=view_vouchers_by_tour_type&tourtype=Фитнес-тур" class="button">${fitness}</a><a>  ✈  </a>
                            <a href="Controller?command=view_vouchers_by_tour_type&tourtype=Тур выходного дня" class="button">${weekend}</a>
                        </p>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p style="color: #0c5a6a; font-weight: bold; font-size: 18px; margin: 10px">${loginMessage}</p>
                    </td>
                    <td>
                        <form action="Controller" method="GET"  align="center">
                            <input type="hidden" name="command" value="to_login" />
                            <button class="btn btn-success" type="submit">${logIn}</button>
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p style="color: #0c5a6a; font-weight: bold; font-size: 18px; margin: 10px">${registerMessage}</p>
                    </td>
                    <td>
                        <form action="Controller" method="GET"  align="center">
                            <input type="hidden" name="command" value="to_register" />
                            <button class="btn btn-success" type="submit">${register}</button>
                        </form>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
