<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
session.removeAttribute("admin");
%>
<script type="text/javascript">
	alert("로그아웃 성공");
	location.href="adminLogin.jsp";
</script>
