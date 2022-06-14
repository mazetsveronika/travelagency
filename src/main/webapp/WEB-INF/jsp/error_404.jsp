<%--
  Created by IntelliJ IDEA.
  User: veron
  Date: 14.06.2022
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/error.css" type="text/css"/>
    <title>Error 404</title>
</head>
<body>
<main>
    <form method="get" action="${pageContext.request.contextPath}/index.jsp">
        <div id="error">
            <img src="${pageContext.request.contextPath}/image/error.png" alt="error">
            <div>
                <div id="number">
                    <div>404</div>
                    <div>Not Found</div>
                </div>
                <div><fmt:message>
                    <key>error.404.message</key>
                </fmt:message></div>
                <input type="submit" value="<fmt:message ><key>error.back</key></fmt:message>"/>
            </div>
        </div>
    </form>
</main>
</body>
</html>