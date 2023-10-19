package entidades;

import java.io.Serializable;

public class Ciudad implements Serializable {
    private String nombre;
    private int costoDomicilio; 

    public Ciudad(String nombre, int costoDomicilio) {
        this.nombre = nombre;
        this.costoDomicilio = costoDomicilio;
    }

    public int getCostoDomicilio() {
        return costoDomicilio;
    }

    public String getNombre() {
        return nombre;
    }
}
