package likefav;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import book.BookDTO;

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
	
	//찜한 도서 전체 조회 (찜(like_fav_is) = 1)
		public ArrayList<LikeFavDTO> getLikeBookAll(String id) {
			ArrayList<LikeFavDTO> list = new ArrayList<LikeFavDTO>();
			try {
				conn = ds.getConnection();
				String sql = "select * from like_fav where like_fav_is=1 and like_user_no=(select no from user where id=?) ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					LikeFavDTO dto = new LikeFavDTO();
					dto.setLike_no(rs.getInt("like_no"));
					dto.setLike_reply_no(rs.getInt("like_reply_no")); //좋아요 글번호
					dto.setLike_user_no(rs.getInt("like_user_no")); //좋아요 사용자..?
					dto.setLike_book_no(rs.getInt("like_book_no")); //잭 번호
					dto.setLike_fav_is(rs.getInt("like_fav_is")); //좋아요,찜 구분
					list.add(dto);
				}
				
			} catch (Exception e) {
				System.out.println("getLikeBookAll err : " + e);
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
		
		//가장 최근 신고 번호 찾기
		public int currentMaxNum() {
			int num = 0;
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement("select max(like_no) from like_fav");
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
		
		//특정 도서 찜 유무
		public boolean checkFav(int no){
			boolean b = false;
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement("select like_no from like_fav where like_no = ?");
				pstmt.setInt(1, no);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					b = true; //db의 첫번째 값을 정수로 가져오기
				}
			} catch (Exception e) {
				System.out.println("checkFav err : " + e);
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
				return b;
			}
		}
		
		//찜 취소
		public boolean cancelLike(int no){
			boolean b = false;
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement("delete from like_fav where like_no= ?");
				pstmt.setInt(1, no);
				
				if(pstmt.executeUpdate()>0) {
					b = true; //db의 첫번째 값을 정수로 가져오기
				}
			} catch (Exception e) {
				System.out.println("cancelLike err : " + e);
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
		/*
	//찜 하기
	public void FavBook(int no){
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("insert into like_fav (like_no) values();");
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
