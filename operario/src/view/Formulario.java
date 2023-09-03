package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Formulario extends JFrame {

    private JTextField nombreField;
    private JTextField numeroField;
    private JTextField direccionField;
    private JButton registrarButton;
    private MenuApp menuApp;

    public Formulario(MenuApp menuApp) {
        this.menuApp = menuApp;
        setTitle("Registro de Persona");
        setSize(400, 240);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Fondo negro
        JPanel contentPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel bienvenidaLabel = new JLabel("<html><center><font color='white'>¡Bienvenido a nuestro restaurante!<br>Estamos felices de tenerte aquí.</font></center></html>");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPanel.add(bienvenidaLabel, gbc);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setForeground(Color.WHITE);
        nombreField = new JTextField(20);
        JLabel numeroLabel = new JLabel("Número:");
        numeroLabel.setForeground(Color.WHITE);
        numeroField = new JTextField(20);
        JLabel direccionLabel = new JLabel("<html>Calle/Carrera-Numero-casa<br>apto/Barrio:</html>");
        direccionLabel.setForeground(Color.WHITE);
        direccionField = new JTextField(20);

        // Centrar el texto verticalmente en las etiquetas JLabel
        nombreLabel.setVerticalAlignment(JLabel.CENTER);
        numeroLabel.setVerticalAlignment(JLabel.CENTER);
        direccionLabel.setVerticalAlignment(JLabel.CENTER);

        registrarButton = new JButton("Registrar");

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        contentPanel.add(nombreLabel, gbc);
        gbc.gridx = 1;
        contentPanel.add(nombreField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(numeroLabel, gbc);
        gbc.gridx = 1;
        contentPanel.add(numeroField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPanel.add(direccionLabel, gbc);
        gbc.gridx = 1;
        contentPanel.add(direccionField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        contentPanel.add(new JLabel(), gbc); // Espacio en blanco
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        contentPanel.add(registrarButton, gbc);

        add(contentPanel, BorderLayout.CENTER);

        // Acción del botón de registro
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtén los datos del formulario
                String nombre = nombreField.getText();
                String numero = numeroField.getText();
                String direccion = direccionField.getText();

                // Crea una nueva persona y agrégala a la lista en MenuApp
                Persona nuevaPersona = new Persona(nombre, numero, direccion);
                menuApp.getListaPersonasRegistradas().add(nuevaPersona);

                JOptionPane.showMessageDialog(null, "Registro exitoso.");
                setVisible(false); // Cierra el formulario
                dispose(); // Libera los recursos del formulario
                menuApp.setVisible(true); // Vuelve a mostrar MenuApp
            }
        });
    }

    // Resto del código...
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Fogones fogones = new Fogones(4);
                MenuApp tiendaFrame = new MenuApp(fogones);

                Persona personaPrueba = new Persona("Juan Perez", "1234", "Calle 123, Barrio ABC");
                tiendaFrame.getListaPersonasRegistradas().add(personaPrueba);

                Formulario registroFrame = new Formulario(tiendaFrame);
                registroFrame.setVisible(true);
            }
        });
    }
}
