package controller;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import entidades.Pedido;
import entidades.Producto;
import entidades.UserClient;
import entidades.estructuras.doublee.linked.DoubleLinkedList;
import entidades.estructuras.queue.QueueList;
import interfaces.SkeletonDomicilio;
import model.DomiclioModel;
import view.FacturaView;
import javax.swing.Timer;


public class ControllerFactura {
    private SkeletonDomicilio skeletonDomicilio;
    private DefaultListModel<Producto> facturaListModel;
    private JList<Producto> facturaList;
    private Pedido currentPedido;
    public DomiclioModel model;
    public FacturaView view;
    public Timer timer;
    public int segundosRestantes = 300;
    private JLabel timerLabel;
    public DoubleLinkedList pedidosAMostrar;

    public ControllerFactura(DomiclioModel model , FacturaView view) {
        this.view = view;
        this.model = model;
        view.inicializar();
        view.setController(this);
        view.setVisible(true);
        empezarContador();
        pedidosAMostrar = new DoubleLinkedList<>();
    }

    public UserClient getCurrentUser() {
        UserClient currentUser = new UserClient();
        currentUser.setNombre_client("Nombre del Cliente");
        currentUser.setMunicipio("Municipio del Cliente");

        return currentUser;
    }

    public void empezarContador() {
        timerLabel = view.timerLabel;
    
        if (segundosRestantes == 0) {
            segundosRestantes = 300; // Reiniciar los segundos
        }
    
        timer = new Timer(1000, e -> {
            segundosRestantes--;
            int minutos = segundosRestantes / 60;
            int segundos = segundosRestantes % 60;
            String tiempoRestante = String.format("%02d:%02d", minutos, segundos);
    
            SwingUtilities.invokeLater(() -> {
                view.timerLabel.setText(tiempoRestante);
            });
    
            if (segundosRestantes <= 0) {
                timer.stop();
                JOptionPane.showMessageDialog(view, "El temporizador ha expirado.");
            }
        });
    
        timer.setRepeats(true);
        timer.start();
    }

    public void pararTimer(){
        if (timer != null && timer.isRunning()) {
            timer.stop();
            segundosRestantes = 0;
            view.timerLabel.setText("00:00");
        }
    }

    public void addPedido(UserClient cliente) {
        try {
            skeletonDomicilio.addPedido(cliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProductoToPedido(Producto producto, Pedido pedido) {
        try {
            skeletonDomicilio.addProductoToPedido(producto, pedido);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getCostoDomicilio(UserClient user) {
        try {
            return skeletonDomicilio.getCostoDomicilio(user);
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public void deliverPedido() {
        if (currentPedido != null) {
            // Agrega la lógica para entregar el pedido, por ejemplo, a través de RMI
            try {
                skeletonDomicilio.generarPedido();
                currentPedido = null; // Marca el pedido como entregado
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public DoubleLinkedList<Producto> getSelectedProducts() {
        DoubleLinkedList<Producto> productList = new DoubleLinkedList<>();

        int[] selectedIndices = facturaList.getSelectedIndices();

        for (int index : selectedIndices) {
            if (index >= 0 && index < facturaListModel.size()) {
                Producto selectedProduct = facturaListModel.getElementAt(index);
                productList.add(selectedProduct);
            }
        }

        return productList;
    }

    public DoubleLinkedList<Producto> getProductList() {
        DoubleLinkedList<Producto> productList = new DoubleLinkedList<>();
        return productList;
    }

    public Pedido getCurrentPedido() {
        return currentPedido;
    }

    public void generarPedido(){
        try {
            QueueList pedidos = model.generarPedido();

            for (int i = 0; i < pedidos.size(); i++) {
                pedidosAMostrar.add(pedidos.pop());
            }

            System.out.println("Se han adicionado los pedidos a mostrar con exito.");
        } catch (Exception e) {
            System.out.println("Error en generarPedido " + e.getMessage());
        }
    }

}