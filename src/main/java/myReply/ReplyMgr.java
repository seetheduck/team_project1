package myReply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReplyMgr {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DataSource ds;
	
	public ReplyMgr() { //db 연결
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("db 연결 실패 : " + e.getMessage());
		}
	}
	// 내 리뷰 모두 조회
	public ArrayList<ReplyBean> myReplyAll(String id){
		ArrayList<ReplyBean> list = new ArrayList<ReplyBean>();
		try {
			conn = ds.getConnection();
			String sql = "select reply_book_no,reply_cont,reply_create_date,reply_title,reply_point,reply_like_cnt"
					+ " from reply where reply_id = ? and reply_onum=0";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReplyBean dto = new ReplyBean();
				dto.setReply_no(rs.getInt("reply_book_no"));					//책번호
				dto.setReply_cont(rs.getString("reply_cont"));					//리뷰 내용
				dto.setReply_create_date(rs.getString("reply_create_date"));	//리뷰쓴 날짜
				dto.setReply_title(rs.getString("reply_title"));				//리뷰 제목
				dto.setReply_point(rs.getInt("reply_point"));					//리뷰 평점
				dto.setReply_like_cnt(rs.getInt("reply_like_cnt"));					//리뷰 좋아요 수
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("myReplyAll err : " + e);
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
	}
	
}
