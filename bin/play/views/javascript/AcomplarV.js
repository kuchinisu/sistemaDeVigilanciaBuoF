const botonSig = document.getElementById("sig");
const botonAnt = document.getElementById("ant")
var videoNumber = 0;

const count = document.getElementById("valVideo");
const videoContainer = document.getElementById("videoContainer");
const marcadores = document.getElementById("marcadores");
////
const barraTiempVid = document.getElementById("seekBar");
const tiempoAct = document.getElementById("tiempoAct");
const duracion = document.getElementById("duracion");

const video = document.getElementById("videoPlayer");

const valV = document.getElementById("valVideo");


function porcentajeT(val){
    return (val/1439) * 100;
}

function relojAbarra(horas, minutos){
    const valb = (horas * 60) + minutos;

    return valb;
};

function actualizaTiempo() {
    const tiempN = barraTiempVid.value;
    tiempoAct.innerText = formatearTiempo(tiempN);
    video.currentTime = tiempN * video.duration / 1439;
};

function formatearTiempo(tiempo) {
    const minutos = Math.floor(tiempo / 60);
    const segundos = Math.floor(tiempo % 60);
    return `${String(minutos).padStart(2, '0')}:${String(segundos).padStart(2, '0')}`;
};

function obtJson(url){
    
    return fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();  
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

//
function crearEtiquetas(numJ) {
    var cantEl = 0;
    var url = `json/detectados${numJ}.json`
    marcadores.innerHTML = "";
    obtJson(url)
        .then(data => {

            console.log(data);
            cantEl = Object.keys(data).length;
            const keyJ = Object.keys(data);

            for (let i = 0; i < cantEl; i++) {
                const lablet = data[keyJ[i]];
                const marcador = document.createElement("div");
                var cas;
                if (parseInt(lablet) > 91){
                    cas = "default";
                }else{
                    cas = lablet;
                }
                marcador.className = `marcador case_${cas}`;
                marcador.innerText = keyJ[i] + '- ' + lablet;

                const partes = keyJ[i].split(":");
                const h = parseInt(partes[0], 10);
                const m = parseInt(partes[1], 10);

                const mv = relojAbarra(h, m);

                marcador.style.left = `${porcentajeT(mv)}%`;
                //
                marcadores.appendChild(marcador);
            };

            barraTiempVid.max = Math.floor(video.duration / 60);

            
    });
};

////

const videoSrc = `media/video/${videoNumber}.mp4`;
    const videoElement = document.createElement('video');
    videoElement.width = 1140;  
    videoElement.height = 900; 
    videoElement.src = videoSrc;
    videoElement.controls = true;
    videoElement.className = 'videoPlayer';

    videoContainer.innerHTML = '';
    videoContainer.appendChild(videoElement);

function cmbVid() {
    const videoSrc = `media/video/${videoNumber}.mp4`;
    const videoElement = document.createElement('video');
        
    videoElement.width = 1140;  
    videoElement.height = 900; 
    
    videoElement.src = videoSrc;
    videoElement.controls = true;
    videoElement.className = 'videoPlayer';
    
    videoContainer.innerHTML = '';
    videoContainer.appendChild(videoElement);
    
    count.innerText = videoNumber;
}
    

function anterior() {
    videoNumber--;
    cmbVid();
    crearEtiquetas(videoNumber);
}

function siguiente() {
    console.log("Botón 'sig' clickeado");
    videoNumber++;
    cmbVid();
    crearEtiquetas(videoNumber);
}

crearEtiquetas(videoNumber);


botonSig.addEventListener("click", siguiente);
botonAnt.addEventListener("click", anterior);