package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entidades.User;
import model.ModelDashboard;
import model.ModelLogin;
import view.dashboadMainPageView;
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
            ControllerDashboardMain controller = new ControllerDashboardMain(new dashboadMainPageView(), new ModelDashboard(), usuario);
            view.dispose();
        }else{
            System.out.println("Error");
        }
    }
    
}
