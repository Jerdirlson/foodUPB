package rmiserver;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    
    private String ip;
    private String port;
    private String serviceName;
    private String uri;

    public Server(String ip, String port, String serviceName, String uri) {
        this.ip = ip;
        this.port = port;
        this.serviceName = serviceName;
        this.uri = String.format("//%s:%s/$s", this.ip, this.port, this.serviceName);
    }

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
