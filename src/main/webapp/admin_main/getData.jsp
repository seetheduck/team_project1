<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="adminMgr" class="admin.AdminMgr"></jsp:useBean>

<%
    String jsonData = adminMgr.getDataUser();
    response.getWriter().print(jsonData);
%>