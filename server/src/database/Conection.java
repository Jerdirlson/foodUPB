package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.ConfigLoader;

import entidades.*;
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
        try{ 
            String host = ConfigLoader.getProperty("HOST");
            String user = ConfigLoader.getProperty("USER");
            String password = ConfigLoader.getProperty("PASSWORD");
            // int port = 3306;
            String database = ConfigLoader.getProperty("DATABASE");
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

    public static UserClient getUserClient(String numClient){
        UserClient userClient = new UserClient();
        try {
            c = getConecction();
            cstm = c.prepareCall("SELECT tbl_cliente.nombre_cliente, tbl_cliente.numero_cliente, tbl_cliente.vip, tbl_direccion.barrio, tbl_direccion.calle, tbl_direccion.numero, tbl_direccion.casa, tbl_direccion.municipio\n" + //
                    "FROM tbl_cliente\n" + //
                    "INNER JOIN tbl_direccion ON tbl_cliente.direccion_cliente = tbl_direccion.idtbl_direccion\n" + //
                    "WHERE tbl_cliente.numero_cliente = ?;");
            cstm.setString(1, numClient);
            ResultSet rs = cstm.executeQuery();

            if (rs.next()) {
                userClient.setNombre_client(rs.getString("nombre_cliente"));
                userClient.setNumero_cliente(rs.getLong("numero_cliente"));
                userClient.setVip(rs.getBoolean("vip"));
                userClient.setBarrio(rs.getString("barrio"));
                userClient.setCalle(rs.getString("calle"));
                userClient.setNumero(rs.getString("numero"));
                userClient.setCasa(rs.getBoolean("casa"));
                userClient.setMunicipio(rs.getString("municipio"));

            }
    
            rs.close(); 
            cstm.close();
            c.close(); 

            
        } catch (Exception e) {
            System.out.println("Error en obtener el usuario " + e.getMessage());
        }

        return userClient;
    }

    public static Producto[] getProductos(){
        Producto[] productos = null;
        try {
            c = getConecction();
        cstm = c.prepareCall("SELECT * FROM tbl_producto");
        ResultSet rs = cstm.executeQuery();

        //Esta lista se debe cambiar por una doblemente enlazada
        List<Producto> listaProductos = new ArrayList<>();

        while (rs.next()) {
            Producto producto = new Producto();
            producto.setNombre_producto(rs.getString("nombre_producto"));
            producto.setUri_img(rs.getString("uri_img")); 
            producto.setPrecio_unitario(rs.getLong("precio_unitario"));
            listaProductos.add(producto);
        }

        // Convierte la lista de productos en un array
        productos = listaProductos.toArray(new Producto[0]);

        rs.close();
        cstm.close();
        c.close();

            
        } catch (Exception e) {
            System.out.println("Error en obtener los productos " + e.getMessage());
        }

        return productos;
    }
}
