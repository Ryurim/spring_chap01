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
    <title>게시판 등록</title>
    <meta charset="utf-8">
    <style>
        #titleCheck {
            display: none;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"/>
<%
    //String title_check_flag = request.getAttribute("title_check_flag") != null ? (String)request.getAttribute("title_check_flag") : "";
    //String regDate_check_flag = request.getAttribute("regDate_check_flag") != null ? (String)request.getAttribute("regDate_check_flag") : "";
    //String content_check_flag = request.getAttribute("content_check_flag") != null ? (String)request.getAttribute("content_check_flag") : "";
    //String hobby_check_flag = request.getAttribute("hobby_check_flag") != null ? (String)request.getAttribute("hobby_check_flag") : "";
    //String sex_check_flag = request.getAttribute("sex_check_flag") != null ? (String)request.getAttribute("sex_check_flag") : "";

%>
    <h1>게시판 등록</h1>
    <c:if test="${not empty result}">
        <div>
            <span style="color: red">${result}</span>
        </div>
    </c:if>
    <div>
        <form name="frmRegist" id="frmRegist" method="post" action="/bbs/regist">
            <input type="hidden" name="user_id" id="user_id" value="${sessionScope.member.user_id}"/>
            <table>
                <tr>
                    <td><span>제목 : </span></td>
                    <td><input type="text" name="title" id="title" value="" maxlength="200" placeholder="글 제목을 입력하세요."/></td>
<%--                    <%if (title_check_flag != "") { %><td style="color: red;">제목을 입력하세요.</td> <%} %>--%>
<%--                    <c:if test="${not empty title_check_flag}"><td><span style="color: red;">${title_check_flag}</span></td></c:if>--%>
                    <td id="titleCheck" style="display:${title_check_flag}; color:red;">제목을 1~10자 이내로 입력하세요.</td>
                </tr>
                <tr>
                    <td><span>글내용 : </span></td>
                    <td><textarea name="content" id="content" rows="10" cols="80"></textarea></td>
                    <c:if test="${not empty content_check_flag}"><td><span style="color: red;">${content_check_flag}</span></td></c:if>
                </tr>
                <tr>
                    <td><span>등록일 : </span></td>
                    <td><input type="date" name="display_date" id="display_date" value=""/></td>
<%--                    <%if (regDate_check_flag != "") { %><td style="color: red;">등록일을 입력하세요.</td> <%} %>--%>
                    <c:if test="${not empty displayDate_check_flag}"><td><span style="color: red;">${displayDate_check_flag}</span></td></c:if>
                </tr>

<%--                <tr>--%>
<%--                    <td><span>취미 : </span></td>--%>
<%--                    <td><input type="checkbox" name="hobby" id="hobby_0" value="여행"> 여행--%>
<%--                        <input type="checkbox" name="hobby" id="hobby_1" value="독서"> 독서--%>
<%--                        <input type="checkbox" name="hobby" id="hobby_2" value="수영"> 수영--%>
<%--                        <input type="checkbox" name="hobby" id="hobby_3" value="잠자기"> 잠자기--%>
<%--                        <input type="checkbox" name="hobby" id="hobby_4" value="게임"> 게임--%>
<%--                    </td>--%>
<%--                    <c:if test="${not empty hobby_check_flag}"><td><span style="color: red;">${hobby_check_flag}</span></td></c:if>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td><span>성별 : </span></td>--%>
<%--                    <td><input type="radio" name="sex" id="sex_0" value="남"> 남자--%>
<%--                        <input type="radio" name="sex" id="sex_1" value="여"> 여자--%>
<%--                    </td>--%>
<%--                    <c:if test="${not empty sex_check_flag}"><td><span style="color: red;">${sex_check_flag}</span></td></c:if>--%>
<%--                </tr>--%>
                <tr>
                    <td colspan="2">
                        <button type="submit">등록</button>
                        <button type="reset">초기화</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
