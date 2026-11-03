package prototipo.panel;

import prototipo.Entidad.Deuda;
import prototipo.Entidad.Gasto;
import prototipo.Entidad.Ingreso;
import prototipo.Servicio.Guardado;

public class p1 extends javax.swing.JFrame {
    
    Deuda deuda = new Deuda();
    Ingreso ingreso = new Ingreso();
    Gasto gasto = new Gasto();
    Guardado save = new Guardado();
        
    String ingreso_monto,deuda_monto,gasto_monto,mp,des, tip, per,cat,cuot;
     
    public p1() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JTextArea();
        monto_ingreso = new javax.swing.JTextField();
        monto_gasto = new javax.swing.JTextField();
        continuar = new javax.swing.JButton();
        deuda_box = new javax.swing.JCheckBox();
        añadir_ingreso = new javax.swing.JButton();
        añadir_gasto = new javax.swing.JButton();
        monto_inicial = new javax.swing.JTextField();
        cuota = new javax.swing.JTextField();
        añadir_deuda = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        medio_pago = new javax.swing.JComboBox();
        categoria = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tipo = new javax.swing.JComboBox();
        Periocidad = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Fecha_Ingreso = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        fecha_gasto = new com.toedter.calendar.JDateChooser();
        fecha_deuda = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        descripcion.setColumns(20);
        descripcion.setRows(5);
        jScrollPane1.setViewportView(descripcion);

        monto_ingreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monto_ingresoActionPerformed(evt);
            }
        });

        monto_gasto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monto_gastoActionPerformed(evt);
            }
        });

        continuar.setText("Continuar");
        continuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continuarActionPerformed(evt);
            }
        });

        deuda_box.setText("Deudas");
        deuda_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deuda_boxActionPerformed(evt);
            }
        });

        añadir_ingreso.setText("Añadir");
        añadir_ingreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                añadir_ingresoActionPerformed(evt);
            }
        });

        añadir_gasto.setText("Añadir");
        añadir_gasto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                añadir_gastoActionPerformed(evt);
            }
        });

        monto_inicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monto_inicialActionPerformed(evt);
            }
        });

        cuota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuotaActionPerformed(evt);
            }
        });

        añadir_deuda.setText("Añadir");
        añadir_deuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                añadir_deudaActionPerformed(evt);
            }
        });

        jLabel1.setText("Ingreso");

        jLabel2.setText("Gasto");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        medio_pago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Transferencia", "Tarjeta de Credito", "Tarjeta de Debito", "Dinero Fisico" }));
        medio_pago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medio_pagoActionPerformed(evt);
            }
        });

        categoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Supermercado", "Entretenimiento", "Salud", "Hogar", "Transporte", "Indumentaria", "Retiro de Efectivo", "Educacion", " ", " " }));
        categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaActionPerformed(evt);
            }
        });

        jLabel3.setText("Medio de pago");

        jLabel4.setText("Categoria");

        jLabel5.setText("Descripcion");

        tipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ingreso Fijo", "Ingreso Variable" }));
        tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoActionPerformed(evt);
            }
        });

        Periocidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diaria", "Semanal", "Mensual" }));
        Periocidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PeriocidadActionPerformed(evt);
            }
        });

        jLabel6.setText("Tipo");

        jLabel7.setText("Periodicidad");

        jLabel13.setText("Fecha");

        jLabel8.setText("Monto Inicial");

        jLabel9.setText("Cantidad de Cuotas Restantes");

        jLabel10.setText("Vencimiento");

        jLabel11.setText("Fecha");

        jLabel12.setText("Monto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addComponent(deuda_box))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(añadir_ingreso)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(Periocidad, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel7))
                                                .addGap(4, 4, 4))
                                            .addComponent(monto_ingreso, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(Fecha_Ingreso, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel6))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(añadir_deuda))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(monto_inicial, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                        .addComponent(cuota)
                                        .addComponent(fecha_deuda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(continuar)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                        .addComponent(monto_gasto, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(71, 71, 71)
                                            .addComponent(jLabel2))
                                        .addComponent(categoria, 0, 166, Short.MAX_VALUE)
                                        .addComponent(medio_pago, 0, 166, Short.MAX_VALUE)
                                        .addComponent(fecha_gasto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(añadir_gasto))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(monto_gasto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addGap(2, 2, 2)
                        .addComponent(fecha_gasto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel3)
                        .addGap(4, 4, 4)
                        .addComponent(medio_pago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(añadir_gasto)
                        .addGap(63, 63, 63)
                        .addComponent(continuar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(9, 9, 9)
                        .addComponent(monto_ingreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Fecha_Ingreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Periocidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(añadir_ingreso)
                        .addGap(18, 18, 18)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deuda_box)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(monto_inicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha_deuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(añadir_deuda)))
                .addContainerGap())
            .addComponent(jSeparator1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void añadir_ingresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añadir_ingresoActionPerformed
        try{
 //Ingreso
            /*monto*/
            ingreso_monto = monto_ingreso.getText();
            long mi = Long.parseLong(ingreso_monto);
            ingreso.setMonto(mi);
            /*tipo*/
            tip = tipo.getSelectedItem().toString();
            ingreso.setTipo(tip);
            /*periocidad*/
            per = Periocidad.getSelectedItem().toString();
            ingreso.setPeriocidad(per); 
            
            save.SQLIngreso(ingreso);   
            save.Guardado_Local_Ingreso(ingreso);
            
        }catch(Exception ex){
            System.out.println("Error p1-001 - Añadir_Ingreso "+ex);
        }
    }//GEN-LAST:event_añadir_ingresoActionPerformed

    private void monto_ingresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monto_ingresoActionPerformed
   
    }//GEN-LAST:event_monto_ingresoActionPerformed

    private void tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoActionPerformed

    }//GEN-LAST:event_tipoActionPerformed

    private void PeriocidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PeriocidadActionPerformed

    }//GEN-LAST:event_PeriocidadActionPerformed

    private void monto_inicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monto_inicialActionPerformed

    }//GEN-LAST:event_monto_inicialActionPerformed

    private void cuotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuotaActionPerformed

    }//GEN-LAST:event_cuotaActionPerformed

    private void monto_gastoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monto_gastoActionPerformed

    }//GEN-LAST:event_monto_gastoActionPerformed

    private void medio_pagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medio_pagoActionPerformed

    }//GEN-LAST:event_medio_pagoActionPerformed

    private void categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaActionPerformed

    }//GEN-LAST:event_categoriaActionPerformed

    private void añadir_deudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añadir_deudaActionPerformed
        try{
//Deuda
            /*monto*/
            deuda_monto = monto_inicial.getText();
            long dm = Long.parseLong(deuda_monto);
            deuda.setMonto_inicial(dm);
            /*cuota*/
            cuot = cuota.getText();
            int cuotas = Integer.parseInt(cuot);
            deuda.setCouota(cuotas);
            
            if(deuda_box.getVerifyInputWhenFocusTarget()){
                save.SQLDeuda(deuda);
                save.Guardado_Local_Deuda(deuda);     
            }
            
        }catch(Exception ex){
            System.out.println("Error p1-002 - Añadir Deuda "+ex);
        }
    }//GEN-LAST:event_añadir_deudaActionPerformed

    private void añadir_gastoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añadir_gastoActionPerformed
        try{
//Gasto
            /*monto*/
            gasto_monto = monto_gasto.getText();
            long mg = Long.parseLong(gasto_monto);
            gasto.setMonto(mg);
            /*medio de pago*/
            mp = medio_pago.getSelectedItem().toString();
            gasto.setMedio_Pago(mp);
            /*categoria*/
            cat = categoria.getSelectedItem().toString();
            gasto.setCategoria(cat);
            /*descripcion*/
            des = descripcion.getText();
            gasto.setDescripcion(des); 
            
            save.SQLGasto(gasto);           
            save.Guardado_Local_Gasto(gasto);
            
        }catch(Exception ex){
            System.out.println("Error p1-003 - Añadir Gasto "+ex);
        }
    }//GEN-LAST:event_añadir_gastoActionPerformed

    private void continuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continuarActionPerformed
        try{                         
            
        }catch(Exception ex){
            System.out.println("Error p1-004 - Continuar "+ex);
        }
    }//GEN-LAST:event_continuarActionPerformed

    private void deuda_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deuda_boxActionPerformed

    }//GEN-LAST:event_deuda_boxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Fecha_Ingreso;
    private javax.swing.JComboBox Periocidad;
    private javax.swing.JButton añadir_deuda;
    private javax.swing.JButton añadir_gasto;
    private javax.swing.JButton añadir_ingreso;
    private javax.swing.JComboBox categoria;
    private javax.swing.JButton continuar;
    private javax.swing.JTextField cuota;
    private javax.swing.JTextArea descripcion;
    private javax.swing.JCheckBox deuda_box;
    private com.toedter.calendar.JDateChooser fecha_deuda;
    private com.toedter.calendar.JDateChooser fecha_gasto;
    private javax.swing.Box.Filler filler1;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox medio_pago;
    private javax.swing.JTextField monto_gasto;
    private javax.swing.JTextField monto_ingreso;
    private javax.swing.JTextField monto_inicial;
    private javax.swing.JComboBox tipo;
    // End of variables declaration//GEN-END:variables
}
