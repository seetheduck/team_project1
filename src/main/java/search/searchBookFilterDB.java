package search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import book.BookDTO;

public class searchBookFilterDB {
    private DataSource ds;
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public searchBookFilterDB() {
        try {
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/jdbc_maria");
        } catch (Exception e) {
            System.out.println("Db 연결 실패:" + e);
        }
    }

    public ArrayList<BookDTO> getResult(String title, String author, String publisher, Integer pyear, Integer bnum) {
        ArrayList<BookDTO> result = new ArrayList<BookDTO>();
        try {
            conn = ds.getConnection();
            String sql = "SELECT * FROM book WHERE title LIKE ? AND author LIKE ? AND publisher LIKE ?";
            if (pyear != null) {
                sql += " AND pyear = ?";
            }
            if (pyear == null && bnum != null) {
                sql += " AND bnum = ?";
            }
            if (pyear != null && bnum != null) {
                sql += " AND bnum = ?";
            }
            System.out.println("db 연결 성공시 bnum:" + bnum);
            System.out.println("db 연결 성공시 sql:" + sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + title + "%");
            pstmt.setString(2, "%" + author + "%");
            pstmt.setString(3, "%" + publisher + "%");
            
            int index = 4;
            if (pyear != null && bnum != null) {
                pstmt.setInt(index++, pyear);
            }
            if (pyear != null && bnum == null) {
                pstmt.setInt(index, pyear);
            }
            if (bnum != null) {
                pstmt.setInt(index, bnum);
            }
            
            
            rs = pstmt.executeQuery();

            while (rs.next()) {
            	BookDTO book = new BookDTO();
            	
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPyear(rs.getInt("pyear"));
                book.setBnum(rs.getInt("bnum"));
                result.add(book);
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

        return result;
    }
}
