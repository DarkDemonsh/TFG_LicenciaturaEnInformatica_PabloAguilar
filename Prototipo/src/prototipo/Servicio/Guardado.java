package prototipo.Servicio;

import META_INF.Conector_JBDC;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import org.json.JSONObject;
import prototipo.Entidad.Deuda;
import prototipo.Entidad.Gasto;
import prototipo.Entidad.Ingreso;

public class Guardado {
    
    Deuda deuda = new Deuda();
    Ingreso ingreso = new Ingreso();
    Gasto gasto = new Gasto();
    
    Connection conn = Conector_JBDC.conectar();
    
 
public void SQLIngreso(Ingreso i){
    try{
        
        String sql = ("INSERT INTO ingresos (ID, Fecha, monto,tipo,descripcion,periocidad) VALUES (?,?,?,?,?,?)");
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setInt(1, i.getID());
        ps.setDate(2, i.getFecha());
        ps.setDouble(3, i.getMonto());
        ps.setString(4, i.getTipo());
        ps.setString(5, i.getDescripcion());
        ps.setString(6, i.getPeriocidad());
        
        ps.executeUpdate();
        ps.close();
        
    }catch(Exception ex){
        System.out.println("Error s1-001 - SQL Ingreso "+ex);
    }
}
public void SQLGasto(Gasto g){
    try{
        
        String sql = ("INSERT INTO registro_de_gastos (ID, Fecha, monto,categoria,descripcion,medio_de_pago) VALUES (?,?,?,?,?,?)");
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setInt(1,g.getID());
        ps.setDate(2, g.getFecha());
        ps.setDouble(3, g.getMonto());
        ps.setString(4, g.getCategoria());
        ps.setString(5, g.getDescripcion());
        ps.setString(6, g.getMedio_Pago());
        
        ps.executeUpdate();
        ps.close();
        
    }catch(Exception ex){
        System.out.println("Error s1-002 - SQL Gasto "+ex);
    }
}
public void SQLDeuda(Deuda d){
    try{
        
        String sql = ("INSERT INTO deuda (ID, monto_inicial,cuota,vencimiento) VALUES (?,?,?,?)");
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setInt(1, d.getID());
        ps.setDouble(2, d.getMonto_inicial());
        ps.setDouble(3, d.getCouota());
        ps.setDate(4, d.getVencimiento());
        
        ps.executeUpdate();
        ps.close();
        
    }catch(Exception ex){
        System.out.println("Error s1-003 - SQL Deuda "+ex);
    }
}

public void Guardado_Local_Ingreso(Ingreso i){
            JSONObject j = new JSONObject();
            
            j.put("Ingreso_Id", i.getID());
            j.put("Ingreso_Fecha", i.getFecha());
            j.put("Ingreso_Monto", i.getMonto());
            j.put("Ingreso_Periocidad", i.getPeriocidad());
            j.put("Ingreso_Tipo", i.getTipo());
            j.put("Ingreso_Descripcion", i.getDescripcion());
            
        String carpeta = "Datos Crudos";
        File directorio = new File(carpeta);
        
        if (!directorio.exists()) {
            if (directorio.mkdirs()){
                directorio.getAbsolutePath();
            } else {
                return;
            }
        }
            
        try(FileWriter file = new FileWriter(carpeta+File.separator+LocalDate.now()+"Ingreso.json")){
               file.write(j.toString());
        }catch(IOException ex){
            System.out.println("Error en al guardar el JSON");
            System.out.println("Error s1-004 - Local Ingreso "+ex);
        }
    }
public void Guardado_Local_Gasto(Gasto g){
            JSONObject j = new JSONObject();
            
            j.put("Gasto_Id", g.getID());
            j.put("Gasto_Fecha", g.getFecha());
            j.put("Gasto_Monto", g.getMonto());
            j.put("Gasto_Medio_Pago", g.getMedio_Pago());
            j.put("Gasto_Categoria", g.getCategoria());
            j.put("Gasto_Descripcion", g.getDescripcion());
            
        String carpeta = "Datos Crudos";
        File directorio = new File(carpeta);
        
        if (!directorio.exists()) {
            if (directorio.mkdirs()){
                directorio.getAbsolutePath();
            } else {
                return;
            }
        }
            
        try(FileWriter file = new FileWriter(carpeta+File.separator+LocalDate.now()+"Gasto.json")){
               file.write(j.toString());
        }catch(IOException ex){
            System.out.println("Error en al guardar el JSON");
            System.out.println("Error s1-005 - Local Gasto "+ex);
        }
    }
public void Guardado_Local_Deuda(Deuda d){
            JSONObject j = new JSONObject();
            
            j.put("Deuda_Id", d.getID());
            j.put("Deuda_Monto_Inicial", d.getMonto_inicial());
            j.put("Deuda_Cuota", d.getCouota());
            j.put("Deuda_Vencimiento", d.getVencimiento());
            
        String carpeta = "Datos Crudos";
        File directorio = new File(carpeta);
        
        if (!directorio.exists()) {
            if (directorio.mkdirs()){
                directorio.getAbsolutePath();
            } else {
                return;
            }
        }
            
        try(FileWriter file = new FileWriter(carpeta+File.separator+LocalDate.now()+"Deuda.json")){
               file.write(j.toString());
        }catch(IOException ex){
            System.out.println("Error en al guardar el JSON");
            System.out.println("Error s1-006 - Local Deuda "+ex);
        }
    }
}
