package model;
//Aqui va la conexion a la base de datos 

import app.ConfigLoader;
import client.Client;
import entidades.User;

/**
 * A class representing a model for the Login view.
 */
public class ModelLogin {

    public User user = new User();
    
    /**
     * Retrieves the authenticated user based on the provided email and password.
     *
     * @param  email     the email of the user
     * @param  password  the password of the user
     * @return           the authenticated user
     */
    public static User getAuthenticatedUser(String email, String password){

        String IP = ConfigLoader.getProperty("IP");
        String PORT = ConfigLoader.getProperty("PORT");
        String SERVICENAMEOPERARIO = ConfigLoader.getProperty("SERVICENAMEOPERARIO");
        
        User user = null;

        Client cliente = new Client(IP, PORT, SERVICENAMEOPERARIO);

        try {
            user = cliente.login(email, password);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

        return user;
    }

}
