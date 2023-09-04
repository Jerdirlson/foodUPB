
package model;

/**
 *
 * @author 57314
 */
public class CompraProducto {
     private String nombre;
    private int cantidad;
    private int precio;

    public CompraProducto(String nombre, int cantidad, int precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio= precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }
    
    public int getPrecio(){
        return precio;
    }
}
