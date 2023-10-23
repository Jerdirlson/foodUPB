package view;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


/**
+ * The `Administrador` class represents a JFrame for an administrator.
+ *
+ * @author 57314
+ */
public class AdministradorView extends JFrame {

    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    
    
    private javax.swing.JPanel panelProduct;
    private javax.swing.JButton panelProductojButton2;
    private javax.swing.JButton panelProductojButton3;
    private javax.swing.JLabel panelProductojLabel1;
    private javax.swing.JLabel panelProductojLabel2;
    private javax.swing.JPanel panelProductosPanel1;

    
    /**
    * Constructs a new `Administrador` object.
    */
    public AdministradorView(){
        setTitle("Administrador");
        setSize(580, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        
        panelProduct = new javax.swing.JPanel();
        panelProductosPanel1 = new javax.swing.JPanel();
        panelProductojLabel1 = new javax.swing.JLabel();
        panelProductojLabel2 = new javax.swing.JLabel();
        panelProductojButton2 = new javax.swing.JButton();
        panelProductojButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 1080));

        jLabel1.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("Admin : yeye");

        jLabel2.setFont(new java.awt.Font("Monospaced", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("Administrador");

        jButton2.setBackground(new java.awt.Color(255, 204, 51));
        jButton2.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        jButton2.setText("Agregar o eliminar productos");
        // jButton2.setBorder(100);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setVisible(false);
                OpcionesProducto opcionesProducto = new OpcionesProducto();
                opcionesProducto.initComponents();
                opcionesProducto.setVisible(true);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 204, 51));
        jButton5.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        jButton5.setText("Agregar o eliminar operario");
        // jButton5.setBorder(100);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setVisible(false);
                OpcionesOperarioView agregarOperarioFrame = new OpcionesOperarioView();
                // Hacer que el nuevo frame sea visible
                agregarOperarioFrame.initComponents();
                agregarOperarioFrame.setVisible(true);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(53, 53, 53))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(690, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(669, 669, 669))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(138, 138, 138)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addGap(59, 59, 59)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(201, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );


        ////// Panel producto 
        panelProduct.setBackground(new java.awt.Color(0, 0, 0));
        panelProduct.setForeground(new java.awt.Color(255, 255, 255));
        panelProduct.setPreferredSize(new java.awt.Dimension(1920, 1080));




        pack();
    }

    public void inicializarPanelProducto(){

        panelProductosPanel1 = new javax.swing.JPanel();
        panelProductojLabel1 = new javax.swing.JLabel();
        panelProductojLabel2 = new javax.swing.JLabel();
        panelProductojButton2 = new javax.swing.JButton();
        panelProductojButton3 = new javax.swing.JButton();

        panelProductosPanel1.setBackground(new java.awt.Color(0, 0, 0));

        panelProductojLabel1.setFont(new java.awt.Font("Monospaced", 1, 70)); // NOI18N
        panelProductojLabel1.setForeground(new java.awt.Color(255, 0, 0));
        panelProductojLabel1.setText("Que desea hacer con el operario");

        panelProductojLabel2.setFont(new java.awt.Font("Monospaced", 1, 48)); // NOI18N
        panelProductojLabel2.setForeground(new java.awt.Color(255, 0, 51));
        panelProductojLabel2.setText("Admin : yeye");

        panelProductojButton2.setBackground(new java.awt.Color(255, 204, 51));
        panelProductojButton2.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        panelProductojButton2.setText("Eliminar Operario");
        // panelProductojButton2.setBorder(100);
        panelProductojButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // panelProductojButton2ActionPerformed(evt);
            }
        });

        panelProductojButton3.setBackground(new java.awt.Color(255, 204, 51));
        panelProductojButton3.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        panelProductojButton3.setText("Agregar Operario");
        // panelProductojButton3.setBorder(100);
        panelProductojButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // panelProductojButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelProductosPanel1Layout = new javax.swing.GroupLayout(panelProductosPanel1);
        panelProductosPanel1.setLayout(panelProductosPanel1Layout);
        panelProductosPanel1Layout.setHorizontalGroup(
            panelProductosPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductosPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(panelProductojLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                .addComponent(panelProductojLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProductosPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelProductosPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelProductojButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelProductojButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(655, 655, 655))
        );
        panelProductosPanel1Layout.setVerticalGroup(
            panelProductosPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductosPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(panelProductosPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(panelProductojLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelProductojLabel2))
                .addGap(172, 172, 172)
                .addComponent(panelProductojButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
                .addComponent(panelProductojButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelProductosPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelProductosPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    public void inicializar(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdministradorView().setVisible(true);
            }
        });
    }

}
