let currentSlide = 0;
const slides = document.querySelectorAll('.inner');
const slideCount = slides.length;

function showSlide(n) {
    const innerSlide = document.querySelector('.inner_slide');
    innerSlide.style.transform = `translateX(-${n * 100}%)`;
}

function nextSlide() {
    currentSlide = (currentSlide + 1) % slideCount;
    showSlide(currentSlide);
}

function prevSlide() {
    currentSlide = (currentSlide - 1 + slideCount) % slideCount;
    showSlide(currentSlide);
}

document.addEventListener('DOMContentLoaded', () => {
    showSlide(currentSlide);
    setInterval(nextSlide, 3000); // 3초마다 자동 슬라이드
});