package Vistas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
public class MenuApp extends JFrame {

     private ArrayList<CompraProducto> ListaProductos = new ArrayList<>();
     int recibo=0;
     private DecimalFormat decimalFormat = new DecimalFormat("#.00");
     
     
    public MenuApp() {
        
        setTitle("Tienda Online");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el panel superior con el menú
        JPanel menuPanel = new JPanel();
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Menu");
        JMenuItem exitMenuItem = new JMenuItem("Salir");

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarProductosComprados();
                System.exit(0);
            }
        });

        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        menuPanel.add(menuBar);


        add(menuPanel, BorderLayout.NORTH);

        // Crear el panel central con las imágenes y detalles de los productos
        JPanel productosPanel = new JPanel();
        productosPanel.setLayout(new GridLayout(3, 4)); // Varios productos en filas de 2

        agregarProducto(productosPanel,"Mojarra:20000", "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Plato1.png", 20000,1,1);
        agregarProducto(productosPanel, "Batido:10000", "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Batido.png",10000,1,2);
        agregarProducto(productosPanel, " Mazamorra:10000", "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Maz.png",10000,1,3);
        agregarProducto(productosPanel, "Papas fritas 50000", "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Papas.png",50000,1,4);
        agregarProducto(productosPanel, "Carne: 15000", "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Carne.png",15000,2,1);
        agregarProducto(productosPanel, "Pollo Asado:22000", "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Asado.png",22000,2,2);
        agregarProducto(productosPanel, "Jugos:30000", "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Jugos.png",30000,2,3);
        agregarProducto(productosPanel, "Pan:35000", "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Bread.png",35000,2,4);
        agregarProducto(productosPanel, "Pollo frito", "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Pollo.png", 40000,3,1);
        agregarProducto(productosPanel, "Cerveza", "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Beer.png", 70000,3,2);
        agregarProducto(productosPanel, "Galletas", "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Cookies .png",200000,3,3);
        agregarProducto(productosPanel, "Pure de papa", "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Pure.png", 10000,3,3);
        add(productosPanel, BorderLayout.CENTER);
        
        JButton verComprasButton = new JButton("Ver Compras");
        verComprasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarProductosComprados();
            }
        });
        add(verComprasButton, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MenuApp tiendaFrame = new MenuApp();
                tiendaFrame.setVisible(true);
            }
        });
    }
    
    

     private void mostrarProductosComprados() {
        StringBuilder mensaje = new StringBuilder("Productos comprados:\n");

        for (CompraProducto compraProducto : ListaProductos) {
            double subtotal = compraProducto.getPrecio() * compraProducto.getCantidad();
            mensaje.append("- ").append(compraProducto.getNombre()).append(" (")
                    .append(compraProducto.getCantidad()).append("Unidad) - Precio:")
                    .append(decimalFormat.format(subtotal)).append("\n");
            recibo += subtotal;
        }

        mensaje.append("Recibo: $").append(decimalFormat.format(recibo));

        JOptionPane.showMessageDialog(this, mensaje.toString(), "Productos Seleccionados", JOptionPane.INFORMATION_MESSAGE);
    }

    private void agregarProducto(JPanel panel, String nombre, String ruta, int precio, int row, int col) {
        JPanel ProductosdelPanel = new JPanel(new BorderLayout());
        ProductosdelPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        ImageIcon Imagenproducto = new ImageIcon(ruta);
        JLabel imagenLabel = new JLabel(Imagenproducto);
        ProductosdelPanel.add(imagenLabel, BorderLayout.CENTER);

        JLabel nombreJ = new JLabel(nombre);
        nombreJ.setHorizontalAlignment(JLabel.CENTER);
        ProductosdelPanel.add(nombreJ, BorderLayout.NORTH);

        JSpinner cantidadSpinner = new JSpinner(new SpinnerNumberModel(0, 0,100, 1));
        ProductosdelPanel.add(cantidadSpinner, BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(ProductosdelPanel, gbc);

        cantidadSpinner.addChangeListener(e -> {
            int cantidad = (int) cantidadSpinner.getValue();
            CompraProducto compraProducto = new CompraProducto(nombre, cantidad, precio);
            ListaProductos.add(compraProducto);
        });
    }

      

}
