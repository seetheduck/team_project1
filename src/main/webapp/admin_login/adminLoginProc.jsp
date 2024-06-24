<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="adminMgr" class="admin.AdminMgr"></jsp:useBean>


<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");
String pw = request.getParameter("pw");

boolean b = adminMgr.adminLoginCheck(id, pw);

if(b){
	session.setAttribute("admin", id);
	response.sendRedirect("../admin_main/adminMain.jsp");
}else{
	%>
	<script>
	alert("님 관리자 아니죠?");
	location.href="adminLogin.jsp";
	</script>
	<%
}

%>
