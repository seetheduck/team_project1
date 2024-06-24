$(document).ready(function() {
    fetch('../admin_main/getData.jsp')
        .then(response => response.json())  // 응답을 JSON으로 변환
        .then(data => {
            const ctx = document.getElementById('myChart').getContext('2d');
            
            // 서버에서 받은 데이터로부터 'sign_up_date'를 라벨로 사용
            const labels = data.data.map(item => item.sign_up_date);
            
            // 서버에서 받은 데이터로부터 각 'sign_up_date'의 'count' 값을 추출
            const counts = data.data.map(item => item.count);
            
            // counts 배열에서 최대값을 구하고, 10과 비교하여 더 큰 값을 maxY로 설정
            const maxY = Math.max(...counts, 10);
            
           
            if (window.casesChart) { // 이전에 생성된 차트가 있다면 파괴함
                window.casesChart.destroy();
            }

            window.casesChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: labels,  // x축에 표시될 라벨 설정
                    datasets: [{
                        label: '최근 가입 유저 현황',
                        data: counts,  // y축에 표시될 데이터
                        backgroundColor: 'rgba(54, 162, 235, 0.2)',
                        borderColor: 'rgba(54, 162, 235, 1)',
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true,
                            suggestedMin: 10,  // y축의 최소값 제안
                            max: maxY,  // y축의 최대값 설정
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
        })
        .catch(error => console.error('Error fetching data:', error)); // 데이터 요청 중 오류 발생 시 콘솔에 에러 출력
});