import server.Server;

public class App {
    public static void main(String[] args) throws Exception {
        Server server = new Server( "localhost", "5000", "servicioOperario" );
        server.deploy();
        System.out.println("Servicio en linea");
    }
}
