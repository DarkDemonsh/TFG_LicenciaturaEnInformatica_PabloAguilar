package prototipo;

import static META_INF.Conector_JBDC.conectar;
import java.sql.Connection;
import prototipo.panel.Pantalla_Ingreso_Datos;

public class Prototipo {

    public static void main(String[] args) {
       Connection con = conectar();
       
       Pantalla_Ingreso_Datos pp = new Pantalla_Ingreso_Datos();
       pp.setVisible(true);
       pp.setLocationRelativeTo(null);
    }
    
}
