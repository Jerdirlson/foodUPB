package app;

import View.VistaCocina;
import client.Client;

public class App {
    public static void main(String[] args) throws Exception {
        String IP = ConfigLoader.getProperty("IP_COCINA");
        String PORT = ConfigLoader.getProperty("PORT_COCINA");
        String SERVICENAMECOCINA = ConfigLoader.getProperty("SERVICENAMECOCINA");
        Client clienteCocina = new Client(IP, PORT, SERVICENAMECOCINA);
        System.out.println("El cliente esta conectado? " +  clienteCocina.isConnected());

        VistaCocina.main(args);
    }
}
