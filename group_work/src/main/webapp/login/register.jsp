<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<link href="../css/registerpage.css" rel="stylesheet" type="text/css">
<script src="../js/register.js"></script>

<script type="text/javascript">
window.onload = function() {
	regForm.id.focus();
	document.getElementById("idCheck").onclick = idCheck;
	document.getElementById("btnSubmit").onclick = inputCheck;
}
</script>
</head>
<body>
<div id="header">
<img alt="" src="../image/logo ver1.png" onclick="location.href='../mainpage/mainpage.jsp'">
</div>
	<div id="wrapper">
	<form name="regForm" method="post" action="registerproc.jsp">
		<div id="content">
			<div>
				<h3>
				<label for="id">아이디</label>
				</h3>
					<input type="text" id="id" class="box" name="id">
					<span class="error_next_box"></span>
					<button type="button" id="idCheck" class="btnpush">
						<span>중복체크</span>
					</button>
			</div>
			<div>
				<h3>
				<label for="pw">비밀번호</label>
				</h3>
					<input type="text" id="pw" class="box" name="pw">
					<span class="error_next_box"></span>
			</div>
			<div>
				<h3>
				<label for="repw">비밀번호 확인</label>
				</h3>
					<input type="text" id="repw" class="box">
					<span class="error_next_box"></span>
			</div>
			<div>
				<h3>
				<label for="uname">이름</label>
				</h3>
					<input type="text" id="uname" class="box" name="uname">
					<span class="error_next_box"></span>
			</div>
			<div>
				<h3>
				<label for="gender">성별</label>
				</h3>
					<label for="gender_m">
					<input type="radio" id="gender_m" value="1" name="gender">
					<span>남성</span>
					</label>
					 <br>
					<label for="gender_w"> 
					<input type="radio" id="gender_w" value="0" name="gender">
					<span>여성</span>
					</label>
			</div>
			<div>
				<h3>
				<label for="email">이메일</label>
				</h3>
					<input type="text" id="email" class="box" name="email">
					<span class="error_next_box"></span>
			</div>
			<%-- 회원가입 버튼 --%>
			<div class="btn_area">
				<button type="button" id="btnSubmit" >
					<span>가입하기</span>
				</button>
			 </div>
		</div>
	</form>
	</div>
</body>
</html>