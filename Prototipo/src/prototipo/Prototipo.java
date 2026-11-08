package prototipo;

import static META_INF.Conector_JBDC.conectar;
import java.sql.Connection;
import prototipo.panel.Menu;
import prototipo.panel.Sesion;

public class Prototipo {

    public static void main(String[] args) {
        Connection con = conectar();

        javax.swing.SwingUtilities.invokeLater(() -> {

            Sesion pantalla = new Sesion();

            pantalla.setLocationRelativeTo(null);
            pantalla.setVisible(true);

        });
    }

}
