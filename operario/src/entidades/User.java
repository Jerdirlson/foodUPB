package entidades;

import java.io.Serializable;

public class User implements Serializable{
    public String name;
    public String email;
    public Long phone;
    public String adress;
    public String password;

    /**
    * Constructs a new User object with the specified name, email, phone number, address, and password.
    *
    * @param  name      the name of the user
    * @param  email     the email address of the user
    * @param  phone     the phone number of the user
    * @param  address   the address of the user
    * @param  password  the password of the user
    */
    public User(String name, String email, Long phone, String adress, String password){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.adress = adress;
        this.password = password;
    }


    public User(){

    }


    /**
     * Sets the nombre of the object.
     *
     * @param  name  the new nombre to set
     */
    public void setNombre(String name) {
        this.name = name;
    }


    /**
     * Sets the email address.
     *
     * @param  email  the email address to be set
     */
    public void setCorreo(String email) {
        this.email = email;
    }


    /**
     * Set the value of the telefono property.
     *
     * @param  phone  the new value for telefono
     */
    public void setTelefono(Long phone) {
        this.phone = phone;
    }


    /**
     * Sets the address of the object.
     *
     * @param  adress  the new address for the object
     */
    public void setDireccion(String adress) {
        this.adress = adress;
    }


    /**
     * Sets the password for the object.
     *
     * @param  password  the new password to be set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    
    
}
