package model;
//Aqui va la conexion a la base de datos 

import database.Conection;

public class ModelLogin {

    public User user = new User();
    
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
