package main.java.calculator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

import main.java.calculator.logic.History;
import main.java.calculator.logic.LastOperation;
import main.java.calculator.logic.calculo;

public class buttonActions implements ActionListener {
    private String valor;
    private StringBuilder textoActual;
    private JTextField textoPantalla;
    
    public buttonActions(String valor, StringBuilder textoActual, JTextField textoPantalla) {
        this.valor = valor;
        this.textoActual = textoActual;
        this.textoPantalla = textoPantalla;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
        switch(valor) {
            case "+":
                case "-":
                case "x":
                case "/":
                    if (puedeAgregarOperador(valor)) {
                        textoActual.append(valor);
                    }
                    break;
            case "<-": 
                if(textoActual.length() > 0) {
                    textoActual.deleteCharAt(textoActual.length() - 1);
                }
                break;
            case "AC": 
                textoActual.setLength(0);
                break;
                
            case "=": 
                try {
                    String expresion;
                    if (LastOperation.getOperator() != null && isNumeric(textoActual.toString())) {
                        expresion = textoActual + LastOperation.getOperator() + LastOperation.getOperand();
                    } else {
                        expresion = textoActual.toString();
                    }
                    String result = new calculo(expresion).getResultadoFinal();
                    textoActual.setLength(0);
                    textoActual.append(result);
                    // Actualizar última operación si es una expresión simple
                    String[] tokens = expresion.split("(?<=[-+x/])|(?=[-+x/])");
                    if (tokens.length == 3) {
                        LastOperation.setLastOperation(tokens[1], tokens[2]);
                    }
                    History.addEntry(expresion, result);
                } catch (ArithmeticException ex) {
                    textoActual.setLength(0);
                    textoActual.append("Error: División por cero");
                }
                break;
            case "+-":
                toggleSign();
                break;
            case ",":  // Caso nuevo para manejar comas
                if (puedeAgregarComa()) {
                    textoActual.append(valor);
                }
                break;
            default: 
                textoActual.append(valor);
            }
        }
        catch(NumberFormatException exeption){
            System.out.println("Error rebut " + exeption);
        }
            
        
        textoPantalla.setText(textoActual.toString());
        textoPantalla.repaint();
    }
    private void toggleSign() {
        if (textoActual.length() == 0) {
            textoActual.append('-');
        } else {
            int start = 0;
            for (int i = textoActual.length() - 1; i >= 0; i--) {
                char c = textoActual.charAt(i);
                if (c == '+' || c == '-' || c == 'x' || c == '/') {
                    start = i + 1;
                    break;
                }
            }
            String currentNumber = textoActual.substring(start);
            if (currentNumber.isEmpty()) {
                textoActual.append('-');
            } else {
                if (currentNumber.startsWith("-")) {
                    textoActual.delete(start, start + 1);
                } else {
                    textoActual.insert(start, '-');
                }
            }
        }
    }
    private boolean isNumeric(String str) {
                try {
                    Double.parseDouble(str.replace(',', '.'));
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
    private boolean puedeAgregarComa() {
        String expresion = textoActual.toString();
        if (expresion.isEmpty()) return false;  // Evitar "," al inicio
        
        // Encontrar el último operador válido
        int ultimoOperador = -1;
        for (int i = expresion.length() - 1; i >= 0; i--) {
            char c = expresion.charAt(i);
            if (c == '+' || c == 'x' || c == '/' || (c == '-' && i != 0 && "+-x/".indexOf(expresion.charAt(i-1)) == -1)) {
                ultimoOperador = i;
                break;
            }
        }
        
        String numeroActual = expresion.substring(ultimoOperador + 1);
        return !numeroActual.contains(",") && !numeroActual.isEmpty();  // Solo 1 coma por número
    }
    private boolean esOperador(char c) {
        return c == '+' || c == '-' || c == 'x' || c == '/';
    }
    private boolean puedeAgregarOperador(String operador) {
        if (textoActual.length() == 0) {
            return operador.equals("-"); // Permitir negativo al inicio
        }

        char ultimoChar = textoActual.charAt(textoActual.length() - 1);
        
        // Bloquear todos los operadores después de otro operador (excepto "-" como negativo)
        if (esOperador(ultimoChar)) {
            return operador.equals("-") && ultimoChar != '-'; // Permitir solo "-" negativo
        }
        
        return true;
    }    
}
    