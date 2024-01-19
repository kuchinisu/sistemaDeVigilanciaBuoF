//const fs = require('fs');






barraTiempVid.addEventListener("input", actualizaTiempo);
video.addEventListener('timeupdate', () => {
    barraProgreso.value = video.currentTime;
});

