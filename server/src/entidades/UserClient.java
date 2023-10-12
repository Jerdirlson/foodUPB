package entidades;

import java.io.Serializable;

public class UserClient implements Serializable{
    public String nombre_client;
    public Long numero_cliente;
    public Boolean vip;
    public String barrio;
    public String calle;
    public String numero; 
    public Boolean casa;
    public String municipio;


    public UserClient(String nombre_client, Long numero_cliente, Boolean vip, String barrio, String calle, String numero,
            Boolean casa, String municipio) {
        this.nombre_client = nombre_client;
        this.numero_cliente = numero_cliente;
        this.vip = vip;
        this.barrio = barrio;
        this.calle = calle;
        this.numero = numero;
        this.casa = casa;
        this.municipio = municipio;
    }


    public UserClient(){

    }


    public String getNombre_client() {
        return nombre_client;
    }


    public void setNombre_client(String nombre_client) {
        this.nombre_client = nombre_client;
    }


    public Long getNumero_cliente() {
        return numero_cliente;
    }


    public void setNumero_cliente(Long numero_cliente) {
        this.numero_cliente = numero_cliente;
    }


    public Boolean getVip() {
        return vip;
    }


    public void setVip(Boolean vip) {
        this.vip = vip;
    }


    public String getBarrio() {
        return barrio;
    }


    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }


    public String getCalle() {
        return calle;
    }


    public void setCalle(String calle) {
        this.calle = calle;
    }


    public String getNumero() {
        return numero;
    }


    public void setNumero(String numero) {
        this.numero = numero;
    }


    public Boolean getCasa() {
        return casa;
    }


    public void setCasa(Boolean casa) {
        this.casa = casa;
    }


    public String getMunicipio() {
        return municipio;
    }


    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }


    
    
}
