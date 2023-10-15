package model;

import app.ConfigLoader;
import client.Client;
import entidades.UserClient;
import entidades.Pedido;
import entidades.Producto;

public class ModelDashboard {

    public UserClient client = new UserClient();


    public static UserClient getUserClient(String numUser){
        UserClient client = null;

        Client cliente = new Client("localhost", "5000", "servicioOperario");

        try {
            client = cliente.getUserClient(numUser);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

        return client;
    }

    public static Producto[] getProductos(){
        Producto[] productos = null;

        Client cliente = new Client("localhost", "5000", "servicioOperario");

        try {
            productos = cliente.getProductos();
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

        return productos;
    }

    public static void enviarPedido(Pedido pedido){

        String IP = ConfigLoader.getProperty("IP");
        String PORT = ConfigLoader.getProperty("PORT");
        String SERVICENAMEOPERARIO = ConfigLoader.getProperty("SERVICENAMEOPERARIO");

        Client cliente = new Client(IP, PORT, SERVICENAMEOPERARIO);

        try {
            cliente.enviarPedido(pedido);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

    }

}
