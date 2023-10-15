/*package Controller;



import javax.swing.JButton;

import Model.CocinaModel;
import View.VistaCocina;

public class CocinaController {
    private CocinaModel modelo;
    private VistaCocina vista;

    public CocinaController(CocinaModel modelo, VistaCocina vista, JButton[] iniciarButtons, JButton[] terminarButtons, JButton[] mostrarPedidoButtons) {
        this.modelo = modelo;
        this.vista = vista;

        // Agrega los controladores de eventos a los botones aquí
        for (int i = 1; i <= 16; i++) {
            final int fogonNumero = i;
        
            // ActionListener para el botón "Iniciar" en la vista
            vista.getIniciarButton(fogonNumero).addActionListener(e -> iniciarPedido(fogonNumero));
        
            // ActionListener para el botón "Terminar" en la vista
            vista.getTerminarButton(fogonNumero).addActionListener(e -> terminarPedido(fogonNumero));
        
            // ActionListener para el botón "Mostrar Pedido" en la vista
            vista.getMostrarPedidoButton(fogonNumero).addActionListener(e -> mostrarPedido(fogonNumero));
        }
    }

    // Implementa los controladores de eventos aquí
    // Puedes llamar a métodos del modelo y la vista según sea necesario

    public void iniciarPedido(int fogonNumero) {
        vista.setEstadoLabel(fogonNumero, "Cocinando pedido");
        modelo.prepararPedido();
    }

    public void terminarPedido(int fogonNumero) {
        vista.setEstadoLabel(fogonNumero, "Fogon " + fogonNumero);
        modelo.finishCooking();
    }

    public void mostrarPedido(int fogonNumero) {
        // Implementa la lógica para mostrar el pedido en el fogón deseado
        // Utiliza fogonNumero para identificar el fogón y obtener el pedido correspondiente.
        // Por ejemplo: cocinaModel.mostrarPedido(fogonNumero);
    }
}
*/