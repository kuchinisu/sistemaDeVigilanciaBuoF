package vistasJ;
import javafx.util.Duration;
import javafx.animation.KeyFrame;

import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
//import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import vistasJ.subFuncionesJava.Matematicas;

public class Monitoreo {

    Timeline timeline;
    String directorioActual = System.getProperty("user.dir");
    private String pathImgs = directorioActual + "\\src\\img\\"; // ojo aqui, posibles fallos

    private int contadorImagenes = 0;
    private List <Integer> imgsSizes = new ArrayList<Integer>();

    private int imgAlt = 790;
    private int imgAnch = 1040;
    private String pathPropts = directorioActual + "\\src\\recados\\propt.txt";
    private String pathPred = directorioActual + "\\src\\recados\\pred.txt";
    String pred;
    boolean ejecutado = false;
    boolean buscandol = false;
    boolean escritol = false;
    
    VBox vbox = new VBox();
    Label etiqEncPromB = new Label();
    File[] archivos;
    BufferedReader reader;
    FileReader rfr;
    List <String> lpreds;
    private Matematicas MatsLib = new Matematicas();
    private List<ImageView> listImageView;
    public VBox constRMonitor(boolean buscando, boolean escrito, TextArea textField) {
        //cantImgDist

        File directorio = new File(pathImgs);
        buscandol = buscando;
        escritol = escrito;
        

            //
        if(!ejecutado){
            ejecutado=true;
        if (directorio.isDirectory()) {
        
            archivos = directorio.listFiles();

            for (File archivo : archivos) {
                
                if (archivo.isFile() && archivo.getName().toLowerCase().matches(".*\\.(jpg|jpeg|png|gif|bmp)")) {
                    contadorImagenes++;
                }
            }

            System.out.println("Cantidad de imágenes en el directorio: " + contadorImagenes);
        } else {
            System.out.println("La ruta especificada no es un directorio válido.");
        };
        
        System.out.println(contadorImagenes);
        if(contadorImagenes > 1){
            imgsSizes = MatsLib.redutcImg(contadorImagenes, imgAlt, imgAnch);
            imgAlt = (int)imgsSizes.get(0);
            imgAnch = (int) imgsSizes.get(1);
        
        };
        };

        //////////////////////////////////////////////////
        listImageView = new ArrayList<>();
        for(int i = 1; i<= contadorImagenes;i++){
            Image imagenA = new Image("file:" + pathImgs + "frame" + i + ".jpg");
            ImageView imgVw = new ImageView(imagenA);
            imgVw.setFitHeight(imgAlt);
            imgVw.setFitWidth(imgAnch);

            listImageView.add(imgVw);
        }
        //String propts;
        ///////
        
        
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.05), event -> {

            if(lpreds != null){
                lpreds.clear();
            }else{
                lpreds = new ArrayList<>();
            };
            String propts = textField.getText();

            ///
            try {
                rfr = new FileReader(pathPred); 
             }catch(IOException e){
                 e.printStackTrace();
             }
             
             
             reader = new BufferedReader(rfr);
            ///

            for(int i= 0; i < contadorImagenes; i++){
                Image imagenB = new Image("file:" + pathImgs + "frame" + (i+1) + ".jpg");

                ///
                if(buscandol){
                    //System.out.println("buscando clip activado");
                    //etiqEncProm.setVisible(true);
                    if(!escritol){

                        try(BufferedWriter writer = new BufferedWriter(new FileWriter(pathPropts))){
                        writer.write(propts);
                        }catch (IOException e) {
                            e.printStackTrace();
                        }; 
                    ///
                    }
                    try{
                        for(int r = 0; r < contadorImagenes; r++){
                            lpreds.add(reader.readLine());
                        }
                        //pred = reader.readLine();

                    }catch(IOException e){
                        e.printStackTrace();
                      
                    };
                        
                    }else{
                        //etiqEncProm.setVisible(false);
                    };
                ///


                listImageView.get(i).setImage(imagenB);
            };
        }));


        
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        ////
        for (int i = 1; i <= contadorImagenes; i += 2) {
            HBox hbox = new HBox();

            if (i + 1 <= contadorImagenes) {
                    
                StackPane panelStak1 = new StackPane();
                Label lpA = new Label();
                StackPane stacklab = new StackPane();
                Rectangle rectangloLab = new Rectangle(150,100);
                BorderPane botLab = new BorderPane();
                BorderPane izqLab = new BorderPane();
                stacklab.getChildren().addAll(rectangloLab,lpA);
                botLab.setBottom(stacklab);
                izqLab.setRight(botLab);
                lpA.setStyle("-fx-font-size: 28;");
                if(buscando){lpA.setVisible(true);rectangloLab.setVisible(true);}else{lpA.setVisible(false);rectangloLab.setVisible(false);}
                    
                if(lpreds != null){  
                    if(!lpreds.isEmpty()){
                        if(lpreds.get(i).equals("y")){
                            lpA.setText("Enocntrado");
                            lpA.setStyle("-fx-text-fill: green; -fx-font-size: 28;");
                        }else{
                            lpA.setText("No encontrado");
                            lpA.setStyle("-fx-text-fill: red; -fx-font-size: 28;");
                        }
                    }
                };
                panelStak1.getChildren().addAll(listImageView.get(i-1), izqLab);
                hbox.getChildren().add(panelStak1);
                    
                    
                    
                Label lpB = new Label();
                StackPane staklabB = new StackPane();
                Rectangle rectangloLabB = new Rectangle(150,100);
                BorderPane botlabB = new BorderPane();
                BorderPane izqLabB = new BorderPane();
                staklabB.getChildren().addAll(rectangloLabB,lpB);
                botlabB.setBottom(staklabB);
                izqLabB.setRight(botlabB);
                lpB.setStyle("-fx-font-size: 26;");
                if(buscando){lpB.setVisible(true);rectangloLabB.setVisible(true);}else{lpB.setVisible(false);rectangloLabB.setVisible(false);};
                    
                if(lpreds != null){
                    if(!lpreds.isEmpty()){
                        if(lpreds.get(i).equals("y")){
                            lpB.setText("Enocntrado");
                            lpB.setStyle("-fx-text-fill: green; -fx-font-size: 26;");
                        }else{
                            lpB.setText("No encontrado");
                            lpB.setStyle("-fx-text-fill: red; -fx-font-size: 26;");
                        };

                    };
                };
                    
                StackPane panelStak2 = new StackPane();
                panelStak2.getChildren().addAll(listImageView.get(i), izqLabB);

                hbox.getChildren().addAll(panelStak2);
                
                }else{
                    StackPane panelStak1 = new StackPane();
                Label lpA = new Label();
                StackPane stacklab = new StackPane();
                Rectangle rectangloLab = new Rectangle(150,100);
                BorderPane botLab = new BorderPane();
                BorderPane izqLab = new BorderPane();
                stacklab.getChildren().addAll(rectangloLab,lpA);
                botLab.setBottom(stacklab);
                izqLab.setRight(botLab);
                lpA.setStyle("-fx-font-size: 28;");
                if(buscando){lpA.setVisible(true);rectangloLab.setVisible(true);}else{lpA.setVisible(false);rectangloLab.setVisible(false);}
                    
                if(lpreds != null){  
                    if(!lpreds.isEmpty()){
                        if(lpreds.get(i-1).equals("y")){
                            lpA.setText("Enocntrado");
                            lpA.setStyle("-fx-text-fill: green; -fx-font-size: 28;");
                        }else{
                            lpA.setText("No encontrado");
                            lpA.setStyle("-fx-text-fill: red; -fx-font-size: 28;");
                        }
                    }
                };
                panelStak1.getChildren().addAll(listImageView.get(i-1), izqLab);
                hbox.getChildren().add(panelStak1);
                };

                vbox.getChildren().add(hbox);
            }
        ////
        return vbox;
        //
    };
    
}
