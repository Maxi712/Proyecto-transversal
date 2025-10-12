/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.de.alumnos.ulp;

/**
 *
 * @author Valentin Barros
 */
public class Inscripcion {
    private int idInscripto;
    private int nota;
    private Alumno alumno;
    private Materia materia;

    public Inscripcion() {
    }

    public Inscripcion(int idInscripto, int nota, Alumno alumno, Materia materia) {
        this.idInscripto = idInscripto;
        this.nota = nota;
        this.alumno = alumno;
        this.materia = materia;
    }

    public int getIdInscripto() {
        return idInscripto;
    }

    public void setIdInscripto(int idInscripto) {
        this.idInscripto = idInscripto;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Alumno getIdAlumno() {
        return alumno;
    }

    public void setIdAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getIdMateria() {
        return materia;
    }

    public void setIdMateria(Materia materia) {
        this.materia = materia;
    }
    
    
}
