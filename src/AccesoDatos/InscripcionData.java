/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import gestion.de.alumnos.ulp.Materia;
import gestion.de.alumnos.ulp.Alumno;
import gestion.de.alumnos.ulp.Inscripcion;
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
public class InscripcionData {

    private Connection conexion;

    public InscripcionData() {
        conexion = Conexion.getConexion();
    }

    public void inscribirAlumno(Materia materia, Alumno alumno, Inscripcion inscripcion) {
        String sql = "INSERT INTO inscripcion(idMateria, idAlumno) VALVUES (?,?)";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, materia.getIdMateria());
            ps.setInt(2, alumno.getIdAlumno());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                inscripcion.getIdMateria().setIdMateria(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Alumno inscripto exitosamente ...");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia ..." + ex.getMessage());
        }
    }

    public void deinscribirAlumno(int idMateria, int idAlumno) {
        String sql = "DELETE FROM inscripcion WHERE idMateria= ? AND idAlumno= ? ";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idMateria);
            ps.setInt(2, idAlumno);
            int exito = ps.executeUpdate();
            if(exito == 1){
                JOptionPane.showMessageDialog(null, "Inscripcion eliminada ...");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia ..." + ex.getMessage());
        }
    }
}
