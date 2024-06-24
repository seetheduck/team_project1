<%@page import="javax.servlet.*"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>

{
"user":[
<%
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

try{
    Class.forName("org.mariadb.jdbc.Driver");
    String Url ="jdbc:mariadb://localhost:3306/bookie";
    conn = DriverManager.getConnection(Url, "root", "123");
    pstmt = conn.prepareStatement("select id, uname from user");
    rs = pstmt.executeQuery();

    String result = "";

    while(rs.next()){
        result += "{";
        result += "\"id\":\"" + rs.getString("id") + "\",";
        result += "\"uname\":\"" + rs.getString("uname") + "\"";
        result += "},";
    }
    if(result.length() > 0){
        result = result.substring(0, result.length() - 1);
    }
    out.print(result);
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
]
}
