
        $(document).ready(function() {
            var urlParams = new URLSearchParams(window.location.search);
           /*
           window.location.search는 현재 페이지 URL의 쿼리 문자열 부분을 반환합니다. 
           예를 들어, URL이 http://example.com?title=Java인 경우 window.location.search는 
           ?title=Java를 반환합니다.
           이 쿼리 문자열을 파싱하여 쿼리 파라미터를 쉽게 읽을 수 있는 URLSearchParams 객체를 만듭니다.
           */
            var title = urlParams.get('title');
			/*
			URLSearchParams 객체에서 title이라는 이름의 쿼리 파라미터 값을 추출합니다. 
			예를 들어, URL이 http://example.com?title=Java인 경우, title 변수는 "Java" 값을 가집니다.
			*/
            if (title) {
                fetchResults(title);
            }
			//title에 값이 있을 경우 fetchResult(title) 실행
            function fetchResults(title) {
                $.ajax({
                    url: "searchProc.jsp",
                    type: "GET",
                    data: { title: title },
                    success: function(data) {
                        $("#results").html(data);
                    },
                    error: function() {
                        alert("검색 중 오류가 발생했습니다.");
                    }
                });
            }
        });
  