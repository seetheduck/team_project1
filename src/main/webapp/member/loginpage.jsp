<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link href="../css/registerpage.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="login.jsp" method="post">
<div id="header">
	<div id="wrapper">
		<div id="content_login"> <%-- 내용 --%>
			<div>
					<input type="text" id="id" class="box" placeholder="아이디">
			</div>
			<div>
					<input type="text" id="pw" class="box" placeholder="비밀번호">
			</div>
			<%-- 로그인 버튼 --%>
			<div class="btn_area">
				<button type="button" id="btn">
					<span>로그인</span>
				</button>
			 </div>
		</div>
	</div>
</div>
</form>
</body>
</html>