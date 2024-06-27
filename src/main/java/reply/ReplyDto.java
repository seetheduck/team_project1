package reply;

public class ReplyDto {
	private String reply_id,	//글쓴이ID
			reply_cont,			//내용
			reply_create_date,	//쓴 날짜
			reply_ip,			//작성자 ip
			reply_modify_date,	//수정 날짜
			reply_del_date,		//삭제 날짜
			reply_title,		//제목
			reply_image;		//이미지
			
	
	private int reply_no,		//글넘버(pk)
			reply_book_no,		//도서넘버
			reply_point,		//도서 평점
			reply_like_cnt,		//도서 좋아요 수
			reply_del_is,		//삭제 유무
			reply_blocked,		//차단 유무(0/1)
			reply_blocked_cnt,	//신고된 수
			reply_gnum,			//리뷰 넘버
			reply_onum;			//0 이외의 댓글넘버


	public String getReply_id() {
		return reply_id;
	}


	public void setReply_id(String reply_id) {
		this.reply_id = reply_id;
	}


	public String getReply_cont() {
		return reply_cont;
	}


	public void setReply_cont(String reply_cont) {
		this.reply_cont = reply_cont;
	}


	public String getReply_create_date() {
		return reply_create_date;
	}


	public void setReply_create_date(String reply_create_date) {
		this.reply_create_date = reply_create_date;
	}


	public String getReply_ip() {
		return reply_ip;
	}


	public void setReply_ip(String reply_ip) {
		this.reply_ip = reply_ip;
	}


	public String getReply_modify_date() {
		return reply_modify_date;
	}


	public void setReply_modify_date(String reply_modify_date) {
		this.reply_modify_date = reply_modify_date;
	}


	public String getReply_del_date() {
		return reply_del_date;
	}


	public void setReply_del_date(String reply_del_date) {
		this.reply_del_date = reply_del_date;
	}


	public String getReply_title() {
		return reply_title;
	}


	public void setReply_title(String reply_title) {
		this.reply_title = reply_title;
	}


	public String getReply_image() {
		return reply_image;
	}


	public void setReply_image(String reply_image) {
		this.reply_image = reply_image;
	}


	public int getReply_no() {
		return reply_no;
	}


	public void setReply_no(int reply_no) {
		this.reply_no = reply_no;
	}


	public int getReply_book_no() {
		return reply_book_no;
	}


	public void setReply_book_no(int reply_book_no) {
		this.reply_book_no = reply_book_no;
	}


	public int getReply_point() {
		return reply_point;
	}


	public void setReply_point(int reply_point) {
		this.reply_point = reply_point;
	}


	public int getReply_like_cnt() {
		return reply_like_cnt;
	}


	public void setReply_like_cnt(int reply_like_cnt) {
		this.reply_like_cnt = reply_like_cnt;
	}


	public int getReply_del_is() {
		return reply_del_is;
	}


	public void setReply_del_is(int reply_del_is) {
		this.reply_del_is = reply_del_is;
	}


	public int getReply_blocked() {
		return reply_blocked;
	}


	public void setReply_blocked(int reply_blocked) {
		this.reply_blocked = reply_blocked;
	}


	public int getReply_blocked_cnt() {
		return reply_blocked_cnt;
	}


	public void setReply_blocked_cnt(int reply_blocked_cnt) {
		this.reply_blocked_cnt = reply_blocked_cnt;
	}


	public int getReply_gnum() {
		return reply_gnum;
	}


	public void setReply_gnum(int reply_gnum) {
		this.reply_gnum = reply_gnum;
	}


	public int getReply_onum() {
		return reply_onum;
	}


	public void setReply_onum(int reply_onum) {
		this.reply_onum = reply_onum;
	}
}