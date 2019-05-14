var selectedImageIndex = -1;

function nextImage() {
    var images = $('#slideshow li span');
    var numImages = images.length;
    var nextIndex = (selectedImageIndex + 1) % numImages;

    if (images[selectedImageIndex]) images[selectedImageIndex].style.opacity = '0';
    if (images[nextIndex]) images[nextIndex].style.opacity = '1';

    selectedImageIndex = nextIndex;
}

function toggleFullScreen() {
    if (!document.fullscreenElement && !document.webkitFullScreenElement && !document.mozFullScreenElement && !document.msFullScreenElement) {
        if (document.documentElement.requestFullscreen) {
            document.documentElement.requestFullscreen();
        } else if (document.documentElement.webkitRequestFullscreen) {
            document.documentElement.webkitRequestFullscreen();
        } else if (document.documentElement.mozRequestFullscreen) {
            document.documentElement.mozRequestFullscreen();
        } else if (document.documentElement.msRequestFullscreen) {
            document.documentElement.msRequestFullscreen();
        }
    } else {
        if (document.exitFullscreen) {
            document.exitFullscreen();
        } else if (document.webkitExitFullscreen) {
            document.webkitExitFullscreen();
        } else if (document.mozExitFullscreen) {
            document.mozExitFullscreen();
        } else if (document.msExitFullscreen) {
            document.msExitFullscreen();
        }
    }
}

document.onfullscreenchange = function() {
    var x = document.getElementById("goFullScreen");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
};

document.onwebkitfullscreenchange = function() {
    var x = document.getElementById("goFullScreen");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
};

function loopImages() {
    nextImage();
    setTimeout(loopImages, 6000);
}

document.addEventListener('DOMContentLoaded', loopImages);
