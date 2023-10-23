package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        panelAgregarOperario.setLayout(new GridLayout(5, 2));

        labelTitulo = new JLabel("Introduzca los datos del operario");
        labelTitulo.setFont(new Font("Monospaced", Font.BOLD, 40));
        labelTitulo.setForeground(new Color(255, 0, 0));

        labelNombre = new JLabel("Nombre:");
        labelCorreo = new JLabel("Correo:");
        labelContraseña = new JLabel("Contraseña:");

        textFieldNombre = new JTextField();
        textFieldCorreo = new JTextField();
        passwordField = new JPasswordField();

        botonGuardar = new JButton("Guardar");
        botonCancelar = new JButton("Cancelar");

        labelNombre.setFont(new Font("Liberation Sans", Font.BOLD, 36));
        labelCorreo.setFont(new Font("Liberation Sans", Font.BOLD, 36));
        labelContraseña.setFont(new Font("Liberation Sans", Font.BOLD, 36));

        textFieldNombre.setFont(new Font("Liberation Sans", Font.PLAIN, 36));
        textFieldCorreo.setFont(new Font("Liberation Sans", Font.PLAIN, 36));
        passwordField.setFont(new Font("Liberation Sans", Font.PLAIN, 36));

        botonGuardar.setBackground(new Color(255, 204, 51));
        botonGuardar.setFont(new Font("Liberation Sans", Font.BOLD, 36));

        botonCancelar.setBackground(new Color(255, 204, 51));
        botonCancelar.setFont(new Font("Liberation Sans", Font.BOLD, 36));

        // Agregar el ActionListener para el botón Guardar
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textFieldNombre.getText();
                String correo = textFieldCorreo.getText();
                char[] contrasena = passwordField.getPassword();

                // Realizar acciones para guardar los datos del operario
                // Aquí debes implementar la lógica para guardar los datos en tu modelo.
            }
        });

        // Agregar el ActionListener para el botón Cancelar
        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpcionesOperarioView agregarOperarioFrame = new OpcionesOperarioView();
                // Hacer que el nuevo frame sea visible
                agregarOperarioFrame.initComponents();
                agregarOperarioFrame.setVisible(true);
                setVisible(false);
            }
        });

        panelAgregarOperario.add(labelTitulo);
        panelAgregarOperario.add(new JLabel(""));

        panelAgregarOperario.add(labelNombre);
        panelAgregarOperario.add(textFieldNombre);

        panelAgregarOperario.add(labelCorreo);
        panelAgregarOperario.add(textFieldCorreo);

        panelAgregarOperario.add(labelContraseña);
        panelAgregarOperario.add(passwordField);

        panelAgregarOperario.add(botonGuardar);
        panelAgregarOperario.add(botonCancelar);

        setLayout(new BorderLayout());
        add(panelAgregarOperario, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AgregarOperario agregarOperario = new AgregarOperario();
                agregarOperario.setVisible(true);
            }
        });
    }
}
