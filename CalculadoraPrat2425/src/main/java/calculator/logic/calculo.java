package main.java.calculator.logic;

import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.Deque;

public class calculo {
    private String expresion;
    public calculo(String expresion) {
        this.expresion = expresion.replace('x', '*').replace(',', '.').replaceAll("\\s+", "");
    }
    public String getResultadoFinal() {
        try {
            if (!validarExpresion() || expresion.matches(".*[-+x/]{2,}.*")) {
                return "Error";
            }

            double resultado = evaluarExpresion();
            
            if (Double.isInfinite(resultado)) {
                throw new ArithmeticException("División por cero");
            }

            DecimalFormat df = new DecimalFormat("#.##########");
            df.setDecimalSeparatorAlwaysShown(false);
            return df.format(resultado).replace('.', ',');
        } catch (ArithmeticException e) {
            return "Error: Div/0";
        } catch (Exception e) {
            return "Error";
        }
    }
    private boolean validarExpresion() {
        return expresion.matches("^(-?\\d+(\\.\\d+)?)([-+x/](-?\\d+(\\.\\d+)?))*$");
    }
    private double evaluarExpresion() {
        Deque<Double> numeros = new ArrayDeque<>();
        Deque<Character> operadores = new ArrayDeque<>();
        boolean esperaNumero = true;

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);
            
            if (Character.isDigit(c) || c == '.' || (c == '-' && esperaNumero) || (c == '+' && esperaNumero)) {
                StringBuilder numStr = new StringBuilder();
                if (c == '-' || c == '+') {
                    numStr.append(c);
                    i++;
                }
                
                while (i < expresion.length() && (Character.isDigit(expresion.charAt(i)) || expresion.charAt(i) == '.')) {
                    numStr.append(expresion.charAt(i++));
                }
                i--;
                
                numeros.push(Double.parseDouble(numStr.toString()));
                esperaNumero = false;
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operadores.isEmpty() && precedencia(operadores.peek()) >= precedencia(c)) {
                    aplicarOperador(numeros, operadores.pop());
                }
                operadores.push(c);
                esperaNumero = true;
            }
        }

        while (!operadores.isEmpty()) {
            aplicarOperador(numeros, operadores.pop());
        }

        return numeros.pop();
    }
    private int precedencia(char op) {
        return (op == '+' || op == '-') ? 1 : 2;
    }
    private void aplicarOperador(Deque<Double> numeros, char op) {
        double b = numeros.pop();
        double a = numeros.isEmpty() ? 0 : numeros.pop();
        
        switch (op) {
            case '+': numeros.push(a + b); break;
            case '-': numeros.push(a - b); break;
            case '*': numeros.push(a * b); break;
            case '/': 
                if (b == 0) throw new ArithmeticException();
                numeros.push(a / b); 
                break;
        }
    }
}