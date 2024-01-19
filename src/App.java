import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.Reader;
//import java.util.Stack;
import java.nio.file.Path;
import java.nio.file.Paths;

import vistasJ.Reproducir;
import vistasJ.fotos;
import vistasJ.Monitoreo;
import vistasJ.Sonidos;
import vistasJ.ComboxLabels;


public class App extends Application {
    private fotos opcForo = new fotos();
    ///elementos para la busqueda rectangulo
    private boolean buscando = false;
    private boolean escrito = true;
    private String pathUsClip = "C:\\Users\\RODOLFO\\Documents\\visual_studio\\deep_corvus_seq\\x64\\Release\\javaUi\\javaFXUi\\src\\recados\\usarClip.txt";
    String pred;
    TextArea textField = new TextArea();
    Monitoreo monitoreoVis = new Monitoreo();
    VBox monitorInt = monitoreoVis.constRMonitor(buscando, escrito, textField);
    ////sonido
    Sonidos alarma = new Sonidos();
    ///
    //private String ldet;
    
    private int vueltas = 0;
    private String rutaArchivoS;
    public String directorioActual = System.getProperty("user.dir");
    @Override
    public void start(Stage primaryStage) {
        ComboxLabels combbxlab = new ComboxLabels();
        
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            int camsNum = combbxlab.imgCant;
            for(int i = 0; i<=camsNum; i++){
                String rutaArchivoS = directorioActual + "\\src\\recados\\detects" +(i+1) +".txt";

                try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivoS))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        combbxlab.labesDets.add(linea);
                        //ldet=linea;
                    }
                } catch (IOException e) {
                e.printStackTrace();
                };
                
            }
            
            
            if(combbxlab.ppals != null){
                
                for (String palabra : combbxlab.ppals) {
                    System.out.println(palabra);
                    
                    for (int i = 0; i <= camsNum; i++){
                        
                        if(combbxlab.labesDets.contains(palabra)){
                                    combbxlab.encontradoAlguno = true;
                    };
                    };
                    
            }
            }
            

            if(combbxlab.encontradoAlguno){
                alarma.alarma(directorioActual + "\\src\\audio\\trompetita.wav");

            };

            if(!combbxlab.labesDets.isEmpty()){
                combbxlab.labesDets.clear();
            }; 
            vueltas ++;
            if (vueltas >= 2) {
                combbxlab.encontradoAlguno = false;
                vueltas = 0;
            };
           
    
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
        ///barra izquieda
        Rectangle barraIzq = new Rectangle(400, 800);
        barraIzq.setStyle("-fx-fill: #1E1E1E; -fx-stroke: #2E2E2E; -fx-stroke-width: 1; -fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.4), 5, 0, 0, 0);");


        textField.setStyle("-fx-control-inner-background: #333333; -fx-text-fill: #FFFFFF;");

        textField.setPrefWidth(300);
        textField.setPrefHeight(60);
        textField.setWrapText(true);
        ///
        Button buscartButton = new Button("buscar");
        HBox inputBox = new HBox(10);
        inputBox.setAlignment(Pos.CENTER);
        inputBox.getChildren().addAll(textField, buscartButton);
        HBox.setMargin(textField, new javafx.geometry.Insets(20, 0, 0, 20));
        //
        

        //// objetos dentro de la ventana
        Button btn_r = new Button("▶");
        StackPane root = new StackPane();
        BorderPane root_elP = new BorderPane();
        StackPane barraR = new StackPane();
        BorderPane BotonReload = new BorderPane();
        
        //
        BotonReload.setBottom(btn_r);

        /////
        barraR.setAlignment(Pos.TOP_CENTER); //// Atencion aqui
        
        // Configuración de la disposición de nodos en barraR
        Rectangle margenBR = new Rectangle(30,60);
        margenBR.setVisible(false);
        StackPane margenBRs = new StackPane();
        margenBRs.getChildren().addAll(margenBR, BotonReload);
        StackPane.setAlignment(margenBRs, Pos.BOTTOM_CENTER);// Atencion aqui
        
        

        VBox botonesBarraR = new VBox();
        
        StackPane opcFoto = opcForo.opcFoto();
        botonesBarraR.setAlignment(Pos.TOP_CENTER);
        
        Rectangle margen3 = new Rectangle(30,30);
        margen3.setVisible(false);
        //StackPane.setAlignment(stackMargenCmbx, Pos.BOTTOM_CENTER);

        botonesBarraR.getChildren().addAll(BotonReload, inputBox, opcFoto, margen3, combbxlab.comboxLabs());
        //
        barraR.getChildren().addAll(barraIzq, botonesBarraR);
        root_elP.setRight(barraR);
        ///contImagNet constRmonitor
        root.getChildren().addAll(monitorInt, root_elP);
        System.out.println("ww");
        // funciones del botón de reproduccion
        btn_r.setOnAction(event -> {
            System.out.println("Se hizo clic en el botón Reproducir");
            root.getChildren().clear(); 

            
            Reproducir reproduccion = new Reproducir();
            root.getChildren().add(reproduccion.vistaReproduccion());
        });

        buscartButton.setOnAction(event -> {
            System.err.println("buscando");
            buscando = !buscando;
            escrito = false;

            
            try(BufferedWriter writerA = new BufferedWriter(new FileWriter(pathUsClip))){
                if(buscando){
                    writerA.write("True");
                    buscartButton.setText("Dejar de buscar");
                }else{
                    writerA.write("False");
                    buscartButton.setText("Buscar");
                }
                        
            }catch(IOException e ){
                        e.printStackTrace();
            };
            monitorInt.getChildren().clear();
            System.out.println(buscando);
            monitorInt = monitoreoVis.constRMonitor(buscando, escrito, textField);
            
        });

        root.setStyle("-fx-background-color: #1E1E1E;");
        Scene scene = new Scene(root, 800, 800);
        scene.getStylesheets().add(getClass().getResource("vistasJ\\styles\\estilo.css").toExternalForm());

        primaryStage.setTitle("sistema Búho ");
        primaryStage.setScene(scene);
        primaryStage.show();

        //
    }
    
    public static void main(String[] args) {
        launch(args);
    }      
}          
