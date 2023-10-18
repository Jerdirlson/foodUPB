package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.ClienteInputDialog;
import view.dashboadMainPageView;
import model.ModelDashboard;
import entidades.Pedido;
import entidades.Producto;
import entidades.User;
import entidades.UserClient;
import entidades.estructuras.doublee.linked.DoubleLinkedList;
import entidades.estructuras.nodes.DoubleLinkedNode;

public class ControllerDashboardMain implements ActionListener{
    
    private dashboadMainPageView view;
    private ModelDashboard model;
    public static UserClient cliente;
    public Producto[] productos;
    public User operario;
    public DoubleLinkedList<Producto> productosElegidos = new DoubleLinkedList<>();
    public Pedido pedido;
    boolean registroClienteExitoso = false;

    /**
     * Constructs a new ControllerDashboard object with the specified view.
     *
     * @param  view  the dashboard object representing the view
     */
    public ControllerDashboardMain(dashboadMainPageView view, ModelDashboard model, User usuario){
        this.view = view;
        this.model = model;
        this.cliente = new UserClient();
        this.operario = usuario;

        
        productos = model.getProductos();
        
        System.out.println("estos son los productos " + productos);
        
        this.view.inicializar(usuario);
        
        //Pintar los productos que estan en la base de datos
        for (Producto product : productos) {
            JPanel productPanel = createProductComponent(product);
            view.productContainer.add(productPanel);
        }

        //Observable para filtrar los productos 
        view.jTextField2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterProducts();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterProducts();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterProducts();
            }
        });
        this.view.jButton1.addActionListener(this);

        //Acciones para el jButton2, que es el boton de enviar pedido
        view.jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarPedido();
            }
        });
    }
    
    
    /** 
     * @return UserClient
     */
    public static UserClient getUsuario(){
        return cliente;
    }
    
    private void filterProducts() {
        String filtro = view.jTextField2.getText().toLowerCase(Locale.getDefault());

        DoubleLinkedList<Producto> productosFiltrados = new DoubleLinkedList<>();

        for (Producto product : productos) {
            String nombreProducto = product.getNombre_producto().toLowerCase(Locale.getDefault());
            if (nombreProducto.contains(filtro)) {
                productosFiltrados.add(product);
            }
        }
        view.productContainer.removeAll();


        DoubleLinkedNode<Producto> currentNode = productosFiltrados.getHead();
        while (currentNode != null) {
            Producto product = currentNode.getObject();
            JPanel productPanel = createProductComponent(product);
            view.productContainer.add(productPanel);
            currentNode = currentNode.getNext();
        }
        view.productContainer.revalidate();
        view.productContainer.repaint();
    }

    private JPanel createProductComponentPedido(Producto producto) {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BorderLayout());


        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BorderLayout());
        innerPanel.setPreferredSize(new Dimension(200, 100));

        JLabel nameLabel = new JLabel(producto.getNombre_producto());
        innerPanel.add(nameLabel, BorderLayout.NORTH);

        JLabel priceLabel = new JLabel("Precio: $" + producto.getPrecio_unitario());
        innerPanel.add(priceLabel, BorderLayout.SOUTH);

        JButton deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productosElegidos.remove(producto);
                view.selectedProductsPanel.remove(productPanel);
                view.jScrollPane1.setViewportView(view.selectedProductsPanel);
                view.jScrollPane1.revalidate();
            }
        });
        innerPanel.add(deleteButton, BorderLayout.EAST);

        productPanel.add(innerPanel, BorderLayout.CENTER);
        
        productPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return productPanel;
    }

    /**
     * Method to create a new product panel
     * @param productPanel
     * @return product
     */
    private JPanel createProductComponent(Producto product) {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BorderLayout());

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BorderLayout());
        innerPanel.setPreferredSize(new Dimension(200, 100));
        
        JLabel nameLabel = new JLabel(product.getNombre_producto());
        innerPanel.add(nameLabel, BorderLayout.NORTH);

        
        JLabel priceLabel = new JLabel("Precio: $" + product.getPrecio_unitario());
        innerPanel.add(priceLabel, BorderLayout.SOUTH);

        JButton addToCartButton = new JButton("Agregar al Pedido");
            addToCartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addProduct(product);
                }
            });
        innerPanel.add(addToCartButton, BorderLayout.CENTER);
        productPanel.add(innerPanel, BorderLayout.CENTER);
        
        productPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return productPanel;    
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.jButton1) {

            String numeroCliente = view.jTextField1.getText();

            this.cliente = model.getUserClient(numeroCliente);

            if(this.cliente.nombre_client != null) {
                view.actualizarCamposUsuario(this.cliente);
                System.out.println("Se actualizo el cliente");
            }else{
                JOptionPane.showMessageDialog(null, "El usuario no está registrado ", "Mensaje", JOptionPane.ERROR_MESSAGE);
            }
        }

        
    }
    /**
     * method to display a message dialog with addProduct
     * @param product
     */
    public void addProduct(Producto product) {
        productosElegidos.add(product);
        JPanel productPanelPedido = createProductComponentPedido(product);
        view.selectedProductsPanel.add(productPanelPedido);
        view.jScrollPane1.setViewportView(view.selectedProductsPanel);
        view.jScrollPane1.revalidate();
    }

    public void enviarPedido(){
        if (!productosElegidos.isEmpty()) {
            if (cliente.getNombre_client() == null) {
                System.out.println("Aquí llegar");
                ClienteInputDialog dialog = new ClienteInputDialog();
                UserClient clienteData = dialog.showDialog();
                if (clienteData != null) {
                    cliente = clienteData;
                    view.actualizarCamposUsuario(cliente);
                    model.insertarCliente(cliente);
                }
            }
                System.out.println("Se está enviando el producto");
                pedido = new Pedido();
                pedido.setCliente(cliente);
                pedido.setProductos(productosElegidos);
                model.enviarPedido(pedido);
        }else
        JOptionPane.showMessageDialog(null, "Debe agregar productos antes de continuar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    
}
