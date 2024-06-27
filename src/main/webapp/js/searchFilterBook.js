

$(document).ready(function() {
    // 폼의 submit 이벤트 처리
    $("#searchForm").on("submit", function(event) {
        event.preventDefault();
        performSearch();
    });
});

function performSearch() {
	
    var title = $("#title").val();
    var author = $("#author").val();
    var publisher = $("#publisher").val();
    var pyear = $("#pyear").val();
    var bnum = $("#bnum").val();
    
      // 모든 필드가 비어 있는지 확인
    if (!title && !author && !publisher && !pyear && !bnum) {
        alert("검색 조건을 하나 이상 입력해주세요.");
        return; // 검색 요청을 중단
    }
    
console.log("searchFilterBook.js 파일에서 ajax () 수행하기 전 title 값 받음: " + title);
    $.ajax({
        url: "searchFilterProc.jsp",
        type: "GET",
        data: {
			
            title: title,
            author: author,
            publisher: publisher,
            pyear: pyear,
            bnum: bnum
        },
        success: function(data) {
            $("#results").html(data);
            $('html, body').animate({
                scrollTop: $("#results").offset().top
            }, 1000); // 1초 동안 부드럽게 스크롤
        },
        error: function() {
            alert("검색 중 오류가 발생했습니다.");
        }
    });
}
