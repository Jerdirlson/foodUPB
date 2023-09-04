package app;
import view.dashboard;
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
        ControllerDashboard dashboard = new ControllerDashboard(new dashboard());
    }
}