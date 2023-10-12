/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
public class RegistrarClienteSirve extends JFrame {

    public RegistrarClienteSirve() {
        getContentPane().setBackground(Color.BLACK);

        setTitle("Información");
        setSize(580, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un panel para el contenido
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.BLACK);

        // Agregar un JLabel rojo "INFORMACION" en la esquina superior izquierda
        JLabel titleLabel = new JLabel("INFORMACION");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.RED);
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // Crear un panel para los JTextField centrados
        JPanel textFieldsPanel = new JPanel(new GridBagLayout());
        textFieldsPanel.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado

        Font labelFont = new Font("Arial", Font.PLAIN, 20);

        // Agregar un JTextField para el nombre del cliente
        JLabel nameLabel = new JLabel("Nombre del Cliente:");
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(Color.WHITE);
        textFieldsPanel.add(nameLabel, gbc);

        gbc.gridy++; // Mover al siguiente renglón

        JTextField nameTextField = new JTextField(25);
        nameTextField.setFont(labelFont);
        textFieldsPanel.add(nameTextField, gbc);

        gbc.gridy++; // Mover al siguiente renglón

        // Agregar un JTextField para la dirección
        JLabel addressLabel = new JLabel("Dirección:");
        addressLabel.setFont(labelFont);
        addressLabel.setForeground(Color.WHITE);
        textFieldsPanel.add(addressLabel, gbc);

        gbc.gridy++; // Mover al siguiente renglón

        JTextField addressTextField = new JTextField(25);
        addressTextField.setFont(labelFont);
        textFieldsPanel.add(addressTextField, gbc);

        gbc.gridy++; // Mover al siguiente renglón

        // Agregar un JTextField para la ciudad
        JLabel cityLabel = new JLabel("Ciudad:");
        cityLabel.setFont(labelFont);
        cityLabel.setForeground(Color.WHITE);
        textFieldsPanel.add(cityLabel, gbc);

        gbc.gridy++; // Mover al siguiente renglón

        JTextField cityTextField = new JTextField(25);
        cityTextField.setFont(labelFont);
        textFieldsPanel.add(cityTextField, gbc);

        contentPanel.add(textFieldsPanel, BorderLayout.CENTER);

        // Agregar un JButton para registrar nuevo cliente
        JButton registerButton = new JButton("Registrar Nuevo Cliente");
        registerButton.setFont(labelFont);
        registerButton.setBackground(Color.RED);
        registerButton.setForeground(Color.WHITE);
        registerButton.setPreferredSize(new Dimension(250, 40));
        contentPanel.add(registerButton, BorderLayout.SOUTH);

        getContentPane().add(contentPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RegistrarClienteSirve informacionCliente = new RegistrarClienteSirve();
                informacionCliente.setVisible(true);
            }
        });
    }
}

