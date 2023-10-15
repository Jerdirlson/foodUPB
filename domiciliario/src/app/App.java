import javax.swing.SwingUtilities;

import controller.ControllerFactura;
import model.FacturaModel;
import model.Persona;
import model.Producto;
import view.FacturaView;
import java.util.List;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Persona cliente = new Persona("Nombre del Cliente", "Dirección del Cliente", "");
            String numeroFactura = "12345";

            List<Producto> productos = List.of(
                    new Producto("Producto 1", 10000, 2),
                    new Producto("Producto 2", 15000, 3),
                    new Producto("Producto 3", 50000, 1));

            FacturaModel facturaModel = new FacturaModel(cliente, numeroFactura, productos);
            FacturaView facturaView = new FacturaView(facturaModel);
            ControllerFactura controllerFactura = new ControllerFactura(facturaModel, facturaView);

            facturaView.setVisible(true);
        });
    }
}
