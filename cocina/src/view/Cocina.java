
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * The `Cocina` class represents a JFrame that represents a kitchen.
 */
public class Cocina extends JFrame {
   /**
   * Constructs a new `Cocina` object.
   */
    public Cocina() {
        setTitle("Login");
        setSize(580, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Etiqueta de imagen de fondo
        ImageIcon backgroundImage = new ImageIcon(
                "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Cocina.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        mainPanel.add(backgroundLabel, BorderLayout.CENTER);

        // Panel para los campos de texto y el botón
        JPanel contentPanel = new JPanel(new GridLayout(0, 1, 20, 20));
        contentPanel.setOpaque(false); // Hace que el panel sea transparente
        mainPanel.add(contentPanel, BorderLayout.WEST);

        // Campo de texto para el nombre
        JLabel nameLabel = new JLabel("Nombre:");
        JTextField nameTextField = new JTextField(25);
        contentPanel.add(nameLabel);
        contentPanel.add(nameTextField);

        // Campo de texto para la contraseña
        JLabel passwordLabel = new JLabel("Contraseña:");
        JPasswordField passwordField = new JPasswordField(25);
        contentPanel.add(passwordLabel);
        contentPanel.add(passwordField);

        // Botón de inicio de sesión
        JButton loginButton = new JButton("Iniciar sesión");
        contentPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = nameTextField.getText();
                String password = new String(passwordField.getPassword());
                JOptionPane.showMessageDialog(null,
                        "Nombre: " + username + "\nContraseña: " + password + "\n REGISTRO EXITOSO");
            }
        });

        // Establece el panel principal como el contenido del JFrame
        this.setContentPane(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Cocina().setVisible(true);
            }
        });
    }
}
