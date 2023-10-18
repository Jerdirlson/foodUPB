package app;

import Client.Client;

public class App {
    public static void main(String[] args) throws Exception {
        String IP = ConfigLoader.getProperty("IP");
        String PORT = ConfigLoader.getProperty("PORT");
        String SERVICENAMECOCINA = ConfigLoader.getProperty("SERVICENAMECOCINA");
        Client clienteCocina = new Client(IP, PORT, SERVICENAMECOCINA);
        System.out.println("El cliente esta conectado? " +  clienteCocina.isConnected());
    }
}
