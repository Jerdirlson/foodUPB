package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import database.Conection;

public class AgregarOperario extends JFrame {

    private JPanel panelAgregarOperario;
    private JLabel labelTitulo;
    private JLabel labelNombre;
    private JLabel labelCorreo;
    private JLabel labelContraseña;
    private JTextField textFieldNombre;
    private JTextField textFieldCorreo;
    private JPasswordField passwordField;
    private JButton botonGuardar;
    private JButton botonCancelar;

    public AgregarOperario() {
        setTitle("Agregar Operario");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    public void initComponents() {
        // Crear un JPanel que cubra toda la ventana y configurar su color de fondo a negro
        panelAgregarOperario = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        GridBagLayout gridBagLayout = new GridBagLayout();
        panelAgregarOperario.setLayout(gridBagLayout);

        labelTitulo = new JLabel("Introduzca los datos del operario");
        labelTitulo.setFont(new Font("Monospaced", Font.BOLD, 40));
        labelTitulo.setForeground(new Color(255, 0, 0));

        labelNombre = new JLabel("Nombre:");
        labelNombre.setForeground(Color.WHITE);
        labelCorreo = new JLabel("Correo:");
        labelCorreo.setForeground(Color.WHITE);
        labelContraseña = new JLabel("Contraseña:");
        labelContraseña.setForeground(Color.WHITE);

        textFieldNombre = new JTextField(30);
        textFieldCorreo = new JTextField(30);
        passwordField = new JPasswordField(30);

        botonGuardar = new JButton("Guardar");

        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textFieldNombre.getText();
                String correo = textFieldCorreo.getText();
                char[] contrasena = passwordField.getPassword();
                String contrasenaString = new String(contrasena);


                if (Conection.insertarOperario(nombre, correo, contrasenaString)){
                    JOptionPane.showMessageDialog(null, "Inserción exitosa del operario", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "No se pudo realizar la inserción del operario", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botonCancelar = new JButton("Cancelar");

        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpcionesOperarioView opcionesOperarioView = new OpcionesOperarioView();
                opcionesOperarioView.initComponents();
                opcionesOperarioView.setVisible(true);
                setVisible(false);

            }
        });

        labelNombre.setFont(new Font("Liberation Sans", Font.BOLD, 36));
        labelCorreo.setFont(new Font("Liberation Sans", Font.BOLD, 36));
        labelContraseña.setFont(new Font("Liberation Sans", Font.BOLD, 36));

        textFieldNombre.setFont(new Font("Liberation Sans", Font.PLAIN, 36));
        textFieldCorreo.setFont(new Font("Liberation Sans", Font.PLAIN, 36));
        passwordField.setFont(new Font("Liberation Sans", Font.PLAIN, 36));

        botonGuardar.setBackground(new Color(255, 204, 51));
        botonGuardar.setFont(new Font("Liberation Sans", Font.BOLD, 50));

        botonCancelar.setBackground(new Color(255, 204, 51));
        botonCancelar.setFont(new Font("Liberation Sans", Font.BOLD, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 20, 10); // Espaciado entre componentes (aumento en la parte inferior)

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelAgregarOperario.add(labelTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        panelAgregarOperario.add(labelNombre, gbc);

        gbc.gridx = 1;
        panelAgregarOperario.add(textFieldNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panelAgregarOperario.add(labelCorreo, gbc);

        gbc.gridx = 1;
        panelAgregarOperario.add(textFieldCorreo, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panelAgregarOperario.add(labelContraseña, gbc);

        gbc.gridx = 1;
        panelAgregarOperario.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // Ancho completo para los botones
        panelAgregarOperario.add(botonGuardar, gbc);

        gbc.gridy++;
        panelAgregarOperario.add(botonCancelar, gbc);

        setLayout(new BorderLayout());
        add(panelAgregarOperario, BorderLayout.CENTER);
    }
}
