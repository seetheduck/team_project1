<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/adminMain.css">
</head>
<body>
    <div class="container">
        <header class="header">
        </header>
        
        <nav class="sidebar">
			<ul>
				<li><a href="#" onclick="showPage('page-dashboard')">대시보드</a></li>
        		<li><a href="#" onclick="showPage('page-users')">유저</a></li>
        		<li><a href="#" onclick="showPage('page-reviews')">리뷰/댓글</a></li>
        		<li><a href="#" onclick="showPage('page-reports')">신고/문의</a></li>
			</ul>
		</nav>
        
        <%@ include file="admin_dashboard.jsp"  %>
        
        <%@ include file="admin_foot.jsp" %>
        
    </div>
</body>
</html>