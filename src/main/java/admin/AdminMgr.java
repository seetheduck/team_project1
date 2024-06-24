package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import reply.ReplyBean;
import report.ReportBean;
import report.ReportDTO;

public class AdminMgr {
    private Connection conn;      // DB 연결을 관리하는 객체
    private PreparedStatement pstmt; // SQL 문을 실행하는 객체
    private ResultSet rs;         // SQL 쿼리 결과를 담는 객체
    private DataSource ds;        // 데이터 소스, DB 연결을 위한 객체

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
    
}