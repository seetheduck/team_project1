<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id = "likeFavbean" class="likeFav.LikeFavbean"/>
<jsp:useBean id="likeFavMgr" class="likeFav.LikeFavMgr"/>
<%
//String id = request.getParameter("id");
int like_no = Integer.parseInt(request.getParameter("like_no"));

boolean b = likeFavMgr.checkMyFav(like_no); //찜목록 확인, 이미 좋아요 true
//System.out.println(b);
	if(b != true){ //찜 취소
	      likeFavMgr.canLike(like_no); //찜하기 취소
	}
%>

