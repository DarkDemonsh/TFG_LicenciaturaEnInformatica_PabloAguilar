package prototipo.panel;

import META_INF.Conector_JBDC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.time.YearMonth;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import prototipo.Servicio.Analisis_Gastos;
import prototipo.Servicio.Controlador;
import prototipo.Servicio.Guardado;

public class Pantalla_Muestra extends javax.swing.JFrame {

    private Controlador controlador = new Controlador();
    private Analisis_Gastos analisisGastos;

    public Pantalla_Muestra() {
        initComponents();

        setLocationRelativeTo(null);

        jTextArea1.setEditable(false);
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);

        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = tabla.rowAtPoint(evt.getPoint());
                int columna = tabla.columnAtPoint(evt.getPoint());

                if (fila >= 0 && columna >= 2) {
                    try {

                        YearMonth periodo = controlador.obtenerUltimoPeriodo();
                        if (periodo != null) {
                            Informe ventanaInforme = new Informe();

                            ventanaInforme.generarYMostrarInforme(periodo);
                            ventanaInforme.setVisible(true);
                            ventanaInforme.setLocationRelativeTo(null);

                        } else {
                            JOptionPane.showMessageDialog(null, "No hay un período válido seleccionado.");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al abrir el informe: " + ex.getMessage());
                    }
                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        Salir = new javax.swing.JButton();
        Cargar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Ingreso", "Gasto", "Balance", "Ahorro", "Informe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });

        Cargar.setText("Cargar");
        Cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1106, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Salir, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                            .addComponent(Cargar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(21, 21, 21))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(Cargar)
                        .addGap(29, 29, 29)
                        .addComponent(Salir)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        dispose();
    }//GEN-LAST:event_SalirActionPerformed

    private void CargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargarActionPerformed
        try {

            StringBuilder informe = new StringBuilder();
            informe.append(controlador.obtenerResumenFinanciero());
            informe.append("\n\n");

            boolean entrenado = controlador.entrenarModelo();

            if (entrenado) {
                informe.append("===== MODELO SOM =====\n\n");
                informe.append(controlador.evaluarModelo());
                informe.append("\n");
                informe.append(controlador.obtenerPerfilesFinancieros());
                YearMonth periodo = controlador.obtenerUltimoPeriodo();

                if (periodo != null) {
                    informe.append("\n===== ANÁLISIS DEL ÚLTIMO MES =====\n\n");
                    informe.append(controlador.analizarMes(periodo));
                    informe.append("\n===== RECOMENDACIONES =====\n\n");

                    java.util.List<String> recomendaciones = controlador.obtenerRecomendaciones(periodo);

                    if (recomendaciones == null || recomendaciones.isEmpty()) {
                        informe.append("No hay recomendaciones disponibles.\n");
                    } else {
                        for (int i = 0; i < recomendaciones.size(); i++) {
                            informe.append(i + 1).append(". ").append(recomendaciones.get(i)).append("\n");
                        }
                    }
                }
            } else {
                informe.append("\nNo hay suficientes datos históricos para entrenar el modelo SOM.");
            }

            try {
                double ingresos = controlador.proyectarIngresos(3);
                double gastos = controlador.proyectarGastos(3);

                informe.append("\n\n===== PROYECCIÓN FINANCIERA =====\n\n");
                informe.append(String.format("Ingresos estimados: $%.2f%n", ingresos));
                informe.append(String.format("Gastos estimados: $%.2f%n", gastos));
                informe.append(String.format("Balance estimado: $%.2f%n", ingresos - gastos));

            } catch (IllegalArgumentException ex) {
                informe.append("\nNo fue posible realizar la proyección: " + ex.getMessage());
            }

            jTextArea1.setText(informe.toString());
            jTextArea1.setCaretPosition(0);

            double totalIngresos = controlador.obtenerTotalIngresos();
            double totalGastos = controlador.obtenerTotalGastos();
            double balance = totalIngresos - totalGastos;
            double porcentajeAhorro = (totalIngresos > 0) ? (balance / totalIngresos) * 100 : 0;

            if (analisisGastos == null) {
                analisisGastos = new Analisis_Gastos();
            }

            Guardado guardarRespaldo = new Guardado();
            guardarRespaldo.Guardado_Local_IA("Informe_Financiero", informe.toString());
            guardarRespaldo.SQLIA("Informe_Financiero", informe.toString());
            guardarRespaldo.SQLResumen(analisisGastos);

            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0);

            modelo.addRow(new Object[]{
                String.format("%.2f", totalIngresos),
                String.format("%.2f", totalGastos),
                String.format("%.2f", balance),
                String.format("%.2f%%", porcentajeAhorro),
                "Ver informe"
            });

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los resultados:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_CargarActionPerformed

    private void cargarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setNumRows(0);

        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;
        int columna = 0;

        try {

            Connection con = Conector_JBDC.conectar();
            ps = con.prepareStatement("SELECT Ingresos, Gastos, Balnce, Ahorro, Gasto_Promedio, Variacion_Mensual FROM analisis_de_gastos");
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            columna = rsmd.getColumnCount();

            while (rs.next()) {

                Object[] fila = new Object[columna];

                for (int i = 0; i < columna; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }

        } catch (Exception ex) {
            System.out.println("Error pm-001 " + ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cargar;
    private javax.swing.JButton Salir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
