package view;

import javax.swing.*;

import controller.ControllerAdministrator;
import model.ModelAdministrator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpcionesProducto extends JFrame {

    private JPanel panelProductosPanel1;
    private JLabel panelProductojLabel1;
    private JLabel panelProductojLabel2;
    private JButton panelProductojButton2;
    private JButton panelProductojButton3;
    private JButton volverAtrasButton; 

    public OpcionesProducto() {
        setTitle("Administrador Producto");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    public void initComponents() {
        panelProductosPanel1 = new JPanel();
        panelProductojLabel1 = new JLabel();
        panelProductojLabel2 = new JLabel();
        panelProductojButton2 = new JButton();
        panelProductojButton3 = new JButton();
        volverAtrasButton = new JButton("Volver Atrás");
        volverAtrasButton.setBackground(new Color(255, 204, 51));
        volverAtrasButton.setFont(new Font("Liberation Sans", 1, 36));

        panelProductosPanel1.setBackground(new Color(0, 0, 0));

        panelProductojLabel1.setFont(new Font("Monospaced", 1, 70));
        panelProductojLabel1.setForeground(new Color(255, 0, 0));
        panelProductojLabel1.setText("Que desea hacer con el producto");

        panelProductojLabel2.setFont(new Font("Monospaced", 1, 48));
        panelProductojLabel2.setForeground(new Color(255, 0, 51));
        panelProductojLabel2.setText("Admin: yeye");

        panelProductojButton2.setBackground(new Color(255, 204, 51));
        panelProductojButton2.setFont(new Font("Liberation Sans", 1, 36));
        panelProductojButton2.setText("Eliminar producto");
        panelProductojButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                 EliminarProducto eliminarOperario = new EliminarProducto();

                eliminarOperario.initComponents();
                eliminarOperario.setVisible(true);
                setVisible(false);
            }
        });

        panelProductojButton3.setBackground(new Color(255, 204, 51));
        panelProductojButton3.setFont(new Font("Liberation Sans", 1, 36));
        panelProductojButton3.setText("Agregar producto");
        panelProductojButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AgregarProducto agregarOperaio = new AgregarProducto();

                agregarOperaio.initComponents();
                agregarOperaio.setVisible(true);
                setVisible(false);
            }
        });

        // Configuramos el botón para volver atrás
        volverAtrasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                setVisible(false);
            ControllerAdministrator controller = new ControllerAdministrator(new AdministradorView(), new ModelAdministrator());

            }
        });

        GroupLayout panelProductosPanel1Layout = new GroupLayout(panelProductosPanel1);
        panelProductosPanel1.setLayout(panelProductosPanel1Layout);
        panelProductosPanel1Layout.setHorizontalGroup(
            panelProductosPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelProductosPanel1Layout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addComponent(panelProductojLabel1, GroupLayout.PREFERRED_SIZE, 1316, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                    .addComponent(panelProductojLabel2, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
                    .addGap(44, 44, 44))
                .addGroup(GroupLayout.Alignment.TRAILING, panelProductosPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelProductosPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panelProductojButton3, GroupLayout.PREFERRED_SIZE, 561, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panelProductojButton2, GroupLayout.PREFERRED_SIZE, 561, GroupLayout.PREFERRED_SIZE)
                        .addComponent(volverAtrasButton, GroupLayout.PREFERRED_SIZE, 561, GroupLayout.PREFERRED_SIZE) // Agregamos el botón de volver atrás
                    )
                    .addGap(655, 655, 655)
                )
        );
        panelProductosPanel1Layout.setVerticalGroup(
            panelProductosPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelProductosPanel1Layout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addGroup(panelProductosPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(panelProductojLabel1, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panelProductojLabel2)
                    )
                    .addGap(172, 172, 172)
                    .addComponent(panelProductojButton3, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                    .addComponent(panelProductojButton2, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                    .addComponent(volverAtrasButton, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE) // Agregamos el botón de volver atrás
                    .addGap(133, 133, 133)
                )
        );

        setLayout(new BorderLayout());
        add(panelProductosPanel1, BorderLayout.CENTER);
    }
}
