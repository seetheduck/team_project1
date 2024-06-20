<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/mainpage.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<div class="head_wrapper">
	<div class="header_menu">
	햄버거 아이콘
	</div>
	<div class="header_logo">
	로고가 들어갈 자리
	</div>
	<div class="header_search">
		<form class="search_box" action="" method="get">
			<input class="search_txt" type="text">
			<button class="search_btn" type="submit">
				<i class="fa-solid fa-magnifying-glass"></i>
			</button>
			<button class="search_detailbtn" type="submit">
			 상세검색
			</button>
		</form>
	</div>
	<div class="header_login">
	<form action="../login/loginpage.jsp" method="post">
	<button class="login_btn" type="submit">
	로그인		
	</button>
	</form>
	</div>
	
</div>
</body>
</html>