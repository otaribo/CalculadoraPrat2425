package main.java.calculator;

import main.java.calculator.UI.calculatorUI;

public class Main {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        calculatorUI ui = new calculatorUI();
        ui.setVisible(true);
    }
}
