package main.java.calculator.UI;

import java.awt.*;
import java.io.InputStream;

public class textFont {
    private static Font customFont;
    {
        cargarFuente();
    }

    private void cargarFuente() {
        try {
            // Ruta CORRECTA con '/' y desde raíz de classpath
            InputStream is = textFont.class.getResourceAsStream("/TextFont/Calculator.ttf");
            
            if (is == null) {
                throw new Exception("Fuente no encontrada en: /TextFont/Calculator.ttf");
            }
            
            customFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e) {
            System.err.println("Error carga fuente: " + e.getMessage());
            customFont = new Font("Arial", Font.PLAIN, 12);
        }
    }
    public Font obtenerFuente(int estilo, float tamaño) {
        return customFont.deriveFont(estilo, tamaño);
    }
}
