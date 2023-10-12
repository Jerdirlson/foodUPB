package controller;

import model.FacturaModel;
import model.Producto;
import model.Persona;
import view.FacturaView;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.io.FileInputStream;
import java.awt.Font;

public class ControllerFactura {
    private FacturaModel facturaModel;
    private FacturaView facturaView;

    public ControllerFactura(FacturaModel facturaModel, FacturaView facturaView) {
        this.facturaModel = facturaModel;
        this.facturaView = facturaView;

        facturaView.getFacturaTextArea().append("FoodUpb\n");
        Persona cliente = facturaModel.getCliente();
        facturaView.getFacturaTextArea().append("Cliente: " + cliente.getNombre() + "\n");
        facturaView.getFacturaTextArea().append("Dirección: " + cliente.getDireccion() + "\n");
        facturaView.getFacturaTextArea().append("Factura #: " + facturaModel.getNumeroFactura() + "\n\n");

        facturaView.getFacturaTextArea().append("Productos Comprados:\n");
        List<Producto> productos = facturaModel.getProductos();
        for (Producto producto : productos) {
            facturaView.getFacturaTextArea().append(
                    producto.getNombre() + " - $" + producto.getPrecio() + " x " + producto.getCantidad() + "\n");
        }

        // Calcular el subtotal
        double subtotal = calcularSubtotal(productos);
        double impuesto = subtotal * facturaModel.getImpuestoFijo();
        double costoDomicilio = facturaModel.getCostoDomicilio();
        double total = subtotal + impuesto + costoDomicilio;

        // Mostrar el subtotal, impuesto, costo de envío y total en el área de texto
        facturaView.getFacturaTextArea().append("\nSubtotal: $" + String.format("%.2f", subtotal) + "\n");
        facturaView.getFacturaTextArea().append("Impuesto (8%): $" + String.format("%.2f", impuesto) + "\n");
        facturaView.getFacturaTextArea().append("Costo de Domicilio: $" + String.format("%.2f", costoDomicilio) + "\n");
        facturaView.getFacturaTextArea().append("Total: $" + String.format("%.2f", total) + "\n");

        Font fuentePersonalizada = cargarFuentePersonalizada(
                "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\rainyhearts.ttf", 18.0f);
        aplicarFuentePersonalizada(facturaView.getFacturaTextArea(), fuentePersonalizada);

        facturaView.getFacturaTextArea().setForeground(Color.RED); 
    }

    private double calcularSubtotal(List<Producto> productos) {
        double subtotal = 0.0;
        for (Producto producto : productos) {
            subtotal += producto.getPrecio() * producto.getCantidad();
        }
        return subtotal;
    }

    private Font cargarFuentePersonalizada(String rutaFuente, float tamano) {
        Font customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(rutaFuente)).deriveFont(tamano);
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar errores relacionados con la carga de la fuente personalizada
            // Esto podría incluir mostrar un mensaje de error o usar una fuente
            // predeterminada
        }
        return customFont;
    }

    private void aplicarFuentePersonalizada(JComponent componente, Font fuente) {
        componente.setFont(fuente);
    }

}
