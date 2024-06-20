package pack.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AdminMgr {
    private Connection conn;      // DB 연결을 관리하는 객체
    private PreparedStatement pstmt; // SQL 문을 실행하는 객체
    private ResultSet rs;         // SQL 쿼리 결과를 담는 객체
    private DataSource ds;        // 데이터 소스, DB 연결을 위한 객체

    // 클래스 생성자
    public AdminMgr() {
        try {
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/jdbc_maria");
        } catch (Exception e) {
            System.out.println("db 연결 실패 : " + e);
        }
    }
    
    // user테이블의 sign_up_date 데이터를 json으로 반환
    public String getDataUser() {
        String res = "{ \"data\": [";
        try {
            conn = ds.getConnection();
            String sql = "SELECT DATE_FORMAT(sign_up_date, '%Y-%m-%d') AS date, COUNT(*) AS count FROM user GROUP BY date ORDER BY date";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            boolean isFir = true; // 첫번째 항목인지 확인하기 위한 플래그
            while (rs.next()) {
                if (!isFir) {// 첫번째 항목이 아닐 경우,
                    res += ","; // json 배열에 객체 추가 전 사이에 콤마 추가
                }
                isFir = false; // 첫번째 항목 처리됨
                // json 데이터 추가
                res += "{\"sign_up_date\":\"" + rs.getString("date") + "\", \"count\":" + rs.getInt("count") + "}";
            }
        } catch (Exception e) {
            System.out.println("getDataUser error : " + e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        res += "]}";
        return res;
    }
}