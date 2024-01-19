package vistasJ.subFuncionesJava;

import java.io.File;

public class Imagen {
    
    public int conteoImg(File directorio){
        int imgCant = 0;
        File[] archivos;
        if (directorio.isDirectory()) {
        
            archivos = directorio.listFiles();

            for (File archivo : archivos) {
                
                if (archivo.isFile() && archivo.getName().toLowerCase().matches(".*\\.(jpg|jpeg|png|gif|bmp)")) {
                    imgCant++;
                }
            }

            System.out.println("Cantidad de imágenes en el directorio: " + imgCant);
        } else {
            System.out.println("La ruta especificada no es un directorio válido.");
        };

        return imgCant;

    }
    
}
