package view;

import java.awt.*;
import java.net.URL;

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
        setVentanaIcono();
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

    private void setVentanaIcono() {
        
        URL resource = getClass().getResource("/view/Imagenes/Frame3.png");

        if (resource != null) {
            ImageIcon icono = new ImageIcon(resource);

            // Establece el ícono de la ventana
            setIconImage(icono.getImage());
        } else {
            System.err.println("No se pudo cargar la imagen del ícono.");
        }
    }

}
