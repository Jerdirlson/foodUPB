package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import interfaces.SkeletonCocina;
import interfaces.SkeletonDomicilio;
import interfaces.SkeletonOperario;

public class Server {

    private String ip;
    private String port;
    private String serviceName;
    public String uri;

    /**
     * Constructs a new instance of the Server class with the specified IP address,
     * port number, service name, and URI.
     *
     * @param ip          the IP address of the server
     * @param port        the port number of the server
     * @param serviceName the name of the service
     * @param uri         the URI of the service
     */
    public Server(String ip, String port, String serviceName) {
        this.ip = ip;
        this.port = port;
        this.serviceName = serviceName;
        this.uri = String.format("//%s:%s/%s", this.ip, this.port, this.serviceName);
    }

    /**
     * Deploys the service by setting the "java.rmi.server.hostname" system property
     * to the specified IP address,
     * creating a registry on the specified port, and binding the service to the
     * specified URI.
     *
     * @return true if the service is deployed successfully, false otherwise
     */
    public boolean deployOperario() {
    boolean bool = false;
        try {
            if (ip == null || port == null || serviceName == null) {
                return bool;
            }
            
            System.setProperty("java.rmi.server.hostname", ip);
            
                SkeletonOperario service = new ServiceOperario();
                LocateRegistry.createRegistry(Integer.parseInt(port));
                Naming.rebind(uri, service);
            
            bool = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    return bool;
}

    public boolean deployCocina() {
    boolean bool = false;
        try {
            if (ip == null || port == null || serviceName == null) {
                return bool;
            }
            
            System.setProperty("java.rmi.server.hostname", ip);
            
                SkeletonCocina service = new ServiceCocina();
                LocateRegistry.createRegistry(Integer.parseInt(port));
                Naming.rebind(uri, service);
            
            bool = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }


    public boolean deployDomicilio() {
    boolean bool = false;
        try {
            if (ip == null || port == null || serviceName == null) {
                return bool;
            }
            
            System.setProperty("java.rmi.server.hostname", ip);
            
                SkeletonDomicilio service = new ServiceDomicilio();
                LocateRegistry.createRegistry(Integer.parseInt(port));
                Naming.rebind(uri, service);
            
            bool = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }

}
