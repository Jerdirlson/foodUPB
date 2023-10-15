/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author 57314
 */
public class CompraProducto {
    private String nombre;
    private int precio;
    private int cantidad;
    private int tiempoDeCocion;

    public CompraProducto(String nombre, int precio, int cantidad, int tiempoDeCocion) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.tiempoDeCocion=tiempoDeCocion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public int getTiempoDeCocion(){
        return tiempoDeCocion;
    }
}

