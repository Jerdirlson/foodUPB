package app;
import controller.ControllerLogin;
import model.ModelLogin;
import view.LoginView;
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

            ControllerLogin loginController = new ControllerLogin(new LoginView(), new ModelLogin());
            
        } catch (Exception e) {
            System.out.println("Error al intentar conectar el servidor "+ e.getMessage());
        }
    }
}
    