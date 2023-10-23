package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarProducto extends JFrame {

    private JPanel panelEliminarOperario;
    private JLabel labelTitulo;
    private JLabel labelBuscar;
    private JTextField textFieldBusqueda;
    private JButton botonEliminar;
    private JButton botonCancelar;

    public EliminarProducto() {
        setTitle("Eliminar Producto");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    public void initComponents() {
        // Crear un JPanel que cubra toda la ventana y configurar su color de fondo a negro
        panelEliminarOperario = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panelEliminarOperario.setLayout(new GridLayout(4, 2));

        labelTitulo = new JLabel("Buscar y Eliminar Producto");
        labelTitulo.setFont(new Font("Monospaced", Font.BOLD, 40));
        labelTitulo.setForeground(new Color(255, 0, 0));

        labelBuscar = new JLabel("Buscar por Nombre:");
        textFieldBusqueda = new JTextField();

        botonEliminar = new JButton("Eliminar Producto");
        botonCancelar = new JButton("Cancelar");

        labelBuscar.setFont(new Font("Liberation Sans", Font.BOLD, 36));

        textFieldBusqueda.setFont(new Font("Liberation Sans", Font.PLAIN, 36));

        botonEliminar.setBackground(new Color(255, 0, 0));
        botonEliminar.setFont(new Font("Liberation Sans", Font.BOLD, 36));
        botonEliminar.setForeground(Color.WHITE);

        botonCancelar.setBackground(new Color(255, 204, 51));
        botonCancelar.setFont(new Font("Liberation Sans", Font.BOLD, 36));

        // Agregar el ActionListener para el botón Eliminar Operario
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String busqueda = textFieldBusqueda.getText();
                // Realizar acciones para eliminar el operario
                // Aquí debes implementar la lógica para buscar y eliminar los datos del operario en tu modelo.
            }
        });

        // Agregar el ActionListener para el botón Cancelar
        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpcionesOperarioView opcionesOperarioFrame = new OpcionesOperarioView();
                // Hacer que el nuevo frame sea visible
                opcionesOperarioFrame.initComponents();
                opcionesOperarioFrame.setVisible(true);
                setVisible(false);
            }
        });

        panelEliminarOperario.add(labelTitulo);
        panelEliminarOperario.add(new JLabel(""));

        panelEliminarOperario.add(labelBuscar);
        panelEliminarOperario.add(textFieldBusqueda);

        panelEliminarOperario.add(botonEliminar);
        panelEliminarOperario.add(botonCancelar);

        setLayout(new BorderLayout());
        add(panelEliminarOperario, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                EliminarOperario eliminarOperario = new EliminarOperario();
                eliminarOperario.setVisible(true);
            }
        });
    }
}
