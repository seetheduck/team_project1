package search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import book.BookDTO;

public class searchBookDB {
    private DataSource ds;
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public searchBookDB() {
        try {
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/jdbc_maria");
        } catch (Exception e) {
            System.out.println("Db 연결 실패:" + e);
        }
    }

    public ArrayList<BookDTO> getResult(String title) {
        ArrayList<BookDTO> result2 = new ArrayList<BookDTO>();
        try {
        	
            conn = ds.getConnection();
            StringBuilder sql = new StringBuilder("SELECT * FROM book WHERE title LIKE ?");
            
            pstmt = conn.prepareStatement(sql.toString());
            
            pstmt.setString(1, "%" + title + "%");

            rs = pstmt.executeQuery();
           
            while (rs.next()) {
            	BookDTO book = new BookDTO();
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPyear(rs.getInt("pyear"));
                result2.add(book);
            }
        } catch (Exception e) {
            System.out.println("검색 실패: " + e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println("자원 해제 실패: " + e);
            }
        }
        return result2;
    }
}
