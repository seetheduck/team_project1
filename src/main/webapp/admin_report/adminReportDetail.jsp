<%@page import="reply.ReplyBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="adminMgr" class="admin.AdminMgr"></jsp:useBean>

<%
request.setCharacterEncoding("utf-8");
int report_reply_no = Integer.parseInt(request.getParameter("report_reply_no"));
//out.println(report_reply_no);

ReplyBean bean = adminMgr.getReply(report_reply_no);
boolean isBlocked = bean.getReply_blocked() == 1;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고 상세보기</title>
<link rel="stylesheet" href="../css/adminMain.css">
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    let isBlocked = <%= isBlocked %>;

    if (isBlocked) {
        $("#block").prop("disabled", false).val("block 해제하기");
    }

    $("#block").click(function() {
        let action = isBlocked ? 'unblock' : 'block';
        $.ajax({
            url: 'blockReplyAction.jsp',
            type: 'POST',
            data: {
                report_reply_no: <%= report_reply_no %>,
                action: action  // block 또는 unblock 액션 지정
            },
            success: function(response) {
                if (isBlocked) {
                    alert("블록이 해제되었습니다.");
                    $("#block").prop("disabled", false).val("block하기");
                } else {
                    alert("블록 처리 되었습니다.");
                    $("#block").prop("disabled", false).val("block 해제하기");
                }
                isBlocked = !isBlocked;
            },
            error: function(xhr, status, error) {
                alert("처리 중 오류가 발생했습니다.");
                console.error("Error details:", error);
            }
        });
    });
});

</script>
</head>
<body>
<div class="container">
<%@ include file="../admin_main/adminSidebar.jsp"  %>
<main class="main-content">
<h2>해당 글 보기</h2>
<div class="reply_cont">
<br>
<h3><%=bean.getReply_title() %></h3>
<br>
<p><%=bean.getReply_no() %>번 글</p>

<textarea style="width: 90%; height: 22em; resize: none;">
<%=bean.getReply_cont() %>
</textarea>
<br>
<p><%=bean.getReply_create_date()%>작성</p>

</div>

<input type="button" onclick="history.back()" value="뒤로가기">
<input type="button" id="block" value="block하기">

</main>
<%@ include file="../admin_main/admin_foot.jsp" %>      
</div>
</body>
</html>