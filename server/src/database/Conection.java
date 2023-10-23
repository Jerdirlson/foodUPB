package database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.ConfigLoader;

import entidades.*;
// import java.sql.Connection;
import entidades.estructuras.doublee.linked.DoubleLinkedList;
import entidades.estructuras.nodes.DoubleLinkedNode;

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
            cstm = c.prepareCall("SELECT  tbl_cliente.idtbl_cliente ,tbl_cliente.nombre_cliente, tbl_cliente.numero_cliente, tbl_cliente.vip, tbl_direccion.barrio, tbl_direccion.calle, tbl_direccion.numero, tbl_direccion.casa, tbl_direccion.municipio\n" + //
                    "FROM tbl_cliente\n" + //
                    "INNER JOIN tbl_direccion ON tbl_cliente.direccion_cliente = tbl_direccion.idtbl_direccion\n" + //
                    "WHERE tbl_cliente.numero_cliente = ?;");
            cstm.setString(1, numClient);
            ResultSet rs = cstm.executeQuery();

            if (rs.next()) {
                userClient.setUserId(rs.getLong("idtbl_cliente"));
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
            producto.setIdProducto(rs.getLong("idtbl_producto"));
            producto.setNombre_producto(rs.getString("nombre_producto"));
            producto.setUri_img(rs.getString("uri_img")); 
            producto.setPrecio_unitario(rs.getLong("precio_unitario"));
            producto.setTiempoDeCocion(rs.getLong("tiempo_coccion"));
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

    public static boolean insertarCliente(UserClient cliente){
        boolean result = false;
                
        try {
             c = getConecction();
            
            // Primero, inserta la dirección en la tabla tbl_direccion
            String insertDireccionSQL = "INSERT INTO tbl_direccion (calle, numero, barrio, municipio, casa) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement direccionPS = c.prepareStatement(insertDireccionSQL, Statement.RETURN_GENERATED_KEYS)) {
                direccionPS.setString(1, cliente.calle);
                direccionPS.setString(2, cliente.numero);
                direccionPS.setString(3, cliente.barrio);
                direccionPS.setString(4, cliente.municipio);
                direccionPS.setBoolean(5, cliente.casa);

                int rowsAffected = direccionPS.executeUpdate();
                if (rowsAffected == 1) {
                    // La inserción en tbl_direccion fue exitosa, obten el ID generado
                    ResultSet generatedKeys = direccionPS.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int idDireccion = generatedKeys.getInt(1);

                        // Luego, inserta el usuario en la tabla tbl_cliente con el ID de dirección
                        String insertClienteSQL = "INSERT INTO tbl_cliente (nombre_cliente, numero_cliente, vip, direccion_cliente) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement clientePS = c.prepareStatement(insertClienteSQL)) {
                            clientePS.setString(1, cliente.nombre_client);
                            clientePS.setLong(2, cliente.numero_cliente);
                            clientePS.setBoolean(3, cliente.vip);
                            clientePS.setInt(4, idDireccion);

                            rowsAffected = clientePS.executeUpdate();
                            if (rowsAffected == 1) {
                                System.out.println("Inserción exitosa en tbl_cliente");
                                result = true;
                            } else {
                                System.out.println("No se pudo insertar el registro en tbl_cliente");
                            }
                        }
                    } else {
                        System.out.println("No se pudo obtener el ID de dirección generado.");
                    }
                } else {
                    System.out.println("No se pudo insertar la dirección en tbl_direccion");
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        

        return result;
    }

    public static boolean registrarPedido(Pedido pedido){
        boolean result = false;

        try {
            c = getConecction();

            String insertDireccionSQL = "INSERT INTO tbl_pedido (tbl_cliente_idtbl_cliente) VALUES (?)";

            try (PreparedStatement direccionPS = c.prepareStatement(insertDireccionSQL, Statement.RETURN_GENERATED_KEYS)) {
                direccionPS.setLong(1, pedido.getCliente().getUserId());

                int rowsAffected = direccionPS.executeUpdate();
                if (rowsAffected == 1) {
                    // La inserción en tbl_direccion fue exitosa, obten el ID generado
                    ResultSet generatedKeys = direccionPS.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int idPedido = generatedKeys.getInt(1);
                        String insertClienteSQL = "INSERT INTO tbl_pedido_producto (tbl_pedido_idtbl_pedido, tbl_pedido_tbl_cliente_idtbl_cliente, tbl_producto_idtbl_producto) VALUES (?, ?, ?)";

                        Iterator iterador = pedido.getProductos().iterator();

                        while (iterador.hasNext()){
                            DoubleLinkedNode<Producto> currentNode = (DoubleLinkedNode<Producto>)iterador.next();
                            
                            try (PreparedStatement clientePS = c.prepareStatement(insertClienteSQL)) {
                                clientePS.setLong(1, idPedido);
                                clientePS.setLong(2,pedido.getCliente().getUserId());
                                clientePS.setLong(3, currentNode.getObject().idProducto);
    
                                rowsAffected = clientePS.executeUpdate();
                                if (rowsAffected == 1) {
                                    System.out.println("Inserción exitosa en tbl_pedido_producto");
                                    result = true;
                                } else {
                                    System.out.println("No se pudo insertar el registro en tbl_pedido_producto");
                                }
                            }catch(Exception e){
    
                            }
                            
                        }
                        
                    } else {
                        System.out.println("No se pudo obtener el ID del pedido generado.");
                    }
                } else {
                    System.out.println("No se pudo insertar la dirección en tbl_pedido_producto");
                }
            }
            return result;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }


    public static Producto[] getProductosRecientes(UserClient client) {
        Producto[] productos = null;
        Connection c = null;
        CallableStatement cstm = null;
    
        try {
            c = getConecction();
            cstm = c.prepareCall("SELECT tbl_producto_idtbl_producto, COUNT(*) AS total_pedidos " +
            "FROM tbl_pedido_producto " +
            "WHERE tbl_pedido_tbl_cliente_idtbl_cliente = ? " +
            "GROUP BY tbl_producto_idtbl_producto " + // Agregar espacio aquí
            "ORDER BY total_pedidos DESC " +
            "LIMIT 5");

            cstm.setLong(1, client.getUserId()); // Reemplaza client.getId() con el método adecuado para obtener el ID del cliente.
    
            ResultSet rs = cstm.executeQuery();
    
            List<Long> productoIds = new ArrayList<>();
    
            while (rs.next()) {
                long productoId = rs.getLong("tbl_producto_idtbl_producto");
                productoIds.add(productoId);
            }
    
            // Crea una lista de productos con la información de tbl_producto
            List<Producto> listaProductos = new ArrayList<>();
    
            for (Long productoId : productoIds) {
                PreparedStatement productoStmt = c.prepareStatement("SELECT * FROM tbl_producto WHERE idtbl_producto = ?");
                productoStmt.setLong(1, productoId);
                ResultSet productoRs = productoStmt.executeQuery();
    
                if (productoRs.next()) {
                    Producto producto = new Producto();
                    producto.setIdProducto(productoRs.getLong("idtbl_producto"));
                    producto.setNombre_producto(productoRs.getString("nombre_producto"));
                    producto.setUri_img(productoRs.getString("uri_img"));
                    producto.setPrecio_unitario(productoRs.getLong("precio_unitario"));
                    producto.setTiempoDeCocion(productoRs.getLong("tiempo_coccion"));
                    listaProductos.add(producto);
                }
    
                productoRs.close();
                productoStmt.close();
            }
    
            // Convierte la lista de productos en un array
            productos = listaProductos.toArray(new Producto[0]);
    
            rs.close();
            cstm.close();
        } catch (Exception e) {
            System.out.println("Error en obtener los productos " + e.getMessage());
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    // Manejo de excepciones en caso de errores al cerrar la conexión.
                }
            }
        }
    
        return productos;
    }
}
