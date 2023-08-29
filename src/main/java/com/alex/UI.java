package com.alex;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UI extends JFrame {
    Logic logic;
    // Styles:
    Color clrPrimaryDarkerBlue = new Color(59, 123, 213);
    Color clrPrimaryLightBlue = new Color(114, 158, 221);
    Color clrLight = new Color(230, 230, 230);
    Font fontTemperature = new Font("Roboto", Font.PLAIN, 35);
    Font fontPrimary = new Font("Roboto", Font.PLAIN, 22);
    Font fontPrimaryBold = new Font("Roboto", Font.BOLD, 22);
    Font fontBig = new Font("Roboto", Font.BOLD, 50);
    Font fontStats = new Font("Roboto", Font.PLAIN, 18);

    String imgPathName;
    JTextField searchField, locationField, temperatureField, weatherField;
    JTextField humidityField, windField, statField1, statField2;
    JLabel imageLabel, humidityLabel, windLabel;

    public UI() {
        logic = new Logic(this);
        initializeUI();
        logic.setupSearchButton();
        configureFrame();
    }

    private void initializeUI() {
        // Frame
        setTitle("Weather App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 510);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(clrPrimaryDarkerBlue);
        this.setContentPane(contentPane);        
        setLayout(null);
        setResizable(false);
        // Search textfield
        searchField = new JTextField("");
        configureSearchField(searchField); 
        // Image label
        imageLabel = new JLabel();
        configureImageLabel(imageLabel);
        // Temperature textfield
        temperatureField = new JTextField("");
        configureTemperatureField(temperatureField);
        // Weather textfield
        weatherField = new JTextField("");
        configureWeatherField(weatherField);
        // Humidity label
        humidityLabel = new JLabel();
        configureHumidityLabel(humidityLabel); 
        // Wind label
        windLabel = new JLabel();
        configureWindLabel(windLabel);
        // Humidity textfield
        humidityField = new JTextField("");  
        configureHeaderField(humidityField); 
        humidityField.setHorizontalAlignment(JTextField.LEFT); 
        humidityField.setBounds(42, 380, 100, 40);
        // Wind textfield
        windField = new JTextField("");
        configureHeaderField(windField);
        windField.setHorizontalAlignment(JTextField.RIGHT); 
        windField.setBounds(180, 380, 150, 40);
        // Stat textfields
        statField1 = new JTextField("Humidity");
        configureStatField(statField1);
        statField1.setBounds(42, 400, 115, 50);
        statField2 = new JTextField("Wind Speed");
        configureStatField(statField2);
        statField2.setBounds(200, 400, 140, 50);
    }

    private void configureFrame() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void configureButtonUI(JButton button) {
        button.setBounds(270, 20, 50, 50);
        button.setBackground(clrLight);
        button.setFocusable(false);
        button.setBorderPainted(false);
        ImageIcon icon = new ImageIcon(UI.class.getResource("/search.png"));
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(16, 18, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);
        button.setIcon(resizedIcon);
        add(button);
    }

    private void configureSearchField(JTextField textField) {
        textField.setBounds(18, 20, 240, 50);
        textField.setBackground(clrPrimaryLightBlue);
        textField.setForeground(clrLight);
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setFont(fontPrimary);
        textField.setBorder(new EmptyBorder(10, 15, 10, 15));
        add(textField);
    }

    private void configureTemperatureField(JTextField textField) {
        textField.setBounds(18, 270, 300, 65);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setOpaque(false);
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setEditable(false);
        textField.setForeground(clrLight);
        textField.setFont(fontBig);
        textField.setBorder(new EmptyBorder(10, 15, 10, 15));
        add(textField);
    }

    private void configureWeatherField(JTextField textField) {
        textField.setBounds(0, 312, 350, 65);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setOpaque(false);
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setEditable(false);
        textField.setForeground(clrLight);
        textField.setFont(fontPrimary);
        textField.setBorder(new EmptyBorder(10, 15, 10, 15));
        add(textField);
    }

    private void configureHeaderField(JTextField textField) {
        textField.setOpaque(false);
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setEditable(false);
        textField.setForeground(clrLight);
        textField.setFont(fontPrimaryBold);
        textField.setBorder(new EmptyBorder(10, 15, 10, 15));
        add(textField);
    }

    private void configureStatField(JTextField textField) {
        textField.setHorizontalAlignment(JTextField.LEFT);
        textField.setOpaque(false);
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setEditable(false);
        textField.setForeground(clrLight);
        textField.setFont(fontStats);
        textField.setBorder(new EmptyBorder(10, 15, 10, 15));
        add(textField);
    }

    private void configureImageLabel(JLabel label) {
        label.setBounds(80, 75, 300, 210);
        ImageIcon icon = new ImageIcon(UI.class.getResource("/clear.png"));
        label.setIcon(resizeIcon(icon));
        add(label);
    }

    private void configureHumidityLabel(JLabel label) {
        int width = 32;
        int height = 32;
        label.setBounds(18, 400, width, height);
        ImageIcon icon = new ImageIcon(UI.class.getResource("/humidity.png"));
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);
        label.setIcon(resizedIcon);
        add(label);
    }

    private void configureWindLabel(JLabel label) {
        int width = 32;
        int height = 32;
        label.setBounds(173, 400, width, height);
        ImageIcon icon = new ImageIcon(UI.class.getResource("/wind.png"));
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);
        label.setIcon(resizedIcon);
        add(label);
    }

    private ImageIcon resizeIcon(ImageIcon icon) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);
        return resizedIcon;
    }

    public void updateImageLabel(String imgName) {
        ImageIcon icon = new ImageIcon(UI.class.getResource(imgName));
        imageLabel.setIcon(resizeIcon(icon));
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JTextField getTemperatureField() {
        return temperatureField;
    }

    public JTextField getWeatherField() {
        return weatherField;
    }
    
    public JTextField getHumidityField() {
        return humidityField;
    }

    public JTextField getWindField() {
        return windField;
    }
}