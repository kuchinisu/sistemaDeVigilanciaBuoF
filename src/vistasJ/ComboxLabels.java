package vistasJ;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
//import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import vistasJ.subFuncionesJava.Imagen;
 

public class ComboxLabels {
    String directorioActual = System.getProperty("user.dir");
    String dirImg = directorioActual + "\\src\\img";
    private Imagen fImagen = new Imagen();
    private File pathImg = new File(dirImg);

    public ObservableList<String> camaraOpt = FXCollections.observableArrayList();
    public int imgCant;
    public TextArea testo = new TextArea();
    //private String str;
    public boolean encontradoAlguno = false;
    public List<String> labesDets = new ArrayList<>();
    public String[] ppals;
    public VBox comboxLabs(){

        ObservableList<String> opciones = FXCollections.observableArrayList(
            "humano",           // case 0
            "bicicleta",        // case 1
            "carro",            // case 2
            "motocicleta",      // case 3
            "avion",            // case 4
            "autobús",          // case 5
            "tren",             // case 6
            "camion",           // case 7
            "barco",            // case 8
            "semaforo",         // case 9
            "boca de incendios", // case 10
            "señal de alto",    // case 12
            "parquimetro",      // case 13
            "banco",            // case 14
            "pajaro",           // case 15
            "gato",             // case 16
            "perro",            // case 17
            "caballo",          // case 18
            "oveja",            // case 19
            "vaca",             // case 20
            "elefante",         // case 21
            "oso",              // case 22
            "cebra",            // case 23
            "jirafa",           // case 24
            "mochila",          // case 26
            "paraguas",         // case 27
            "bolso de mano",    // case 30
            "corbata",          // case 31
            "maleta",           // case 32
            "frisbee",          // case 33
            "esquís",           // case 34
            "snowboard",        // case 35
            "pelota de deportes", // case 36
            "cometa",           // case 37
            "bate de béisbol",  // case 38
            "guante de béisbol", // case 39
            "patineta",         // case 40
            "tabla de surf",    // case 41
            "raqueta de tenis", // case 42
            "botella",          // case 43
            "copa de vino",     // case 45
            "taza",             // case 46
            "tenedor",          // case 47
            "cuchillo",         // case 48
            "cuchara",          // case 49
            "tazon",            // case 50
            "banana",           // case 51
            "manzana",          // case 52
            "sándwich",         // case 53
            "naranja",          // case 54
            "brocoli",          // case 55
            "zanahoria",        // case 56
            "hot dog",          // case 57
            "pizza",            // case 58
            "dona",             // case 59
            "pastel",           // case 60
            "silla",            // case 61
            "sofá",             // case 62
            "planta en maceta", // case 63
            "cama",             // case 64
            "mesa de comedor",  // case 66
            "inodoro",          // case 69
            "televisor",        // case 71
            "computadora portátil", // case 72
            "ratón",            // case 73
            "control remoto",   // case 74
            "teclado",          // case 75
            "teléfono celular", // case 76
            "microondas",       // case 77
            "horno",            // case 78
            "tostadora",        // case 79
            "fregadero",        // case 80
            "refrigerador",     // case 81
            "libro",            // case 83
            "reloj",            // case 84
            "jarrón",           // case 85
            "tijeras",          // case 86
            "oso de peluche",   // case 87
            "secador de pelo",  // case 88
            "cepillo de dientes" // case 89
        );
        
        int imgCant = fImagen.conteoImg(pathImg);
        for(int i = 0; i < imgCant; i++){
            camaraOpt.add("_camara_" + (i+1));
        };

        ///area para el combox para seleccionar la cámara
        ComboBox<String> comboBoxCam = new ComboBox<>(camaraOpt);
        HBox hComBox = new HBox();
        ///

        ComboBox<String> comboBox = new ComboBox<>(opciones);
        TextArea testo = new TextArea();
        testo.setStyle("-fx-control-inner-background: #333333; -fx-text-fill: #FFFFFF;");

        testo.setPrefWidth(300); 
        testo.setPrefHeight(60);
        testo.setWrapText(true);

        //dinamico
        comboBox.setOnAction(event -> {
            String opcionSeleccionada = comboBox.getValue();
            System.out.println("Opción seleccionada: " + opcionSeleccionada);

            testo.setText(testo.getText() + " " + opcionSeleccionada+comboBoxCam.getValue());

            String contT = testo.getText();

            
            ppals = contT.split("\\s+");

        });

        VBox root = new VBox();
        hComBox.getChildren().addAll(comboBox,comboBoxCam);
        root.getChildren().addAll(testo,hComBox);
        
        return root;
    };
}
