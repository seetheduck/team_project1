<%@page import="javax.tools.DocumentationTool.Location"%>
<%@page import="likeFav.LikeFavMgr"%>
<%@page import="likeFav.LikeFavbean"%>
<%@page import="reply.ReplyDto"%>
<%@page import="book.BookDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<jsp:useBean id="replyMgr" class="reply.ReplyMgr" />
<jsp:useBean id="dto" class="reply.ReplyDto" />
<jsp:useBean id="bookMgr" class="book.BookMgr"/>
<jsp:useBean id="likeFavMgr" class="likeFav.LikeFavMgr"/>

<%
int reply_book_no = Integer.parseInt(request.getParameter("reply_book_no")); //책번호(haha.jpg)

String reply_id= "aa";

String id = (String)session.getAttribute("idkey");


double avgPoint = replyMgr.avgPoint(reply_book_no); //총평점 계산(소수점1자리까지)
int totReply= replyMgr.totReply(reply_book_no);//총리뷰수 계산 메소드 호출 

BookDTO bookdto = bookMgr.getBook(reply_book_no); //해당 번호의 책정보 갖고오기
if(bookdto == null) {
    out.println("<script>alert('해당 책 정보를 찾을 수 없습니다.');</script>");
    return;
}

// --------------찜하기--------------

LikeFavbean bean = likeFavMgr.getFav(reply_book_no); //해당 번호의 책정보 갖고오기
boolean isFav = bean.getLike_fav_is() == 1;

// --------------------------------
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책 상세 정보 출력</title>
<link rel="stylesheet" href="../css/basic.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function() { //찜하기
    let isfav = <%=isFav%>;
    if(isfav){
        $('#favbtn').prop("disabled", false).val("찜 해제");
    }
    <%
    if(id != null){
    %>
    $("#favbtn").click(function(){
        let action = isfav? 'fav' : 'unfav';
        //alert(action);
        $.ajax({
            type : "post",
            url : "../likeFav/favproc.jsp",
            data : {
                reply_book_no : <%=reply_book_no%>,
                id : '<%=id%>',
                action : action
            },
            success : function(response){
                if(action === 'fav'){
                    alert("찜하기 완료");
                    $(".zbtn").prop("disabled", false).val("찜하기");
                } else {
                    alert("찜이 취소되었습니다.");
                    $(".zbtn").prop("disabled", false).val("찜해제");
                }
                isfav = !isfav;
            },
            error: function(xhr, status, error) {
                alert("에러 발생 : " + status);
                console.log(error);
            }
        });
    });
    <%
    }else if(id == null){
    	%>
    	$("#favbtn").click(function(){
    		alert("로그인하세요;;");
    	});
    	<%
    }
    %>
});


</script>
</head>
<body>
	<div class="wrapper">
   <h2>책 상세 정보 출력</h2>
   
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <!-- 책정보 start -->
  <div class="book">
	<img src="../image/book.jpg">
	<!--///////// 찜하기 버튼 -->

		<button class="zbtn" type="button" id="favbtn">찜</button>

	<!-- /////// 찜하기 버튼 -->
</div> 

<p>
	<br>
	책 제목: <%=bookdto.getTitle()%>
	<br>
	저자: <%=bookdto.getAuthor()%>
	<br>
	출판사: <%=bookdto.getPublisher()%>
	<br>
	발행년도: <%=bookdto.getPyear()%>
	<br>
	<br>
	평균 평점: <%= avgPoint %> / 총 리뷰수: <%=totReply %>
</p>
<div>
<button class="reply" type="button" 
onclick="location.href='../reply/replyinsert.jsp?reply_book_no=<%=reply_book_no%>'">리뷰작성</button>
<button onclick="location.href='../mainpage/mainpage.jsp'">메인페이지</button>
</div>  
<!-- 책정보 end -->
   
   <!-- 리뷰 start -->
   <form action="replydetails.jsp" name="detailForm">
   <table style="width: 80%" class="review-tr">
      <tr style="background-color: beige;">
         <th>번호</th>
         <th>제목</th>
         <th>작성자id</th>
         <th>작성일</th>
         <th>좋아요 수</th>
      </tr>
      <%
      
      //책 상세페이지보여줄때 url에 책 넘버가 적히게 된다. url접근 방법제한두기
      int bno = 0;
      try {
    	  bno = Integer.parseInt(request.getParameter("reply_book_no"));
    	  //System.out.print("reply_book_no : " + request.getParameter("reply_book_no"));
      } catch (Exception e) {
    	  bno = 1;
      }
      if (bno<= 0)
    	  bno= 1;
	
      ArrayList<ReplyDto> list = replyMgr.getDataAll(reply_book_no);
      for (int i = 0; i < list.size(); i++) {
         dto = (ReplyDto) list.get(i);
      %>
      <tr style="text-align: center;">
         <td><%=dto.getReply_no()%></td>
         <td><a href="../reply/replydetails.jsp?reply_no=<%=dto.getReply_no() %>&reply_book_no=<%=reply_book_no%>">
				<%=dto.getReply_title()%>
			</a>
		</td>
					<!-- bookdto.getBnum() -->
         <td><%=dto.getReply_id()%></td>
         <td><%=dto.getReply_create_date()%></td>
         <td><%=dto.getReply_like_cnt()%></td>
      </tr>
      <%
      }
      %>
   </table>
   </form>
 	<div class="footer">	
		<%@ include file="../mainpage/main_bottom.jsp" %>
	</div>
</div> 
</body>
</html>