package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Operador extends JFrame {
    public Operador() {
        setTitle("Login");
        setSize(580, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Etiqueta de imagen de fondo
        ImageIcon backgroundImage = new ImageIcon("operario/assets/Operador.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        mainPanel.add(backgroundLabel, BorderLayout.CENTER);

        // Panel para los campos de texto y el botón
        JPanel contentPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        contentPanel.setOpaque(false); // Hace que el panel sea transparente
        mainPanel.add(contentPanel, BorderLayout.WEST);

        // Campo de texto para el nombre
        JLabel nameLabel = new JLabel("Nombre:");
        JTextField nameTextField = new JTextField(15);
        contentPanel.add(nameLabel);
        contentPanel.add(nameTextField);

        // Campo de texto para la contraseña
        JLabel passwordLabel = new JLabel("Contraseña:");
        JPasswordField passwordField = new JPasswordField(15);
        contentPanel.add(passwordLabel);
        contentPanel.add(passwordField);

        // Botón de inicio de sesión
        JButton loginButton = new JButton("Iniciar sesión");
        contentPanel.add(loginButton);

        // Agrega un ActionListener para el botón "Iniciar sesión"
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = nameTextField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals(username) && password.equals(password)) {
                    // Cerrar la ventana actual
                    dispose();

                    // Abrir la ventana BuscarCliente
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            BuscarCliente buscarCliente = new BuscarCliente();
                            buscarCliente.setVisible(true);
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "Nombre de usuario o contraseña incorrectos", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Establece el panel principal como el contenido del JFrame
        this.setContentPane(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Operador().setVisible(true);
            }
        });
    }
}
