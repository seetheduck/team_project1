package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import reply.ReplyBean;
import reply.ReplyDto;
import report.ReportBean;
import user.UserBean;

public class AdminMgr {
    private Connection conn;      // DB 연결을 관리하는 객체
    private PreparedStatement pstmt; // SQL 문을 실행하는 객체
    private ResultSet rs;         // SQL 쿼리 결과를 담는 객체
    private DataSource ds;        // 데이터 소스, DB 연결을 위한 객체

    private int recTotal = 0; //레코드 전체 갯수
	private int pageSize = 5; // 페이지 당 출력 레코드 수
	private int totalPage = 0; //전체 페이지 수
    
    // 클래스 생성자
    public AdminMgr() {
        try {
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/jdbc_maria");
        } catch (Exception e) {
            System.out.println("db 연결 실패 : " + e);
        }
    }
    
    public boolean adminLoginCheck(String id, String pw) {
    	boolean b = false;
    	
    	try {
			conn = ds.getConnection();
			String sql = "select * from admin where id=? and pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			b = rs.next();
    		
		} catch (Exception e) {
            System.out.println("adminLoginCheck error : " + e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                
            }
        }
    	
    	return b;
    }
    
    // user테이블의 sign_up_date 데이터를 json으로 반환
    public String getDataUser() {
        String res = "{ \"data\": [";
        try {
            conn = ds.getConnection();
            String sql = "select date_format(sign_up_date, '%Y-%m-%d') as date, count(*) as count from user group by date order by date";
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
                
            }
        }
        res += "]}";
        return res;
    }
    
    public String getDataReply() {
        String res = "{ \"data\": [";
        try {
            conn = ds.getConnection();
            String sql = "select date_format(reply_create_date, '%Y-%m-%d') as date, count(*) as count from reply where reply_create_date >= curdate() - interval 5 day group by date order by date";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            boolean isFir = true; // 첫번째 항목인지 확인하기 위한 플래그
            while (rs.next()) {
                if (!isFir) { // 첫번째 항목이 아닐 경우,
                    res += ","; // json 배열에 객체 추가 전 사이에 콤마 추가
                }
                isFir = false; // 첫번째 항목 처리됨
                // json 데이터 추가
                res += "{\"reply_create_date\":\"" + rs.getString("date") + "\", \"count\":" + rs.getInt("count") + "}";
            }
        } catch (Exception e) {
            System.out.println("getDataReply error : " + e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
            }
        }
        res += "]}";
        return res;
    }
    
    public ArrayList<ReportBean> getReportAll(){
    	ArrayList<ReportBean> list = new ArrayList<ReportBean>();
    	
    	try {
			conn = ds.getConnection();
			String sql = "select * from report order by report_no desc";
    		pstmt = conn.prepareStatement(sql);
    		rs = pstmt.executeQuery();
    		
    		while(rs.next()) {
    			ReportBean dto = new ReportBean();
    			dto.setReport_no(rs.getInt("report_no"));
    			dto.setReporter_user_id(rs.getString("reporter_user_id"));
    			dto.setReport_date(rs.getString("report_date"));
    			dto.setReport_code(rs.getInt("report_code"));
    			dto.setReported_user_no(rs.getString("reported_user_no"));
    			dto.setReport_reply_no(rs.getInt("report_reply_no"));
    			list.add(dto);
    			
    		}
    		
		} catch (Exception e) {
            System.out.println("getReportAll error : " + e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                
            }
        }
    	
    	return list;
    }
    
    public ReplyBean getReply(int report_reply_no) {
    	ReplyBean bean = new ReplyBean();
    	
    	try {
			conn = ds.getConnection();
			String sql = "select * from reply where reply_no in (select report_reply_no from report where report_reply_no=?)";
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, report_reply_no);
    		rs = pstmt.executeQuery();
			
    		if(rs.next()) {
    			bean.setReply_no(rs.getInt("reply_no"));
    			bean.setReply_book_no(rs.getInt("reply_book_no"));
    			bean.setReply_id(rs.getString("reply_id"));
    			bean.setReply_cont(rs.getString("reply_cont"));
    			bean.setReply_create_date(rs.getString("reply_create_date"));
    			bean.setReply_title(rs.getString("reply_title"));
    			bean.setReply_ip(rs.getString("reply_ip"));
    			bean.setReply_blocked(rs.getInt("reply_blocked"));
    			bean.setReply_blocked_cnt(rs.getInt("reply_blocked_cnt"));
    			bean.setReply_gnum(rs.getInt("reply_gnum"));
    		}
			
		} catch (Exception e) {
            System.out.println("getReply error : " + e);
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
    
    public void blockReply(int report_reply_no) {
    	try {
			conn= ds.getConnection();
			String sql = "update reply set reply_blocked = 1, reply_blocked_cnt = reply_blocked_cnt + 1 "
					+ "where reply_no in (select report_reply_no from report where report_reply_no=?)";
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, report_reply_no);
    		pstmt.executeUpdate();
			
		} catch (Exception e) {
            System.out.println("blockReply error : " + e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                
            }
        }
    	
    }
    
    public void unblockReply(int report_reply_no) {
        try {
            conn = ds.getConnection();
            String sql = "UPDATE reply SET reply_blocked = 0, reply_blocked_cnt = reply_blocked_cnt - 1 WHERE reply_no = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, report_reply_no);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("unblockReply error : " + e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public UserBean getUser(int no) {
		UserBean bean = null;
		try {
			conn = ds.getConnection();
			String sql = "select * from user where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean = new UserBean();
				bean.setNo(rs.getInt("no"));
				bean.setId(rs.getString("id"));
				bean.setPw(rs.getString("pw"));
				bean.setUname(rs.getString("uname"));
				bean.setEmail(rs.getString("email"));
				bean.setGender(rs.getInt("gender"));
				bean.setSignout_is(rs.getInt("signout_is"));
				bean.setSign_up_date(rs.getString("sign_up_date"));
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
    
    public boolean userUpdate(UserBean userBean, String id) {
		boolean b = false;
		
		try {
			conn = ds.getConnection();
			String sql = "update member set pw=?,uname=?,email=?,gender=?,signout_is=?,sign_up_date=?,no=? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userBean.getPw());
			pstmt.setString(2, userBean.getUname());
			pstmt.setString(3, userBean.getEmail());
			pstmt.setInt(4, userBean.getGender());
			pstmt.setInt(5, userBean.getSignout_is());
			pstmt.setString(6, userBean.getSign_up_date());
			pstmt.setInt(7, userBean.getNo());
			pstmt.setString(8, id);
			if(pstmt.executeUpdate() > 0) b = true;
			
		} catch (Exception e) {
			System.out.println("userUpdate err :" + e);
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
    
    public ArrayList<ReplyDto> getReplyAdmin(){
		ArrayList<ReplyDto> list = new ArrayList<ReplyDto>();
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM reply";
			pstmt = conn.prepareStatement(sql);
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
			System.out.println("getReplyAdmin() err:"+e);
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
    
	
    //---------------------------------------------------------------------------------------//
    public int userTotalPage() {
		try {
			
			conn = ds.getConnection();
			String sql = "select count(*) from user";
    		pstmt = conn.prepareStatement(sql);
    		rs = pstmt.executeQuery();
			
			if(rs.next()) recTotal = rs.getInt(1); //한칸내려와보니 값이있으면 첫번째 값을 recTotal에게 넘겨준다
			
			//전체 페이지 수 구하기
			totalPage = recTotal / pageSize;
			if(recTotal % pageSize != 0) totalPage += 1; // 자투리 계산 
			System.out.println("전체 페이지 수: "+ totalPage);
			
		} catch (Exception e) {
			System.out.println("userTotalPage err:"+ e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return totalPage;
		
	}
   
    public int reportTotalPage() {
		try {
			
			conn = ds.getConnection();
			String sql = "select count(*) from report";
    		pstmt = conn.prepareStatement(sql);
    		rs = pstmt.executeQuery();
			
			if(rs.next()) recTotal = rs.getInt(1); //한칸내려와보니 값이있으면 첫번째 값을 recTotal에게 넘겨준다
			
			//전체 페이지 수 구하기
			totalPage = recTotal / pageSize;
			if(recTotal % pageSize != 0) totalPage += 1; // 자투리 계산 
			System.out.println("전체 페이지 수: "+ totalPage);
			
		} catch (Exception e) {
			System.out.println("reportTotalPage err:"+ e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return totalPage;
		
	}
    
    
	public ArrayList<UserBean> getUserAll(String pa){
		//System.out.println("pa:"+pa);
		
		ArrayList<UserBean> list = new ArrayList<UserBean>();
		try {
			conn = ds.getConnection();
			String sql = "select * from user order by no desc";
    		pstmt = conn.prepareStatement(sql);
    		rs = pstmt.executeQuery();
			
			
			
			int startNum = (Integer.parseInt(pa)-1) * pageSize +1;
			for(int p = 1; p<startNum; p++) {
				rs.next(); 
				//레코드포인터 위치 이동 pa:1(recPoint:0), pa:2(recPoint:5),  pa:3(recPoint:10)
				
			}
			int i=1;
			
			while(rs.next() && i<= pageSize) {
    			UserBean dto = new UserBean();
    			dto.setNo(rs.getInt("no"));
    			dto.setId(rs.getString("id"));
    			dto.setPw(rs.getString("pw"));
    			dto.setUname(rs.getString("uname"));
    			dto.setGender(rs.getInt("gender"));
    			dto.setEmail(rs.getString("email"));
    			dto.setSignout_is(rs.getInt("signout_is"));
    			dto.setSign_up_date(rs.getString("sign_up_date"));
    			list.add(dto);
    			i++;
    		}
		} catch (Exception e) {
			System.out.println("getDataAll err:"+ e);
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
	
	public ArrayList<ReplyDto> getReplyDash(){
		ArrayList<ReplyDto> list = new ArrayList<ReplyDto>();
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT reply_no, reply_book_no, "
					+ "DATE_FORMAT(reply_create_date, '%Y-%m-%d') as reply_create_date, reply_like_cnt, reply_ip "
					+ "FROM reply WHERE reply_blocked=0 ORDER BY reply_create_date DESC LIMIT 5";
			//blocked 1이면 안뜨고 reply_create_date 기준 최근 5개의 글만 나오게
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			
			
		while(rs.next()) {
			ReplyDto dto= new ReplyDto();
			dto.setReply_no(rs.getInt(1));
			dto.setReply_book_no(rs.getInt(2));
			dto.setReply_create_date(rs.getString(3));
			dto.setReply_like_cnt(rs.getInt(4));
			dto.setReply_ip(rs.getString(5));
			list.add(dto);
		}
			
		} catch (Exception e) {
			System.out.println("getReplyDash() err:"+e);
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
	
}