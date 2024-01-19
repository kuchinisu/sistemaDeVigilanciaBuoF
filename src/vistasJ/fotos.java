package vistasJ;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
//import javafx.scene.shape.Rectangle;

public class fotos {
    
    String directorioActual = System.getProperty("user.dir");

    public StackPane opcFoto() {
        HBox elementos = new HBox();
        TextField textoPath = new TextField();
        textoPath.setStyle("-fx-background-color: #333333; -fx-text-fill: #FFFFFF;");

        Button botonTomarFoto = new Button();
        botonTomarFoto.setVisible(true);

        StackPane stakboton = new StackPane();

        String gifPath = directorioActual + "\\src\\icon\\icons8-cámara.gif";
        Image gifImagen = new Image("file:" + gifPath);
        ImageView icono = new ImageView(gifImagen);
        stakboton.getChildren().addAll(icono, botonTomarFoto);

        
        elementos.getChildren().addAll(textoPath, stakboton);

        
        HBox.setMargin(textoPath, new javafx.geometry.Insets(20, 0, 0, 20));

        StackPane panl = new StackPane();
        panl.getChildren().addAll(elementos);

        botonTomarFoto.setOnAction(event -> {
            tomarFoto(textoPath.getText());
        });

        return panl;
    };

    private void tomarFoto(String pathFoto){
        
        Path directorioOrigen = Paths.get(directorioActual+"\\src\\img\\imagennp\\frame_m.jpg");
        Path directorioDestino = Paths.get(pathFoto);
        String nomb = "scr.jpg";
        Path rutaImagenDestino = directorioDestino.resolve(nomb);
        


        try {
            Files.copy(directorioOrigen, rutaImagenDestino);

            System.out.println("screenshot");
        } catch (IOException e) {
            e.printStackTrace();
        }

    };


}
