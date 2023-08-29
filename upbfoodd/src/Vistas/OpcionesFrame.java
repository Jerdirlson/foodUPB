/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;



public class OpcionesFrame extends JFrame {

     public OpcionesFrame() {
        // Crea un objeto Imagenes para cargar la imagen de fondo
        Imagenes fondo = new Imagenes("C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Listadeoperadores.jpg");

        setTitle("Elegir Opción");
        setSize(580, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crea el panel de opciones
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setOpaque(false); // Hace que el fondo del panel de opciones sea transparente

        JButton cocinaButton = new JButton("Cocina");
        JButton operadorButton = new JButton("Operador Telefónico");
        JButton administradorButton = new JButton("Administrador");

        // Cambia el tamaño de la fuente para hacer que los botones sean más grandes
        Font buttonFont = new Font("Arial", Font.PLAIN, 20); // Ajusta el tamaño según tu preferencia
        cocinaButton.setFont(buttonFont);
        operadorButton.setFont(buttonFont);
        administradorButton.setFont(buttonFont);

        optionsPanel.add(Box.createVerticalStrut(100)); // Espacio vertical más grande
        optionsPanel.add(cocinaButton);
        optionsPanel.add(Box.createVerticalStrut(70));
        optionsPanel.add(operadorButton);
        optionsPanel.add(Box.createVerticalStrut(70));
        optionsPanel.add(administradorButton);

        cocinaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Seleccionaste Cocina");
                Cocina otroFrame = new Cocina();
                otroFrame.setVisible(true);
               
            }
        });

        operadorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Seleccionaste Operador Telefónico");
                Operador operador= new Operador();
                operador.setVisible(true);
                
                
            }
            
        });

        administradorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Seleccionaste Administrador");
                Administrador admin= new Administrador();
                admin.setVisible(true);
                
            }
        });

        // Crea un panel para alinear el botón "No tiene cuenta" a la derecha
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setOpaque(false); // Hace que el fondo del panel sea transparente

        JButton noCuentaButton = new JButton("No tiene cuenta");
        noCuentaButton.setFont(buttonFont); // Cambia el tamaño de la fuente para el botón "No tiene cuenta"
        rightPanel.add(noCuentaButton);

        noCuentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "No tienes cuenta");
                Registro colilla = new Registro();
                colilla.setVisible(true);
                
            }
        });

        // Establece el panel de opciones como el contenido del panel de fondo
        fondo.setLayout(new BorderLayout());
        fondo.add(optionsPanel, BorderLayout.CENTER);
        fondo.add(rightPanel, BorderLayout.SOUTH);

        // Establece el contenedor principal como el panel de fondo con la imagen
        this.setContentPane(fondo);
    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                OpcionesFrame opcionesFrame = new OpcionesFrame();
                opcionesFrame.setVisible(true);
            }
        });
    }
}
