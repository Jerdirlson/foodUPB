package view;

import java.awt.*;

import javax.swing.*;

public class LoginView extends JFrame{
    
    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18);
    public JTextField tfEmail;
    public JPasswordField pfPassword;
    public JButton btnLogin = new JButton("Login");
    
    public LoginView(){
        
    }
    public void inicializar(){
        System.out.println("Inicializando");
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

        btnLogin.setFont(mainFont);


        // SE SUPONE QUE ESTO ES LO QUE DEBE IR EN EL CONTROLADOR


        // btnLogin.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         String email = tfEmail.getText();
        //         String password = String.valueOf(pfPassword.getPassword());

        //         User user = getAuthenticatedUser(email, password);
                

        //         if(user.email != null){
        //             Main mainFrame = new Main();
        //             mainFrame.inicializar(user);
        //             dispose();
        //         }else{
        //             JOptionPane.showMessageDialog(LoginForm.this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        //         }


        //     }
        // });

        

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(mainFont);
        // btnCancel.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         dispose();
        //     }
        // });

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
}
