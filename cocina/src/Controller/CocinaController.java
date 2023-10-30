package Controller;

import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Model.CocinaModel;
import View.VistaCocina;

public class CocinaController {
    private CocinaModel modelo;
    private VistaCocina vista;

    public CocinaController(CocinaModel modelo, VistaCocina vista, JButton[] iniciarButtons, JButton[] terminarButtons, JButton[] mostrarPedidoButtons) {
        this.modelo = modelo;
        this.vista = vista;

      
        
    }

    public void iniciarPedido(int fogonNumero) throws RemoteException{
        vista.setEstadoLabel(fogonNumero, "Cocinando "+ modelo.getStove(fogonNumero).getPedidoPreparandose().getNombre_producto());
        int i= fogonNumero;
         modelo.prepararPedido(modelo.getStove(fogonNumero).getPedidoPreparandose());
         System.out.println("El pedido que se va a preparar es : "+modelo.getStove(fogonNumero).getPedidoPreparandose().getNombre_producto());
    }

    public void terminarPedido(int fogonNumero) throws RemoteException {
        vista.setEstadoLabel(fogonNumero, "Fogon " + fogonNumero);
        modelo.finishCooking(fogonNumero);
    }

    public void mostrarPedido(int fogonNumero) throws RemoteException {
        try {
            modelo.getStoves();

            if (CocinaModel.stoves[fogonNumero].getPedidoPreparandose() != null) {
                String string = CocinaModel.stoves[fogonNumero].getPedidoPreparandose().nombre_producto;
                JOptionPane.showMessageDialog(null, "El pedido que se va a preparar en este fogon es :" + string);
            } else {
                JOptionPane.showMessageDialog(null, "No hay pedido en preparación en este fogon.");
            }
            
        } catch (Exception e) {
            System.out.println("Error en mostar pedido Controller " + e.getMessage());
        }
         
    }
}