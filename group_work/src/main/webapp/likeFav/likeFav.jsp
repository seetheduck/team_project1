<%@page import="likefav.LikeFavDTO"%>
<%@page import="book.BookDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="likeFavMgr" class="likefav.LikeFavMgr"/>
<jsp:useBean id="bookMgr" class="book.BookMgr"/>
<%
//String id = (String)session.getAttribute("id"); // 세션유지?
String id="aa";
ArrayList<LikeFavDTO> list = likeFavMgr.getLikeBookAll(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>찜한 도서</title>
<script type="text/javascript">
function isfav(no) {
	document.frm.like_no.value = no;
	document.frm.submit();
}
</script>
</head>
<body>
<h1>*찜한 도서*</h1>
	<table border="1">
		<%
		for(LikeFavDTO li:list){
			BookDTO dto = bookMgr.getfavBook(li.getLike_book_no()); //해당 도서의 책 썸네일.. 
			%>
			<tr>
				<td><%=li.getLike_no() %></td>
				<!-- <td>< %=dto.getThumb_nail()%></td> -->
				<td><img src="../img/<%=dto.getThumb_nail()%>" alt="책 이미지"></td>
				<td><%=dto.getTitle() %></td><!-- 책 이름 -->
				<td><input type="button" name ="hart" value=" ♥ " onclick="isfav('<%=li.getLike_no()%>')"></td><!-- 좋아요 취소 -->
			</tr>
			</table>
			<form action="likeFavproc.jsp?like_no=<%=li.getLike_no()%>" name="frm">
				<input type="hidden" name="like_no">
			</form>
			<%
		}
		%>
</body>
</html>