/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Valentin Barros
 */
public class Conexion {
    private static final String url = "jdbc:maria://localHost/";
    private static final String db = "sistema de gestion";
    private static final String usuario = "root";
    private static final String pass = "";
    private static  Connection conexion;
    
    public static Connection getConexion(){
        if(conexion == null){
            try{
                Class.forName("org.mariadb.jdbc.Driver");
                conexion = DriverManager.getConnection(url+db, usuario, pass);
            }catch(ClassNotFoundException ex){
                JOptionPane.showMessageDialog(null, "Error al cargar los drivers ..."+ex.getMessage());
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Error de conexion ..."+ex.getMessage());
            }
        }
        return conexion;
    }
}
