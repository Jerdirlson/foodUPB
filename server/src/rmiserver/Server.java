package rmiserver;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    
    private String ip;
    private String port;
    private String serviceName;
    private String uri;

        /**
     * Constructs a new instance of the Server class with the specified IP address, port number, service name, and URI.
     *
     * @param ip           the IP address of the server
     * @param port         the port number of the server
     * @param serviceName the name of the service
     * @param uri          the URI of the service
     */
    public Server(String ip, String port, String serviceName, String uri) {
        this.ip = ip;
        this.port = port;
        this.serviceName = serviceName;
        this.uri = String.format("//%s:%s/$s", this.ip, this.port, this.serviceName);
    }

    /**
     * Deploys the service by setting the "java.rmi.server.hostname" system property to the specified IP address,
     * creating a registry on the specified port, and binding the service to the specified URI.
     *
     * @return  true if the service is deployed successfully, false otherwise
     */
    public boolean deploy() {
        try {
            System.setProperty("java.rmi.server.hostname", ip);
            SkeletonLogin service = new ServiceLogin();
            LocateRegistry.createRegistry(Integer.parseInt(port));
            Naming.rebind(uri, service);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
