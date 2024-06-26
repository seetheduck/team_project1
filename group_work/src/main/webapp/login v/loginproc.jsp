<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="userMgr" class="user.UserMgr" />   
<% 
request.setCharacterEncoding("utf-8");

String id = request.getParameter("id");
String pw = request.getParameter("pw");

boolean b = userMgr.loginCheck(id, pw);

if(b){
	session.setAttribute("idkey", id);
	response.sendRedirect("loginpage.jsp");
}else{
	response.sendRedirect("logfail.jsp");
}
%>