package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class VentanaIcono extends JFrame {
    public VentanaIcono() {
        super("Ventana con Ícono Personalizado");

        // Llamamos al método que establece el ícono personalizado
        setVentanaIcono();

        // Configuración básica de la ventana
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Agregamos un botón a la ventana
        JButton boton = new JButton("Haz clic");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(VentanaIcono.this, "¡Has hecho clic en el botón!");
            }
        });

        // Agregamos el botón al panel de contenido de la ventana
        getContentPane().add(boton, BorderLayout.CENTER);
    }

    // Método para establecer el ícono personalizado
    private void setVentanaIcono() {
        // Obtiene la URL del recurso como un recurso del classpath
        URL resource = getClass().getResource("/View/Imagenes/Frame3.png");

        if (resource != null) {
            ImageIcon icono = new ImageIcon(resource);

            // Establece el ícono de la ventana
            setIconImage(icono.getImage());
        } else {
            System.err.println("No se pudo cargar la imagen del ícono.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaIcono().setVisible(true);
            }
        });
    }
}
