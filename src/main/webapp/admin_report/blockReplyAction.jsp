<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="adminMgr" class="admin.AdminMgr"></jsp:useBean>

<%
request.setCharacterEncoding("UTF-8");
int report_reply_no = Integer.parseInt(request.getParameter("report_reply_no"));
String action = request.getParameter("action"); // block 또는 unblock 액션을 결정하는 파라미터

try {
    if ("block".equals(action)) {
        adminMgr.blockReply(report_reply_no);
    } else if ("unblock".equals(action)) {
        adminMgr.unblockReply(report_reply_no);
    }
    response.getWriter().write("{\"status\":\"success\"}");
} catch (Exception e) {
    response.getWriter().write("{\"status\":\"error\"}");
}
%>