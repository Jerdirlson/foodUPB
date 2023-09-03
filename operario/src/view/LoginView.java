package view;

import java.awt.*;

import javax.swing.*; 

public class LoginView extends JFrame{
    
    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18);
    public JTextField nameTextField = new JTextField(15);
    public JPasswordField passwordField = new JPasswordField(15);
    public JButton loginButton = new JButton("Login");
    
    public LoginView(){
        
    }
    public void inicializar(){

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
        contentPanel.add(nameLabel);
        contentPanel.add(nameTextField);

        // Campo de texto para la contraseña
        JLabel passwordLabel = new JLabel("Contraseña:");
        contentPanel.add(passwordLabel);
        contentPanel.add(passwordField);

        // Botón de inicio de sesión
        contentPanel.add(loginButton);

        // Establece el panel principal como el contenido del JFrame
        this.setContentPane(mainPanel);
        this.setVisible(true);
    }
}
