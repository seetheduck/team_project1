package likeFav;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LikeFavMgr {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DataSource ds;
	
	/*페이징*/
	private int favtot; //전체 레코드(행) 수
	private int plist = 10; // 페이지 당 출력 행 수
	private int pageSu; // 전체 페이지 수
	
	
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
				String sql = "select * from like_fav where like_fav_is=1 and like_user_id=? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					LikeFavDTO dto = new LikeFavDTO();
					dto.setLike_no(rs.getInt("like_no"));
					dto.setLike_reply_no(rs.getInt("like_reply_no")); //좋아요 글번호
					dto.setLike_user_id(rs.getString("like_user_id"));  //좋아요 id
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
		
		//해당 리뷰
		public LikeFavbean getFav(int no) {
			LikeFavbean bean = new LikeFavbean();
	    	
	    	try {
				conn = ds.getConnection();
				String sql = "select * from like_fav where like_book_no in (select bnum from book where bnum = ?)";
	    		pstmt = conn.prepareStatement(sql);
	    		pstmt.setInt(1, no);
	    		rs = pstmt.executeQuery();
				
	    		if(rs.next()) {
	    			bean.setLike_no(rs.getInt("like_no"));
	    			bean.setLike_reply_no(rs.getInt("like_reply_no"));
	    			bean.setLike_user_id(rs.getString("like_user_id"));
	    			bean.setLike_book_no(rs.getInt("like_book_no"));
	    			bean.setLike_fav_is(rs.getInt("like_fav_is"));
	    		}
				
			} catch (Exception e) {
	            System.out.println("getLike error : " + e);
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (pstmt != null) pstmt.close();
	                if (conn != null) conn.close();
	            } catch (Exception e) {
	                
	            }
	        }
	    	
	    	return bean;
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
		public boolean checkFav(int no,String id){
			boolean b = false;
			try {
				conn = ds.getConnection();
				String sql="select like_no from like_fav where like_book_no = ? and like_user_id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				pstmt.setString(2, sql);
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
			}
			return b;
		}
		
		//찜 취소
		public boolean cancelLike(int no){
			boolean b = false;
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement("delete from like_fav where like_book_no= ?");
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
	
		//찜 하기
		public void FavBook(LikeFavbean likeFavbean){
				
			try {
				conn = ds.getConnection();
				String sql = "insert into like_fav (like_no,like_user_id,like_book_no,like_fav_is) values(?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,likeFavbean.getLike_no());
				pstmt.setString(2,likeFavbean.getLike_user_id());
				pstmt.setInt(3,likeFavbean.getLike_book_no());
				pstmt.setInt(4,1);
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("FavBook err : " + e);
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
		
		
		///////////////////mypage.favbookt 시작//////////////////////////
		
		//특정 도서 찜 유무
				public boolean checkMyFav(int no){
					boolean b = false;
					try {
						conn = ds.getConnection();
						String sql="select like_no from like_fav where like_book_no = ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, no);
						pstmt.setString(2, sql);
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
					}
					return b;
				}
	
		//내가 찜한 책 취소
			public boolean canLike(int no){
				boolean b = false;
				System.out.println(" - -"+no);
				try {
					conn = ds.getConnection();
					pstmt = conn.prepareStatement("delete from like_fav where like_no= ?");
					pstmt.setInt(1, no);
					if(pstmt.executeUpdate()>0) {
						b = true; //db의 첫번째 값을 정수로 가져오기
					}
				} catch (Exception e) {
					System.out.println("canLike err : " + e);
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
	
	
	// ----------페이징 시작 ------------
	//자신의 찜 목록수 전체 가져오기
	public void myFavTot(String id){
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select count(*) from like_fav where like_user_no= ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			favtot = rs.getInt(1);
			
		} catch (Exception e) {
			System.out.println("myFavTot err : " + e);
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
	//페이지 수 증가
	public int getFavPageSu() {
		pageSu = favtot / plist;
		if(favtot % plist > 0)
			pageSu++;
		return pageSu;
		
	}
	// ----------페이징 끝 ------------
}
