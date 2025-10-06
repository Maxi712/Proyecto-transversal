/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import gestion.de.alumnos.ulp.Alumno;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Statement;

/**
 *
 * @author Valentin Barros
 */
public class AlumnoData {
    private Connection conexion;
    
    public AlumnoData(){
        conexion = Conexion.getConexion();
    }
    
    public void guardarAlumno(Alumno alumno){
        String sql = "INSERT INTO alumno( dni, apellido, nombre, fechaNacimiento, estado) VALUES (?,?,?,?,?)";
        try{
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, java.sql.Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                alumno.setIdAlumno(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Alumno guardado exitosamente ...");
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno ..."+ex.getMessage());
        }
    }
    
    public void modificarAlumno(Alumno alumno){
        try{
            String sql = "UPDATE alumno SET dni=?, apellido=?, nombre=?, fechaNacimiento=?, estado=? WHERE idAlumno=?";
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado());
            int exito = ps.executeUpdate();
            if(exito == 1){
                JOptionPane.showMessageDialog(null, "Alumno modificado ...");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno ..."+ex.getMessage());
        }
    }
    
    public void eliminarAlumno(int idAlumno){
        String sql = "UPDATE alumno SET estado = 0 WHERE idAlumno=?";
        PreparedStatement ps;
        try{
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            int exito = ps.executeUpdate();
            if(exito == 1){
                JOptionPane.showMessageDialog(null, "Alumno eliminado ...");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno ..."+ex.getMessage());
        }
    }
    
    public void altaAlumno(int idAlumno){
        String sql = "UPDATE alumno SET estado = 1 WHERE idAlumno =?";
        PreparedStatement ps;
        try{
           ps = conexion.prepareStatement(sql);
           ps.setInt(1, idAlumno);
           int exito  = ps.executeUpdate();
           if(exito==1){
              JOptionPane.showMessageDialog(null, "Alumno dado de alta ..."); 
           }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno ..."+ex.getMessage());
        }
    }
    
    public ArrayList<Alumno> listarAlumno(){
        ArrayList <Alumno> listaA = new ArrayList();
        String sql = "SELECT * FROM alumno WHERE estado=1";
        try{
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
              int idAlumno = rs.getInt("idAlumno");
              int dni = rs.getInt("dni");
              String apellido = rs.getString("apellido");
              String nombre = rs.getString("nombre");
              LocalDate fechaNacimiento = rs.getDate("fechaNacimiento").toLocalDate();
              boolean estado = rs.getBoolean("estado");
              Alumno a = new Alumno(idAlumno, dni, apellido, nombre, fechaNacimiento, estado);
              listaA.add(a);
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno ..."+ex.getMessage());
        }
        return listaA;
    }
    
    public Alumno buscarPorId(int idAlumno){
        String sql = "SELECT * FROM alumno WHERE idAlumno =?";
        Alumno a = new Alumno();
        try{
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                a.setDni(rs.getInt("idAlumno"));
                a.setApellido(rs.getString("apellido"));
                a.setNombre(rs.getString("nombre"));
                a.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                a.setEstado(rs.getBoolean("estado"));
                
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno ..."+ex.getMessage());
        }
        return a;
    }
    
    public Alumno buscarPorDocumento(int documento){
        String sql = "SELECT * FROM alumno WHERE dni =?";
        Alumno a = new Alumno();
        try{
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, documento);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                a.setDni(rs.getInt("idAlumno"));
                a.setApellido(rs.getString("apellido"));
                a.setNombre(rs.getString("nombre"));
                a.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                a.setEstado(rs.getBoolean("estado"));
                
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno ..."+ex.getMessage());
        }
        return a;
    }
}
