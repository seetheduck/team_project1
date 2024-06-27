<%@page import="reply.ReplyDto"%>
<%@page import="reply.ReplyMgr"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int reply_no = Integer.parseInt(request.getParameter("reply_no"));

//리뷰 좋아요 업데이트 메소드 호출
ReplyMgr replyMgr = new ReplyMgr();
//boolean success = replyMgr.replyLikecnt(reply_no);
/*
if(success){
	//좋아요 수가 업데이트 된 데이터 다시 갖고오기
	ReplyDto rdto = replyMgr.getData(reply_no);
    int likeCount = rdto.getReply_like_cnt();
    
    out.print(likeCount);
}else{
	out.print("0");
}
 
*/
%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>