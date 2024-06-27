<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="replyMgr" class="reply.ReplyMgr"/>    
<jsp:useBean id="dto" class="reply.ReplyDto"/>

<%
String id = (String)session.getAttribute("idkey");
if(id == null){
    response.sendRedirect("../login/loginpage.jsp");
}

int reply_no= Integer.parseInt(request.getParameter("reply_no"));
String reply_book_no= request.getParameter("reply_book_no");
//System.out.println("crno: "+ reply_no);
//System.out.println("crno: "+ reply_book_no);
dto = replyMgr.getCommentData(reply_no);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글쓰기</title>
<script>
function go(){
	if(frm.cont.value== ""){
		confirm("내용을 입력하세요");
		frm.cont.focus();
	}else
		frm.submit();
}
</script>

</head>

<body>
<h2 style="text-align: center;">댓글 쓰기</h2>
<form name="frm" method="post" action="commentsave.jsp?reply_book_no=<%=reply_book_no %>&reply_no=<%=reply_no %>">
<table>
	<tr>
		<td align="left" width="100">댓글제목</td>
		<td><input name="title" style="width:99%"></td>
	</tr>
	<tr>
		<td align="left" width="100">한줄댓글</td>
		<td><textarea name="cont" rows="10" style="width:99%"></textarea></td>
	</tr>
	<tr>
	<td colspan="2" align="center" height="30">
		<input type="button" value="댓글작성" onclick="go()">&nbsp;
		<input type="button" value="목  록"
			 onClick="location.href='../bookview/view.jsp?reply_no=<%=reply_no %>'"></td>
	</tr>
</table>
</form>
</body>
</html>
