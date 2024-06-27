<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/mainpage.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
	integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<!-- 이 부분도 변경 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../js/searchBook.js"></script>
<script src="../js/voiceRec.js"></script>

</head>
<body>
	<div class="head_wrapper">
		<div class="header_menu"></div>
		<div class="header_logo" onclick="location.href='../mainpage/mainpage.jsp'">
		<img class="logo_img" src="../image/logo_ver1.png">
		</div>
		<div class="header_search">
			<!-- 여기 변경했어용 -->
			<form class="search_box" id="searchForm" action="searchResult" method="get">
			<%--class="search_txt"는 voiceRec 식별자용 --%>
				<input type="text" id="searchInput" class="search_txt" placeholder=" 책 제목 입력"><br>
				<div class="record_btn">
					 <button type="button" onclick="startRecord()" class="btn_record">
						<i class="fa-regular fa-circle-dot"></i>
					</button>
					<button type="button" onclick="endRecord()" class="btn_recordstop">
						<i class="fa-regular fa-circle-stop"></i>
					</button>
					<button class="search_btn" type="button" onclick="performSearch()">
						<i class="fa-solid fa-magnifying-glass"></i>
					</button>
				</div>
	
				<div class="results" id="results">
						<!-- 검색 결과가 여기에 표시됩니다. -->
					</div>
				<button class="search_detailbtn" type="button"
					onclick="location.href='../search/searchFilterResult.jsp'">상세검색</button>
			</form>
		</div>
		<div class="header_login">
			<%
			String id = (String)session.getAttribute("idkey");
			
			String log = "";

			if (id == null) {
			%>
			<form action="../login/loginpage.jsp" method="post">
				<button class="login_btn" type="submit">로그인</button>
			</form>
			<%
			} else {
			%>
			<form action="../mypage/mypage.jsp" method="post">
				<button class="login_btn" type="submit">마이페이지</button>
			</form>
			<%
			}
			%>
		</div>

	</div>
</body>
</html>