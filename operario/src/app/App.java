package app;
import view.dashboard;


import client.Client;
import controller.ControllerDashboard;

/**
 * A class representing the entry point of the application.
 */
public class App {
    /**
     * A description of the entire Java function.
     *
     * @param  args  an array of command line arguments
     * @throws Exception  if an error occurs during execution
     */
    public static void main(String[] args) throws Exception {
        try {
            String IP = ConfigLoader.getProperty("IP_OPERARIO");
            String PORT = ConfigLoader.getProperty("PORT_OPERARIO");
            String SERVICENAMEOPERARIO = ConfigLoader.getProperty("SERVICENAMEOPERARIO");

            Client client = new Client(IP,PORT,SERVICENAMEOPERARIO);
            System.out.println("El cliente esta conectado? " + client.isConnected());
            ControllerDashboard dashboard = new ControllerDashboard(new dashboard());
        } catch (Exception e) {
            System.out.println("Error en la conexion con el cliente" + e.getMessage());
        }
    }
}