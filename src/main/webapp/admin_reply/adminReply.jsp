<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="adminMgr" class="admin.AdminMgr"></jsp:useBean>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지(리뷰/댓글)</title>
</head>
<body>
<div class="container">
<%@ include file="../admin_main/adminSidebar.jsp"  %>
<main class="main-content">
<h2>리뷰/댓글</h2>
<div class="table">
<table style="width:90%;">
	<tr>
		<th>번호</th><th>책 번호</th><th>작성일</th><th>좋아요 수</th><th>작성자 ip</th><th>보기</th>
	</tr>

	<c:forEach items="<%=adminMgr.getReplyAdmin() %>" var="reply">
		 	<tr style="text-align: center;">
		 		<td>${reply.reply_no}</td>
				<td>${reply.reply_book_no}</td>
				<td>${reply.reply_create_date}</td>
				<td>${reply.reply_like_cnt}</td>
				<td>${reply.reply_ip}</td>
				<td><a href="javascript:adReportDetail(${reply.reply_no})">[보기]</a></td>
		 	</tr>
	</c:forEach>
	
</table>
</div>

</main>
<%@ include file="../admin_main/admin_foot.jsp" %>

</div>
</body>
</html>