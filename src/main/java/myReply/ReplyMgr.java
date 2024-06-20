package myReply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import book.BookDTO;

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
	// 내 리뷰 모두 조회 (삭제된 리뷰 빼고)
	public ArrayList<ReplyBean> myReplyAll(String id){
		ArrayList<ReplyBean> list = new ArrayList<ReplyBean>();
		try {
			conn = ds.getConnection();
			String sql = "select reply_no,reply_book_no,reply_cont,reply_create_date,reply_title,reply_point,reply_like_cnt"
					+ " from reply where reply_id=? and reply_onum=0 and reply_del_is=0";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReplyBean dto = new ReplyBean();
				dto.setReply_no(rs.getInt("reply_no"));
				dto.setReply_book_no(rs.getInt("reply_book_no"));					//책번호
				dto.setReply_cont(rs.getString("reply_cont"));					//리뷰 내용
				dto.setReply_create_date(rs.getString("reply_create_date"));	//리뷰쓴 날짜
				dto.setReply_title(rs.getString("reply_title"));				//리뷰 제목
				dto.setReply_point(rs.getInt("reply_point"));				//리뷰 평점
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
	
	//리뷰 삭제
	public boolean deleteReply(int no) {
		boolean b = false;
		try {
			conn = ds.getConnection();
			String sql = "update reply set reply_del_is=1, reply_del_date = now() where reply_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			if (pstmt.executeUpdate()>0) {
				b = true;
			}
			
		}catch (Exception e) {
			System.out.println("deleteReply err : " + e);
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
	
	//삭제된 리뷰 찾기
	public BookDTO isDel(int no){
		BookDTO dto = null;
		try {
			conn = ds.getConnection();
			String sql = "select * from reply where reply_no=? and reply_del_is=1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new BookDTO();
				dto.setBum(rs.getInt("bnum"));
				dto.setTitle(rs.getString("title"));
				dto.setAuthor(rs.getString("author"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setThumb_nail(rs.getString("thumb_nail"));
				dto.setPyear(rs.getInt("pyear"));
			}
		} catch (Exception e) {
			System.out.println("isDel err : " + e);
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
		return dto;
	}
}
