..<%@page import="java.util.Date"%>
<%@page import="io.jsonwebtoken.Jwts"%>
<%@page import="io.jsonwebtoken.security.Keys"%>
<%@page import="java.security.Key"%>
<%@page import="java.util.Base64"%>
<%@page import="javax.websocket.SendResult"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%-- JWT의 잔해들
<jsp:useBean id="userBean" class="pack.user.UserBean" />

<% 
String id = request.getParameter("id");
String pw = request.getParameter("pw");

String validId = userBean.getId() ;
String validPw = userBean.getPw() ;

if(id != null && pw != null && id.equalsIgnoreCase(validId) 
&& pw.equalsIgnoreCase(validPw)){
	  String encodedKey = (String) getServletContext().getAttribute("secretKey");
      byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
      Key secretKey = Keys.hmacShaKeyFor(decodedKey);
      
      long expirationTime = 3600000; 
      
  	String jwt = Jwts.builder()
  					.setSubject(id)  
  					.setIssuedAt(new Date()) 
  					.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
  					.signWith(secretKey)   
  					.compact(); 
  					
  	Cookie jwtCookie = new Cookie("jwt", jwt);
  	jwtCookie.setHttpOnly(true);
  	jwtCookie.setPath("/");  //모든 경로에서 쿠키 접근 가능
  	response.addCookie(jwtCookie);
  	
  	response.sendRedirect("mainpage.jsp");  //success.html로도 가능(html파일도 가능)

  }else{
  	//실패한 경우 처리 작업
  	out.println("<html><body>");
  	out.println("<h3>로그인 실패</h3>");
  	out.println("<a href= 'loginpage.jsp'>다시 시도</a>");
  	out.println("</body></okhtml>");

}
%>
  --%>  
  
  