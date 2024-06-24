function deleteReply(no){
	if(confirm){
		alert(no);
		document.delFrm.reply_no.value = no;
		document.delFrm.submit();
	}
}

function reviewDetail(no){/* 리뷰 디테일로 바로가기 */
	let detailurl ="replyDetail.jsp?reply_no="+no;
	window.location.href = detailurl
}