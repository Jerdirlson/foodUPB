package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.LoginView;
import view.dashboard;

// Este import es para llamara al controlador del login
import controller.ControllerLogin;
import model.ModelLogin;

/**
 * A class representing a controller for the dashboard view.
 */
public class ControllerDashboard implements ActionListener{

    private dashboard view;

    /**
     * Constructs a new ControllerDashboard object with the specified view.
     *
     * @param  view  the dashboard object representing the view
     */
    public ControllerDashboard(dashboard view){
        this.view = view;
        this.view.btnIngresar.addActionListener(this);
        this.view.inicializar();
    }
    /**
     * Overrides the actionPerformed method from the ActionListener interface. 
     * This method is called when an action is performed, such as a button click.
     *
     * @param  arg0  the event that triggered the action
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        ControllerLogin controller = new ControllerLogin(new LoginView(), new ModelLogin());
        view.setVisible(false);
    }
    
}
