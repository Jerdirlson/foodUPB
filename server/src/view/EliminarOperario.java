package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarOperario extends JFrame {

    private JPanel panelEliminarOperario;
    private JLabel labelTitulo;
    private JLabel labelBuscar;
    private JTextField textFieldBusqueda;
    private JButton botonEliminar;
    private JButton botonCancelar;

    public EliminarOperario() {
        setTitle("Eliminar Operario");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    public void initComponents() {
        panelEliminarOperario = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        GridBagLayout gridBagLayout = new GridBagLayout();
        panelEliminarOperario.setLayout(gridBagLayout);

        labelTitulo = new JLabel("Buscar y Eliminar Operario");
        labelTitulo.setFont(new Font("Monospaced", Font.BOLD, 70));
        labelTitulo.setForeground(new Color(255, 0, 0));

        labelBuscar = new JLabel("Buscar por Nombre:");
        labelBuscar.setForeground(Color.WHITE);
        textFieldBusqueda = new JTextField(30);

        botonEliminar = new JButton("Eliminar Operario");

        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String busqueda = textFieldBusqueda.getText();
                // Realizar acciones para eliminar el operario
                // Implementa la lógica para buscar y eliminar los datos del operario en tu modelo.
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

        labelBuscar.setFont(new Font("Liberation Sans", Font.BOLD, 50));
        textFieldBusqueda.setFont(new Font("Liberation Sans", Font.PLAIN, 50));

        botonEliminar.setBackground(new Color(255, 0, 0));
        botonEliminar.setFont(new Font("Liberation Sans", Font.BOLD, 50));
        botonEliminar.setForeground(Color.WHITE);

        botonCancelar.setBackground(new Color(255, 204, 51));
        botonCancelar.setFont(new Font("Liberation Sans", Font.BOLD, 70));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 20, 10); // Espaciado entre componentes (aumento en la parte inferior)

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelEliminarOperario.add(labelTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        panelEliminarOperario.add(labelBuscar, gbc);

        gbc.gridx = 1;
        panelEliminarOperario.add(textFieldBusqueda, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // Ancho completo para los botones
        panelEliminarOperario.add(botonEliminar, gbc);

        gbc.gridy++;
        panelEliminarOperario.add(botonCancelar, gbc);

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
