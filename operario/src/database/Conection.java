package database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.User;
// import java.sql.Connection;

/**
 * A class representing a connection for the database.
 */
public class Conection {
    private static Connection c;
    private static CallableStatement cstm;
    
    /** 
     * This is the method that generates the connection to the database.
     * @return Connection
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws IOException
     */
    // private static ResultSet rs;

    
    /**
     * Retrieves a connection to the database.
     *
     * @return              the connection to the database
     * @throws SQLException if an error occurs while connecting to the database
     * @throws FileNotFoundException if the configuration file is not found
     * @throws IOException   if an I/O error occurs while reading the configuration file
     */
    public static Connection getConecction() throws SQLException, FileNotFoundException, IOException{
        // Properties config = new Properties();
        try  { //(FileInputStream file = new FileInputStream(new File("/U/Estructuras/proyecto/foodUPB/operario/src/config.properties")))
            // config.load(file);
            String host = "127.0.0.1";
            String user = "root";
            String password = "20011308Aron*";
            // int port = 3306;
            String database = "estructuras-database";
            boolean ssl = false;
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                System.out.println("Libreria no encontrada" + e.getMessage());
            }
            
            String url = String.format("jdbc:mysql://%s/%s?user=%s&password=%s&useSSL=%b", host, database,user, password, ssl);
            c = DriverManager.getConnection(url);
            return c;
        } catch (Exception e) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error en la conexion a la base de datos");
        }
        return null;
    }


    
    /** 
     * Metodo para retornar usuario en especifico pasandole correo
     * @param correo
     * @param password
     * @return User
     */
    public static User getUser(String correo, String password){
        User user = new User();
        try {
            c = getConecction();
            cstm = c.prepareCall("SELECT * FROM tbl_usuario WHERE email = ? AND password = ?");
            cstm.setString(1, correo);
            cstm.setString(2, password);
            ResultSet rs = cstm.executeQuery();

            if (rs.next()) {
                user.setNombre(rs.getString("name"));
                user.setCorreo(rs.getString("email"));
                user.setTelefono(rs.getLong("phone"));
                user.setDireccion(rs.getString("adress"));
                user.setPassword(rs.getString("password"));
            }
    
            rs.close(); 
            cstm.close();
            c.close(); 

            
        } catch (Exception e) {
            System.out.println("Error en obtener el usuario " + e.getMessage());
        }

        return user;
    }
}
