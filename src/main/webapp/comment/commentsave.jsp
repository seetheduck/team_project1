<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8");%> 
<jsp:useBean id="bean" class="reply.ReplyBean" scope="request"/>
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="replyMgr" class="reply.ReplyMgr"/>

<%
//String id = (String)session.getAttribute("idkey");
String id = "bb";

int reply_book_no = Integer.parseInt(request.getParameter("reply_book_no"));
int reply_no= Integer.parseInt(request.getParameter("reply_no"));

bean.setReply_book_no(reply_book_no);
bean.setReply_gnum(reply_no);
bean.setReply_ip(request.getRemoteAddr());
bean.setReply_no(replyMgr.currentMaxNo()+1);

bean.setReply_title(request.getParameter("title")); // 제목 설정
bean.setReply_cont(request.getParameter("cont"));   // 내용 설정

replyMgr.replySave(bean, id);

//response.sendRedirect("../reply/replydetails.jsp?reply_book_no="+reply_book_no); 
response.sendRedirect("../bookview/view.jsp?reply_book_no="+reply_book_no);

%>
