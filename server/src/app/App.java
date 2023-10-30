package app;
import controller.ControllerAdministrator;
import controller.ControllerLogin;
import model.ModelAdministrator;
import model.ModelLogin;
import server.Server;
import view.AdministradorView;
import view.LoginView;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            String IP_OPERARIO = ConfigLoader.getProperty("IP");
            String PORT_OPERARIO = ConfigLoader.getProperty("PORT_OPERARIO");
            String SERVICENAMEOPERARIO = ConfigLoader.getProperty("SERVICENAMEOPERARIO");
            Server serverOperario = new Server( IP_OPERARIO,PORT_OPERARIO,SERVICENAMEOPERARIO);
            serverOperario.deployOperario();
            System.out.println("Servicio en linea de Operario");
            
            String IP_COCINA = ConfigLoader.getProperty("IP");
            String PORT_COCINA = ConfigLoader.getProperty("PORT_COCINA");
            String SERVICENAMECOCINA = ConfigLoader.getProperty("SERVICENAMECOCINA");
            Server servercocina = new Server( IP_COCINA,PORT_COCINA,SERVICENAMECOCINA);
            servercocina.deployCocina();
            System.out.println("Servicio en linea de Cocina");

            String IP_DOMICILIO = ConfigLoader.getProperty("IP");
            String PORT_DOMICILIO = ConfigLoader.getProperty("PORT_DOMICILIO");
            String SERVICEDOMICILIO = ConfigLoader.getProperty("SERIVENAMEDOMICILIO");
            Server serverdomicilio = new Server( IP_DOMICILIO,PORT_DOMICILIO,SERVICEDOMICILIO);
            serverdomicilio.deployDomicilio();
            System.out.println("Servicio en linea de domicilio");
            
        
            ControllerLogin controller = new ControllerLogin(new LoginView(), new ModelLogin());
            
        } catch (Exception e) {
            System.out.println("Error al intentar conectar el servidor "+ e.getMessage());
        }
    }
}
    