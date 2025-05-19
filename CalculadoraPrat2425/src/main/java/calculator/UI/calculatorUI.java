package main.java.calculator.UI;

import main.resources.TextFont.*; 

import java.io.*;
import javax.swing.*;
import java.awt.*;


public class calculatorUI extends JFrame {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 700;
    
    private String textoAC = "Perro vegano";



    public calculatorUI() {
        this.setSize(WIDTH, HEIGHT);
        this.setLocation(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Calculadora tope guapa");
        this.iniciarComponentes();
    }

    public void iniciarComponentes() {
        textFont customFont = new textFont();
        JPanel mainPanel = new JPanel(new BorderLayout());
        this.setContentPane(mainPanel);

        JTextField pantalla = new JTextField();
        pantalla.setFont(new Font("Arial", Font.BOLD, 30));
        pantalla.setEditable(false);
        mainPanel.add(pantalla, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridBagLayout());
        panelBotones.setBackground(Color.YELLOW);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(2, 2, 2, 2);

        gbc.gridwidth = 4;
        JTextField textoPantalla = new JTextField(textoAC);
        textoPantalla.setFont(new Font("Arial", Font.BOLD, 20));
        textoPantalla.setHorizontalAlignment(JTextField.CENTER);
        textoPantalla.setEditable(false);
        textoPantalla.setBackground(Color.RED);
        textoPantalla.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelBotones.add(textoPantalla, gbc);

        gbc.gridwidth = 1;
        String[][] botones = {
            {"AC", "+-", "%", "/"},
            {"7", "8", "9", "x"},
            {"4", "5", "6", "–"},
            {"1", "2", "3", "+"},
            {"<-", "0", ",", "="}
        };

        for(int i = 0; i < botones.length; i++) {
            for(int j = 0; j < 4; j++) {
                agregarBoton(panelBotones, gbc, botones[i][j], j, i + 1);
            }
        }

        mainPanel.add(panelBotones, BorderLayout.CENTER);
    }

    private void agregarBoton(JPanel panel, GridBagConstraints gbc, String texto, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 20));
        boton.setBackground(Color.RED);
        boton.setOpaque(true);
        boton.setBorderPainted(false);
        panel.add(boton, gbc);
    }
}