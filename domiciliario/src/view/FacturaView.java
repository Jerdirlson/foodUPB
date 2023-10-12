package view;

import model.FacturaModel;
import model.Producto;
import model.Persona;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FacturaView extends JFrame {
    private JTextArea facturaTextArea;

    public FacturaView(FacturaModel facturaModel) {
        setTitle("Factura de Compra");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.BLACK);

        JPanel tituloPanel = new JPanel();
        JLabel tituloLabel = new JLabel("Factura de Compra");
        tituloLabel.setFont(new Font("ADLaM Display", Font.BOLD, 24));
        tituloLabel.setForeground(Color.RED);
        tituloPanel.setBackground(Color.BLACK);
        tituloPanel.add(tituloLabel);
        facturaTextArea = new JTextArea();

        // Configurar el área de texto y mostrar datos
        // codigo de configuración del área de texto omitido para brevedad

        panelPrincipal.add(tituloPanel, BorderLayout.NORTH);
        panelPrincipal.add(new JScrollPane(facturaTextArea), BorderLayout.CENTER);

        setContentPane(panelPrincipal);
    }

    public JTextArea getFacturaTextArea() {
        return facturaTextArea;
    }
}
