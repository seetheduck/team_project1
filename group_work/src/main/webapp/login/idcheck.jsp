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
<title>Id 검색</title>
<link href="../css/registerpage.css" rel="stylesheet" type="text/css">
<script src="../js/script.js"></script>
<script type="text/javascript">
window.onload = () => {
	document.querySelector("#btn_close").onclick = function() {
		window.close();
	};
}

</script>
</head>
<body>
<% 
if(b){
%>
	이미 사용 중인 아이디 입니다 <p/>
	<div>
		<button type="button" id="btn_close">확인</button>
	</div>
<% }else{%>
	사용 가능합니다 <p/>
	<div>
		<button type="button" id="btn_close">확인</button>
	</div>
<% 
}	
%>	
</body>
</html>