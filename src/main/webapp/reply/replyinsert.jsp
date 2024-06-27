<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <jsp:useBean id="dto" class="reply.ReplyDto"/>
<%
int reply_book_no = Integer.parseInt(request.getParameter("reply_book_no"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰작성</title>
<link rel="stylesheet" type="text/css" href="../css/star_rating.css">
<script>
function check(){
	if(frm.reply_point.value==""){
		confirm("평점을 입력해주세요");
		frm.reply_point.focus();
	    return false;
	}else if(frm.reply_title.value==""){
	      confirm("리뷰제목을 작성해주세요");
	      frm.reply_cont.focus();
	      return false;
	}else if(frm.reply_cont.value==""){
      confirm("리뷰내용을 작성해주세요");
      frm.reply_cont.focus();
      return false;
	}else frm.submit();
}

</script>
</head>
<body>
<form name="frm" method="post" action="replysave.jsp?reply_book_no=<%=reply_book_no %>" enctype="multipart/form-data">
<h2>리뷰 작성하기</h2>
책 평점주기
<br>
<div class ="star_rating">
     <input type="radio" class="star" name="reply_point" value="1">
    <input type="radio" class="star" name="reply_point" value="2">
    <input type="radio" class="star" name="reply_point" value="3">
    <input type="radio" class="star" name="reply_point" value="4">
    <input type="radio" class="star" name="reply_point" value="5">
    <!-- 라디오버튼5개 별모양으로. 마우스오버로 빈별>색칠된별 전환. 클릭시 고정. -->
</div>
<br>
리뷰 제목<br>
<input type="text" name="reply_title">
<br><br>
리뷰 내용<br>
 <textarea rows="10" name="reply_cont" placeholder="리뷰 내용을 작성해주세요." ></textarea> 
<br><br>
인증사진 : <input type="file" name="image"><br>
<br><br>
<input type="button" class="btnOk" value="리뷰등록" onClick="check()"/>
<input type="button"  value="목록으로"  onClick="location.href='../bookview/view.jsp?reply_book_no=<%=reply_book_no%>'"/>
</form>
</body>
</html>