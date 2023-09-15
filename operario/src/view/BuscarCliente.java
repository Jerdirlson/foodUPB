
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuscarCliente extends JFrame {
    private JTextField phoneTextField;

    public BuscarCliente() {
        getContentPane().setBackground(Color.BLACK);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        setSize(580, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un panel para el contenido
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0); // Espaciado vertical

        Font labelFont = new Font("Arial", Font.PLAIN, 20);

        // Agregar el JLabel para "Número de teléfono del cliente"
        JLabel phoneLabel = new JLabel("Número de teléfono del cliente:");
        phoneLabel.setFont(labelFont);
        phoneLabel.setForeground(Color.WHITE); // Texto en blanco
        contentPanel.add(phoneLabel, gbc);

        gbc.gridy++; // Mover al siguiente renglón

        // Agregar el JTextField para ingresar el número del cliente
        phoneTextField = new JTextField(25);
        phoneTextField.setFont(labelFont);
        contentPanel.add(phoneTextField, gbc);

        gbc.gridy++; // Mover al siguiente renglón

        // Agregar el JButton para buscar
        JButton searchButton = new JButton("Buscar");
        searchButton.setFont(labelFont);
        searchButton.setBackground(Color.YELLOW); // Fondo amarillo
        searchButton.setForeground(Color.BLACK); // Texto en negro
        searchButton.setPreferredSize(new Dimension(150, 40)); // Tamaño personalizado
        contentPanel.add(searchButton, gbc);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Agregar ActionListener al botón de búsqueda
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phoneNumber = phoneTextField.getText();
                // MostrarPedidosRecientes mostrarPedidos = new MostrarPedidosRecientes(menuApp);
        
                // Cerrar la ventana actual de BuscarCliente
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BuscarCliente buscarCliente = new BuscarCliente();
                buscarCliente.setVisible(true);
            }
        });
    }
}
