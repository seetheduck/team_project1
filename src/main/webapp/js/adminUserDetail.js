
function memUpdate(id) {
    $("#userDetailModal").show();
}



$(document).ready(function() {
	
	
           
           
    
    // 모달 닫기 버튼 클릭 시
    $(".close").click(function() {
        $("#userDetailModal").hide();
    });
});
    // 모달을 여는 함수 정의
    function openModal() {
        var modal = document.getElementById("userDetailModal");
        if (modal) {
            modal.style.display = "block";

            // 모달을 닫는 <span> 요소 가져오기
            var span = document.getElementsByClassName("close")[0];

            // 사용자가 <span> (x)를 클릭하면 모달 닫기
            span.onclick = function() {
                modal.style.display = "none";
            }

            // 사용자가 모달 밖의 아무 곳이나 클릭하면 모달 닫기
            window.onclick = function(event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        } else {
            console.error('Modal element not found');
        }
    }

    // adUserDetail 함수 정의
    window.adUserDetail = function(no) {
        // AJAX를 사용하여 데이터 전송
        $.ajax({
            url: 'adminUserDetail.jsp', // 데이터를 전송할 서버 URL
            type: 'GET', // GET 방식으로 요청
            data: { no: no }, // 전송할 데이터
            success: function(response) {
                // 페이지 이동이 아닌, 받은 응답을 모달에 출력
                $('#userDetailModal .modal-content').html(response);
                openModal(); // 모달 열기
            },
            error: function(status, error) {
                console.error('ajax err : ', status, error);
            }
        });
    };


  $(".close").click(function() {
        $("#userDetailModal").hide();
    });

    $("#updateForm").submit(function(event) {
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: "userUpdateProc.jsp",
            data: $(this).serialize(),
            success: function(response) {
                alert("수정되었습니다.");
                $("#userDetailModal").hide();
                location.reload();
            },
            error: function(error) {
                alert("수정 중 오류가 발생했습니다.");
            }
        });
    });


function memberUpdateOk(){ 
	//입력자료 오류검사 생략
	document.updateForm.submit();
}
function memberUpdateCancel(){
	location.href="../admin_user/adminUser.jsp";
}



