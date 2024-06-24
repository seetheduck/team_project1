package report;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReportBean {
	private int report_no,report_reply_no,report_code;
	private String reporter_user_id,report_date,reporter_ip,reported_user_ip,reported_user_no;

	public void setReport_date(String report_date) {
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month= now.getMonthValue();
		int day = now.getDayOfMonth();
		
		this.report_date = year+"-"+month+"-"+day;;
	}
	
	
}
