<%@page import="java.util.ArrayList"%>
<%@page import="reply.ReplyDto"%>
<%@page import="com.fasterxml.jackson.core.util.RequestPayload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="replyDto" class="reply.ReplyDto"/>

<jsp:useBean id="reportBean" class="report.ReportBean"/>
<jsp:setProperty property="*" name="reportBean"/>

<jsp:useBean id="reportMgr" class="report.ReportMgr" scope="session"/>
<%
String report_reply_no = request.getParameter("report_reply_no");
//동일한 글 중복신고 방지
boolean b = reportMgr.searchReport(reportBean.getReporter_user_id(),report_reply_no);
if(b){ //중복 신고자라면 신고 못하게하기
%>
   <script>
      alert('이미 신고한 글입니다.');
      window.history.go(-2);
   </script>
<%
}else{
	//신고자 ip
	reportBean.setReporter_ip(request.getRemoteAddr());
	//신고 받은 사람 ip
	reportBean.setReported_user_ip(reportMgr.searchReplyedIp(report_reply_no));
	
	//신고받은 사람 id
	String reported_user_no = reportMgr.searchReplyedId(report_reply_no);
	reportBean.setReported_user_no(report_reply_no);
	
	/*auto-increment*/
  	int newNum = reportMgr.currentMaxNum() +1;
   	reportBean.setReport_no(newNum); //새로운 num, DTO에 저장

   //신고하기
   boolean a = reportMgr.insertReport(reportBean);
   if(a){
	      %>
	      <script type="text/javascript">
	         alert('신고되었습니다.');
	         window.location.href = "reportSuccess.jsp?"; // 신고 후 이동할 페이지
	      </script>
	      <%
	  }
}
%>