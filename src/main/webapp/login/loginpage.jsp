<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% 
 String id = (String)session.getAttribute("idkey");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link href="../css/registerpage.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/register.js" ></script>

<script type="text/javascript">
window.onload = () => {
	document.querySelector("#btnlogin").onclick = funcLogin;
	document.querySelector("#btnregister").onclick = funcRegister;
}
</script>
</head>
<body>
<div class="login">
<div id="header">
		<img alt="" src="../image/logo_ver1.png" onclick="location.href='../mainpage/mainpage.jsp'">
</div>
<div class="login">
	<div id="wrapper">
		<div id="content_login"> <%-- 내용 --%>
			<% 
			if(id != null){
			%>
			<script type="text/javascript">
				location.href="../mainpage/mainpage.jsp";
			</script>	
			<% 
			}else{ 
			%>
			<form name="loginForm">
			<div>
					<input type="text" id="id" class="box" placeholder="아이디" name="id">
			</div>
			<br>
			<div>
					<input type="text" id="pw" class="box" placeholder="비밀번호" name="pw">
			</div>
			<%-- 로그인 버튼 --%>
			<div class="btn_area">
				<button type="button" id="btnlogin" class="btnpush">
					<span>로그인</span>
				</button>
				<button type="button" id="btnregister" class="btnpush">
					<span>회원가입</span>
				</button>
			 </div>
			</form>
		</div>
	</div>
</div>
	<div class="footer">
		<%@ include file="login_bottom.jsp" %>
	</div>
</div>
</form>
<% 
}
%>	
</body>
</html>