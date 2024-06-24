<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String no = request.getParameter("reply_no");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고 완료</title>
</head>
<body>
<div>신고가 완료되었습니다</div>
<input type="button" onclick="location.href='reply.jsp?reply_no='+<%=no%> %>" value="돌아가기">
</body>
</html>