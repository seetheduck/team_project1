<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="likeFavMgr" class="likefav.LikeFavMgr"/>
<jsp:useBean id="likeFavbean" class="likefav.LikeFavbean"/>
<%

int no = Integer.parseInt(request.getParameter("like_no"));

boolean b = likeFavMgr.checkFav(no); //찜을 한 경우 true

if(b){//찜 취소
	likeFavMgr.cancelLike(no);
	response.sendRedirect("likeFav.jsp");
}else{//찜하기
	/*auto-increment*/
	/* int newNum = likeFavMgr.currentMaxNum() +1;
	likeFavbean.setLike_no(newNum);

	likeFavMgr.FavBook(no);*/
}

%>