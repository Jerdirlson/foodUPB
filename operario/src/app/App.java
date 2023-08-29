package app;
import controller.ControllerLogin;
import model.ModelLogin;
import view.LoginView;

public class App {
    public static void main(String[] args) throws Exception { 
        System.out.println("Hello World!");
        ControllerLogin controller = new ControllerLogin(new LoginView(), new ModelLogin());
    }
}