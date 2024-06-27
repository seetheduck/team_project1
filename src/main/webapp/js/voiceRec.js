let recognition;
let isRecognizing = false;

function availabilityFunc() {
    if ('webkitSpeechRecognition' in window) {
        recognition = new webkitSpeechRecognition();
    } else if ('SpeechRecognition' in window) {
        recognition = new SpeechRecognition();
    } else {
        alert("현재 브라우저는 음성 인식을 지원하지 않습니다.");
        return;
    }

    recognition.lang = "ko";
    recognition.maxAlternatives = 5;

    recognition.addEventListener("speechstart", () => {
        console.log("음성 인식 시작");
        isRecognizing = true;
    });

    recognition.addEventListener("speechend", () => {
        console.log("음성 인식 종료");
        recognition.stop();
        isRecognizing = false;
    });

    recognition.addEventListener("result", (e) => {
        const searchConsole = document.querySelector(".search_txt");
        if (searchConsole) {
            searchConsole.value = e.results[0][0].transcript;
            console.log("인식된 텍스트: ", searchConsole.value);
        }
    });

    recognition.addEventListener("error", (e) => {
        console.error("음성 인식 오류: ", e);
        isRecognizing = false;
    });
}

function startRecord() {
    if (recognition && !isRecognizing) {
        recognition.start();
        console.log("음성 인식 중...");
    }
}

function endRecord() {
    if (recognition && isRecognizing) {
        recognition.stop();
        console.log("음성 인식 중단");
    }
}

window.addEventListener("load", availabilityFunc);
