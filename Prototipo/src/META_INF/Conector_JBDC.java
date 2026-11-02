package META_INF;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector_JBDC {
        
    static String url="jdbc:mysql://localhost:3306/siapgpf?useSSL=false";
    static String user="root";
    static String pass="root";
    
    public static Connection conectar(){
        Connection con = null;
        try{
            con=DriverManager.getConnection(url, user, pass);
            //System.out.println("Conectado");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return con;
    }
    
}
