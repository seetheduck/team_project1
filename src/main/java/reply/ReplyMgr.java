package reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import book.BookDTO;
import reply.ReplyBean;
import reply.ReplyDto;


public class ReplyMgr {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	// 페이징 ----------
		private int retot; // 전체 레코드(행) 수
		private int plist = 5; // 페이지 당 출력 행 수
		private int pageSu; // 전체 페이지 수
		// -----------
	
	public ReplyMgr() {
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("Db 연결 실패:"+e);
		}
	}
	
	// 해당 책번호의 상세정보출력. 단 지워진 리뷰는 보여주지않는다.-------- 수정
	public ArrayList<ReplyDto> getDataAll(int bno){
		ArrayList<ReplyDto> list = new ArrayList<ReplyDto>();
		
		
		try {
			conn = ds.getConnection();
			String sql = "select * from reply where reply_book_no=? "
					+ "and reply_del_is =0 and reply_blocked =0 and reply_no = reply_gnum";
			//blocked 1이면 안뜨게. 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs= pstmt.executeQuery();
			
			
		while(rs.next()) {
			ReplyDto dto= new ReplyDto();
			dto.setReply_no(rs.getInt(1));
			dto.setReply_book_no(rs.getInt(2));
			dto.setReply_id(rs.getString(3));
			dto.setReply_cont(rs.getString(4));
			dto.setReply_create_date(rs.getString(5));
			dto.setReply_title(rs.getString(6));
			dto.setReply_point(rs.getInt(7));
			dto.setReply_image(rs.getString(8));
			dto.setReply_like_cnt(rs.getInt(9));
			dto.setReply_del_is(rs.getInt(10));
			dto.setReply_del_date(rs.getString(11));
			dto.setReply_ip(rs.getString(12));
			dto.setReply_blocked(rs.getInt(13));
			dto.setReply_blocked_cnt(rs.getInt(14));
			dto.setReply_gnum(rs.getInt(15));
			dto.setReply_onum(rs.getInt(16));
			list.add(dto);
		}
			
		} catch (Exception e) {
			System.out.println("getDataAll() err:"+e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return list;
		
	}
	//---------------------------수정
	public boolean insertReply(HttpServletRequest request, int newNo, ReplyBean bean, String id){
	      boolean b = false;
	      
	      try {
	         //업로드할 이미지 경로: upload 폴더(절대경로)
	         String uploadDir= "C:/work/group_work/src/main/webapp/upload";
	         
	         // MultipartRequest 생성. 파일업로드사이즈제한. 이를 넘는 파일일경우, 깨져서 업로드. 
	         MultipartRequest multi= new MultipartRequest(request, uploadDir, 
	               5*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
	         
	         conn = ds.getConnection();
	         String sql= "insert into reply values(?,?,?,?,now(),?,?,?,?,?,?,?,?,?,?,?)";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, newNo);
	         pstmt.setInt(2, bean.getReply_book_no());
	         pstmt.setString(3, id);
	         pstmt.setString(4, multi.getParameter("reply_cont"));
	         pstmt.setString(5, multi.getParameter("reply_title"));
	         pstmt.setInt(6, Integer.parseInt(multi.getParameter("reply_point")));
	         
	         //이미지 파일명. 파일 업로드 안할수도 있다.
	         if(multi.getFilesystemName("reply_image") == null) {//상품등록시 이미지를 선택하지 않은 경우
	            pstmt.setString(7, "cookiecharc.jpg");
	         }else {
	            pstmt.setString(7, multi.getFilesystemName("reply_image"));
	         }
	         
	         pstmt.setInt(8, 0);// like_cnt
	         pstmt.setInt(9, 0);//del_is
	         pstmt.setInt(10, 0); // del_date
	         pstmt.setString(11, bean.getReply_ip());
	         pstmt.setInt(12, 0); // blocked 
	         pstmt.setInt(13, 0);// blocked_cnt
	         pstmt.setInt(14, newNo);//gnum
	         pstmt.setInt(15, 0);//onum
	         
	         if(pstmt.executeUpdate() > 0) b= true;
	      
	      }catch (Exception e) {
	         System.out.println("insertReply err:"+e);
	         //Field 'reply_no' doesn't have a default value //기본키. 꼭 불러서 값을 넣어주자.
	      }finally {
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
	
	//int newNo= replyMgr.currentMaxNo()+1;
	public int currentMaxNo() { //reply 테이블의 최대 번호 반납
		String sql ="select max(reply_no) from reply";
		int no=0;
		
		try {
			conn=ds.getConnection();
			pstmt= conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			
			if(rs.next()) no = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("currentMaxno err: "+e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				
			}
		}
		return no;
		
	}
	//리뷰 평점 평균
	public double avgPoint(int reply_book_no) {
		String sql= "select avg(reply_point) from reply where reply_book_no=?";
		double a = 0.0; // double 타입으로 선언하여 소수점까지 받을 준비
		
		try {
			conn=ds.getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, reply_book_no);
			rs= pstmt.executeQuery();
			
			if (rs.next()) {
	            a = rs.getDouble(1); // double 타입으로 결과값을 변수에 할당
	            
	        }
			
		} catch (Exception e) {
			System.out.println("avgPoint err: "+e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				
			}
		}
		return Math.round(a * 10.0) / 10.0;
		
		
	}
	//전체 리뷰수 구하기
	public int totReply(int reply_book_no) {
		String sql= "select count(*) from reply where reply_book_no=?";
		int t =0;
		
		try {
			conn=ds.getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, reply_book_no);
			rs= pstmt.executeQuery();
			
			if (rs.next()) {
	            t = rs.getInt(1); // 결과값을 변수에 할당
	        }

		} catch (Exception e) {
			System.out.println("totReply err: "+e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				
			}
		}
		return t;
		
		
	}
	
	
	//dto = ReplyMgr.getData(reply_no);// 해당 리뷰번호에 맞는 리뷰정보 출력하기
	public ReplyDto getData(int reply_no){
		String sql = "select * from reply where reply_no=?";
		ReplyDto dto = null;
		
		try {
			conn=ds.getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, reply_no);
			rs= pstmt.executeQuery(); 
			
			//글을 눌러서 보도록유도. url접근 막기. 
			if(rs.next()) { //포인터이동시켜 자료있을경우 자료보여주기.
				dto = new ReplyDto();
				dto.setReply_id(rs.getString("reply_id"));
				dto.setReply_create_date(rs.getString("reply_create_date"));
				dto.setReply_like_cnt(rs.getInt("reply_like_cnt"));
				dto.setReply_title(rs.getString("reply_title"));
				dto.setReply_image(rs.getString("reply_image"));
				dto.setReply_cont(rs.getString("reply_cont"));
				
			}
			
		} catch (Exception e) {
			System.out.println("getData err: "+e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				
			}
		}
		return dto;
	}
	
	 //ArrayList<ReplyDto> list = replyMgr.getComData(reply_book_no);
		//해당 리뷰글의 댓글들 보여주기--------------작업중--------------------
		public ArrayList<ReplyDto> getComData(int rno){
			ArrayList<ReplyDto> list = new ArrayList<ReplyDto>();
			
			
			try {
				conn = ds.getConnection();
				String sql ="select * from reply where reply_del_is=0 and reply_blocked=0 "
						+ "and reply_gnum=? and reply_onum=1";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, rno);
				rs= pstmt.executeQuery();
				
				
			while(rs.next()) {
				ReplyDto dto= new ReplyDto();
				dto.setReply_no(rs.getInt(1));
				dto.setReply_book_no(rs.getInt(2));
				dto.setReply_id(rs.getString(3));
				dto.setReply_cont(rs.getString(4));
				dto.setReply_create_date(rs.getString(5));
				dto.setReply_title(rs.getString(6));
				dto.setReply_point(rs.getInt(7));
				dto.setReply_image(rs.getString(8));
				dto.setReply_like_cnt(rs.getInt(9));
				dto.setReply_del_is(rs.getInt(10));
				dto.setReply_del_date(rs.getString(11));
				dto.setReply_ip(rs.getString(12));
				dto.setReply_blocked(rs.getInt(13));
				dto.setReply_blocked_cnt(rs.getInt(14));
				dto.setReply_gnum(rs.getInt(15));
				dto.setReply_onum(rs.getInt(16));
				list.add(dto);
			}
				
			} catch (Exception e) {
				System.out.println("getComData() err:"+e);
			}finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			return list;
			
		}
	
	//replyMgr.updateReply(request, bean);  //수정된리뷰정보 업데이트시키기
	public boolean updateReply(HttpServletRequest request, ReplyBean bean){
		boolean b = false;
		
		try {
			//업로드할 이미지 경로: upload 폴더(절대경로)
			String uploadDir= "C:/work/group_work/src/main/webapp/upload";
			
			// 파일업로드사이즈제한. 이를 넘는 파일일경우, 깨져서 업로드. 
			MultipartRequest multi= new MultipartRequest(request, uploadDir, 
					5*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
			
			conn = ds.getConnection();
			
			if(multi.getFilesystemName("reply_image") == null) {
				String sql= "update reply set reply_point=?, reply_title=?, reply_cont=? where reply_no=?";
				pstmt= conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(multi.getParameter("reply_point")));
				pstmt.setString(2, multi.getParameter("reply_title"));
				pstmt.setString(3, multi.getParameter("reply_cont"));
				pstmt.setInt(4, bean.getReply_no());
				
			}else {
				String sql= "update reply set reply_point=?, reply_title=?, reply_cont=?, reply_image=? where reply_no=?";
				pstmt= conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(multi.getParameter("reply_point")));
				pstmt.setString(2, multi.getParameter("reply_title"));
				pstmt.setString(3, multi.getParameter("reply_cont"));
				pstmt.setString(4, multi.getFilesystemName("reply_image"));
				pstmt.setInt(5, bean.getReply_no());
			}
			if(pstmt.executeUpdate() > 0) b= true; //>0말고 =1이렇게 물어봐도된다.	
			
		}catch (Exception e) {
				System.out.println("updateReply err:"+e);
			}finally {
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
		
	//dto = replyMgr.getReplyData(reply_no); 
		public ReplyDto getCommentData(int reply_no) { //댓글용 글읽기
			String sql = "select * from reply where reply_no=?";
			ReplyDto dto = null;
			
			try {
				conn=ds.getConnection();
				pstmt= conn.prepareStatement(sql);
				pstmt.setInt(1, reply_no);
				rs= pstmt.executeQuery(); 
				
			 
				if(rs.next()) { 
					dto =new ReplyDto();
					dto.setReply_title(rs.getString("reply_title"));
					dto.setReply_gnum(rs.getInt("reply_gnum"));
					dto.setReply_onum(rs.getInt("reply_onum"));
				}
		
				
			} catch (Exception e) {
				System.out.println("getCommentData err: "+e);
			}finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				} catch (Exception e2) {
					
				}
			}
			
			return dto;
			
		}
	
	
	//삭제처리된 리뷰는 영구삭제가 아닌, 보이지 않게 한다. -------수정
		//replyMgr.delReply(bean);
		public void delReply(ReplyBean bean) {
			String sql = "update reply set reply_del_date=now(), reply_del_is=1 where reply_no=?";
			//reply_del_is=1,
			try {
				conn= ds.getConnection();
				pstmt= conn.prepareStatement(sql);
				pstmt.setInt(1, bean.getReply_no());
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("delReply err: "+e);
			}finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				} catch (Exception e2) {
					
				}
			}
		}
		
		//replyMgr.updateOnum(gn, on);// 댓글 gnum, onum 넣어주기
				public void updateOnum(int gnum, int onum) {
					String sql= "update reply set reply_onum = reply_onum +1 where reply_onum >= ? and reply_gnum = ?";
					
					try {
						conn=ds.getConnection();
						pstmt= conn.prepareStatement(sql);
						pstmt.setInt(1, onum);
						pstmt.setInt(2, gnum);
						pstmt.executeUpdate();
						
					} catch (Exception e) {
						System.out.println("updateOnum err: "+e);
					}finally {
						try {
							if(rs != null) rs.close();
							if(pstmt != null) pstmt.close();
							if(conn != null) conn.close();
						} catch (Exception e2) {
							
						}
					}
				}		
		
				
				//replyMgr.replySave(bean, id); //댓글 저장
				public void replySave(ReplyBean bean, String id) {
					String sql= "insert into reply values(?,?,?,?,now(),?,?,?,?,?,?,?,?,?,?,?)";
					
					try {
						conn=ds.getConnection();
						pstmt= conn.prepareStatement(sql);
						
						pstmt.setInt(1, bean.getReply_no());
						pstmt.setInt(2, bean.getReply_book_no());
						pstmt.setString(3, id);
						pstmt.setString(4, bean.getReply_cont());
						pstmt.setString(5, bean.getReply_title());
						pstmt.setInt(6, 0); //reply_point
						pstmt.setInt(7, 0); //reply_image
						pstmt.setInt(8, 0);//Reply_like_cnt. 
						pstmt.setInt(9, 0);//del_is
						pstmt.setInt(10, 0); // del_date
						pstmt.setString(11, bean.getReply_ip());
						pstmt.setInt(12, 0); // blocked 
						pstmt.setInt(13, 0);// blocked_cnt
						pstmt.setInt(14, bean.getReply_gnum());
						pstmt.setInt(15, 1);// reply_onum
						pstmt.executeUpdate();
						
					} catch (Exception e) {
						System.out.println("replySave err: "+e);
						
					}finally {
						try {
							if(rs != null) rs.close();
							if(pstmt != null) pstmt.close();
							if(conn != null) conn.close();
						} catch (Exception e2) {
							
						}
					}
				}
		
		//---------------내 리뷰-----------------------------------
		
		// 내 리뷰 모두 조회 (삭제된 리뷰 빼고)
		public ArrayList<ReplyBean> myReviewAll(String id) {
			ArrayList<ReplyBean> list = new ArrayList<ReplyBean>();
			try {
				conn = ds.getConnection();
				String sql = "select reply_no,reply_book_no,reply_cont,reply_create_date,reply_title,reply_point,reply_like_cnt from reply";
				/*String sql = "select reply_no,reply_book_no,reply_cont,reply_create_date,reply_title,reply_point,reply_like_cnt"
						+ " from reply where reply_id=? and reply_onum=0 and reply_del_is=0";*/
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					ReplyBean dto = new ReplyBean();
					dto.setReply_no(rs.getInt("reply_no")); // 댓글 번호
					dto.setReply_book_no(rs.getInt("reply_book_no")); // 책번호
					dto.setReply_cont(rs.getString("reply_cont")); // 리뷰 내용
					dto.setReply_create_date(rs.getString("reply_create_date")); // 리뷰쓴 날짜
					dto.setReply_title(rs.getString("reply_title")); // 리뷰 제목
					dto.setReply_point(rs.getInt("reply_point")); // 리뷰 평점
					dto.setReply_like_cnt(rs.getInt("reply_like_cnt")); // 리뷰 좋아요 수
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
		
		//특정(번호에 맞는) 리뷰 가져오기
		public ReplyBean getmyReview(String no){
			ReplyBean dto=null;
			try {
				conn = ds.getConnection();
				String sql = "select * from reply where reply_no = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, no);
				rs= pstmt.executeQuery();
				
				while(rs.next()) {
					dto= new ReplyBean();
					dto.setReply_no(rs.getInt(1));
					dto.setReply_book_no(rs.getInt(2));
					dto.setReply_id(rs.getString(3));
					dto.setReply_cont(rs.getString(4));
					dto.setReply_create_date(rs.getString(5));
					dto.setReply_title(rs.getString(6));
					dto.setReply_point(rs.getInt(7));
					dto.setReply_image(rs.getString(8));
					dto.setReply_like_cnt(rs.getInt(9));
					dto.setReply_del_is(rs.getInt(10));
					dto.setReply_del_date(rs.getString(11));
					dto.setReply_ip(rs.getString(12));
					dto.setReply_blocked(rs.getInt(13));
					dto.setReply_blocked_cnt(rs.getInt(14));
					dto.setReply_gnum(rs.getInt(15));
					dto.setReply_onum(rs.getInt(16));
				}
			} catch (Exception e) {
				System.out.println("getmyReview err:"+e);
			}finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			return dto;
		}
	
		// 리뷰 삭제
		public boolean deleteReview(int no) {
			boolean b = false;
			try {
				conn = ds.getConnection();
				String sql = "update reply set reply_del_is=1, reply_del_date = now() where reply_no=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				if (pstmt.executeUpdate() > 0) {
					b = true;
				}
			} catch (Exception e) {
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
		
		// 삭제된 리뷰 찾기
		public BookDTO ismyDel(int no) {
			BookDTO dto = null;
			try {
				conn = ds.getConnection();
				String sql = "select * from reply where reply_no=? and reply_del_is=1";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					dto = new BookDTO();
					dto.setBnum(rs.getInt("bnum"));
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
		
		
		
		// ======== 페이징 시작 =============
		//내 리뷰 전체 레코드 수 계산(리뷰 onum=0)
		public void myReplyTot() {
			
			try {
				conn = ds.getConnection();
				String sql = "select count(*) from reply where reply_onum=0";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				rs.next();
				retot = rs.getInt(1); //전체 레코드수 저장

			} catch (Exception e) {
				System.out.println("myReplyTot err : " + e);
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
		
		public int getPageSu() {
			pageSu = retot / plist; //전체 페이지 수
			if(retot % plist > 0) //초과한다면
				pageSu++; 		  //+1
			return pageSu;
			
		}
		
		// ======== 페이징 끝 =============
		
		// ----------------------내 댓글 -------------------------------

		// 내 댓글 모두 조회 (삭제된 댓글 제외)
		public ArrayList<ReplyBean> myReplyAll(String id) {
			ArrayList<ReplyBean> list = new ArrayList<ReplyBean>();
			try {
				conn = ds.getConnection();
				String sql = "select reply_no,reply_book_no,reply_cont,reply_create_date,reply_title,reply_point,reply_like_cnt"
						+ " from reply where reply_id=? and reply_onum=1 and reply_del_is=0";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					ReplyBean dto = new ReplyBean();
					dto.setReply_no(rs.getInt("reply_no"));
					dto.setReply_book_no(rs.getInt("reply_book_no")); // 책번호
					dto.setReply_cont(rs.getString("reply_cont")); // 리뷰 내용
					dto.setReply_create_date(rs.getString("reply_create_date"));// 리뷰쓴 날짜
					dto.setReply_title(rs.getString("reply_title")); // 리뷰 제목
					dto.setReply_point(rs.getInt("reply_point")); // 리뷰 평점
					dto.setReply_like_cnt(rs.getInt("reply_like_cnt")); // 리뷰 좋아요 수
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

		// 댓글 삭제
		public boolean deleteReply(int no) {
			boolean b = false;
			try {
				conn = ds.getConnection();
				String sql = "update reply set reply_del_is=1, reply_del_date = now() where reply_no=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				if (pstmt.executeUpdate() > 0) {
					b = true;
				}

			} catch (Exception e) {
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

	
}
