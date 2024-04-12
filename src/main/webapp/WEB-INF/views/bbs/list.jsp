<%--
  Created by IntelliJ IDEA.
  User: db400tea
  Date: 2024-04-04
  Time: 오전 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>게시판 리스트</title>
    <meta charset="utf-8">
</head>
<body>
<jsp:include page="header.jsp"/>
    <h1> 게시판 리스트</h1>
    <ul>
        <c:forEach var="bbsDto" items="${list}">
            <li><a href="/bbs/view?idx=${bbsDto.idx}">${bbsDto}</a></li>
        </c:forEach>
    </ul>
    <br>
    <c:if test="${not empty sessionScope.member.user_id}">
        <button id="btn_regist" onclick="location.href='/bbs/regist'">글등록</button>
    </c:if>
<script>
</script>
</body>
</html>
