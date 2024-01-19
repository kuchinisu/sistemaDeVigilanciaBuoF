package vistasJ;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


public class Sonidos {
    public void alarma(String rutaArchivo) {
        try {
            // Obtener un flujo de entrada de audio desde el archivo
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(rutaArchivo).getAbsoluteFile());

            // Obtener un objeto Clip para reproducir sonido
            Clip clip = AudioSystem.getClip();

            // Abrir el flujo de entrada de audio y cargar el clip
            clip.open(audioInputStream);

            // Reproducir el sonido
            clip.start();

        } catch (Exception e) {
            System.out.println("Error al reproducir el sonido: " + e.getMessage());
        }
    };

}
