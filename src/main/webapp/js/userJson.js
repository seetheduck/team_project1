
$(document).ready(function(){
    $('#test4').on("click", function(){
        $("#disp").empty();
        $("#userDetail").empty();

        $.ajax({
            type: "get",
            url: "userJson.jsp",
            dataType: "json",
            success: function(datas){
                let str = "";
                let count = 0;
                str += "<table>";
                str += "<tr><th>아이디</th><th>이름</th><th>상세보기</th></tr>";

                $.each(datas.user, function(idx, data){
                    str += `
                        <tr>
                            <td>${data.id}</td>
                            <td>${data.uname}</td>
                            <td><button onclick='showDetail("${data.id}")'>상세보기</button></td>
                        </tr>
                    `;
                    count++;
                });
                str += "</table>";
                $("#disp").append(str);
            },
            error: function(xhr, status, error){
                console.error('AJAX Error: ' + status + ': ' + error);
                console.error('Response Text: ' + xhr.responseText);
                $("#disp").text('회원 목록을 불러오는 중 오류가 발생했습니다.');
            }
        });
    });

    // 모달 닫기
    $(document).on("click", ".close", function() {
        $("#myModal").hide();
    });

    // 모달 외부 클릭 시 닫기
    $(window).on("click", function(event) {
        if (event.target.id == "myModal") {
            $("#myModal").hide();
        }
    });
});

function showDetail(userId) {
    $.ajax({
        type: "get",
        url: "userDetailJson.jsp",
        data: { id: userId },
        dataType: "json",
        success: function(data) {
            let str = "<h3>회원 상세 정보</h3>";
            str += `<p>아이디: ${data.id}</p>`;
            str += `<p>패스워드: ${data.pw}</p>`;
            str += `<p>이름: ${data.uname}</p>`;
            str += `<p>이메일: ${data.email}</p>`;
            $("#modal-body").html(str);
            $("#myModal").show();
        },
        error: function(xhr, status, error) {
            console.error('AJAX Error: ' + status + ': ' + error);
            console.error('Response Text: ' + xhr.responseText);
            $("#userDetail").text('회원 상세 정보를 불러오는 중 오류가 발생했습니다.');
        }
    });
}
