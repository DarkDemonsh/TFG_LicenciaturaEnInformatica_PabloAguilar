package prototipo.Servicio;

import META_INF.Conector_JBDC;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONObject;
import prototipo.Entidad.Deuda;
import prototipo.Entidad.Gasto;
import prototipo.Entidad.Ingreso;

public class Guardado {

    Deuda deuda = new Deuda();
    Ingreso ingreso = new Ingreso();
    Gasto gasto = new Gasto();

    Connection conn = Conector_JBDC.conectar();

    public void SQLIngreso(Ingreso i) {
        try {

            String sql = "INSERT INTO ingresos (Fecha, monto, tipo, descripcion, periocidad, TagSesion) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDate(1, i.getFecha());
            ps.setDouble(2, i.getMonto());
            ps.setString(3, i.getTipo());
            ps.setString(4, i.getDescripcion());
            ps.setString(5, i.getPeriocidad());
            ps.setInt(6, SesionActual.getTagSesion());

            ps.executeUpdate();
            ps.close();

        } catch (Exception ex) {
            System.out.println("Error s1-001 - SQL Ingreso " + ex);
        }
    }

    public void SQLGasto(Gasto g) {
        try {

            String sql = "INSERT INTO registro_de_gastos (Fecha, monto, categoria, descripcion, medio_de_pago, TagSesion) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDate(1, g.getFecha());
            ps.setDouble(2, g.getMonto());
            ps.setString(3, g.getCategoria());
            ps.setString(4, g.getDescripcion());
            ps.setString(5, g.getMedio_Pago());
            ps.setInt(6, SesionActual.getTagSesion());

            ps.executeUpdate();
            ps.close();

        } catch (Exception ex) {
            System.out.println("Error s1-002 - SQL Gasto " + ex);
        }
    }

    public void SQLDeuda(Deuda d) {
        try {

            String sql = "INSERT INTO deuda (monto_inicial, cuota, vencimiento, TagSesion) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDouble(1, d.getMonto_inicial());
            ps.setInt(2, d.getCouota());
            ps.setDate(3, d.getVencimiento());
            ps.setInt(4, SesionActual.getTagSesion());

            ps.executeUpdate();
            ps.close();

        } catch (Exception ex) {
            System.out.println("Error s1-003 - SQL Deuda " + ex);
        }
    }

    public void Guardado_Local_Ingreso(Ingreso i) {
        String carpeta = "Datos Crudos";
        String ruta = carpeta + File.separator + LocalDate.now() + "_Ingreso.json";

        File directorio = new File(carpeta);
        File archivo = new File(ruta);

        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                directorio.getAbsolutePath();
            } else {
                return;
            }
        }

        JSONArray ja = new JSONArray();

        if (archivo.length() > 0 && archivo.exists()) {
            try {
                String contenidoEncriptado = new String(Files.readAllBytes(archivo.toPath()));
                String contenidoPlano = Cifrado.desencriptar(contenidoEncriptado);
                if (contenidoPlano != null && !contenidoPlano.trim().isEmpty()) {
                    ja = new JSONArray(contenidoPlano);
                }
            } catch (Exception ex) {
                System.out.println("Error al leer o desencriptar " + ex);
            }
        }

        JSONObject j = new JSONObject();

        j.put("Ingreso_Id", i.getID());
        j.put("Ingreso_Fecha", i.getFecha());
        j.put("Ingreso_Monto", i.getMonto());
        j.put("Ingreso_Periocidad", i.getPeriocidad());
        j.put("Ingreso_Tipo", i.getTipo());
        j.put("Ingreso_Descripcion", i.getDescripcion());

        ja.put(j);

        String jsonTextoPlano = ja.toString(4);
        String jsonEncriptado = Cifrado.encriptar(jsonTextoPlano);

        try (FileWriter file = new FileWriter(archivo)) {
            file.write(jsonEncriptado);
            file.flush();
        } catch (IOException ex) {
            System.out.println("Error en al guardar el JSON");
            System.out.println("Error s1-004 - Local Ingreso " + ex);
        }
    }

    public void Guardado_Local_Gasto(Gasto g) {
        String carpeta = "Datos Crudos";
        String ruta = carpeta + File.separator + LocalDate.now() + "_Gasto.json";

        File directorio = new File(carpeta);
        File archivo = new File(ruta);

        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                directorio.getAbsolutePath();
            } else {
                return;
            }
        }

        JSONArray ja = new JSONArray();

        if (archivo.length() > 0 && archivo.exists()) {
            try {
                String contenidoEncriptado = new String(Files.readAllBytes(archivo.toPath()));
                String contenidoPlano = Cifrado.desencriptar(contenidoEncriptado);
                if (contenidoPlano != null && !contenidoPlano.trim().isEmpty()) {
                    ja = new JSONArray(contenidoPlano);
                }
            } catch (Exception ex) {
                System.out.println("Error al leer o desencriptar " + ex);
            }
        }

        JSONObject j = new JSONObject();

        j.put("Gasto_Id", g.getID());
        j.put("Gasto_Fecha", g.getFecha());
        j.put("Gasto_Monto", g.getMonto());
        j.put("Gasto_Medio_Pago", g.getMedio_Pago());
        j.put("Gasto_Categoria", g.getCategoria());
        j.put("Gasto_Descripcion", g.getDescripcion());

        ja.put(j);

        String jsonTextoPlano = ja.toString(4);
        String jsonEncriptado = Cifrado.encriptar(jsonTextoPlano);

        try (FileWriter file = new FileWriter(archivo)) {
            file.write(jsonEncriptado);
            file.flush();
        } catch (IOException ex) {
            System.out.println("Error en al guardar el JSON");
            System.out.println("Error s1-005 - Local Gasto " + ex);
        }
    }

    public void Guardado_Local_Deuda(Deuda d) {
        String carpeta = "Datos Crudos";
        String ruta = carpeta + File.separator + LocalDate.now() + "_Deuda.json";

        File directorio = new File(carpeta);
        File archivo = new File(ruta);

        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                directorio.getAbsolutePath();
            } else {
                return;
            }
        }

        JSONArray ja = new JSONArray();

        if (archivo.length() > 0 && archivo.exists()) {
            try {
                String contenidoEncriptado = new String(Files.readAllBytes(archivo.toPath()));
                String contenidoPlano = Cifrado.desencriptar(contenidoEncriptado);
                if (contenidoPlano != null && !contenidoPlano.trim().isEmpty()) {
                    ja = new JSONArray(contenidoPlano);
                }
            } catch (Exception ex) {
                System.out.println("Error al leer o desencriptar " + ex);
            }
        }

        JSONObject j = new JSONObject();

        j.put("Deuda_Id", d.getID());
        j.put("Deuda_Monto_Inicial", d.getMonto_inicial());
        j.put("Deuda_Cuota", d.getCouota());
        j.put("Deuda_Vencimiento", d.getVencimiento());

        ja.put(j);

        String jsonTextoPlano = ja.toString(4);
        String jsonEncriptado = Cifrado.encriptar(jsonTextoPlano);

        try (FileWriter file = new FileWriter(archivo)) {
            file.write(jsonEncriptado);
            file.flush();
        } catch (IOException ex) {
            System.out.println("Error en al guardar el JSON");
            System.out.println("Error s1-006 - Local Deuda " + ex);
        }
    }

    public void SQLResumen(Analisis_Gastos ag) {
        if (ag == null) {
            System.out.println("Error s1-007: El objeto Analisis_Gastos es nulo.");
            return;
        }

        String sql = "INSERT INTO analisis_de_gastos (Ingresos, Gastos, Balance, Ahorro, Gasto_Promedio, Variacion_Mensual, TagSesion) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conector_JBDC.conectar()) {
            if (conn == null) {
                System.out.println("Error s1-007: No se pudo conectar a la base de datos.");
                return;
            }

            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setDouble(1, ag.calcularTotalIngresos());
                ps.setDouble(2, ag.calcularTotalGastos());
                ps.setDouble(3, ag.calcularBalance());
                ps.setDouble(4, ag.calcularPorcentajeAhorro());
                ps.setDouble(5, ag.calcularGastoPromedio());
                ps.setDouble(6, ag.calcularVariacionMensual());
                ps.setInt(7, SesionActual.getTagSesion());

                ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Error s1-007 - SQL Resumen: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void Guardado_Local_IA(String tipoAnalisis, String contenidoResultado) {
        String carpeta = "Datos_IA_Respaldo";
        String ruta = carpeta + File.separator + LocalDate.now() + "_" + tipoAnalisis + ".json";

        File directorio = new File(carpeta);
        File archivo = new File(ruta);

        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        JSONObject j = new JSONObject();
        j.put("Fecha_Generacion", LocalDate.now().toString());
        j.put("Tipo", tipoAnalisis);
        j.put("Resultado", contenidoResultado);

        String jsonTextoPlano = j.toString(4);
        String jsonEncriptado = Cifrado.encriptar(jsonTextoPlano);

        try (FileWriter file = new FileWriter(archivo)) {
            file.write(jsonEncriptado);
            file.flush();
        } catch (IOException ex) {
            System.out.println("Error s1-008 - Local IA " + ex);
        }
    }

    public void SQLIA(String tipoAnalisis, String contenidoResultado) {
        try {

            String sql = ("INSERT INTO resultados_ia (Fecha_Generacion, Tipo,Resultado, TagSesion) VALUES (?,?,?,?)");
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setString(2, tipoAnalisis);
            ps.setString(3, contenidoResultado);
            ps.setInt(4, SesionActual.getTagSesion());

            ps.executeUpdate();
            ps.close();

        } catch (Exception ex) {
            System.out.println("Error s1-009 - SQL Deuda " + ex);
        }
    }
}
