package main.java.calculator.UI;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class textFont {
    private static Font customFont;
    private static String fontPath = "C:\\Github\\CalculadoraPrat24-25\\CalculadoraPrat2425\\CalculadoraPrat2425\\src\\main\\resources\\TextFont\\Calculator.ttf";
    private static String specialFont = "C:\\Github\\CalculadoraPrat24-25\\CalculadoraPrat2425\\CalculadoraPrat2425\\src\\main\\resources\\TextFont\\NotoSans-Black.ttf";
    {
        cargarFuente(1);
    }

    private void cargarFuente(int i) {
        
        try {
           
            File ruta = new File(i==1?fontPath:specialFont);
            
            customFont = Font.createFont(Font.TRUETYPE_FONT, ruta);
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
        this.cargarFuente(2);
        return customFont.deriveFont(estilo, tamaño);
    }
}
