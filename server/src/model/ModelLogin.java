package model;
//Aqui va la conexion a la base de datos 

import database.Conection;
import entidades.User;
import server.ServiceOperario;

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
        
       User user = null;

        try {
            user = Conection.getUser(email, password);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

        return user;
    }

}
