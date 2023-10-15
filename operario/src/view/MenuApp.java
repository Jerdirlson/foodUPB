package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;




public class MenuApp extends JFrame {
    private ArrayList<CompraProducto> listaProductos = new ArrayList<>();
    private int recibo = 0;
    private DecimalFormat decimalFormat = new DecimalFormat("#.00");
    private Fogones fogones;
    private ArrayList<Persona> listaPersonasRegistradas = new ArrayList<>(); // Lista de personas registradas
    private JTextArea pedidosRecientesTextArea; // TextArea para mostrar pedidos recientes

    public MenuApp(Fogones fogones) {

        this.fogones = fogones;
        setTitle("Tienda Online");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el panel superior con el menú
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.BLACK);
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Menu");
        JMenuItem exitMenuItem = new JMenuItem("Salir");

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFactura();
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

        agregarProducto(productosPanel, "Mojarra:20000", "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Plato1.png", 20000, 1, 1,15);
        agregarProducto(productosPanel, "Batido:10000", "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Batido.png", 10000, 1, 2,5);
        agregarProducto(productosPanel, "Mazamorra:10000", "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Maz.png", 10000, 1, 3,20);
        agregarProducto(productosPanel, "Papas fritas:50000", "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Papas.png", 50000, 1, 4,25);
        agregarProducto(productosPanel, "Carne:15000", "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Carne.png", 15000, 2, 1,10);
        agregarProducto(productosPanel, "Pollo Asado:22000", "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Asado.png", 22000, 2, 2,30);
        agregarProducto(productosPanel, "Jugos:30000", "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Jugos.png", 30000, 2, 3,5);
        agregarProducto(productosPanel, "Pan:35000", "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Bread.png", 35000, 2, 4,2);
        agregarProducto(productosPanel, "Pollo frito:40000", "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Pollo.png", 40000, 3, 1,15);
        agregarProducto(productosPanel, "Cerveza:70000", "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Beer.png", 70000, 3, 2,0);
        agregarProducto(productosPanel, "Galletas:200000", "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Cookies .png", 200000, 3, 3,5);
        agregarProducto(productosPanel, "Pure de papa:10000", "C:\\Users\\57314\\Documents\\NetBeansProjects\\Disenoapp\\src\\disenoapp\\Imagenes\\Pure.png", 10000, 3, 4,10);
        add(productosPanel, BorderLayout.CENTER);

        // Botón de búsqueda
        JButton buscarButton = new JButton("Buscar por Número");
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPersona();
            }
        });
        menuPanel.add(buscarButton);

        // TextArea para mostrar pedidos recientes
        pedidosRecientesTextArea = new JTextArea("Pedidos Recientes:\n");
        pedidosRecientesTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(pedidosRecientesTextArea);
        scrollPane.setPreferredSize(new Dimension(250, 400)); // Ajusta el tamaño del JScrollPane

        add(scrollPane, BorderLayout.EAST);

        JButton realizarPedidoButton = new JButton("Realizar Pedido");
        realizarPedidoButton.setPreferredSize(new Dimension(40, 40));
        realizarPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numeroFogonAsignado = fogones.asignarFogonDisponible();
                if (numeroFogonAsignado != -1) {
                    fogones.actualizarFogones();
                    mostrarFactura(); // Mostrar factura después de realizar un pedido
                    mostrarPedidosRecientes(); // Mostrar pedidos recientes
                }
            }
        });
        menuPanel.add(realizarPedidoButton);
    }

    private void mostrarFactura() {
    double total = 0.0; // Inicializa el total como double

    StringBuilder factura = new StringBuilder("Factura:\n");

    for (CompraProducto compraProducto : listaProductos) {
        double subtotal = compraProducto.getPrecio() * compraProducto.getCantidad();
        factura.append(compraProducto.getNombre()).append(" (")
                .append(compraProducto.getCantidad()).append(" Unidad) - Precio Unitario: $")
                .append(decimalFormat.format(compraProducto.getPrecio())).append(" - Subtotal: $")
                .append(decimalFormat.format(subtotal)).append("\n");
        total += subtotal; // Acumula el subtotal al total
    }

    factura.append("Total: $").append(decimalFormat.format(total));

    JOptionPane.showMessageDialog(this, factura.toString(), "Factura", JOptionPane.INFORMATION_MESSAGE);
}

    private void mostrarPedidosRecientes() {
        StringBuilder pedidosRecientes = new StringBuilder("Pedidos Recientes:\n");

        for (CompraProducto compraProducto : listaProductos) {
            pedidosRecientes.append("Producto: ").append(compraProducto.getNombre()).append("\n");
            pedidosRecientes.append("Cantidad: ").append(compraProducto.getCantidad()).append("\n");
            pedidosRecientes.append("Precio Unitario: $").append(decimalFormat.format(compraProducto.getPrecio())).append("\n");
            pedidosRecientes.append("Subtotal: $").append(decimalFormat.format(compraProducto.getPrecio() * compraProducto.getCantidad())).append("\n");
            pedidosRecientes.append("\n");
        }

        pedidosRecientesTextArea.setText(pedidosRecientes.toString()); // Actualiza el texto del TextArea
    }

    private void agregarProducto(JPanel panel, String nombre, String ruta, int precio, int row, int col,int tiempoDeCocion) {
        JPanel ProductosdelPanel = new JPanel(new BorderLayout());
        ProductosdelPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        ImageIcon Imagenproducto = new ImageIcon(ruta);
        JLabel imagenLabel = new JLabel(Imagenproducto);
        ProductosdelPanel.add(imagenLabel, BorderLayout.CENTER);

        JLabel nombreJ = new JLabel(nombre);
        nombreJ.setHorizontalAlignment(JLabel.CENTER);
        ProductosdelPanel.add(nombreJ, BorderLayout.NORTH);

        JSpinner cantidadSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        cantidadSpinner.setPreferredSize(new Dimension(80, 30)); // Ajusta el tamaño aquí
        ProductosdelPanel.add(cantidadSpinner, BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(ProductosdelPanel, gbc);

        cantidadSpinner.addChangeListener(e -> {
            int cantidad = (int) cantidadSpinner.getValue();
            CompraProducto compraProducto = new CompraProducto(nombre, cantidad, precio,tiempoDeCocion);
            listaProductos.add(compraProducto);
        });
    }

    private void buscarPersona() {
        String numeroBuscado = JOptionPane.showInputDialog("Ingrese el número a buscar:");

        boolean encontrado = false;
        for (Persona persona : listaPersonasRegistradas) {
            if (persona.getNumero().equals(numeroBuscado)) {
                JOptionPane.showMessageDialog(null, "Número encontrado en el sistema.");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            Formulario registroFrame = new Formulario(this);
            registroFrame.setVisible(true);
        }
    }

    public ArrayList<Persona> getListaPersonasRegistradas() {
        return listaPersonasRegistradas;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Fogones fogones = new Fogones(4);
                MenuApp tiendaFrame = new MenuApp(fogones);

                fogones.setVisible(true);

                Persona personaPrueba = new Persona("Juan Perez", "1234", "Calle 123, Barrio ABC");
                tiendaFrame.getListaPersonasRegistradas().add(personaPrueba);

                tiendaFrame.setVisible(true);
            }
        });
    }
}


