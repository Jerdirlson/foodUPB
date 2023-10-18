package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import entidades.User;
import model.ModelAdministrator;
import model.ModelLogin;
import view.AdministradorView;
import view.LoginView;


/**
 * A class representing a controller for the Login view.
 */
public class ControllerLogin implements ActionListener {

    private LoginView view;
    private ModelLogin model;

    /**
     * Constructs a new ControllerLogin object with the specified view and model.
     *
     * @param  view   the LoginView object representing the view
     * @param  model  the ModelLogin object representing the model
     */
    public ControllerLogin(LoginView view, ModelLogin model) {
        this.view = view;
        this.model = model;
        this.view.loginButton.addActionListener(this);
        this.view.inicializar();
    }

    /**
     * Executes the action event triggered by the user.
     *
     * @param  arg0  the ActionEvent object representing the event
     * @return       void
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        
        model.user.setCorreo(view.nameTextField.getText());
        model.user.setPassword(String.valueOf(view.passwordField.getPassword()));
        User usuario = ModelLogin.getAuthenticatedUser(model.user.email, model.user.password);

        if(usuario.email != null){
            ControllerAdministrator controller = new ControllerAdministrator(new AdministradorView(), new ModelAdministrator());
            view.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "El operador no esta registrado, usuario o contraseña incorrecto.", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
