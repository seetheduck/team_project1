<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<link rel="stylesheet" href="../css/mainpage.css">
</head>
<body>
<div id="container" >
	<div class="header">
		<%@ include file="main_top.jsp" %>
		
	</div>
	<div class="content">
		<div class="top-content">
			<h3> 서비스 소개 </h3>
			
			<br>
				‘어릴적 먹었던 쿠키처럼 즐거운 기억을 주는 독서리뷰’ 라는 테마에  <br>
				 맞춰 아이들이 독서에 대해 즐거운 기억을  가질 수 있도록 <br>
				 리뷰 챌린지와 독서 모임을 가질 수 있도록 지원합니다.
		</div>
		<div class="rec-review">
			<h2>이달의 리뷰</h2>
				<div class="review-first">
				리뷰 1
				</div>
				<div class="review-second">
				리뷰 2
				</div>
				<div class="review-third">
				리뷰 3
				</div>
			
		</div>
		<div class="event-banner">
			<h2>이벤트</h2>
		</div>
		<div class="rec-book">
			<h2>추천도서</h2>
			<div class="book-first">
			책1
			</div>
			<div class="book-second">
			책2
			</div>
			<div class="book-third">
			책3
			</div>
			<div class="book-fourth">
			책4
			</div>
		</div>
	</div>
	<div class="footer">	
		<%@ include file="main_bottom.jsp" %>
	</div>

</div>
</body>
</html>