package view;

import java.awt.*;

import javax.swing.*; 

/**
 * Este es la vista que vera el operario recien ingresa a la aplicacion
 * @author 
 */
public class dashboard extends JFrame {

    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18);
    public JButton btnIngresar = new JButton("Ingresar");
    Imagenes fondo;

    public dashboard() {
    }

    public void inicializar(){
        fondo = new Imagenes("operario/assets/Bienvenida.jpg");

        this.setContentPane(fondo);

        setTitle("Dashboard - Operario");

        setSize(580, 420);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        btnIngresar.setPreferredSize(new Dimension(40, 40));

        add(btnIngresar, BorderLayout.SOUTH);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
