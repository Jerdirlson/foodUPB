package app;
import view.dashboard;
import controller.ControllerDashboard;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        ControllerDashboard dashboard = new ControllerDashboard(new dashboard());
    }
}