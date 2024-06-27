<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>상세 검색 페이지</title>
<jsp:useBean id="searchBookFilterDB" class="search.searchBookFilterDB" />
<link rel="stylesheet" type="text/css" href="../css/searchFilterResult.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../js/searchFilterBook.js"></script>
</head>

<body>
   <div class="container">
        <h1>상세 검색</h1>
        <hr>
 <form class="search-form" id="searchForm">
 			<label for="bnum">번호:</label> <input type="text" id="bnum" name="bnum">
            <label for="name">제목:</label> <input type="text" id="title" name="title">
            <label for="author">작가:</label> <input type="text" id="author" name="author">
            <label for="publisher">출판사:</label> <input type="text" id="publisher" name="publisher">
            <label for="issueDate">출판일:</label> <input type="text" id="pyear" name="pyear">
            
            <br>
            <button type="button" onclick="performSearch()">검색하기</button>
            <br>
            <br>
        </form>
          <div class="results" id="results">
            <!-- 검색 결과가 여기에 표시됩니다. -->
        </div>
           </div>
    
</body>
</html>
