/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author 57314
 */
public class Entrada extends JFrame {

    private JButton boton;
    private JButton botonAbajo;
    Imagenes fondo;
    public Entrada() {
        fondo = new Imagenes("C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Bienvenida.jpg");

        botonAbajo = new JButton("Ingresar");
        
        botonAbajo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open the other frame
                OpcionesFrame otroFrame = new OpcionesFrame();
                otroFrame.setVisible(true);
            }
        });

        this.setContentPane(fondo);

        setTitle("Domicilio");

        setSize(580, 420);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        botonAbajo.setPreferredSize(new Dimension(40, 40));

        add(botonAbajo, BorderLayout.SOUTH);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Entrada();
    }
}
