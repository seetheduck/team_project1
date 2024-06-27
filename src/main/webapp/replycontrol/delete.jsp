<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String reply_no= request.getParameter("reply_no");
String reply_book_no= request.getParameter("reply_book_no");
//System.out.println("del: "+reply_no);
//System.out.println("del: "+reply_book_no);
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<script type="text/javascript">
function check(){
	
	if(confirm("정말 삭제할까요?"))
		frm.submit();
	
}
</script>
</head>
<body>
글 삭제하기
<br>
<form action="deleteok.jsp" method="post" name="frm">
	<input type="hidden" name="reply_no" value="<%=reply_no%>">
	<input type="hidden" name="reply_book_no" value="<%=reply_book_no%>">
	<input type="button" onclick="check()" value="삭제 확인">
	<input type="button" onclick="location.href='../bookview/view.jsp?reply_book_no=12'" value="목록 보기">
</form>
</body>
</html>