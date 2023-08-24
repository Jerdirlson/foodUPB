package database;

import java.sql.*;

import model.User;
// import java.sql.Connection;

public class Conection {
    private static Connection c;
    private static CallableStatement cstm;
    private static ResultSet rs;

    
    public static Connection getConecction() throws SQLException{
        Connection c;
        String host = "127.0.0.1";
        String user = "root";
        String password = "20011308Aron*";
        int port = 3306;
        String database = "estructuras-database";
        boolean ssl = false;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Libreria no encontrada" + e.getMessage());
        }

        String url = String.format("jdbc:mysql://%s/%s?user=%s&password=%s&useSSL=%b", host, database,user, password, ssl);
        return DriverManager.getConnection(url);
    }


    public static User getUser(String correo, String password){
        User user = new User();
        try {
            c = getConecction();
            cstm = c.prepareCall("SELECT * FROM tbl_usuario WHERE email = ? AND password = ?");
            cstm.setString(1, correo);
            cstm.setString(2, password);
            ResultSet rs = cstm.executeQuery();

            if (rs.next()) {
                user = new User();
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
            System.out.println("Error" + e.getMessage());
        }

        return user;
    }
}
