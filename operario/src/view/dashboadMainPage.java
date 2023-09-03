package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;
import model.User;

public class dashboadMainPage extends JFrame{

    public void inicializar(User user){
        /*Info panel */

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0, 2, 5, 5));
        infoPanel.add(new JLabel("Name"));
        infoPanel.add(new JLabel(user.name));
        infoPanel.add(new JLabel("Email"));
        infoPanel.add(new JLabel(user.email));
        infoPanel.add(new JLabel("Phone"));
        infoPanel.add(new JLabel(user.phone.toString()));
        infoPanel.add(new JLabel("Adress"));
        infoPanel.add(new JLabel(user.adress));


        add(infoPanel, BorderLayout.NORTH);


        setTitle("Dashboard");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1100, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
}
