<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="reply.ReplyBean"/>
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="replyMgr" class="reply.ReplyMgr"/>
<%request.setCharacterEncoding("utf-8");%>

<%
//String id = (String)session.getAttribute("idkey");
String id = "aa";
int reply_book_no = Integer.parseInt(request.getParameter("reply_book_no"));
//System.out.print(reply_book_no); // 12233485
bean.setReply_book_no(reply_book_no);
//System.out.println("Reply book no : " + bean.getReply_book_no()); 
bean.setReply_ip(request.getRemoteAddr()); //클라이언트의 ipaddress 
//System.out.println("Reply Reply_ip: " + bean.getReply_ip()); 
//replyMgr.saveData(bean);

//리뷰수 번호붙이기
int newNo= replyMgr.currentMaxNo()+1; 
//bean.setReply_no(newNo);
//bean.setReply_gnum(newNo);
//System.out.println("newNo : " + newNo);

//리뷰 출력
replyMgr.insertReply(request, newNo, bean, id); 


response.sendRedirect("../bookview/view.jsp?reply_book_no="+reply_book_no); 
%>