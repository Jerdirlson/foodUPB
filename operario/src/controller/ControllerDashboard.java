package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.LoginView;
import view.dashboard;

// Este import es para llamara al controlador del login
import controller.ControllerLogin;
import model.ModelLogin;

public class ControllerDashboard implements ActionListener{

    private dashboard view;

    public ControllerDashboard(dashboard view){
        this.view = view;
        this.view.btnIngresar.addActionListener(this);
        this.view.inicializar();
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        System.out.println("Esta llegando aqui");
        ControllerLogin controller = new ControllerLogin(new LoginView(), new ModelLogin());
        view.setVisible(false);
    }
    
}
