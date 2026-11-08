package prototipo.panel;

import META_INF.Conector_JBDC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Pantalla_Historial extends javax.swing.JFrame {

    public Pantalla_Historial() {
        initComponents();
        cargarFechasEnLista();
    }

    private void cargarFechasEnLista() {
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        jList2.setModel(modeloLista);

        String sql = "SELECT DISTINCT AnioMes FROM (" + "  SELECT DATE_FORMAT(Fecha, '%Y-%m') AS AnioMes FROM ingresos " + "  UNION "
                + "  SELECT DATE_FORMAT(Fecha, '%Y-%m') AS AnioMes FROM registro_de_gastos " + "  UNION " + "  SELECT DATE_FORMAT(vencimiento, '%Y-%m') AS AnioMes FROM deuda"
                + ") AS meses_unificados " + "WHERE AnioMes IS NOT NULL " + "ORDER BY AnioMes DESC";

        try (Connection con = Conector_JBDC.conectar();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String anioMes = rs.getString("AnioMes");
                if (anioMes != null && !anioMes.isEmpty()) {
                    modeloLista.addElement(anioMes);
                }
            }
        } catch (Exception ex) {
            System.out.println("Error al cargar historial en lista: " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Buscar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        Salir = new javax.swing.JButton();

        jMenuItem1.setText("jMenuItem1");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Buscar.setText("Buscar");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });

        jList2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList2);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Ingreso", "Gastos", "Recomendacion", "Deuda"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Buscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(Buscar)
                        .addGap(18, 18, 18)
                        .addComponent(Salir)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        String mesSeleccionado = (String) jList2.getSelectedValue();

        if (mesSeleccionado == null || mesSeleccionado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un mes de la lista.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection con = Conector_JBDC.conectar()) {
            double totalIngresos = 0;
            double totalGastos = 0;
            double totalDeudas = 0;

            PreparedStatement psIng = con.prepareStatement("SELECT SUM(monto) FROM ingresos WHERE DATE_FORMAT(fecha, '%Y-%m') = ?");
            psIng.setString(1, mesSeleccionado);
            ResultSet rsIng = psIng.executeQuery();
            if (rsIng.next()) {
                totalIngresos = rsIng.getDouble(1);
            }

            PreparedStatement psGas = con.prepareStatement("SELECT SUM(monto) FROM registro_de_gastos WHERE DATE_FORMAT(Fecha, '%Y-%m') = ?");
            psGas.setString(1, mesSeleccionado);
            ResultSet rsGas = psGas.executeQuery();
            if (rsGas.next()) {
                totalGastos = rsGas.getDouble(1);
            }

            PreparedStatement psDeu = con.prepareStatement("SELECT SUM(monto_inicial) FROM deuda WHERE DATE_FORMAT(vencimiento, '%Y-%m') = ?");
            psDeu.setString(1, mesSeleccionado);
            ResultSet rsDeu = psDeu.executeQuery();
            if (rsDeu.next()) {
                totalDeudas = rsDeu.getDouble(1);
            }

            String recomendacion = (totalIngresos >= totalGastos)
                    ? "Buen balance general en este período mensual."
                    : "Atención: Los gastos mensuales superaron a los ingresos.";

            DefaultTableModel modeloTabla = (DefaultTableModel) jTable2.getModel();
            modeloTabla.setRowCount(0);
            modeloTabla.addRow(new Object[]{
                String.format("$%.2f", totalIngresos),
                String.format("$%.2f", totalGastos),
                recomendacion,
                String.format("$%.2f", totalDeudas)
            });

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al consultar los datos del historial mensual: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_BuscarActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        dispose();
    }//GEN-LAST:event_SalirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar;
    private javax.swing.JButton Salir;
    private javax.swing.JList jList2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
