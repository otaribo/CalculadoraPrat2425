package main.java.calculator.UI;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class textFont {
    public static void main(String[] args) {
        try {
            // Ruta a tu archivo de fuente (puede ser .ttf, .otf, etc.)
            String fontPath = "src\\main\\resources\\TextFont\\Calculator.ttf";
            
            // Cargar la fuente desde el archivo
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
            
            // Opcional: derivar un tamaño y estilo específico
            Font sizedFont = customFont.deriveFont(12f); // Tamaño 12
            
            // Registrar la fuente en el entorno gráfico para que esté disponible
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            
            // Ahora puedes usar la fuente como cualquier otra
            // Ejemplo con un JLabel:
            // JLabel label = new JLabel("Texto con fuente personalizada");
            // label.setFont(sizedFont);
            
            System.out.println("Fuente cargada correctamente: " + customFont.getFontName());
            
        } 
        catch (IOException | FontFormatException e) {
            System.err.println("Error al cargar la fuente: " + e.getMessage());
        }
    }
    public static Font getFont(){
        return getFont();
    }
}
