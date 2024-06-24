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
	<div id="wrapper">
	<form name="regForm" method="post" action="registerproc.jsp">
		<div id="content">
		
			<div>
				<h3>
				<label for="id">아이디</label>
				</h3>
					<input type="text" id="id" class="box" name="id">
					<span class="error_next_box"></span>
					<button type="button" id="idCheck">
						<span>중복체크</span>
					</button>
			</div>
			<div>
				<h3>
				<label for="pw">비밀번호</label>
				</h3>
					<input type="text" id="pw" class="box" name="pw">
			</div>
			<div>
				<h3>
				<label for="repw">비밀번호 확인</label>
				</h3>
					<input type="text" id="repw" class="box">
			</div>
			<div>
				<h3>
				<label for="uname">이름</label>
				</h3>
					<input type="text" id="uname" class="box" name="uname">
			</div>
			<div>
				<h3>
				<label for="gender">성별</label>
				</h3>
					<span>
					<input type="radio" id="gender" value="1" class="box_gen">
					<label for="1">남성</label>
					</span>
					 <br>
					 <span>
					<input type="radio" id="gender" value="0" class="box_gen">
					<label for="0">여성</label>
					 </span>
			</div>
			<div>
				<h3>
				<label for="email">이메일</label>
				</h3>
					<input type="text" id="email" class="box" name="email">
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
</div>
</body>
</html>