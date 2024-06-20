package likefav;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LikeFavMgr {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DataSource ds;
	
	public LikeFavMgr() { //db 연결
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("db 연결 실패 : " + e.getMessage());
		}
	}
	/*
	public void (){
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select max(report_no) from report");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1); //db의 첫번째 값을 정수로 가져오기
			}
		} catch (Exception e) {
			System.out.println("currentMaxNum err : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	*/
}
