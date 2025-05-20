package main.java.calculator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class buttonActions implements ActionListener {
    private String valor;
    private StringBuilder textoActual;
    private JTextField textoPantalla; // <- Añade esta referencia
    
    public buttonActions(String valor, StringBuilder textoActual, JTextField textoPantalla) {
        this.valor = valor;
        this.textoActual = textoActual;
        this.textoPantalla = textoPantalla; // <- Guarda la referencia al JTextField
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(valor) {
            case "<-": 
                if(textoActual.length() > 0) {
                    textoActual.deleteCharAt(textoActual.length() - 1);
                }
                break;
            case "AC": 
                textoActual.setLength(0);
                break;
            default: 
                textoActual.append(valor);
        }
        
        // Actualiza el JTextField
        textoPantalla.setText(textoActual.toString());
        textoPantalla.repaint(); // Fuerza redibujado
    }
}
