<%--
  Created by IntelliJ IDEA.
  User: db400tea
  Date: 2024-04-12
  Time: 오전 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <style>
        #empty {
            display: none;
        }
        #error {
            display: none;
        }
    </style>
</head>
<body>
    <form id="frmLogin" action="/member/login" method="post">
        <label for="user_id">아이디 : </label><input type="text" id="user_id" name="user_id" value="${cookie.user_id.value}"><br>
        <label for="pwd">비밀번호 : </label><input type="password" id="pwd" name="pwd" value=""><br>
        <label for="idSave">아이디 저장 </label><input type="checkbox" id="idSave" name="idSave" value="Y" ${cookie.idS.value}>
        <label for="autoLogin">자동 로그인</label><input type="checkbox" id="autoLogin" name="autoLogin" value="Y"><br>
        <span id="empty" style="display:${requestScope.isEmpty}; color:red">아이디/비밀번호를 입력해 주세요.</span>
        <span id="error" style="display:${requestScope.loginError}; color:red">아이디/비밀번호를 정확하게 입력해 주세요.</span>
        <input type="submit" id="submit" value="로그인">
    </form>
</body>
</html>
