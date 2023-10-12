package entidades;

import java.io.Serializable;

public class Producto implements Serializable{

    public String nombre_producto;
    public Long precio_unitario;
    public String uri_img;

    public Producto(){

    }

    public Producto(String nombre_producto, Long precio_unitario, String uri_img) {
        this.nombre_producto = nombre_producto;
        this.precio_unitario = precio_unitario;
        this.uri_img = uri_img;
    }



    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public Long getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Long precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public String getUri_img() {
        return uri_img;
    }

    public void setUri_img(String uri_img) {
        this.uri_img = uri_img;
    }

    


}
