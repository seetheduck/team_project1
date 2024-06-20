function deleteReply(no){
	if(confirm('삭제하시겠습니까?')){
		alert(no);
		document.delFrm.reply_no.value = no;
		document.delFrm.submit();
	}
}