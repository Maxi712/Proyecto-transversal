
package gestion.de.alumnos.ulp;

import AccesoDatos.AlumnoData;
import gestion.de.alumnos.ulp.Alumno;
import java.time.LocalDate;
import java.util.ArrayList;


public class TestDeConsola {

    public static void main(String[] args) {
       AlumnoData data=new AlumnoData ();
       /*Alumno a =new Alumno(123,44738237,"Castro","Maximiliano",LocalDate.of(2004, 12, 7), true);
       data.guardarAlumno(a);
       Alumno b =new Alumno(123,40238734,"Barros","Edgardo",LocalDate.of(2004, 12, 7), true);
       data.guardarAlumno(b);
       Alumno c =new Alumno(123,41934744,"Carrizo","Anna",LocalDate.of(2004, 12, 7), true);
       data.guardarAlumno(c);
       Alumno d =new Alumno(123,40238274,"Gimenez","Pablo",LocalDate.of(2004, 12, 7), true);
       data.guardarAlumno(d);
       Alumno e =new Alumno(123,42293843,"Fernandez","Rodrigo",LocalDate.of(2004, 12, 7), true);
       data.guardarAlumno(e);*/
       
       ArrayList<Alumno> al = new ArrayList();
       al= data.listarAlumno();
       for(Alumno alum : al){
            System.out.println("Los nombres son");
            System.out.println(alum.getNombre());;
        }
       
    }
    
}
