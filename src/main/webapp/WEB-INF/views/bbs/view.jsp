<%--
  Created by IntelliJ IDEA.
  User: db400tea
  Date: 2024-04-04
  Time: 오전 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>게시판 상세</title>
    <meta charset="utf-8">
</head>
<body>
<jsp:include page="header.jsp"/>
    <h1>게시판 상세</h1>
    <form id = "frmDelete" method="post" action="/bbs/delete" name="frmDelete">
        <input type="hidden" id="idx" name="idx" value="${dto.idx}"/>
        <div>${dto.idx}</div>
        <div>${dto.user_id}</div>
        <div>${dto.title}</div>
        <div>${dto.content}</div>
        <div>${dto.display_date}</div>
        <div>${dto.read_cnt}</div>

        <div>
            <button type="button" onclick="location.href='/bbs/list'">리스트</button>
            <c:if test="${not empty sessionScope.member.user_id}">
                <button type="button" onclick="goModify();">수정</button>
                <button type="button" onclick="goDelete();">삭제</button>
            </c:if>

        </div>
    </form>
<script>
    function goDelete() {
        //let idx = document.getElementById("idx").value;
        if (${sessionScope.member.user_id != dto.user_id}) {
            alert("자신의 글만 삭제 가능합니다.");
        }
        else {
            let yn = confirm("삭제 하시겠습니까?");
            if (yn) {
                document.getElementById("frmDelete").submit();
            }
        }
    }
    function goModify(e) {
        if (${sessionScope.member.user_id != dto.user_id}) {
            alert("자신의 글만 수정 가능합니다.");
        }
        else {
            location.href="/bbs/modify?idx=${dto.idx}";
        }

    }
</script>
</body>
</html>
