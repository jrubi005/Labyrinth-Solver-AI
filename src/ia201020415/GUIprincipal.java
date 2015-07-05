/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia201020415;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author rubio
 */
public class GUIprincipal extends javax.swing.JFrame {

    ProcesamientoImagen ObjProcesamiento = new ProcesamientoImagen();
    public about ventanita = new about();

    /**
     * Creates new form GUIprincipal
     */
    public GUIprincipal() {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LoadImgBtn = new javax.swing.JButton();
        processImgBtn = new javax.swing.JButton();
        calcularRutaBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        labelImagen = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LoadImgBtn.setText("Cargar Imagen");
        LoadImgBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadImgBtnActionPerformed(evt);
            }
        });

        processImgBtn.setText("Procesar Imagen");
        processImgBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processImgBtnActionPerformed(evt);
            }
        });

        calcularRutaBtn.setText("Calcular Ruta");
        calcularRutaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularRutaBtnActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Imagen"));

        labelImagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelImagenMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
        );

        jMenu1.setText("Carga Predefinida");

        jMenuItem1.setText("Facil 1");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Intermedio 1");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Dificil 1");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LoadImgBtn)
                .addGap(52, 52, 52)
                .addComponent(processImgBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(calcularRutaBtn)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LoadImgBtn)
                    .addComponent(processImgBtn)
                    .addComponent(calcularRutaBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LoadImgBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadImgBtnActionPerformed
        // TODO add your handling code here:
        ImageIcon laberinto;
        this.labelImagen.setIcon(laberinto = new ImageIcon(ObjProcesamiento.CargarEscenario()));
        this.labelImagen.setSize(laberinto.getIconWidth(), laberinto.getIconHeight());
        this.setSize(this.labelImagen.getWidth() + 50, this.labelImagen.getHeight() + 120);

    }//GEN-LAST:event_LoadImgBtnActionPerformed

    private void processImgBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processImgBtnActionPerformed
        // TODO add your handling code here:
        this.labelImagen.setIcon(new ImageIcon(ObjProcesamiento.InterpretarEscenario()));
    }//GEN-LAST:event_processImgBtnActionPerformed

    private void calcularRutaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcularRutaBtnActionPerformed
        // TODO add your handling code here:
        ObjProcesamiento.inicializarAlgoritmoAstar();
        ObjProcesamiento.generarImagen();
        this.labelImagen.setIcon(new ImageIcon(ObjProcesamiento.generarImagen()));

    }//GEN-LAST:event_calcularRutaBtnActionPerformed

    public static void mensaje(String titleBar, String infoMessage) {
        JOptionPane.showMessageDialog(null, infoMessage, "Info: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    private void labelImagenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelImagenMouseClicked
        // TODO add your handling code here:
        int posx = evt.getX();
        int posy = evt.getY();

        if (evt.getButton() == 1) {
            ObjProcesamiento.setOrigen(posx, posy);
            mensaje("Seteo de punto origen", "Punto origen x:" + posx + " y:" + posy);
        } else if (evt.getButton() == 3) {
            ObjProcesamiento.setDestino(posx, posy);
            mensaje("Seteo de punto destino", "Punto destino x:" + posx + " y:" + posy);
        }
    }//GEN-LAST:event_labelImagenMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        ImageIcon laberinto;
        this.labelImagen.setIcon(laberinto = new ImageIcon(ObjProcesamiento.cargaPredefinida("C:\\Users\\rubio\\Pictures\\laberintos\\facil\\lab1.jpg")));
        this.labelImagen.setSize(laberinto.getIconWidth(), laberinto.getIconHeight());
        this.setSize(this.labelImagen.getWidth() + 50, this.labelImagen.getHeight() + 120);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        ImageIcon laberinto;
        this.labelImagen.setIcon(laberinto = new ImageIcon(ObjProcesamiento.cargaPredefinida("C:\\Users\\rubio\\Pictures\\laberintos\\intermedio\\ifacil2.jpg")));
        this.labelImagen.setSize(laberinto.getIconWidth(), laberinto.getIconHeight());
        this.setSize(this.labelImagen.getWidth() + 50, this.labelImagen.getHeight() + 120);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        ImageIcon laberinto;
        this.labelImagen.setIcon(laberinto = new ImageIcon(ObjProcesamiento.cargaPredefinida("C:\\Users\\rubio\\Pictures\\laberintos\\dificil\\prueba1.png")));
        this.labelImagen.setSize(laberinto.getIconWidth(), laberinto.getIconHeight());
        this.setSize(this.labelImagen.getWidth() + 50, this.labelImagen.getHeight() + 120);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIprincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LoadImgBtn;
    private javax.swing.JButton calcularRutaBtn;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelImagen;
    private javax.swing.JButton processImgBtn;
    // End of variables declaration//GEN-END:variables

}