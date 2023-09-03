package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import model.ModelLogin;
import model.User;
import view.dashboadMainPage;
import view.LoginView;

public class ControllerLogin implements ActionListener {

    private LoginView view;
    private ModelLogin model;

    public ControllerLogin(LoginView view, ModelLogin model) {
        this.view = view;
        this.model = model;
        this.view.loginButton.addActionListener(this);
        this.view.inicializar();
    }    

    @Override
    public void actionPerformed(ActionEvent arg0) {
        
        model.user.setCorreo(view.nameTextField.getText());
        model.user.setPassword(String.valueOf(view.passwordField.getPassword()));
        User usuario = ModelLogin.getAuthenticatedUser(model.user.email, model.user.password);

        if(usuario.email != null){
            dashboadMainPage mainFrame = new dashboadMainPage();
            mainFrame.inicializar(usuario);
            view.dispose();
        }else{
            System.out.println("Error");
        }
    }
    
}
