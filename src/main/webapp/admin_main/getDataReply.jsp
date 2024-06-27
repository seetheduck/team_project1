<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="adminMgr" class="admin.AdminMgr"></jsp:useBean>

<%
    String jsonData = adminMgr.getDataReply();
    response.getWriter().print(jsonData);
%>
