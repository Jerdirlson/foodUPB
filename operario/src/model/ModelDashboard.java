package model;

import client.Client;
import entidades.UserClient;
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


}
