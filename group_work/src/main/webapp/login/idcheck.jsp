<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="userMgr" class="user.UserMgr"/>

<% 
String id = request.getParameter("id");
boolean b = userMgr.idCheckProcess(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Id 검사</title>
<link href="../css/registerpage.css" rel="stylesheet" type="text/css">
<script src="../js/register.js"></script>	
</head>
<body style="text-align: center;margin-top: 30px">
<b><%=id%></b>
<% 
if(b){
%>
	이미 사용 중인 아이디 입니다 <p/>
	<div>
		<button type="button" id="btn_close" onclick="opener.document.regForm.id.focus();window.close()">확인</button>
	</div>
<% }else{%>
	사용 가능합니다 <p/>
	<div>
		<button type="button" id="btn_close" onclick="opener.document.regForm.pw.focus(); window.close()">확인</button>
	</div>
<% 
}	
%>	
</body>
</html>