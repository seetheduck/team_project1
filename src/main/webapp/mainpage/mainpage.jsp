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
				<%-- 메인 추천리뷰 --%>
				<div class="review-first" onclick="location.href='../bookview/view.jsp?reply_book_no=12'">
					<div class="first_img">
					</div>
					<div class="first_text">
						<span onclick="location.href='../bookview/view.jsp?reply_book_no=7'">
						<h3>내일은 나도 백만 유튜버??</h3>
						<h4>청산에 살어리랏다</h4>
						유튜브를 자주보면서 유튜브에 대한 관심이 커졌는데...[더보기]
						</span>
					</div>
				</div>
				<%-- 슬라이드 추천리뷰 --%>
				 <div class="review_slide">
		        	<div class="inner_slide">
		            	<div class="inner">
			                <div class="slide_img1" onclick="location.href='../bookview/view.jsp?reply_book_no=5'"></div>
			                <div class="slide_txt1" onclick="location.href='../bookview/view.jsp?reply_book_no=5'">중학교까지 반년이 남았는데..[더보기]</div>
		            	</div>
		            <div class="inner">
		                <div class="slide_img2" onclick="location.href='../bookview/view.jsp?reply_book_no=233'"></div>
		                <div class="slide_txt2" onclick="location.href='../bookview/view.jsp?reply_book_no=233'">지금도 중동에서 전쟁이 벌어지고 있는데..[더보기]</div>
		            </div>
		            <div class="inner">
		                <div class="slide_img3" onclick="location.href='../bookview/view.jsp?reply_book_no=433'"></div>
		                <div class="slide_txt3" onclick="location.href='../bookview/view.jsp?reply_book_no=433'">긍정적인 사람이 되고싶어서 읽어보았습니다...[더보기]</div>
		            </div>
		        </div>
  				 </div>
  				<script src="../js/mainpage.js"></script>
		</div>
		<div class="event-banner">
			<h2>이달의 이벤트</h2>
			<img alt="" src="../image/event-banner.png">
		</div>
		<div class="rec-book">
			<h2>추천도서</h2>
			<div class="book-first" onclick="location.href='../bookview/view.jsp?reply_book_no=753'">
			</div>
			<div class="book-second" onclick="location.href='../bookview/view.jsp?reply_book_no=643'">
			</div>
			<div class="book-third" onclick="location.href='../bookview/view.jsp?reply_book_no=583'">
			</div>
			<div class="book-fourth" onclick="location.href='../bookview/view.jsp?reply_book_no=1223'">
			</div>
		</div>
	</div>
	<div class="footer">	
		<%@ include file="main_bottom.jsp" %>
	</div>

</div>
</body>
</html>