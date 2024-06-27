<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="book.BookDTO" %>
<%@ page import="search.searchBookFilterDB" %>
<%
//int bnum = Integer.parseInt(request.getParameter("bnum"));


String title = request.getParameter("title");
String author = request.getParameter("author");
String publisher = request.getParameter("publisher");
String pyearStr = request.getParameter("pyear");
Integer pyear = null;

if (pyearStr != null && !pyearStr.isEmpty()) {
    try {
        pyear = Integer.parseInt(pyearStr);
    } catch (NumberFormatException e) {
        // Handle the case where pyearStr is not a valid integer
        // You may want to set pyear to null or handle this scenario appropriately
        pyear = null;
    }
}
String bnumStr = request.getParameter("bnum");
Integer bnum = null;
if (bnumStr != null && !bnumStr.isEmpty()) {
    try {
    	bnum = Integer.parseInt(bnumStr);
    } catch (NumberFormatException e) {
        // Handle the case where pyearStr is not a valid integer
        // You may want to set pyear to null or handle this scenario appropriately
        bnum = null;
    }
}

searchBookFilterDB search = new searchBookFilterDB();
ArrayList<BookDTO> result = search.getResult(title, author, publisher, pyear, bnum);
System.out.println("searchFilterProc.jsp에서 searchBookDB의 bnum 값 받음: " + bnum);
%>


<table>
    <tr>
    	<th>번호</th>
        <th>제목</th>
        <th>작가</th>
        <th>출판사</th>
        <th>출판일</th>
    </tr>
    <%
    for (BookDTO s : result) {
    %>
    <tr>
    	<td><a href="../bookview/view.jsp?reply_book_no=<%= s.getBnum() %>"><%= s.getBnum() %></a></td>
        <td><%= s.getTitle() %></td>
        <td><%= s.getAuthor() %></td>
        <td><%= s.getPublisher() %></td>
        <td><%= s.getPyear() %></td>
    </tr>
    <%
    }
    %>
</table>
