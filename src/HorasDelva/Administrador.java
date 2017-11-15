/** 
 * @author Ana Lucia Hernandez 17138
 * Cindy Antillon 14581
 * Andrea Arguello 17801
 * @proposito  modela la informacion del Administrador que controla toda la informacion de las charlas DELVA
 * @since 04/08/2017
 * Administrador.java
*/
package HorasDelva;

import java.util.ArrayList;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

public class Administrador
{
    @Id private ObjectId id; 
    private String usuario;
    private String contrasena;
    private ArrayList<Charla> charlasAdmin;
    private ArrayList<Estudiante> alumnos;
    private boolean existente; //para ver si un estudiante existe en la base de datos o no.
    
    public Administrador()
    {
        charlasAdmin = new ArrayList<>();
        alumnos = new ArrayList<>();
    }
    /**
     * Constructor
     * @param usuario usuario
     * @param contrasena contrasena
     */
    public Administrador(String usuario, String contrasena)
    {
        this.usuario = usuario;
        this.contrasena = contrasena;
        charlasAdmin = new ArrayList<>();
        alumnos = new ArrayList<>();
        existente = true;
    }
    public void setAlumnos(Estudiante estudiante)
    {
        alumnos.add(estudiante);
    }
    /**
     * Para obtener el nombre de usuario del administrador
     * @return string de nombre de usuario
     */
    public String getNombre()
    {
        return usuario;
    }
    /**
     * Para obtener la contrasena del administrador
     * @return string de contrasena
     */
    public String getContrasena()
    {
        return contrasena;
    }
    /**
     * Para agregar una nueva charla delva
     * @param delva: charla que se quiere agregar
     */
    public void agregarCharla(Charla delva)
    {
        charlasAdmin.add(delva);
        for (Estudiante estudiante: alumnos)
        {
            estudiante.getCEst().add(delva);
        }
    }
    /**
     * Metodo para eliminar una charla de la lista. 
     * @param busqueda: nombre de la charla que se quiere eliminar.
     */
    public ArrayList<Charla> eliminarCharla(String busqueda)
    {
        int index =0;
        for (Charla delva : charlasAdmin)
        {
            if (delva.getNombre().equals(busqueda.toUpperCase()))
            {
                index = charlasAdmin.indexOf(delva);
            }
        }
        charlasAdmin.remove(index); //elimina la charla que tiene el index de la charla que quiere eliminar
        return charlasAdmin;
    }	
    /**
     * Para obtener el nombre de usuario del administrador
     * @param bcharla: representa el nombre de la charla a la que quiere ingresarle la asistencia
     * @param carnet: carnet del estudiante que se quiere modificar la asistencia.
     */
    public ArrayList<Estudiante> agregarAsistentes(String bcharla, String carnet) 
    { 
        ArrayList<Estudiante> asistentes = new ArrayList<>();
        for (Charla delva: charlasAdmin)
        {
            if (delva.getNombre().equals(bcharla))
            {
                for (Estudiante estudiante: alumnos)
                {
                    if (estudiante.getNombre().equals(carnet))
                    {
                        estudiante.addHoras(); //agregarle al estudiante las horas que cumplio
                        delva.setAsistentes(estudiante); //para agregar a la lista de asistentes al estudiante
                    }
                }
            }
            asistentes = delva.getAsistentes();
        }
        return asistentes;
    }
    /**
     * Para obtener el nombre de usuario del administrador
     * @return arraylist de las charlas delva que actualmente se tienen vigentes 
     */
    public ArrayList<Charla> mostrarCharlas()
    {
        return charlasAdmin;
    }
    /**
     * Para obtener el nombre de usuario del administrador
     * @return arraylist de estudiantes
     */
    public ArrayList<Estudiante> getAlumnos()
    {
        return alumnos;
    }
    /**
     * Se utilizara para que todos los administradores tengan las mismas charlas (todas las charlas se guardarán en el array 
     * de charlas de Registro, de ahí las jalará).
     * @param charlas: lista de charlas que se quieren poner como la del administrador 
     */
    public void setCharlas(ArrayList<Charla> charlas)
    {
        charlasAdmin = charlas;
    }
	
}
