<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String adminId = (String)session.getAttribute("admin");
if(adminId == null){
	response.sendRedirect("../admin_login/adminLogin.jsp");
}

%>
<link rel="stylesheet" href="../css/adminMain.css">
<header class="header">
<a href="../admin_main/adminMain.jsp" class="home">
	<img src="../image/cookie.jpg" style="width: 50px; border-radius: 70%;">
</a>
<a href="../admin_login/adminLogout.jsp" class="logout">로그아웃</a>
</header>
        
<nav class="sidebar">
	<ul>
		<li><a href="../admin_main/adminMain.jsp">대시보드</a></li>
		<li><a href="../admin_user/adminUser.jsp">유저</a></li>
		<li><a href="../admin_reply/adminReply.jsp">리뷰/댓글</a></li>
		<li><a href="../admin_report/adminReport.jsp">신고/문의</a></li>
	</ul>
</nav>