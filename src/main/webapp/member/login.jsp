<%@page import="java.util.Date"%>
<%@page import="io.jsonwebtoken.Jwts"%>
<%@page import="io.jsonwebtoken.security.Keys"%>
<%@page import="java.security.Key"%>
<%@page import="java.util.Base64"%>
<%@page import="javax.websocket.SendResult"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String id = request.getParameter("id");
String password = request.getParameter("password");

String validId = "ok";
String validpassword = "123";

if(id != null && password != null && id.equalsIgnoreCase(validId) 
&& password.equalsIgnoreCase(validpassword)){
	  String encodedKey = (String) getServletContext().getAttribute("secretKey");
      byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
      Key secretKey = Keys.hmacShaKeyFor(decodedKey);
      
      long expirationTime = 3600000; 
      
  	//jwt 생성 : 문자열로 변환되며, 인증 및 권한부여 매커니즘에서 사용
  	String jwt = Jwts.builder()
  					.setSubject(id)   //Id, 사용자 식별자, 주제 등이 포함된 클레임을 설정
  					.setIssuedAt(new Date()) //클레임 내용 중 lat : 발행시간 
  					.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
  					.signWith(secretKey)   //서명 알고리즘, 비밀치 입력(무결성 보장이 목적)
  					.compact();  //JWT 생성
  					
  	//쿠키에 jwt를 저장
  	Cookie jwtCookie = new Cookie("jwt", jwt);
  	jwtCookie.setHttpOnly(true);
  	jwtCookie.setPath("/");  //모든 경로에서 쿠키 접근 가능
  	response.addCookie(jwtCookie);
  	
  	//성공한 경우 보여줄 페이지
  	response.sendRedirect("jsp10success.jsp");  //success.html로도 가능(html파일도 가능)

  }else{
  	//실패한 경우 처리 작업
  	out.println("<html><body>");
  	out.println("<h3>로그인 실패</h3>");
  	out.println("<a href= 'jsp10jwtlogin.html'>다시 시도</a>");
  	out.println("</body></okhtml>");

}
%>