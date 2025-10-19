/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import gestion.de.alumnos.ulp.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Statement;

/**
 *
 * @author Valentin Barros
 */
public class MateriaData {
    private Connection conexion;
    
    public MateriaData(){
        conexion = Conexion.getConexion();
    }
    
    public void guardarMateria(Materia materia){
        String sql = "INSERT INTO materia(nombre, año, estado) VALUES (?,?,?)";
        try{
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAño());
            ps.setBoolean(3, materia.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                materia.setIdMateria(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Materia guardado exitosamente ...");
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia ..."+ex.getMessage());
        }
    }
    
    public void modificarMateria(Materia materia){
        try{
            String sql = "UPDATE materia SET nombre=?, año=?, estado=?  WHERE idMateria=?";
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAño());
            ps.setBoolean(3, materia.isEstado());
            ps.setInt(4, materia.getIdMateria());
            int exito = ps.executeUpdate();
            if(exito == 1){
                JOptionPane.showMessageDialog(null, "Materia modificado ...");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia ..."+ex.getMessage());
        }
    }
    
    public void eliminarMateria(int idMateria){
        String sql = "UPDATE materia SET estado = 0 WHERE idMateria=?";
        PreparedStatement ps;
        try{
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idMateria);
            int exito = ps.executeUpdate();
            if(exito == 1){
                JOptionPane.showMessageDialog(null, "Materia eliminada ...");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia ..."+ex.getMessage());
        }
    }
    
    public void altaMateria(int idMateria){
        String sql = "UPDATE materia SET estado = 1 WHERE idMateria =?";
        PreparedStatement ps;
        try{
           ps = conexion.prepareStatement(sql);
           ps.setInt(1, idMateria);
           int exito  = ps.executeUpdate();
           if(exito==1){
              JOptionPane.showMessageDialog(null, "Materia dada de alta ..."); 
           }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia ..."+ex.getMessage());
        }
    }
    
    public ArrayList<Materia> listarMateria(){
        ArrayList <Materia> listaM = new ArrayList();
        String sql = "SELECT * FROM materia ";
        try{
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
              int idMateria = rs.getInt("idMateria");
              String nombre = rs.getString("nombre");
              int año = rs.getInt("año");
              boolean estado = rs.getBoolean("estado");
              Materia m = new Materia(idMateria, nombre, año, estado);
              listaM.add(m);
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia ..."+ex.getMessage());
        }
        return listaM;
    }
    
    public Materia buscarPorId(int idMateria){
        String sql = "SELECT * FROM materia WHERE idMateria =?";
        Materia m = new Materia();
        try{
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                m.setNombre(rs.getString("nombre"));
                m.setAño(rs.getInt("año"));
                m.setEstado(rs.getBoolean("estado"));
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia..."+ex.getMessage());
        }
        return m;
    }
    
    public Materia buscarPorNombre(String nombre){
        String sql = "SELECT * FROM materia WHERE nombre =?";
        Materia m = new Materia();
        try{
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                m.setNombre(rs.getString("nombre"));
                m.setAño(rs.getInt("año"));
                m.setEstado(rs.getBoolean("estado"));
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia ..."+ex.getMessage());
        }
        return m;
    }
}
