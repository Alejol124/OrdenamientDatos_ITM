package pkg09.ordenamientodatos;

public class FrmDocumentos extends javax.swing.JFrame {

    public FrmDocumentos() {
        initComponents();
        String nombreArchivo = System.getProperty("user.dir") + "/src/Datos/Datos.csv";
        Documento.obtenerDatosDesdeArchivo(nombreArchivo);
        Documento.mostrarDatos(tblDocumentos);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btnOrdenarBurbuja = new javax.swing.JButton();
        btnOrdenarRapido = new javax.swing.JButton();
        btnOrdenarInserccion = new javax.swing.JButton();
        cmbCriterio = new javax.swing.JComboBox<>();
        txtTiempo = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDocumentos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        btnOrdenarBurbuja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Ordenar.png"))); // NOI18N
        btnOrdenarBurbuja.setToolTipText("Ordenar Burbuja");
        btnOrdenarBurbuja.setFocusable(false);
        btnOrdenarBurbuja.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOrdenarBurbuja.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnOrdenarBurbuja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarBurbujaActionPerformed(evt);
            }
        });
        jToolBar1.add(btnOrdenarBurbuja);

        btnOrdenarRapido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/OrdenarRapido.png"))); // NOI18N
        btnOrdenarRapido.setToolTipText("Ordenar Rapido");
        btnOrdenarRapido.setFocusable(false);
        btnOrdenarRapido.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOrdenarRapido.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnOrdenarRapido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarRapidoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnOrdenarRapido);

        btnOrdenarInserccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/OrdenarInsercion.png"))); // NOI18N
        btnOrdenarInserccion.setToolTipText("Ordenar Inserccion");
        btnOrdenarInserccion.setFocusable(false);
        btnOrdenarInserccion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOrdenarInserccion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnOrdenarInserccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarInserccionActionPerformed(evt);
            }
        });
        jToolBar1.add(btnOrdenarInserccion);

        cmbCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NombreCompleto, TipoDocumento", "TipoDocumento, NombreCompleto" }));
        jToolBar1.add(cmbCriterio);

        txtTiempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTiempoActionPerformed(evt);
            }
        });
        jToolBar1.add(txtTiempo);

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Buscar.png"))); // NOI18N
        btnBuscar.setFocusable(false);
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnBuscar);
        jToolBar1.add(txtBuscar);

        tblDocumentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblDocumentos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOrdenarBurbujaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarBurbujaActionPerformed
        if (cmbCriterio.getSelectedIndex() >= 0) {
            Util.iniciarCronometro();
            Documento.ordenarBurbujaRecursivo(0, cmbCriterio.getSelectedIndex());
            //  Documento.ordenarBurbuja(cmbCriterio.getSelectedIndex());
            txtTiempo.setText(Util.getTextoTiempoCronometro());
            Documento.mostrarDatos(tblDocumentos);
        }

    }//GEN-LAST:event_btnOrdenarBurbujaActionPerformed

    private void txtTiempoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTiempoActionPerformed

    }//GEN-LAST:event_txtTiempoActionPerformed

    private void btnOrdenarRapidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarRapidoActionPerformed
        if (cmbCriterio.getSelectedIndex() >= 0) {
            Util.iniciarCronometro();
            Documento.ordenarRapido(0, Documento.documentos.size() - 1, cmbCriterio.getSelectedIndex());
            txtTiempo.setText(Util.getTextoTiempoCronometro());
            Documento.mostrarDatos(tblDocumentos);
        }
    }//GEN-LAST:event_btnOrdenarRapidoActionPerformed

    private void btnOrdenarInserccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarInserccionActionPerformed
        if (cmbCriterio.getSelectedIndex() >= 0) {
            Util.iniciarCronometro();

            Documento.ordenarInsercionRecursivo(cmbCriterio.getSelectedIndex());
            txtTiempo.setText(Util.getTextoTiempoCronometro());
            Documento.mostrarDatos(tblDocumentos);
        }
    }//GEN-LAST:event_btnOrdenarInserccionActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        Util.iniciarCronometro();
        ArbolBinario ab = Documento.obtenerArbolBinario(cmbCriterio.getSelectedIndex());
        ab.setCriterio(cmbCriterio.getSelectedIndex());
        txtTiempo.setText(Util.getTextoTiempoCronometro());
        ab.mostrar(tblDocumentos);
        //Buscar lo que fue ingresado en el txtBuscar
        ab.buscarDatos(txtBuscar.getText(), ab.getRaiz(), tblDocumentos);

    }//GEN-LAST:event_btnBuscarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmDocumentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmDocumentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmDocumentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmDocumentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmDocumentos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnOrdenarBurbuja;
    private javax.swing.JButton btnOrdenarInserccion;
    private javax.swing.JButton btnOrdenarRapido;
    private javax.swing.JComboBox<String> cmbCriterio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblDocumentos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtTiempo;
    // End of variables declaration//GEN-END:variables
}
