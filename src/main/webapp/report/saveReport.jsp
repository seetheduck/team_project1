<%@page import="com.fasterxml.jackson.core.util.RequestPayload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="replyBean" class="myReply.ReplyBean"/>
<jsp:useBean id="reportBean" class="report.ReportBean"/>
<jsp:setProperty property="*" name="reportBean"/>
<jsp:useBean id="reportMgr" class="report.ReportMgr" scope="session"/>
<%
String id = request.getParameter("id"); //어떻게 넘어오는지 모르겠다.
String reply_no = request.getParameter("reply_no"); //받아와야하는 값
String cbRep = request.getParameter("cbRep");
boolean b = reportMgr.searchReport(id,reply_no); //중복 아이디 확인 (중복 신고 불가)
if(b){ //중복 신고자라면 신고 못하게하기
%>
	<script>
		alert('이미 신고한 글입니다.');
		return;
	</script>
<%
			
}else{
	//넘어온 신고코드
	reportBean.setReport_code(Integer.parseInt(cbRep));
	
	//상세페이지에서 넘어온 값(hidden)
	reportBean.setReport_reply_no(Integer.parseInt(reply_no));
	reportBean.setReporter_user_id(request.getParameter("reporter_user_id"));
	
	reportBean.setReported_user_ip(replyBean.getReply_ip());
	reportBean.setReported_user_no(request.getParameter("reply_no"));
	//신고자 ip
	reportBean.setReporter_ip(request.getRemoteAddr());

	/*auto-increment*/
	int newNum = reportMgr.currentMaxNum() +1;
	reportBean.setReport_no(newNum); //새로운 num DTO에 저장

	boolean a = reportMgr.insertReport(reportBean);

	if(a){
		%>
		<script type="text/javascript">
			alert('신고되었습니다.');
			//window.location.href = "../book/bookie.jsp"; // 신고 후 이동할 페이지
		</script>
		<%
		//response.sendRedirect("관리자 페이지");
	}
}
%>
