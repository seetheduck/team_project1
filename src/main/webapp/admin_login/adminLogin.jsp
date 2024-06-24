<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인</title>
<script src="../js/adminLogin.js"></script>
<script type="text/javascript">
window.onload = () => {
	document.getElementById("btnAdminLogin").onclick=funcAdLogin;
	
}

</script>
</head>
<body>
<form action="adminLoginProc.jsp" name="adminLoginform" method="post">
<table>
	<tr>
		<td	colspan="2" style="text-align: center;">
			<br>admin login<br>
		</td>
	</tr>
	<tr>
		<td>관리자 id : </td>
		<td><input type="text" name="id"></td>
	</tr>
	<tr>
		<td>관리자 pw : </td>
		<td><input type="password" name="pw"></td>
	</tr>
	<tr>
		<td colspan="2" style="text-align: center;">
			<input type="button" value="로그인" id="btnAdminLogin">
			<input type="button" value="메인페이지" onclick="location.href='../login/login.jsp'">
		</td>
	</tr>
</table>
</form>
</body>
</html>