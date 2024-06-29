/*mypage_mylikeFav 내가 찜한 목록 찜 해제*/
$(document).ready(function() {
	$(".likeBtn").click(function() {
		let like_no = $(this).attr("dataLikeNo"); //dataLikeNo 값 가져온다
		//alert(like_no); //2
		$.ajax({	
			type : "POST",
			url : "../mypage/myFavproc.jsp",
			data : {
				like_no : like_no //서버로 전송하는 데이터 설정. like_no 변수의 값 전송
			},
			success : function(response) {
				alert("찜이 취소되었습니다.");
				window.location.reload(); // 성공 시 페이지 새로고침
			},
			error : function(xhr, status, error) { //요청 실패시 콜백 함수
				alert("에러 발생 : "+ status);
				console.log(error);
			}
		});
	});
});
/* /////////  */
