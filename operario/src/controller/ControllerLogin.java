package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import model.ModelLogin;
import model.User;
import view.Dashboad;
import view.LoginView;

public class ControllerLogin implements ActionListener {

    private LoginView view;
    private ModelLogin model;

    public ControllerLogin(LoginView view, ModelLogin model) {
        this.view = view;
        this.model = model;
        System.out.println("Llega hasta aqui");
        this.view.btnLogin.addActionListener(this);
        this.view.inicializar();
    }    

    @Override
    public void actionPerformed(ActionEvent arg0) {
        
        model.user.setCorreo(view.tfEmail.getText());
        model.user.setPassword(String.valueOf(view.pfPassword.getPassword()));
        User usuario = ModelLogin.getAuthenticatedUser(model.user.email, model.user.password);

        if(usuario.email != null){
            Dashboad mainFrame = new Dashboad();
            mainFrame.inicializar(usuario);
            view.dispose();
        }else{
            System.out.println("Error");
        }
    }
    
}
