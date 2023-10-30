package view;

import java.awt.*;

import javax.swing.*; 

/**
 * A class representing view for the dashboard.
 * @author 
 */
public class dashboard extends JFrame {

    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18);
    public JButton btnIngresar = new JButton("Ingresar");
    Imagenes fondo;

    public dashboard() {
    }

    /**
     * Initializes the function. Sets up the background image, title, size, location, layout, and visibility of the frame.
     *
     * @param  None
     * @return None
     */
    public void inicializar(){
        fondo = new Imagenes("assets/Bienvenida.jpg");

        this.setContentPane(fondo);

        setTitle("Dashboard - Operario");

        setSize(1920, 1080);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        btnIngresar.setPreferredSize(new Dimension(40, 40));

        add(btnIngresar, BorderLayout.SOUTH);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
