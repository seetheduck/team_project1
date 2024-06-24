<%@page import="javax.servlet.*"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String userId = request.getParameter("id");
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

try{
    Class.forName("org.mariadb.jdbc.Driver");
    String Url ="jdbc:mariadb://localhost:3306/bookie";
    conn = DriverManager.getConnection(Url, "root", "123");
    pstmt = conn.prepareStatement("select * from user where id = ?");
    pstmt.setString(1, userId);
    rs = pstmt.executeQuery();

    if(rs.next()){
        String result = "{";
        result += "\"id\":\"" + rs.getString("id") + "\",";
        result += "\"pw\":\"" + rs.getString("pw") + "\",";
        result += "\"uname\":\"" + rs.getString("uname") + "\",";
        result += "\"email\":\"" + rs.getString("email") + "\"";
        result += "}";
        out.print(result);
    }
}catch(Exception e){
    System.out.println("에러 : " + e);
}finally{
    try {
        rs.close();
        pstmt.close();
        conn.close();
    }catch (Exception e){
        System.out.println("자원 해제 실패: " + e);
    }
}
%>
