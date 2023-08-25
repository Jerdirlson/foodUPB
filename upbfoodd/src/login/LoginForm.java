package login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import database.Conection;
import app.Main;

import javax.swing.*;  //

import model.User;

public class LoginForm  extends JFrame{

    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18);
    JTextField tfEmail;
    JPasswordField pfPassword; 
    
    public void inicializar(){
        /* Este es el FORM PANEL */

        JLabel lbLoginForm = new JLabel("Login", SwingConstants.CENTER);
        lbLoginForm.setFont(mainFont);


        JLabel lbEmail = new JLabel("Email");
        lbEmail.setFont(mainFont);

        tfEmail = new JTextField();
        tfEmail.setFont(mainFont);

        JLabel lbpassword = new JLabel("Password");
        lbpassword.setFont(mainFont);

        pfPassword = new JPasswordField();
        pfPassword.setFont(mainFont);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 1, 10, 10));

        formPanel.add(lbLoginForm);
        formPanel.add(lbEmail);
        formPanel.add(tfEmail);
        formPanel.add(lbpassword);
        formPanel.add(pfPassword);


        /*Seteo de los botones  */

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(mainFont);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());

                User user = getAuthenticatedUser(email, password);
                

                if(user.email != null){
                    Main mainFrame = new Main();
                    mainFrame.inicializar(user);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(LoginForm.this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }


            }
        });

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(mainFont);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2, 10, 0));
        buttonsPanel.add(btnLogin);
        buttonsPanel.add(btnCancel);


        /*Inicilizar el frame */

        add(formPanel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.SOUTH);


        setTitle("Login");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 500); //Esto va a cambiar para ajustarse a las ventanas de las pantallas del i202
        setMinimumSize(new Dimension(400, 500));
        setLocationRelativeTo(null);
        setVisible(true);
    }



    public static User getAuthenticatedUser(String email, String password){
        
        User user = null;

        try {
            user = Conection.getUser(email, password);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

        return user;
    }

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm();
        loginForm.inicializar();
    }
}