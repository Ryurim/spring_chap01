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
    <title>게시판 수정</title>
    <meta charset="utf-8">
</head>
<body>
<jsp:include page="header.jsp"/>
    <h1>게시판 수정</h1>
    <div>
        <form name="frmModify" id="frmModify" method="post" action="/bbs/modify">
            <input type="hidden" name="user_id" id="user_id" value="${bbsDTO.user_id}"/>
            <input type="hidden" name="idx" id="idx" value="${bbsDTO.idx}"/>
            <table>
                <tr>
                    <td><span>제목 : </span></td>
                    <td><input type="text" name="title" id="title" value="${bbsDTO.title}" maxlength="200" placeholder="글 제목을 입력하세요."/></td>
                    <td id="titleCheck" style="display: none"><span style="color: red">제목을 1~10자 이내로 입력하세요.</span></td>
                    <c:if test="${not empty title_check_flag}"><td><span style="color: red;">${title_check_flag}</span></td></c:if>
                </tr>
                <tr>
                    <td><span>글내용 : </span></td>
                    <td><textarea name="content" id="content" rows="10" cols="80">${bbsDTO.content}</textarea></td>
                    <td id="contentCheck" style="display: none"><span style="color: red">내용을 1~200자 이내로 입력하세요.</span></td>
                    <c:if test="${not empty content_check_flag}"><td><span style="color: red;">${content_check_flag}</span></td></c:if>
                </tr>
                <tr>
                    <td><span>등록일 : </span></td>
                    <td><input type="date" name="display_date" id="display_date" value="${bbsDTO.display_date}"/></td>
                    <td id="displayDateCheck" style="display: none"><span style="color: red">날짜를 오늘날짜 이내로 선택하세요.</span></td>
                    <c:if test="${not empty displayDate_check_flag}"><td><span style="color: red;">${displayDate_check_flag}</span></td></c:if>
                </tr>

                <tr>
                    <td colspan="2">
                        <button type="submit" id="btnSubmit">등록</button>
                        <button type="button" onclick="location='/bbs/view?idx=${bbsDTO.idx}'">취소</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
<script>
    document.querySelector("#btnSubmit").addEventListener("click", (e) => {
        e.preventDefault();

        let title = document.querySelector("#title");
        let content = document.querySelector("#content");
        let display_date = document.querySelector("#display_date");
        let titleCheck = document.querySelector("#titleCheck");
        let contentCheck = document.querySelector("#contentCheck");
        let displayDateCheck = document.querySelector("#displayDateCheck");
        let frmModify = document.querySelector("#frmModify");



        if (title.value == null || title.value.length < 1 || title.value.length > 10) {
            titleCheck.style.display = "block";
            return false;
        }
         if (content.value == null || content.value.length < 1 || content.value.length > 200) {
            contentCheck.style.display = "block";
            return false;
        }
         if (display_date.value == null || display_date.value.length === 0) {
            displayDateCheck.style.display = "block";
            return false;
        }
         if (display_date.value !== "" || display_date.value != null) {
            let today = new Date();
            console.log(today);
            let day = new Date(display_date.value);
            console.log(day);
            if (day.getDate() > today.getDate()) {
                displayDateCheck.style.display = "block";
                return false;
            }
        }


        frmModify.submit();

    }, false);
</script>
</body>
</html>
