$(document).ready(function() {
			// 폼의 submit 이벤트 처리
			$("#searchForm").on("submit", function(event) {
				event.preventDefault();
				performSearch();
			});
		});

		function performSearch() {
			var title = $('#searchInput').val();
			console.log("searchBook.js 파일에서 performSearch함수 수행하기 전 값 받음: " + title);
			window.location.href = `../search/searchResult.jsp?title=${encodeURIComponent(title)}`;
		}