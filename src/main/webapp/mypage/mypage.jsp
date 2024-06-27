<%@page import="user.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="userBean" class="user.UserBean" />
<jsp:useBean id="userMgr" class="user.UserMgr" />

<% 
String id = request.getParameter("idkey");
UserBean bean = userMgr.getUser(id);
%>
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
      <jsp:include page="../mainpage/main_top.jsp" />
   </div>
   <div id="content">
      <div class="btn_area">
         <div class="btn_move">
            <button onclick="location.href='../mypage/myFav.jsp'">
            <span>찜 도서 보기</span>
            </button>
         </div>
         <div class="btn_move">
            <button onclick="location.href='../mypage/myReview.jsp'">
            <span>내 리뷰 보기</span>
            </button>
         </div>
         <div class="btn_move">
            <button onclick="location.href='../mypage/myReply.jsp'">
            <span>내 댓글 보기</span>
            </button>
         </div>
         <div class="btn_move" onclick="location.href='../login/logout.jsp'">
            <button >
            <span>로그 아웃</span>
            </button>
         </div>
      </div>
         <h3>유저 정보</h3>
      <div class="user_data">
         <div class="user_table">
            <div class="row_value">
               <div class="cell_value">
                  <p>ID</p>
               </div>
               <div class="cell_value">
                   <p><%= userBean.getId()%></p>
               </div>
            </div>   
            <div class="row_value">
               <div class="cell_value">
                  <p>비밀번호</p>
               </div>
               <div class="cell_value">
                   <input type="password" name="passwd" value="<%=userBean.getPw()%>">
               </div>
            </div> 
            <div class="row_value">
               <div class="cell_value">
                  <p>이름</p>
               </div>
               <div class="cell_value">
                 <input type="text" name="uname" value="<%= userBean.getUname() %>">
               </div>
            </div>
            <div class="row_value">
               <div class="cell_value">
                  <p>성별</p>
               </div>
               <div class="cell_value">
               		<input type="text" name="gender" value="<%= userBean.getGender() == 1 ? "남성" : "여성" %>">
               </div>
            </div>
            <div class="row_value">
               <div class="cell_value">
                  <p>이메일</p>
               </div>
               <div class="cell_value">
               	<input type="email" name="email" value="<%= userBean.getEmail() %>">
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