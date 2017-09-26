/** 
@author Ana Lucia Hernandez 17138
Cindy Antillon 14581
Andrea Arguello 17801
@proposito  modela la informacion del Administrador que controla toda la informacion de las charlas DELVA
@since 04/08/2017
Administrador.java
*/
import java.util.ArrayList;

public class Administrador
{
    private String usuario;
    private String contrasena;
    protected ArrayList<Charla> charlasAdmin;
    private int index;
    protected ArrayList<Estudiante> alumnos;

    public Administrador(String usuario, String contrasena)
    {
            this.usuario = usuario;
            this.contrasena = contrasena;
    }

    public String getNombre()
    {
        return usuario;
    }
    public String getContrasena()
    {
        return contrasena;
    }
    public void agregarCharla(Charla delva)
    {
            charlasAdmin.add(delva);
            for (Estudiante estudiante: alumnos)
            {
                    estudiante.getCEst().add(delva);
            }
    }
    public void eliminarCharla(String busqueda)
    {
            for (Charla delva : charlasAdmin)
                    {
                            if (delva.getNombre().equals(busqueda))
                            {
                                    index = charlasAdmin.indexOf(delva);
                            }
                    }
            charlasAdmin.remove(index); //elimina la charla que tiene el index de la charla que quiere eliminar
    }	
    public void agregarAsistentes(String bcharla, String carnet) 
    { //bcharla representa el nombre de la charla a la que quiere ingresarle la asistencia
            for (Charla delva: charlasAdmin)
            {
                    if (delva.getNombre().equals(bcharla))
                    {
                            for (Estudiante estudiante: alumnos)
                            {
                                    if (estudiante.getNombre().contains(carnet))
                                    {
                                            estudiante.horasDone += 1; //agregarle al estudiante las horas que cumplio
                                            delva.getAsistentes().add(estudiante); //para agregar a la lista de asistentes al estudiante
                                    }
                            }
                    }
            }
    }
    public ArrayList<Charla> mostrarCharlas()
    {
            return charlasAdmin;
    }
    public ArrayList<Estudiante> getAlumnos()
    {
            return alumnos;
    }
	
}