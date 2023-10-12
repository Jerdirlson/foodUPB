package model;

import java.util.List;

public class FacturaModel {
    private Persona cliente;
    private String numeroFactura;
    private List<Producto> productos;
    private double impuestoFijo=  0.8 ;
    private double costoDomicilio=5000;

    public FacturaModel(Persona cliente, String numeroFactura, List<Producto> productos) {
        this.cliente = cliente;
        this.numeroFactura = numeroFactura;
        this.productos = productos;
        this.impuestoFijo=impuestoFijo;
        this.costoDomicilio= costoDomicilio;
    }

    public Persona getCliente() {
        return cliente;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public double getImpuestoFijo() {
        return impuestoFijo;
    }

    public double getCostoDomicilio() {
        return costoDomicilio;
    }
}
