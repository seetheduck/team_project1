<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("utf-8"); //먼저 주고 받기
%>
<jsp:useBean id="userbean" class="pack.user.UserBean"/>
<jsp:setProperty property="*" name="userbean"/>
<jsp:useBean id="userMgr" class="pack.user.UserMgr" />

<%
//회원가입 여부 확인
int newNo = userMgr.currentMaxNo() + 1;
//System.out.println("newNo : " + newNo);
userbean.setNo(newNo);

boolean b = userMgr.userInsert(userbean);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
if(b){
	out.println("<b>회원가입이 완료되었습니다</b><br>");
	out.println("<a href='loginpage.jsp'>회원 로그인</a><br>");
}else{
	out.println("<b>회원가입 실패</b><br>");
	out.println("<a href='register.jsp'>재시도?</a><br>");
}
%>
</body>
</html>