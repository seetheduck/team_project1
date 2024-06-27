<%@page import="java.util.ArrayList"%>
<%@page import="reply.ReplyDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="replyMgr" class="reply.ReplyMgr"/>
<jsp:useBean id="replydto" class="reply.ReplyDto"/>
<%
int reply_no= Integer.parseInt(request.getParameter("reply_no"));
int reply_book_no = Integer.parseInt(request.getParameter("reply_book_no"));
String reply_id = request.getParameter("reply_id");

String id = (String)session.getAttribute("idkey");

// 해당 리뷰번호의 정보 가지고 오기 
ReplyDto rdto = replyMgr.getData(reply_no);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 상세보기</title>
<link rel="stylesheet" href="../css/basic.css">
<script src="../js/reply.js"></script>
</head>
<body>
리뷰 상세보기
<table>
<tr>
    <td colspan="3" style="text-align: right;">
        <input type="button" id="btnLik" value="좋아요" onclick="likeReply();"/>
        <input type="button" class="btnRep" value="신고" onclick="location.href='../report/report.jsp?reply_no=<%=reply_no%>'"/>
    </td>
</tr>
<tr>
    <td>작성자 id: <%=rdto.getReply_id() %></td>
    <td>작성일: <%=rdto.getReply_create_date() %></td>
    <td>좋아요 수: <%=rdto.getReply_like_cnt() %></td>
</tr>
<tr>
    <td colspan="3">제목: <%=rdto.getReply_title() %></td>
</tr>
<tr>
    <td colspan="3">
        <img src="../upload/<%=rdto.getReply_image()%>" width="150" />
    </td>
</tr>
<tr>
    <td colspan="3">
        <textarea rows="10" style="width:99%" readonly><%=rdto.getReply_cont() %></textarea>
    </td>
</tr>
<tr>
    <td colspan="3" style="text-align: right;">
        <a href="../comment/comment.jsp?reply_no=<%=reply_no %>&reply_book_no=<%=reply_book_no%>">댓글쓰기</a>
        
        <%-- 로그인한 사용자라면 수정하기와 삭제하기가 보이도록 --%>
        <%
        if (id != null) {
        %>
        <a href="../replycontrol/edit.jsp?reply_no=<%=reply_no %>&id=<%=id%>&reply_book_no=<%=reply_book_no%>">수정하기</a>
        <a href="../replycontrol/delete.jsp?reply_no=<%=reply_no %>&id=<%=id%>&reply_book_no=<%=reply_book_no%>">삭제하기</a>
        <%
        }
        %>
        
        <a href="../bookview/view.jsp?reply_book_no=<%=reply_book_no%>">목록으로</a>
    </td>
</tr>
</table>
<table style="width: 80%">
    <tr style="background-color: beige;">
        <th>댓글제목</th>
        <th>작성자id</th>
        <th>작성일</th>
    </tr>
    <tr style="background-color: beige;">
        <th colspan="3">한줄댓글</th>
    </tr>
    <%
    ArrayList<ReplyDto> list = replyMgr.getComData(reply_no);
    for (int i = 0; i < list.size(); i++) {
        replydto = (ReplyDto) list.get(i);
    %>
    <tr style="text-align: center;">
        <td><%=replydto.getReply_title()%></td>
        <td><%=replydto.getReply_id()%></td>
        <td><%=replydto.getReply_create_date()%></td>
    </tr>
    <tr>
        <td colspan="3"><%=replydto.getReply_cont()%></td>
    </tr>
    <%
    }
    %>
</table>
</body>
</html>
