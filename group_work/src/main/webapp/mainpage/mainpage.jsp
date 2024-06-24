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
<div class="container" >
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
					<div class="first_img">
					</div>
					<div class="first_text">
						<span onclick="location.href='https://dhlottery.co.kr/gameResult.do?method=byWin'">
						<h3>내일은 나도 백만 유튜버??</h3>
						<h4>청산에 살어리랏다</h4>
						유튜버는 무슨 놀고먹고 싶다 로또 당첨 되고 싶다 불로 소득 최고시다 이말이야
						</span>
					</div>
				</div>
				<div class="review_slide">
					<div class="inner_slide">
						<div class="inner">
							<div class="slide_img">
								
							</div>
							<div class="slide_txt">
								배고프다 배고프다 배고프다 배고프다 배고프다
							</div>
						</div>
						<div class="inner">
							<div class="slide_img">
								
							</div>
							<div class="slide_txt">
								배고프다 배고프다 배고프다 배고프다 배고프다
							</div>
						</div>
						
					</div>
				</div>
				
				<div class="button-list">
				  <button class="button-left">← Left</button>
				  <button class="button-right">Right →</button>
				</div>
			
		</div>
		<div class="event-banner">
			<h2>이벤트</h2>
		</div>
		<div class="rec-book">
			<h2>추천도서</h2>
			<div class="book-first">
			
			</div>
			<div class="book-second">
			
			</div>
			<div class="book-third">
			
			</div>
			<div class="book-fourth">
			
			</div>
		</div>
	</div>
	<div class="footer">	
		<%@ include file="main_bottom.jsp" %>
	</div>

</div>
</body>
</html>