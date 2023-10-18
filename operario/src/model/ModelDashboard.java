package model;

import app.ConfigLoader;
import client.Client;
import entidades.UserClient;
import entidades.Pedido;
import entidades.Producto;

public class ModelDashboard {
    
    public static String IP = ConfigLoader.getProperty("IP");
    public static String PORT = ConfigLoader.getProperty("PORT");
    public static String SERVICENAMEOPERARIO = ConfigLoader.getProperty("SERVICENAMEOPERARIO");


    public UserClient client = new UserClient();


    public static UserClient getUserClient(String numUser){
        UserClient client = null;

        Client cliente = new Client(IP, PORT, SERVICENAMEOPERARIO);

        try {
            client = cliente.getUserClient(numUser);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

        return client;
    }

    public static Producto[] getProductos(){
        Producto[] productos = null;

        Client cliente = new Client(IP,PORT,SERVICENAMEOPERARIO);

        try {
            productos = cliente.getProductos();
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

        return productos;
    }

    public static void enviarPedido(Pedido pedido){

        Client cliente = new Client(IP, PORT, SERVICENAMEOPERARIO);

        try {
            cliente.enviarPedido(pedido);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

    }

    public static boolean insertarCliente(UserClient client){

        Client cliente = new Client(IP, PORT, SERVICENAMEOPERARIO);

        try {
            return cliente.insertarCliente(client);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            return false;
        }
    }

}
