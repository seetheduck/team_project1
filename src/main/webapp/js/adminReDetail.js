$(document).ready(function() {
    // adReportDetail 함수 정의
    window.adReportDetail = function(report_reply_no) {
        // AJAX를 사용하여 데이터 전송
        $.ajax({
            url: 'adminReportDetail.jsp', // 데이터를 전송할 서버 URL
            type: 'GET', // GET 방식으로 요청
            data: { report_reply_no: report_reply_no }, // 전송할 데이터
            success: function() {
                location.href = 'adminReportDetail.jsp?report_reply_no=' + report_reply_no;
            },
            error: function(status, error) {
                console.error('ajax err : ', status, error);
            }
        });
    };
});