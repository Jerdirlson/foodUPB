package view;

import javax.swing.*;

import entidades.UserClient;

import java.awt.*;

public class ClienteInputDialog extends JPanel {
    private JTextField nombreField;
    private JTextField numeroField;
    private JTextField calleField;
    private JTextField numeroDireccionField;
    private JTextField barrioField;
    private JTextField municipioField;
    private JCheckBox esCasaCheckbox;
    private JCheckBox vipCheckbox;

    public ClienteInputDialog() {
        setLayout(new GridLayout(8, 2));

        nombreField = new JTextField();
        numeroField = new JTextField();
        calleField = new JTextField();
        numeroDireccionField = new JTextField();
        barrioField = new JTextField();
        municipioField = new JTextField();
        esCasaCheckbox = new JCheckBox("Es Casa");
        vipCheckbox = new JCheckBox("VIP");

        add(new JLabel("Nombre del Cliente:"));
        add(nombreField);
        add(new JLabel("Número de Cliente:"));
        add(numeroField);
        add(new JLabel("VIP:"));
        add(vipCheckbox);
        add(new JLabel("Calle:"));
        add(calleField);
        add(new JLabel("Número:"));
        add(numeroDireccionField);
        add(new JLabel("Barrio:"));
        add(barrioField);
        add(new JLabel("Municipio:"));
        add(municipioField);
        add(new JLabel("Tipo de Dirección:"));
        add(esCasaCheckbox);
    }

    public UserClient showDialog() {
        int result = JOptionPane.showConfirmDialog(null, this, "Ingresar Datos del Cliente", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String nombre = nombreField.getText();
            Long numero = Long.parseLong(numeroField.getText());
            boolean vip = vipCheckbox.isSelected();
            String calle = calleField.getText();
            String numeroDireccion = numeroDireccionField.getText();
            String barrio = barrioField.getText();
            String municipio = municipioField.getText();
            boolean casa = esCasaCheckbox.isSelected();

            return new UserClient(nombre, numero, vip, barrio, calle, numeroDireccion, casa, municipio);
        }

        return null;
    }
}
