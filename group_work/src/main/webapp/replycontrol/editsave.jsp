<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8");%>  
<jsp:useBean id="bean" class="reply.ReplyBean"/>
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="replyMgr" class="reply.ReplyMgr"/>
  

<%
int reply_no = Integer.parseInt(request.getParameter("reply_no"));
String reply_book_no = request.getParameter("reply_book_no");
//System.out.println("e2b:" + reply_book_no); 
//System.out.println("e2:" + reply_no); 
bean.setReply_no(reply_no);
//System.out.println("e2rbnobean : " + bean.getReply_no()); 


//수정된 리뷰 저장하기
replyMgr.updateReply(request, bean);

response.sendRedirect("../bookview/view.jsp?reply_book_no="+ reply_book_no);
%>


