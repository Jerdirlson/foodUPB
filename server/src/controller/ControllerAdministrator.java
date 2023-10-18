package controller;

import model.ModelAdministrator;
import view.AdministradorView;
public class ControllerAdministrator {

    private AdministradorView view;
    private ModelAdministrator model;


    public ControllerAdministrator(AdministradorView view, ModelAdministrator model) {
        this.view = view;
        this.model = model;
        view.inicializar();
        
    }

    
    
}
