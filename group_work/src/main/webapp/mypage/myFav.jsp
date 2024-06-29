<%@page import="likeFav.LikeFavDTO"%>
<%@page import="book.BookDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="likeFavMgr" class="likeFav.LikeFavMgr" />
<jsp:useBean id="bookMgr" class="book.BookMgr" />

<%
String id = (String)session.getAttribute("idkey");
if(id == null){
    response.sendRedirect("../login/loginpage.jsp");
}
ArrayList<LikeFavDTO> list = likeFavMgr.getLikeBookAll(id); //특정 아이디의 책 목록
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>찜한 도서</title>
<link rel="stylesheet" href="../css/myfav.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../js/likeFav.js"></script>
</head>
<body>
<div class="container">
	<div class="header">
		<jsp:include page="../mainpage/main_top.jsp" />
	</div>
	<div class="wrapper">
		<h1>찜한 도서</h1>
		<input type="button" onclick="location.href='../mypage/mypage.jsp'" value="돌아가기"> 
		<table>
			<%
			for (LikeFavDTO li : list) {
				BookDTO dto = bookMgr.getfavBook(li.getLike_book_no());
				if (dto != null) {
			%>
			<tr>
			<a href="../bookview/view.jsp?">
				<td><%=li.getLike_no()%></td>
				<td><img src="../image/<%=dto.getThumb_nail()%>" alt="책 이미지"></td>
				<td><%=dto.getTitle()%></td>
				<td><button class="likeBtn" dataLikeNo="<%=li.getLike_no()%>">♥ 찜 취소</button></td>
			</a>
			</tr>
			<%
			} else {
				// dto가 null인 경우 처리할 코드 작성
				// 예: 오류 메시지 출력 또는 대체 이미지/텍스트 출력 등
				continue; //다음 반복으로 넘어가도록 처리
			}
			}
			%>
	</table>
	</div>
	<div class="footer">
		<%@ include file="../mainpage/main_bottom.jsp"%>
	</div>
</div>
</body>
</html>
