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

        for (int i = 0; i < 16; i++) {
            final int fogonNumero = i;

            iniciarButtons[i].addActionListener(e -> {
                try {
                    iniciarPedido(fogonNumero);
                } catch (RemoteException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            });
            terminarButtons[i].addActionListener(e -> {
                try {
                    terminarPedido(fogonNumero);
                } catch (RemoteException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            });
            mostrarPedidoButtons[i].addActionListener(e -> mostrarPedido(fogonNumero));
        }
    }

    public void iniciarPedido(int fogonNumero) throws RemoteException{
        vista.setEstadoLabel(fogonNumero, "Cocinando pedido");
        int i= fogonNumero;
         modelo.finishCooking(modelo.getStove(fogonNumero));
    }

    public void terminarPedido(int fogonNumero) throws RemoteException {
        vista.setEstadoLabel(fogonNumero, "Fogon " + fogonNumero);
        modelo.finishCooking(modelo.getStove(fogonNumero));
    }

    public void mostrarPedido(int fogonNumero) {

          JOptionPane.showMessageDialog(null, "El pedido que se va a preparar en este fogon es :"+ modelo.getStove(fogonNumero).getPedidoPreparandose().nombre_producto);
      
    }
}