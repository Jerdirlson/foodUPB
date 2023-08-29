
package model;

public class User {
    public String name;
    public String email;
    public Long phone;
    public String adress;
    public String password;

    public User(String name, String email, Long phone, String adress, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.adress = adress;
        this.password = password;
    }


    public User(){

    }


    public void setNombre(String name) {
        this.name = name;
    }


    public void setCorreo(String email) {
        this.email = email;
    }


    public void setTelefono(Long phone) {
        this.phone = phone;
    }


    public void setDireccion(String adress) {
        this.adress = adress;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    
    
}
