<%@page import="java.io.Console"%>
<%@page import="report.ReportBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="adminMgr" class="admin.AdminMgr"></jsp:useBean>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin page(report)</title>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script src="../js/adminReDetail.js"></script>
</head>
<body>
<div class="container">
<%@ include file="../admin_main/adminSidebar.jsp"  %>
<main class="main-content">
<h2>신고/문의</h2>

<div class="table">
<table style="width:90%;">
	<tr>
		<th>번호</th><th>신고자 id</th><th>신고날짜</th><th>신고코드</th><th>reported 번호</th><th>보기</th>
	</tr>

	<c:forEach items="<%=adminMgr.getReportAll() %>" var="reports">
		 	<tr style="text-align: center;">
		 		<td>${reports.report_no}</td>
		 		<td>${reports.reporter_user_id}</td>
		 		<td>${reports.report_date}</td>
		 		<td>${reports.report_code}</td>
		 		<td>${reports.reported_user_no}</td>
		 		<td><a href="javascript:adReportDetail(${reports.report_reply_no})">[해당 글]</a></td>
		 	</tr>
	</c:forEach>
	
</table>
</div>
</main>
<%@ include file="../admin_main/admin_foot.jsp" %>

<form action="adminReportDetail.jsp" method="get">
<input type="hidden" name="report_reply_no">
</form>
</div>
</body>
</html>