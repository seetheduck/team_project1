<%@page import="book.BookDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="myReply.ReplyBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="bookMgr" class="book.bookMgr" />
<jsp:useBean id="replyMgr" class="myReply.ReplyMgr" />
<%
//String id = request.getParameter("id"); //넘어온 id 받기
//String id = (String)session.getAttribute("idKey");
String id = "aa";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모아보기(onum == 0)</title>
<script type="text/javascript" src="../js/myReply.js"></script>
</head>
<body>
	<h2>내 리뷰</h2>
	<%
	ArrayList<ReplyBean> list = replyMgr.myReplyAll(id);
	if (list.isEmpty()) { //작성할 리뷰가 없는 경우
	%>
		<div><p>리뷰를 작성해주세요</p></div>
	<%
	} else { //작성한 리뷰가 있는 경우
		for (ReplyBean li : list) {
			BookDTO book = bookMgr.getBook(li.getReply_book_no());
			%>
				<div class="col">
					<div class="card">
					<!-- <a href=".jsp"> </a> 댓글 상세보기로 넘어가기-->
						<img src="../img/<%=book.getThumb_nail() %>" class="card-img-top" alt="..." >
						<div class="card-body">
							<span class="card-text"><small class="text-body-secondary"><%=book.getTitle()%></small></span>
							<span class="card-text"><small class="text-body-secondary"><%=li.getReply_like_cnt()%></small></span>
							<h5 class="card-title"><%=li.getReply_title()%></h5>
							<p class="card-text"><%=li.getReply_point()%></p>
							<p class="card-text"><%=li.getReply_cont()%></p>
							<p class="card-text"><small class="text-body-secondary"><%=li.getReply_create_date()%></small></p>
						</div>
						
						<div><a href="replyproc.jsp?flag=delete&reply_no=<%=li.getReply_no() %>">[ 삭 제 ]</a></div>
					</div>
				</div>
			
			<%
		}
	}
	%>
</body>
</html>