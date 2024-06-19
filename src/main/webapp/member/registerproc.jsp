<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("utf-8"); //먼저 주고 받기
%>
<jsp:useBean id="mbean" class="pack.member.MemberBean"/>
<jsp:setProperty property="*" name="mbean"/>
<jsp:useBean id="memberMgr" class="pack.member.MemberMgr" />

<%
//회원가입 여부 확인
boolean b = memberMgr.memberInsert(mbean);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>