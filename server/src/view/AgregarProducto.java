package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.*;

import com.mysql.cj.protocol.Resultset.Concurrency;

import database.Conection;

public class AgregarProducto extends JFrame {

    private JPanel panelAgregarOperario;
    private JLabel labelTitulo;
    private JLabel labelNombre;
    private JLabel labelPrecio;
    private JLabel labelTiempoCoccion;
    private JTextField textFieldNombre;
    private JTextField textFieldPrecio;
    private JTextField textFieldTiempoCoccion;
    private JButton botonGuardar;
    private JButton botonCancelar;

    public AgregarProducto() {
        setTitle("Agregar Productos");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    public void initComponents() {
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

        labelTitulo = new JLabel("Introduzca los datos del producto");
        labelTitulo.setFont(new Font("Monospaced", Font.BOLD, 40));
        labelTitulo.setForeground(new Color(255, 0, 0));

        labelNombre = new JLabel("Nombre:");
        labelNombre.setForeground(Color.WHITE);
        labelPrecio = new JLabel("Precio:");
        labelPrecio.setForeground(Color.WHITE);
        labelTiempoCoccion = new JLabel("Tiempo de cocción:");
        labelTiempoCoccion.setForeground(Color.WHITE);

        textFieldNombre = new JTextField(30);
        textFieldPrecio = new JTextField(30);
        textFieldTiempoCoccion = new JTextField(30);

        botonGuardar = new JButton("Guardar");

        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre_producto = textFieldNombre.getText();
                Long precio = Long.parseLong(textFieldPrecio.getText());
                Long tiempoCoccion = Long.parseLong(textFieldTiempoCoccion.getText());

                if(Conection.insertarProducto(nombre_producto, precio, tiempoCoccion)){
                    JOptionPane.showMessageDialog(null, "Inserción exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "No se pudo realizar la inserción", "Error", JOptionPane.ERROR_MESSAGE);
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
        labelPrecio.setFont(new Font("Liberation Sans", Font.BOLD, 36));
        labelTiempoCoccion.setFont(new Font("Liberation Sans", Font.BOLD, 36));

        textFieldNombre.setFont(new Font("Liberation Sans", Font.PLAIN, 36));
        textFieldPrecio.setFont(new Font("Liberation Sans", Font.PLAIN, 36));
        textFieldTiempoCoccion.setFont(new Font("Liberation Sans", Font.PLAIN, 36));

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
        panelAgregarOperario.add(labelPrecio, gbc);

        gbc.gridx = 1;
        panelAgregarOperario.add(textFieldPrecio, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panelAgregarOperario.add(labelTiempoCoccion, gbc);

        gbc.gridx = 1;
        panelAgregarOperario.add(textFieldTiempoCoccion, gbc);

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
