function likeReply(){ // 'btnLik'라는 ID를 가진 버튼 요소를 가져옵니다.
	const btnElement = document.getElementById('btnLik');
	
	// 버튼 요소의 현재 값이 "좋아요"인지 확인
	if(btnElement.value === "좋아요"){ // 현재 값이 "좋아요"라면 "좋아요취소"로 변경
		btnElement.value === "좋아요취소";
	}else{// 현재 값이 "좋아요취소"라면 다시 "좋아요"로 변경
		btnElement.value="좋아요";	
	}
	
}