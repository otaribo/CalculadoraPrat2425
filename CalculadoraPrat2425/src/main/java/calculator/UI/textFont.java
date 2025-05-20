package main.java.calculator.UI;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class textFont {
    private static Font customFont;
    private static String fontPath = "src\\main\\resources\\TextFont\\Calculator.ttf";
    private static String specialFont = "src\\main\\resources\\TextFont\\NotoSans-Black.ttf";
    {
        cargarFuente(1);
    }

    private void cargarFuente(int i) {
        
        try {
           
            File ruta = new File(i==1?fontPath:specialFont);
            
            customFont = Font.createFont(0, ruta);
            System.out.println("Fuente cargada correctamente");
        } catch (FontFormatException | IOException e) {
            System.err.println("Error carga fuente: " + e.getMessage());
            customFont = new Font("Arial", Font.PLAIN, 12);
        }
    }
    public Font obtenerFuente(int estilo, float tamaño) {
        return customFont.deriveFont(estilo, tamaño);
    }
    public Font obtenerFuenteEspecial(int estilo, float tamaño){
        customFont = new Font("Sans-Serif",1,30);
        return customFont;//customFont.deriveFont(estilo, tamaño);
    }
}
