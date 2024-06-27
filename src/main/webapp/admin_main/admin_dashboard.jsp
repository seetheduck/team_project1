<%@page import="reply.ReplyMgr"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="userDto" class="user.UserDto"/>
<jsp:useBean id="adminMgr" class="admin.AdminMgr"/>

<link rel="stylesheet" href="../css/adminMain.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script src="../js/graph.js"></script>

<main class="main-content">
    <h2>대시보드</h2>
    <div class="dash1">
	    <div style="width: 50%; height: 300px;">
	        <canvas id="myBarChart" style="width: 100%"></canvas>
	    </div>
	    <div class="dashRe">
	    	<span style="float: right; right: 30px; font-size: 12px;">
	    		<a href="../admin_reply/adminReply.jsp">+ 더보기</a>
	    	</span>
	    	<table style="width: 100%;">
	    		<tr>
	    			<th>리뷰번호</th><th>책번호</th><th>작성일</th><th>좋아요 수</th><th>작성자ip</th>
	    		</tr>
	    		<tr">
				    <c:forEach items="<%=adminMgr.getReplyDash()%>" var="reply">
					 	<tr style="text-align: center;">
					 		<td>${reply.reply_no}</td>
					 		<td>${reply.reply_book_no}</td>
					 		<td>${reply.reply_create_date}</td>
					 		<td>${reply.reply_like_cnt}</td>
					 		<td>${reply.reply_ip}</td>
					 	</tr>
					</c:forEach>
	    		</tr>
	    	</table>
	    </div>
    </div>
    
    <div style="width: 40%; height: 300px;">
        <canvas id="myLineChart" style="width: 100%"></canvas>
    </div>
    <hr>
</main>