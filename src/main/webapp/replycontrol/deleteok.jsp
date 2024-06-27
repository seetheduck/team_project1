<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="bean" class="reply.ReplyBean"/>
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="replyMgr" class="reply.ReplyMgr"/>

<%
int reply_no= Integer.parseInt(request.getParameter("reply_no"));
String reply_book_no= request.getParameter("reply_book_no");
System.out.println("delok: "+reply_no);
System.out.println("delok: "+reply_book_no);
bean.setReply_no(reply_no);
System.out.println("delok : " + bean.getReply_no()); 


// 해당 리뷰는 보이지 않게 처리하기(영구삭제x)
replyMgr.delReply(bean);
System.out.println("delreply 처리했니..");

response.sendRedirect("../bookview/view.jsp?reply_book_no=" + reply_book_no);
%>    
