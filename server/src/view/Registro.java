package view;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
+ * The `Registro` class represents a JFrame for an administrator.
+ *
+ * @author 57314
+ */
public class Registro extends JFrame {

        /**
        * Constructs a new `Registro` object.
        */
        public Registro() {
            setTitle("Formulario de Registro");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crea un panel con BorderLayout para establecer la imagen de fondo
        JPanel backgroundPanel = new JPanel(new BorderLayout());
        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Formulario.jpg"); // Cambia la ruta por la ubicación de tu imagen
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundPanel.add(backgroundLabel, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 10, 5, 10);
        constraints.anchor = GridBagConstraints.WEST;

        JLabel nameLabel = new JLabel("Nombre:");
        JTextField nameTextField = new JTextField(15);
        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPanel.add(nameLabel, constraints);
        constraints.gridy = 1;
        mainPanel.add(nameTextField, constraints);

        JLabel passwordLabel = new JLabel("Contraseña:");
        JPasswordField passwordField = new JPasswordField(15);
        constraints.gridy = 2;
        mainPanel.add(passwordLabel, constraints);
        constraints.gridy = 3;
        mainPanel.add(passwordField, constraints);

        JLabel cityLabel = new JLabel("Ciudad y Dirección:");
        JTextField cityTextField = new JTextField(15);
        constraints.gridy = 4;
        mainPanel.add(cityLabel, constraints);
        constraints.gridy = 5;
        mainPanel.add(cityTextField, constraints);

        JButton submitButton = new JButton("Enviar");
        constraints.gridy = 6;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;
        mainPanel.add(submitButton, constraints);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String password = new String(passwordField.getPassword());
                String city = cityTextField.getText();
                JOptionPane.showMessageDialog(null, "Nombre: " + name + "\nContraseña: " + password + "\nCiudad y Dirección: " + city + "\nREGISTRO EXITOSO");
                
                dispose();

                
            }
        });

        // Se agrega el panel principal al panel de fondo con imagen
        backgroundPanel.add(mainPanel, BorderLayout.WEST);

        this.setContentPane(backgroundPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Registro().setVisible(true);
            }
        });
    }
}






