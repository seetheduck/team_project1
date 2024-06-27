<%@page import="user.UserBean"%>
<%@page import="java.io.Console"%>
<%@page import="report.ReportBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="adminMgr" class="admin.AdminMgr"></jsp:useBean>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>관리자 페이지(유저)</title>

<script src="https://code.jquery.com/jquery-latest.js"></script>
<script src="../js/adminUserDetail.js"></script>
<link rel="stylesheet" href="../css/userModal.css">
<link rel="stylesheet" href="../css/adminMain.css">

</head>
<body>

<div class="container">
<%@ include file="../admin_main/adminSidebar.jsp"  %>
<main class="main-content">
<h2>유저관리</h2>

<div class="userTable">
<table style="width:90%;">
<tr>
<td>번호</td><td>유저 id</td><td>보기</td>
</tr>
<%
// 페이징 처리를 위해 페이지 번호 받기
String pa = request.getParameter("pa");
if(pa == null) pa= "1";

%>
<c:forEach items="<%=adminMgr.getUserAll(pa) %>" var="users">
        <tr style="text-align: center;">
            <td>${users.no}</td>
            <td>${users.id}</td>
            <td><button onclick="javascript:adUserDetail(${users.no})">상세보기</button></td>
        </tr>
    </c:forEach>

</table>
</div>
<div class="pagination">

<% 
adminMgr.userTotalPage(); //전체 페이지 수 계산
%>
<tr>
<td colspan="4" style="text-align: center;">
<%
if(adminMgr.userTotalPage() > 0){
	for(int pageNo=1; pageNo<= adminMgr.userTotalPage(); pageNo++){
%>
<a href="adminUser.jsp?pa=<%=pageNo %>"><%=pageNo %></a>&nbsp;
<%
	}
}
%>
</td>
</tr>

</div>

</main>


<%@ include file="../admin_main/admin_foot.jsp" %>
</div>

<!-- 모달 창 -->
<div id="userDetailModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <div id="modal-body">
            <!-- AJAX를 통해 로드된 사용자 세부 정보가 여기에 표시됩니다 -->
        </div>
    </div>
    
</div>
</div>
</body>
</html>