package prototipo.Servicio;

import META_INF.Conector_JBDC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import prototipo.Entidad.Deuda;
import prototipo.Entidad.Gasto;
import prototipo.Entidad.Ingreso;

public class Obtener_Listas {

    Deuda deuda = new Deuda();
    Ingreso ingreso = new Ingreso();
    Gasto gasto = new Gasto();

    Connection conn = Conector_JBDC.conectar();

    public List<Ingreso> obtenerIngresos() {

        List<Ingreso> ingresos = new ArrayList<>();

        String sql = "SELECT * FROM ingresos WHERE TagSesion = ?";
        try (Connection conn = Conector_JBDC.conectar()) {
            if (conn == null) {
                return ingresos;
            }
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, SesionActual.getTagSesion());
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Ingreso ingreso = new Ingreso();
                        ingreso.setID(rs.getInt("ID"));
                        ingreso.setFecha(rs.getDate("Fecha"));
                        ingreso.setMonto(rs.getDouble("monto"));
                        ingreso.setTipo(rs.getString("tipo"));
                        ingreso.setDescripcion(rs.getString("descripcion"));
                        ingreso.setPeriocidad(rs.getString("periocidad"));
                        ingresos.add(ingreso);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Error al obtener ingresos: " + ex.getMessage());
        }

        return ingresos;
    }

    public List<Gasto> obtenerGastos() {

        List<Gasto> gastos = new ArrayList<>();

        String sql = "SELECT * FROM registro_de_gastos WHERE TagSesion = ?";
        try (Connection conn = Conector_JBDC.conectar()) {
            if (conn == null) {
                return gastos;
            }
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, SesionActual.getTagSesion());
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Gasto gasto = new Gasto();
                        gasto.setID(rs.getInt("ID"));
                        gasto.setFecha(rs.getDate("Fecha"));
                        gasto.setMonto(rs.getDouble("monto"));
                        gasto.setCategoria(rs.getString("categoria"));
                        gasto.setDescripcion(rs.getString("descripcion"));
                        gasto.setMedio_Pago(rs.getString("medio_de_pago"));
                        gastos.add(gasto);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Error al obtener gastos: " + ex.getMessage());
        }

        return gastos;
    }

    public List<Deuda> obtenerDeudas() {

        List<Deuda> deudas = new ArrayList<>();

        String sql = "SELECT * FROM deuda WHERE TagSesion = ?";

        try (Connection conn = Conector_JBDC.conectar()) {

            if (conn == null) {
                return deudas;
            }

            try (PreparedStatement ps = conn.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Deuda deuda = new Deuda();

                    deuda.setID(rs.getInt("ID"));
                    deuda.setMonto_inicial(rs.getDouble("monto_inicial"));
                    deuda.setCouota(rs.getInt("cuota"));
                    deuda.setVencimiento(rs.getDate("vencimiento"));

                    deudas.add(deuda);
                }
            }

        } catch (Exception ex) {
            System.out.println("Error al obtener deudas: "
                    + ex.getMessage());
        }

        return deudas;
    }

}
