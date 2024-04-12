<%--
  Created by IntelliJ IDEA.
  User: db400tea
  Date: 2024-04-12
  Time: 오후 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Header</title>
</head>
<body>
    <c:choose>
        <c:when test="${not empty sessionScope.member}">
            <button onclick="location='/member/logout'">로그아웃</button>
        </c:when>
        <c:otherwise>
            <button onclick="location='/member/login'">로그인</button>
        </c:otherwise>
    </c:choose>



</body>
</html>
