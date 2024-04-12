<%--
  Created by IntelliJ IDEA.
  User: db400tea
  Date: 2024-04-03
  Time: 오후 1:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page trimDirectiveWhitespaces="true"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String errCode = (String)request.getAttribute("errCode");
    %>
    <form name="frmCalc" id="frmCalc" method="post" action="/calc">
        <input type="text" name="num1"><br>
        <input type="text" name="num2"><br>
        <div style="display: <%=errCode != null ? "block" : "none"%>">에러</div>
        <button type="submit">END</button>
    </form>
</body>
</html>
