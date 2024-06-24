/* 신고 버튼 클릭 이벤트 */
function report(form){
	/*유효성 검사*/
	//아무 신고버튼을 누르지 않는 경우
	if($("input[name=cbRep]:checked").length == 0){
		alert('신고 유형을 선택해주세요');
		return;
	};
	form.submit();
};