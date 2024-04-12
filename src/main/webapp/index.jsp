<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<ul>
  <li><a href="/bbs/list">리스트</a></li>
  <li><a href="/bbs/regist">등록</a></li>
  <li><a href="/bbs/view">상세</a></li>
  <li><a href="/bbs/modify">수정</a></li>
  <li><a href="/bbs/delete">삭제</a></li>
  <li><a href="/member/login">로그인</a></li>
</ul>
</body>
</html>