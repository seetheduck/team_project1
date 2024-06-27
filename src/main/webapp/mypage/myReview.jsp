<%@page import="reply.ReplyBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="bookMgr" class="book.BookMgr" />
<jsp:useBean id="replyMgr" class="reply.ReplyMgr" />
<jsp:useBean id="bookDto" class="book.BookDTO"/>
<%
//String id = request.getParameter("id"); //넘어온 id 받기
String id = (String)session.getAttribute("idkey");
if(id == null){
    response.sendRedirect("../login/loginpage.jsp");
}
//paging
int spage = 1, pageSu = 0;
int start, end;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모아보기(onum == 0)</title>
<link rel="stylesheet" type="text/css" href="../css/myReplystyles.css">
</head>
<body>
<div class="container">
	<h2>내 리뷰</h2>
	<input type="button" onclick="location.href='../mypage/mypage.jsp'" value="돌아가기"> 
	<table>
	<%	
	ArrayList<ReplyBean> list = replyMgr.myReviewAll(id);
	if (list.isEmpty()) { //작성할 리뷰가 없는 경우
	%>
	<div>
		<p>리뷰를 작성해주세요</p>
	</div>
	<%
	try{
		spage = Integer.parseInt(request.getParameter("page"));
	}catch(Exception e){
		spage =1;
	}
	if(spage <= 0) spage= 1;
		
	} else { //작성한 리뷰가 있는 경우
		for (ReplyBean li : list) {
			bookDto = bookMgr.getBook(li.getReply_book_no());
	%>
	<tr>
	<div>
		<a href="../reply/replydetails.jsp?reply_no=<%=li.getReply_no()%>&reply_book_no=<%=li.getReply_book_no()%>&reply_id=<%=li.getReply_id()%>&page=<%=spage %>"> 
			<img src="../images/<%=bookDto.getThumb_nail()%>" class="card-img-top"	alt="책 이미지" >
			<div class="card SMN_effect-19"	onclick="reviewDetail('<%=li.getReply_no()%>')">
				<span class="car2.....................................................................................d-text"><small class="text-body-secondary"><%=bookDto.getTitle()%></small></span>
				<span class="card-text"><small class="text-body-secondary"><%=li.getReply_like_cnt()%></small></span>
				<h5 class="card-title"><%=li.getReply_title()%></h5>
				<p class="card-text"><%=li.getReply_point()%></p>
				<p class="card-text"><%=li.getReply_cont()%></p>
				<p class="card-text">
					<small class="date-text"><%=li.getReply_create_date()%></small>
				</p>
			</div>
		</a>
	</div>
	<div class="del-button">
		<a href="reviewproc.jsp?flag=delete&reply_no=<%=li.getReply_no()%>">[ 삭 제 ]</a>
	</div>
	</tr>
		<%
		}
	}
	%>
	<table>
		<tr>
			<td style="text-align : center;">
				<%
				replyMgr.myReplyTot(); //전체 레코드 수 계산
				pageSu = replyMgr.getPageSu(); // 전체 페이지 수 반환
				for(int i =1; i<=pageSu ; i++){
					if(i == spage){
						out.print("<b>["+i+"]</b>" );
					}else{
					out.print("<a href='myReview.jsp?page="+i+"'>["+i+"]</a>"+"  ");							
					}
				}
				%>
			</td>
		</tr>
	</table>

	</table>
	<%@ include file="../mainpage/main_bottom.jsp" %>
</div>
</body>
</html>