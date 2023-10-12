package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.dashboadMainPageView;
import view.dashboard;
import model.ModelDashboard;
import entidades.Producto;
import entidades.User;
import entidades.UserClient;

public class ControllerDashboardMain implements ActionListener{
    
    private dashboadMainPageView view;
    private ModelDashboard model;
    public static UserClient cliente;
    public Producto[] productos;
    public User operario;
    public ArrayList<Producto> pedido = new ArrayList<>();
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
        
        for (Producto product : productos) {
            JPanel productPanel = createProductComponent(product);
            view.productContainer.add(productPanel);
        }
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
    }
    
    public static UserClient getUsuario(){
        return cliente;
        
    }

    private void filterProducts() {
        String filtro = view.jTextField2.getText().toLowerCase(Locale.getDefault());

        ArrayList<Producto> productosFiltrados = new ArrayList<>();

        for (Producto product : productos) {
            String nombreProducto = product.getNombre_producto().toLowerCase(Locale.getDefault());
            if (nombreProducto.contains(filtro)) {
                productosFiltrados.add(product);
            }
        }

        view.productContainer.removeAll();

        for (Producto product : productosFiltrados) {
            JPanel productPanel = createProductComponent(product);
            view.productContainer.add(productPanel);
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

        productPanel.add(innerPanel, BorderLayout.CENTER);
        
        productPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return productPanel;
    }

    
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

            UserClient usuario = model.getUserClient(numeroCliente);


            view.actualizarCamposUsuario(usuario);
        }

        
    }
    
    public void addProduct(Producto product) {
        pedido.add(product);
        JPanel productPanelPedido = createProductComponentPedido(product);
        view.selectedProductsPanel.add(productPanelPedido);
        view.jScrollPane1.setViewportView(view.selectedProductsPanel);
        view.jScrollPane1.revalidate();
    }
    
}
