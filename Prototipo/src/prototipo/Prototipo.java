package prototipo;

import static META_INF.Conector_JBDC.conectar;
import java.sql.Connection;
import prototipo.panel.p1;

public class Prototipo {

    public static void main(String[] args) {
       Connection con = conectar();
       
       p1 pp = new p1();
       pp.setVisible(true);
       pp.setLocationRelativeTo(null);
    }
    
}
