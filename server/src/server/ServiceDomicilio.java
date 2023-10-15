package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.Conection;
import entidades.Producto;
import interfaces.SkeletonDomicilio;

public class ServiceDomicilio extends UnicastRemoteObject implements SkeletonDomicilio {
    public ServiceDomicilio() throws RemoteException {
    }

    public String registrarPedido(String datos) throws RemoteException {
        try (Connection connection = Conection.getConecction();
                PreparedStatement preparedStatement = connection
                        .prepareStatement("INSERT INTO pedidos (datos) VALUES (?)")) {
            preparedStatement.setString(1, datos);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return "Pedido registrado con éxito";
            } else {
                return "Error al registrar el pedido";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al registrar el pedido: " + e.getMessage();
        }
    }

    public String consultarEstadoPedido(int numeroPedido) throws RemoteException {
        try (Connection connection = Conection.getConecction();
                PreparedStatement preparedStatement = connection
                        .prepareStatement("SELECT estado FROM pedidos WHERE numeroPedido = ?")) {
            preparedStatement.setInt(1, numeroPedido);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String estado = resultSet.getString("estado");
                    return "Estado del pedido: " + estado;
                } else {
                    return "Pedido no encontrado";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al consultar el estado del pedido: " + e.getMessage();
        }
    }

    public Producto obtenerPedido(int numeroPedido) throws RemoteException {
        try (Connection connection = Conection.getConecction();
                PreparedStatement preparedStatement = connection
                        .prepareStatement("SELECT numero_pedido, descripcion FROM pedidos WHERE numero_pedido = ?")) {
            preparedStatement.setInt(1, numeroPedido);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int numeroPedidoDB = resultSet.getInt("numero_pedido");
                    String descripcion = resultSet.getString("descripcion");

                    Producto producto = new Producto();
                    producto.setNombre_producto(descripcion);
                    producto.setPrecio_unitario((long) numeroPedidoDB);
                    producto.setUri_img(""); // Agregar la imagen del producto

                    return producto;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
