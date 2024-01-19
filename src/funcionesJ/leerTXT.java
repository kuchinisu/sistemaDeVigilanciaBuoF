package funcionesJ;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class leerTXT {

    public char lecturaTXT(String[] path) {
        FileReader arch = null;
        BufferedReader lect = null;
        StringBuilder contenido = new StringBuilder();

        try {
            arch = new FileReader(path[0]); 
            lect = new BufferedReader(arch);

            String linea;
            while ((linea = lect.readLine()) != null) {
                contenido.append(linea);
            }

        } catch (IOException e) {
            System.out.println("Error con la lectura del archivo .txt: " + e.getMessage());
        } finally {
            try {
                if (lect != null) {
                    lect.close();
                }
                if (arch != null) {
                    arch.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo: " + e.getMessage());
            }
        }
        
        // char
        if (contenido.length() > 0) {
            return contenido.charAt(0);
        } else {
            return 'a'; 
        }
    }
}