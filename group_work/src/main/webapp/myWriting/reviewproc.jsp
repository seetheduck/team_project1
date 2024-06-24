<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="replyMgr" class="myReply.ReplyMgr"/>
<%
String flag = request.getParameter("flag");
int no = Integer.parseInt(request.getParameter("reply_no"));
boolean result = false;
if(flag.equals("delete")){
	%>
	<script>
	if(confirm('삭제하시겠습니까?')){
		alert(no);
		</script>
		<%
		result = replyMgr.deleteRewiew(no);
		%>
		<script>
		alert('삭제되었습니다.');
		location.href="myReflyShow.jsp";
	}
	</script>
	<%
}
%>