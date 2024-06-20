<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="replyMgr" class="myReply.ReplyMgr"/>
<%
String flag = request.getParameter("flag");
int no = Integer.parseInt(request.getParameter("reply_no"));
boolean result = replyMgr.deleteReply(no);
if(result){
	%>
	<script>
		alert('삭제되었습니다.');
		location.href="myReflyShow.jsp";
	</script>
	<%
	
}
%>