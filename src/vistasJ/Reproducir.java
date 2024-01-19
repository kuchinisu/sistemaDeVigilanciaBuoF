package vistasJ;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.ImageView;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;

//import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;


public class Reproducir {
    //private static final String IMAGE_PATH  = "C:\\Users\\RODOLFO\\Documents\\visual_studio\\deep_corvus_seq\\x64\\Release\\javaUi\\javaFXUi\\video\\frames\\";
    private boolean ValorRepr = false;
    //private int sumarValorT = 0;
    public StackPane vistaReproduccion(){
        
        StackPane root = new StackPane();

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        //webEngine.load("file:///C:/ruta/del/video/video.html");

       
        return root;
    }

    public static List<Image> leerVideo(String pathVid) {
        List<Image> frames = new ArrayList<>();

        System.load("C:\\Program Files\\opencv\\build\\java\\x64\\opencv_java480.dll");

        try {
            VideoCapture videocap = new VideoCapture(pathVid);

            if (!videocap.isOpened()) {
                System.out.println("Problema con el video.");
                return frames;
            }

            Mat frame = new Mat();
            while (videocap.read(frame)) {
                // Convertir Mat a bytes
                MatOfByte matOfByte = new MatOfByte();
                Imgcodecs.imencode(".jpg", frame, matOfByte);

                // Crear un array de bytes desde la representación de Mat
                byte[] byteArray = matOfByte.toArray();

                // Convertir bytes a Image
                ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray);
                Image image = new Image(inputStream);

                // Agregar la imagen a la lista
                frames.add(image);

                // Limpiar MatOfByte 
                matOfByte.release();
            }
            videocap.release();
            frame.release();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return frames;
    };

    public boolean leerPixs(String pathImg){
        System.load("C:\\Program Files\\opencv\\build\\java\\x64\\opencv_java480.dll");
        Mat fr = Imgcodecs.imread(pathImg);
        int x = 255; 
        int y = 255; 

        
        double[] pixel = fr.get(y, x); 

        
        Scalar targetColor = new Scalar(1, 1, 255);
        double[] pixelT = {targetColor.val[0], targetColor.val[1], targetColor.val[2]};

        
        boolean p = compararPixeles(pixel, pixelT);
        

        return p;
    };

    ///
    private static boolean compararPixeles(double[] pixel1, double[] pixel2) {
        for (int i = 0; i < pixel1.length; i++) {
            if (pixel1[i] != pixel2[i]) {
                return false;
            }
        }
        return true;
    ///
}}
