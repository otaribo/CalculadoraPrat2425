package main.java.calculator.UI;

import main.java.calculator.controller.buttonActions;

import javax.swing.*;
import java.awt.*;


public class calculatorUI extends JFrame {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 700;
    private static textFont createCustomFont = new textFont();
    private static Font customFont = createCustomFont.obtenerFuente(1, 30f);
    private StringBuilder textoString = new StringBuilder("");
    public JTextField textoPantalla = new JTextField(textoString.toString());



    public calculatorUI() {
        this.setSize(WIDTH, HEIGHT);
        this.setLocation(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Calculadora tope guapa");
        this.iniciarComponentes();
    }

    public void iniciarComponentes() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        this.setContentPane(mainPanel);

        textoPantalla.setFont(customFont);
        textoPantalla.setEditable(false);
        textoPantalla.setHorizontalAlignment(JTextField.RIGHT);
        textoPantalla.setBackground(Color.GRAY);
        mainPanel.add(textoPantalla, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridBagLayout());
        panelBotones.setBackground(Color.GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(2, 2, 2, 2);

        gbc.gridwidth = 4;
        textoPantalla.setFont(customFont);
        textoPantalla.setFont(textoPantalla.getFont().deriveFont(50f));
        textoPantalla.setHorizontalAlignment(JTextField.RIGHT);
        textoPantalla.setEditable(false);
        textoPantalla.setBackground(Color.LIGHT_GRAY);
        textoPantalla.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelBotones.add(textoPantalla, gbc);

        gbc.gridwidth = 1;
        String[][] botones = {
            {"AC", "卍", "%", "/"},
            {"7", "8", "9", "x"},
            {"4", "5", "6", "-"},
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
        boton.setFont(texto.equals("卍")?new textFont().obtenerFuenteEspecial(x, y):customFont);
        boton.setBackground(new Color(255, 153, 51));
        boton.setOpaque(true);
        boton.setBorderPainted(false);
        boton.setFocusable(false);
        boton.addActionListener(new buttonActions(texto, textoString, textoPantalla));
        panel.add(boton, gbc);
    }

    
}