package report;

import lombok.Data;

@Data
public class ReportDTO {
	private int report_no,report_reply_no,report_code;
	private String reporter_user_id,report_date,reporter_ip,reported_user_ip,reported_user_no;

}
