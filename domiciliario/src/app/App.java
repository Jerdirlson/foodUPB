package app;

import client.ClienteDomicilio;
import view.FacturaView;

public class App {
    public static void main(String[] args) throws Exception {
        String IP = ConfigLoader.getProperty("IP_DOMICILIO");
        String PORT = ConfigLoader.getProperty("PORT_DOMICILIO");
        String SERVICEDOMICILIO = ConfigLoader.getProperty("SERIVENAMEDOMICILIO");
        ClienteDomicilio clienteDomicilio = new ClienteDomicilio(IP, PORT, SERVICEDOMICILIO);
        System.out.println("El cliente esta conectado? " +  clienteDomicilio.isConnected());

        // FacturaView.main(args);
    }
}
