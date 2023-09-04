/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.CompraProducto;

/**
 *
 * @author 57314
 */
public class ProductosComprados extends JFrame {
    
    public ProductosComprados(ArrayList<CompraProducto> productosComprados) {
        setTitle("Productos Comprados");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextArea productosTextArea = new JTextArea();
        productosTextArea.setEditable(false);

        for (CompraProducto compraProducto : productosComprados) {
            productosTextArea.append(compraProducto.getNombre() + " (" + compraProducto.getCantidad() + " unidades)\n");
        }

        JScrollPane scrollPane = new JScrollPane(productosTextArea);

        add(scrollPane);
    }
}
