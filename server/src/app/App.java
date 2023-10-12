package app;
import server.Server;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            String IP = ConfigLoader.getProperty("IP");
            String PORT = ConfigLoader.getProperty("PORT");
            String SERVICENAMEOPERARIO = ConfigLoader.getProperty("SERVICENAMEOPERARIO");
            Server server = new Server( IP,PORT,SERVICENAMEOPERARIO);
            server.deploy();
            System.out.println("Servicio en linea");
            
        } catch (Exception e) {
            System.out.println("Error al intentar conectar el servidor "+ e.getMessage());
        }
    }
}
    