<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="replyMgr" class="reply.ReplyMgr"/>
<%
String flag = request.getParameter("flag");
int no = Integer.parseInt(request.getParameter("reply_no"));
boolean result = false;
if(flag.equals("delete")){
	%>
	<script>
	if(confirm('삭제하시겠습니까?')){
		<%
		result = replyMgr.deleteReply(no);
		%>
		alert('삭제되었습니다.');
		location.href="myReply.jsp";
	}
	</script>
	<%
}
%>