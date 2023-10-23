package app;
import controller.ControllerAdministrator;
import controller.ControllerLogin;
import model.ModelAdministrator;
import server.Server;
import view.AdministradorView;
import view.LoginView;

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

        
            ControllerAdministrator controller = new ControllerAdministrator(new AdministradorView(), new ModelAdministrator());
            
        } catch (Exception e) {
            System.out.println("Error al intentar conectar el servidor "+ e.getMessage());
        }
    }
}
    