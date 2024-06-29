package report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.print.attribute.SetOfIntegerSyntax;
import javax.sql.DataSource;

import reply.ReplyDto;

public class ReportMgr {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DataSource ds;
	
	public ReportMgr() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("db 연결 실패 : " + e.getMessage());
		}
	}
	//신고 insert 하기
	public boolean insertReport(ReportBean repoBean) {
		boolean b = false;
		try {
			conn = ds.getConnection();
			String sql = "insert into report value (?,?,?,now(),?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, repoBean.getReport_no());
			pstmt.setInt(2, repoBean.getReport_reply_no());
			pstmt.setString(3,repoBean.getReporter_user_id());
			pstmt.setInt(4, repoBean.getReport_code());
			pstmt.setString(5, repoBean.getReporter_ip());
			pstmt.setString(6, repoBean.getReported_user_ip());
			pstmt.setString(7, repoBean.getReported_user_no());
			
			if(pstmt.executeUpdate()>0) b=true;
			
		} catch (Exception e) {
			System.out.println("insertReport err : " + e);
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
		
		return b;
	}
	
	//가장 최근 신고 번호 찾기
	public int currentMaxNum() {
		int num = 0;
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
		return num;
	}
	
	// 중복 신고 방지
	public boolean searchReport(String id,String reply_no) {
		boolean b= false;
		try {
			conn = ds.getConnection();
			String sql = "SELECT report_no FROM report WHERE report_reply_no = ? "
					+ "AND reporter_user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,reply_no);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) b = true;
			
		} catch (Exception e) {
			System.out.println("searchReport err : " + e);
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
		return b;
	}
	
	//신고된 사람 ip
	/*
	public ArrayList<String> searchReplyed(String reply_no) {
		ArrayList<String> list = null;
		try {
			conn = ds.getConnection();
			String sql ="SELECT ly.reply_ip, er.no FROM reply ly JOIN user er ON er.id = ly.reply_id JOIN report rt ON rt.report_reply_no = ly.reply_no WHERE ly.reply_no = ?";
					"SELECT ly.reply_ip, er.no FROM reply ly JOIN user er ON er.id = ly.reply_id "
					+ "JOIN report rt ON rt.report_reply_no = ly.reply_no WHERE ly.reply_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				list = new ArrayList<String>();
				System.out.println("ly.reply_ip");
				list.add(rs.getString("ly.reply_ip"));
				list.add(rs.getString("er.no"));
				System.out.println(list.get(1)+ "  " + list.get(0));
			}
		} catch (Exception e) {
			System.out.println("searchReplyed err : "+e);
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
		return list;
	}*/

	/* ------------- saveReport.jsp --------------- */
	public String searchReplyedIp(String reply_no) {
		String ip = null;
		try {
			conn = ds.getConnection();
			String sql ="select reply_ip from reply where reply_no= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ip = rs.getString(1);
			}
		} catch (Exception e) {
			System.out.println("searchReplyed err : "+e);
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
		return ip;
	}
	

	//신고받은 사람ID
	public String searchReplyedId(String reply_no) {
		String id = null;
		try {
			conn = ds.getConnection();
			String sql ="select no from user join reply on reply.reply_id= user.id where reply_no= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getString(1);
			}
		} catch (Exception e) {
			System.out.println("searchReplyed err : "+e);
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
		return id;
	}
	/* ---------------------------------- */
}
