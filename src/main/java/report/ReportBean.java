package report;

import java.time.LocalDate;

public class ReportBean {
	private int report_no,report_reply_no;
	private String reporter_user_id,report_date,report_code,reporter_ip,reported_user_ip,reported_user_no;

	public int getReport_no() {
		return report_no;
	}

	public void setReport_no(int report_no) {
		this.report_no = report_no;
	}

	public int getReport_reply_no() {
		return report_reply_no;
	}

	public void setReport_reply_no(int report_reply_no) {
		this.report_reply_no = report_reply_no;
	}

	public String getReporter_user_id() {
		return reporter_user_id;
	}

	public void setReporter_user_id(String reporter_user_id) {
		this.reporter_user_id = reporter_user_id;
	}

	public String getReport_date() {
		return report_date;
	}

	public void setReport_date(String report_date) {
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month= now.getMonthValue();
		int day = now.getDayOfMonth();
		
		this.report_date = year+"-"+month+"-"+day;;
	}

	public String getReport_code() {
		return report_code;
	}

	public void setReport_code(String report_code) {
		this.report_code = report_code;
	}

	public String getReporter_ip() {
		return reporter_ip;
	}

	public void setReporter_ip(String reporter_ip) {
		this.reporter_ip = reporter_ip;
	}

	public String getReported_user_ip() {
		return reported_user_ip;
	}

	public void setReported_user_ip(String reported_user_ip) {
		this.reported_user_ip = reported_user_ip;
	}

	public String getReported_user_no() {
		return reported_user_no;
	}

	public void setReported_user_no(String reported_user_no) {
		this.reported_user_no = reported_user_no;
	}
	
	
	
	
	
}
