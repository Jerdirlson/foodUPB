/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Imagenes extends JPanel implements MouseListener {
    
     private Image imagen;

    public Imagenes(String path) {
        imagen = new ImageIcon(path).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        // Dibujar la imagen escalada al tamaño del JPanel
        g.drawImage(imagen, 0, 0, width, height, this);
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
          System.out.println("Evento mouse Clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Evento mouse Pressed");   
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Evento mouse Released");   
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       System.out.println("Evento mouse Entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
       System.out.println("Evento mouse Exited");
    }
    
}
