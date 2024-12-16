/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.views;

import com.mycompany.interfaces.DAOCliente;
import com.mycompany.models.Cliente;
import com.mycompany.models.DAOClienteImplements;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 52951
 */
public class FormOwner extends javax.swing.JFrame {

    /**
     * Creates new form FormOwner
     */
    com.mycompany.models.Cliente clienteEditable;
    public FormOwner() {
        initComponents();
        lblEmpty.setVisible(false);
    }
    
     public FormOwner(com.mycompany.models.Cliente cliente) {
        initComponents();
        lblEmpty.setVisible(false);
        lblSubtitle.setText("Editar Usuario");
        txtOwner.setText(cliente.getNombre());
        txtEmail.setText(cliente.getCorreo());
        clienteEditable = cliente;
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblSubtitle = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtOwner = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblEmpty = new javax.swing.JLabel();
        btnAccept = new javax.swing.JButton();
        btnCancel = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Veterinaria");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 53, 284, 54));

        lblSubtitle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblSubtitle.setText("Ingresar Cliente");
        jPanel1.add(lblSubtitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 113, -1, -1));

        jLabel2.setText("Nombre del Cliente");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 160, -1, -1));

        txtOwner.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtOwnerKeyTyped(evt);
            }
        });
        jPanel1.add(txtOwner, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 182, 260, 32));

        lblEmail.setText("Correo");
        jPanel1.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 232, -1, -1));

        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmailKeyTyped(evt);
            }
        });
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 254, 260, 32));

        lblEmpty.setForeground(new java.awt.Color(204, 0, 0));
        lblEmpty.setText("SE DEBEN RELLENAR TODOS LOS CAMPOS");
        jPanel1.add(lblEmpty, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 292, 241, -1));

        btnAccept.setText("Aceptar");
        btnAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcceptActionPerformed(evt);
            }
        });
        jPanel1.add(btnAccept, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 314, 284, 39));

        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 359, 284, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtOwnerKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOwnerKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            evt.consume();  // Ignorar la entrada de caracteres no deseados
        }
    }//GEN-LAST:event_txtOwnerKeyTyped

    private void btnAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcceptActionPerformed
        // TODO add your handling code here:
        
        if(txtOwner.getText().isEmpty() ||
            txtEmail.getText().isEmpty()){
            lblEmpty.setVisible(true);
        }
        else{
            if(clienteEditable == null){
            DAOCliente dao = new DAOClienteImplements();
            List <Cliente> list;
            try {
                list = dao.listar("", txtEmail.getText(), 0, 0);
                if(!list.isEmpty()){
                    javax.swing.JOptionPane.showMessageDialog(this, """
                                                                        No se puede agregar el usuario
                                                                        Su correo ya se encuentra en uso""", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    return;
                }else{
                    dao.registrar(new Cliente(txtOwner.getText(),0, txtEmail.getText()));
                }
            } catch (Exception ex) {
                Logger.getLogger(FormOwner.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.setVisible(false);
        }else{
                Cliente c = new Cliente (txtOwner.getText(),clienteEditable.getId_usuario(), txtEmail.getText());
                 DAOCliente dao = new DAOClienteImplements();
                try {
                    dao.modificar(c);
                    this.setVisible(false);
                } catch (Exception ex) {
                    Logger.getLogger(FormOwner.class.getName()).log(Level.SEVERE, null, ex);
                }
            }}
        
        
    }//GEN-LAST:event_btnAcceptActionPerformed

    private void txtEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtEmailKeyTyped

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

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
            java.util.logging.Logger.getLogger(FormOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormOwner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormOwner().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccept;
    private javax.swing.JToggleButton btnCancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmpty;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtOwner;
    // End of variables declaration//GEN-END:variables
}