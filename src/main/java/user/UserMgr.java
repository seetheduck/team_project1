package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserMgr {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;

	public UserMgr() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("Db 연결 실패:" + e);
		}
	}


	public boolean memberInsert(UserBean mbean) {
		boolean b = false;
		// System.out.println(mbean.getId() + " " + mbean.getName());

		try {
			conn = ds.getConnection();
			String sql = "insert into user values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mbean.getId());
			pstmt.setString(2, mbean.getPw());
			pstmt.setString(3, mbean.getUname());
			pstmt.setString(4, mbean.getEmail());
			if (pstmt.executeUpdate() > 0)
				b = true; // insert에 성공하면 b는 true가 된다.

		} catch (Exception e) {
			System.out.println("memberInsert err : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {

			}
		}
		return b;
	}

	
	public UserBean getMember(String id) {
		UserBean bean = null;
		try {
			conn = ds.getConnection();
			String sql = "select * from user where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getString("id"));
				bean.setPw(rs.getString("pw"));
				bean.setUname(rs.getString("name"));
				bean.setEmail(rs.getString("email"));
			}
		
		} catch (Exception e) {
			System.out.println("getMember err :" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {

			}
		}
		
		return bean;
	}
	
	public boolean memberUpdate(UserBean memberBean, String id) {
		boolean b = false;
		
		try {
			conn = ds.getConnection();
			String sql = "update user set pw=?,uname=?,email=? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberBean.getPw());
			pstmt.setString(2, memberBean.getUname());
			pstmt.setString(3, memberBean.getEmail());;
			pstmt.setString(4, id);
			if(pstmt.executeUpdate() > 0) b = true;
			
		} catch (Exception e) {
			System.out.println("memberUpdate err :" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {

			}
		}
		
		return b;
	}
	

	
	//관리자가 전체 회원자료 읽기
	public ArrayList<UserBean> getMemberAll(){
		ArrayList<UserBean> list = new ArrayList<UserBean>();
		
		try {
			conn = ds.getConnection();
			String sql = "select * from user order by id asc";
			
			pstmt = conn.prepareStatement(sql);
	
			rs = pstmt.executeQuery();
			while(rs.next()) {
				UserBean dto = new UserBean();
				dto.setId(rs.getString("id"));
				dto.setUname(rs.getString("uname"));
				dto.setPw(rs.getString("pw"));
				dto.setEmail(rs.getString("email"));
				list.add(dto);
			}

		} catch (Exception e) {
			System.out.println("getMemberAll err :" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {

			}
		}
		return list;
	}
}