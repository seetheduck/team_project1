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
<div class="header">
	<%@ include file="../mainpage/main_top.jsp" %>
</div>
<div>신고가 완료되었습니다</div>
<input type="button" onclick="window.history.go(-3)" value="돌아가기">
<div class="footer">	
	<%@ include file="../mainpage/main_bottom.jsp" %>
</div>
</body>
</html>