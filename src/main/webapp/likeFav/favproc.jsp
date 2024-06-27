<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id = "likeFavbean" class="likeFav.LikeFavbean"/>
<jsp:useBean id="likeFavMgr" class="likeFav.LikeFavMgr"/>
<%
String id = request.getParameter("id");
int bookNo = Integer.parseInt(request.getParameter("reply_book_no")); // 리뷰달린 책 번호
String action =  request.getParameter("action");//찜 확인용 fav / unfav
//System.out.print(action);
 //찜한 목록인지 확인, 이미 좋아요 true
boolean b = likeFavMgr.checkFav(bookNo,id);


if(action.equals("fav")){
	//마지막번호 + 1
	int newNum = likeFavMgr.currentMaxNum() +1;
	likeFavbean.setLike_no(newNum);
	
	likeFavbean.setLike_user_id(id);
	likeFavbean.setLike_book_no(bookNo);
	
	likeFavMgr.FavBook(likeFavbean); //찜하기 구현
	
}else if(action.equals("unfav")){
	boolean bb =  likeFavMgr.cancelLike(bookNo); //찜하기 취소
	
	System.out.print(bb);
}
%>




