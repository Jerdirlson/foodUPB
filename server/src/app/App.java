package app;
import controller.ControllerLogin;
import model.ModelLogin;
import view.LoginView;
import server.Server;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            String IP_OPERARIO = ConfigLoader.getProperty("IP_OPERARIO");
            String PORT_OPERARIO = ConfigLoader.getProperty("PORT_OPERARIO");
            String SERVICENAMEOPERARIO = ConfigLoader.getProperty("SERVICENAMEOPERARIO");
            Server serverOperario = new Server( IP_OPERARIO,PORT_OPERARIO,SERVICENAMEOPERARIO);
            serverOperario.deploy(true);
            System.out.println("Servicio en linea de Operario");

            String IP_COCINA = ConfigLoader.getProperty("IP_COCINA");
            String PORT_COCINA = ConfigLoader.getProperty("PORT_COCINA");
            String SERVICENAMECOCINA = ConfigLoader.getProperty("SERVICENAMECOCINA");
            Server servercocina = new Server( IP_COCINA,PORT_COCINA,SERVICENAMECOCINA);
            servercocina.deploy(false);
            System.out.println("Servicio en linea de Cocina");


            // ControllerLogin loginController = new ControllerLogin(new LoginView(), new ModelLogin());
            
        } catch (Exception e) {
            System.out.println("Error al intentar conectar el servidor "+ e.getMessage());
        }
    }
}
    