<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin page(user)</title>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script src="../js/userJson.js"></script>
<style>
/* 모달 스타일링 */
.modal {
  display: none;
  position: fixed;
  z-index: 1;
  padding-top: 60px;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgb(0,0,0);
  background-color: rgba(0,0,0,0.4);
}

.modal-content {
  background-color: #fefefe;
  margin: 5% auto;
  padding: 20px;
  border: 1px solid #888;
  width: 80%;
}

.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}
</style>
</head>
<body>
<div class="container">
<%@ include file="../admin_main/adminSidebar.jsp" %>
<main class="main-content">
    <h2>유저 관리</h2>
    <br><br>
    <li id="test4">회원 목록 가져오기</li>
    <br><br>
    결과 출력 :
    <div id="disp"></div>
    <div id="userDetail"></div> <!-- 상세 정보 출력 -->

    <!-- 모달 -->
    <div id="myModal" class="modal">
      <div class="modal-content">
        <span class="close">&times;</span>
        <div id="modal-body"></div>
      </div>
    </div>
</main>
<%@ include file="../admin_main/admin_foot.jsp" %>      
</div>
</body>
</html>
