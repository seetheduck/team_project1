<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="userMgr" class="user.UserMgr"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>
<link rel="stylesheet" href="../css/mypage.css">

</head>
<body>
<div id="container">
	<div class="header">
		<%@ include file="../mainpage/main_top.jsp" %>
	</div>
	<div class="content">
		<div class="btn_area">
			<div class="btn_move">
				<button >
				<span>찜 도서 보기</span>
				</button>
			</div>
			<div class="btn_move">
				<button>
				<span>내 리뷰 보기</span>
				</button>
			</div>
			<div class="btn_move">
				<button >
				<span>내 댓글 보기</span>
				</button>
			</div>
			<div class="btn_move" onclick="location.href='../login/logout.jsp'">
				<button >
				<span>로그 아웃</span>
				</button>
			</div>
		</div>
		<div class="user_data">
			<h3>유저 정보</h3>
			<div class="user_table">
				<div class="row_value">
					<div class="cell_value">
						<p>ID</p>
					</div>
					<div class="cell_value">
					</div>
				</div>	
				<div class="row_value">
					<div class="cell_value">
						<p>이름</p>
					</div>
					<div class="cell_value">
					</div>
				</div>
				<div class="row_value">
					<div class="cell_value">
						<p>성별</p>
					</div>
					<div class="cell_value">
					</div>
				</div>
				<div class="row_value">
					<div class="cell_value">
						<p>이메일</p>
					</div>
					<div class="cell_value">
					</div>
				</div>
			</div>
			</div>
		</div>
	<div class="footer">
		<%@ include file="../mainpage/main_bottom.jsp" %>	
	</div>	
	</div>
</div>
</body>
</html>