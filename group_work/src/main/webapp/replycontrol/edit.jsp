<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="replyMgr" class="reply.ReplyMgr"/>
<jsp:useBean id="rdto" class="reply.ReplyDto"/>
<%
int reply_no= Integer.parseInt(request.getParameter("reply_no"));
String reply_book_no= request.getParameter("reply_book_no");
String reply_id = request.getParameter("reply_id");

//System.out.println("edit rno: "+ reply_no);
//System.out.println("edit rbno : "+ reply_book_no);
//System.out.println("edit id: "+ reply_id);

//수정할 자료 읽기
rdto= replyMgr.getData(reply_no);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정하기</title>
<link rel="stylesheet" type="text/css" href="../css/star_rating.css">
<script type="text/javascript">
window.onload =() =>{
	document.querySelector("#btnUpOk").onclick = function(){
	if(confirm("정말 수정하시겠습니까?"))
		frm.submit();
	}
}


</script>
</head>
<body>
<h2 style="text-align: center;">리뷰수정하기</h2>
<form name="frm" method="post" action="editsave.jsp?reply_book_no=<%=reply_book_no %>&reply_no=<%=reply_no %>" enctype="multipart/form-data">
<h3> 리뷰 수정하기 </h3>
책 평점주기
<br>
<div class ="star_rating">
  	<input type="radio" class="star" name="reply_point" value="1">
    <input type="radio" class="star" name="reply_point" value="2">
    <input type="radio" class="star" name="reply_point" value="3">
    <input type="radio" class="star" name="reply_point" value="4">
    <input type="radio" class="star" name="reply_point" value="5">
</div>
<br>
리뷰 제목<br>
<input type="text" name="reply_title" value="<%=rdto.getReply_title() %>">
<br><br>
리뷰 내용<br>
 <textarea rows="10" name="reply_cont"><%=rdto.getReply_cont() %></textarea> 
<br><br>
인증사진 : <input type="file" name="image" size="30" value="<%=rdto.getReply_image()%>"><br>

<input type="button" value="수정 완료" id="btnUpOk">&emsp;
<input type="button" value="목록 보기" onclick="location.href='../bookview/view.jsp?reply_book_no=12'">
</form>
</body>
</html>