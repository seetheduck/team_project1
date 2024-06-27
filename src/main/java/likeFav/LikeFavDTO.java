package likeFav;

public class LikeFavDTO { //좋아요 찜
	private String like_user_id;
	private int like_no,		//좋아요,찜 고유넘버
				like_reply_no,	//리뷰,댓글 넘버
				like_fav_is,	//좋아요(글) = 0 / 찜(책) = 1
				like_book_no;
	
	public String getLike_user_id() {
		return like_user_id;
	}
	public void setLike_user_id(String like_user_id) {
		this.like_user_id = like_user_id;
	}
	public int getLike_no() {
		return like_no;
	}
	public void setLike_no(int like_no) {
		this.like_no = like_no;
	}
	public int getLike_reply_no() {
		return like_reply_no;
	}
	public void setLike_reply_no(int like_reply_no) {
		this.like_reply_no = like_reply_no;
	}
	public int getLike_fav_is() {
		return like_fav_is;
	}
	public void setLike_fav_is(int like_fav_is) {
		this.like_fav_is = like_fav_is;
	}
	public int getLike_book_no() {
		return like_book_no;
	}
	public void setLike_book_no(int like_book_no) {
		this.like_book_no = like_book_no;
	}

	
}
