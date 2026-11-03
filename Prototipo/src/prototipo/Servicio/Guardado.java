package prototipo.Servicio;

import META_INF.Conector_JBDC;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
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
    
 
public void SQLIngreso(Ingreso i){
    try{
        
        String sql = ("INSERT INTO ingresos (Fecha, monto,tipo,descripcion,periocidad) VALUES (?,?,?,?,?)");
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setDate(1, i.getFecha());
        ps.setDouble(2, i.getMonto());
        ps.setString(3, i.getTipo());
        ps.setString(4, i.getDescripcion());
        ps.setString(5, i.getPeriocidad());
        
        ps.executeUpdate();
        ps.close();
        
    }catch(Exception ex){
        System.out.println("Error s1-001 - SQL Ingreso "+ex);
    }
}
public void SQLGasto(Gasto g){
    try{
        
        String sql = ("INSERT INTO registro_de_gastos (Fecha, monto,categoria,descripcion,medio_de_pago) VALUES (?,?,?,?,?)");
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setDate(1, g.getFecha());
        ps.setDouble(2, g.getMonto());
        ps.setString(3, g.getCategoria());
        ps.setString(4, g.getDescripcion());
        ps.setString(5, g.getMedio_Pago());
        
        ps.executeUpdate();
        ps.close();
        
    }catch(Exception ex){
        System.out.println("Error s1-002 - SQL Gasto "+ex);
    }
}
public void SQLDeuda(Deuda d){
    try{
        
        String sql = ("INSERT INTO deuda (monto_inicial,cuota,vencimiento) VALUES (?,?,?)");
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setDouble(1, d.getMonto_inicial());
        ps.setInt(2, d.getCouota());
        ps.setDate(3, d.getVencimiento());
        
        ps.executeUpdate();
        ps.close();
        
    }catch(Exception ex){
        System.out.println("Error s1-003 - SQL Deuda "+ex);
    }
}

public void Guardado_Local_Ingreso(Ingreso i){
        String carpeta = "Datos Crudos";
        String ruta = carpeta+File.separator+LocalDate.now()+"_Ingreso.json";
        
        File directorio = new File(carpeta);
        File archivo = new File(ruta);
        
        if (!directorio.exists()) {
            if (directorio.mkdirs()){
                directorio.getAbsolutePath();
            } else {
                return;
            }
        }
                
        JSONArray ja = new JSONArray();       
        
  if(archivo.length() > 0 && archivo.exists()){
        try(FileReader reader = new FileReader(archivo)){
               org.json.JSONTokener t = new org.json.JSONTokener(reader);
               ja = new JSONArray(t);
        }catch(IOException ex){
            System.out.println("Error al leer "+ex);
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
            
            try (FileWriter file = new FileWriter(archivo)){
               file.write(ja.toString(4));
               file.flush();
        }catch(IOException ex){
            System.out.println("Error en al guardar el JSON");
            System.out.println("Error s1-004 - Local Ingreso "+ex);
        }
    }
public void Guardado_Local_Gasto(Gasto g){
        String carpeta = "Datos Crudos";
        String ruta = carpeta+File.separator+LocalDate.now()+"_Gasto.json";
        
        File directorio = new File(carpeta);
        File archivo = new File(ruta);
        
        if (!directorio.exists()) {
            if (directorio.mkdirs()){
                directorio.getAbsolutePath();
            } else {
                return;
            }
        }
                
        JSONArray ja = new JSONArray();       
        
  if(archivo.length() > 0 && archivo.exists()){
        try(FileReader reader = new FileReader(archivo)){
               org.json.JSONTokener t = new org.json.JSONTokener(reader);
               ja = new JSONArray(t);
        }catch(IOException ex){
            System.out.println("Error al leer "+ex);
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
            
            try (FileWriter file = new FileWriter(archivo)){
               file.write(ja.toString(4));
               file.flush();
        }catch(IOException ex){
            System.out.println("Error en al guardar el JSON");
            System.out.println("Error s1-004 - Local Gasto "+ex);
        }
    }
public void Guardado_Local_Deuda(Deuda d){
        String carpeta = "Datos Crudos";
        String ruta = carpeta+File.separator+LocalDate.now()+"_Deuda.json";
        
        File directorio = new File(carpeta);
        File archivo = new File(ruta);
        
        if (!directorio.exists()) {
            if (directorio.mkdirs()){
                directorio.getAbsolutePath();
            } else {
                return;
            }
        }
                
        JSONArray ja = new JSONArray();       
        
  if(archivo.length() > 0 && archivo.exists()){
        try(FileReader reader = new FileReader(archivo)){
               org.json.JSONTokener t = new org.json.JSONTokener(reader);
               ja = new JSONArray(t);
        }catch(IOException ex){
            System.out.println("Error al leer "+ex);
        }
  }
        
        JSONObject j = new JSONObject();
            
            j.put("Deuda_Id", d.getID());
            j.put("Deuda_Monto_Inicial", d.getMonto_inicial());
            j.put("Deuda_Cuota", d.getCouota());
            j.put("Deuda_Vencimiento", d.getVencimiento());
        
            ja.put(j);
            
            try (FileWriter file = new FileWriter(archivo)){
               file.write(ja.toString(4));
               file.flush();
        }catch(IOException ex){
            System.out.println("Error en al guardar el JSON");
            System.out.println("Error s1-004 - Local Deuda "+ex);
        }
}

public void SQLResumen(Analisis_Gastos ag){
    try{
        
        String sql = ("INSERT INTO analisis_de_gastos (Ingresos,Gastos,Balance,Ahorro,Gasto_Promedio,Variacion_Mensual) VALUES (?,?,?,?,?,?)");
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setDouble(1, ag.calcularTotalIngresos());
        ps.setDouble(2, ag.calcularTotalGastos());
        ps.setDouble(3, ag.calcularBalance());
        ps.setDouble(4, ag.calcularPorcentajeAhorro());
        ps.setDouble(5, ag.calcularGastoPromedio());
        ps.setDouble(6, ag.calcularVariacionMensual());
        
        ps.executeUpdate();
        ps.close();
        
    }catch(Exception ex){
        System.out.println("Error s1-003 - SQL Deuda "+ex);
    }
}

}
