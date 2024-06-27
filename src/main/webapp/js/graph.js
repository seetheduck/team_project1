$(document).ready(function() {
    function fetchData(url) {
        return fetch(url).then(response => response.json());
    }

    // Fetch user sign-up data
    const userDataPromise = fetchData('../admin_main/getData.jsp');
    // Fetch reply data
    const replyDataPromise = fetchData('../admin_main/getDataReply.jsp');

    Promise.all([userDataPromise, replyDataPromise])
        .then(([userData, replyData]) => {
            const barCtx = document.getElementById('myBarChart').getContext('2d');
            const lineCtx = document.getElementById('myLineChart').getContext('2d');
            
            // 오늘 날짜로부터 7일 전의 날짜 계산
            const today = new Date();
            const oneWeekAgo = new Date();
            oneWeekAgo.setDate(today.getDate() - 7);
            
            // 데이터 필터링: 7일 이내의 데이터만 포함
            const recentUserData = userData.data.filter(item => new Date(item.sign_up_date) >= oneWeekAgo);
            const recentReplyData = replyData.data.filter(item => new Date(item.reply_create_date) >= oneWeekAgo);

            // 최근 7일의 데이터로부터 'sign_up_date'를 라벨로 사용
            const userLabels = recentUserData.map(item => item.sign_up_date);
            const replyLabels = recentReplyData.map(item => item.reply_create_date);
            
            // 최근 7일의 데이터로부터 각 'sign_up_date'의 'count' 값을 추출
            const userCounts = recentUserData.map(item => item.count);
            const replyCounts = recentReplyData.map(item => item.count);
            
            // counts 배열에서 최대값을 구하고, 10과 비교하여 더 큰 값을 maxY로 설정
            const maxYUser = Math.max(...userCounts, 10);
            const maxYReply = Math.max(...replyCounts, 10);
           
            if (window.casesBarChart) { // 이전에 생성된 바 차트가 있다면 파괴함
                window.casesBarChart.destroy();
            }

            if (window.casesLineChart) { // 이전에 생성된 라인 차트가 있다면 파괴함
                window.casesLineChart.destroy();
            }

            // 바 차트 생성
            window.casesBarChart = new Chart(barCtx, {
                type: 'bar',
                data: {
                    labels: userLabels,  // x축에 표시될 라벨 설정
                    datasets: [{
                        label: '최근 가입 유저 현황',
                        data: userCounts,  // y축에 표시될 데이터
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                        borderColor: 'rgba(75, 192, 192, 1)',
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true,
                            suggestedMin: 10,  // y축의 최소값 제안
                            max: maxYUser,  // y축의 최대값 설정
                            title: {
                                display: true,
                                text: '가입자 수'
                            }
                        },
                        x: {
                            title: {
                                display: true,
                                text: '가입 일자'
                            }
                        }
                    },
                    responsive: true,  // 차트가 반응형일지 설정
                    maintainAspectRatio: false  // 종횡비 유지 설정
                }
            });

            // 라인 차트 생성
            window.casesLineChart = new Chart(lineCtx, {
                type: 'line',
                data: {
                    labels: replyLabels,  // x축에 표시될 라벨 설정
                    datasets: [{
                        label: '최근 리뷰 작성 현황',
                        data: replyCounts,  // y축에 표시될 데이터
                        backgroundColor: 'rgba(231, 250, 229, 1)',
                        borderColor: 'rgba(145, 234, 139, 1)',
                        borderWidth: 1,
                        fill: true  // 라인 그래프의 아래를 채우지 않음
                    }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true,
                            suggestedMin: 10,  // y축의 최소값 제안
                            max: maxYReply,  // y축의 최대값 설정
                            title: {
                                display: true,
                                text: '댓글 수'
                            }
                        },
                        x: {
                            title: {
                                display: true,
                                text: '댓글 작성 일자'
                            }
                        }
                    },
                    responsive: true,  // 차트가 반응형일지 설정
                    maintainAspectRatio: false  // 종횡비 유지 설정
                }
            });
        })
        .catch(error => console.error('Error fetching data:', error)); // 데이터 요청 중 오류 발생 시 콘솔에 에러 출력
});
