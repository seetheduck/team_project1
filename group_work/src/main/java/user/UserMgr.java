package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserMgr {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	public UserMgr() { //db와 연결
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc_maria");
			
		} catch (Exception e) {	
			System.out.println("db 연결 실패 : " + e);
		}
	}
	public boolean idCheckProcess(String id) { //id체크 프로세스
		boolean b = false;
		
		try {
			conn = ds.getConnection();
			String sql = "select id from user where id=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) b = true;
		} catch (Exception e) {
			System.out.println("idCheckProcess err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
		return b;
	}
	//user테이블의 최대 no 수
		public int currentMaxNo() {  
			String sql = "select max(no) from user";
			int no = 0;
			
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					no = rs.getInt(1);					
				}
			} catch (Exception e) {
				System.out.println("currentMaxNum err : " + e);
			}finally {
				try {
					if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
			return no;
		}
	
	public boolean userInsert(UserBean userbean) {
		boolean b = false;
		try {
			
			conn = ds.getConnection();
			String sql = "insert into user(no,id,pw,uname,gender,email,sign_up_date) values(?,?,?,?,?,?,DATE(now()))"; 
			pstmt = conn.prepareStatement(sql); 

			pstmt.setInt(1, userbean.getNo());
			pstmt.setString(2, userbean.getId());
			pstmt.setString(3, userbean.getPw());
			pstmt.setString(4, userbean.getUname());
			pstmt.setInt(5, userbean.getGender());
			pstmt.setString(6, userbean.getEmail());
			
			if(pstmt.executeUpdate() > 0) b = true;
		} catch (Exception e) {
			System.out.println("UserInsert err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}		
		}
		return b;
	}
	public boolean loginCheck(String id, String pw) {
		boolean b = false;
		try {
			conn = ds.getConnection();
			String sql = "select * from user where id=? and pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			b = rs.next();
		} catch (Exception e) {
			System.out.println("loginCheck err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}		
		}
		return b;
	}
}
