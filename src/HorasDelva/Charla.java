/** 
 * @author Ana Lucia Hernandez (17138)
 * Andrea Arguello (17801) 
 * Cindy Antillon (14581)
 * @since 04/08/2017
 * @proposito  modela la informacion de una charla DELVA
 * Charla.java
**/
package HorasDelva;
import java.util.ArrayList;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Id;

public class Charla
{
    @Id private ObjectId id; 
    private String nombre; //el nombre de la charla siempre ira en mayusculas
    private String lugar;
    private String hora;
    private String fecha;
    private ArrayList<Estudiante> asistentes;
    private int duracion;
    
    public Charla(){asistentes = new ArrayList<>();}
    public Charla(String nombre, String salon, String hora, String fecha, int duracion)
    {
        this.nombre = nombre;
        this.lugar = salon;
        this.hora = hora;
        this.fecha = fecha;
        this.duracion = duracion;
        asistentes = new ArrayList<>();
    }
    /**
     * Obtener el nombre de la charla
     * @return String nombre de charla
     */
    public String getNombre()
    {
        return nombre;
    }
    /**
     * Obtener la fecha de la charla
     * @return String fecha de charla
     */
    public String getFecha()
    {
        return fecha;
    }
    /**
     * Obtener la lista de estudiantes que han asistido a esta charla
     * @return array de estudiantes
     */
    public ArrayList<Estudiante> getAsistentes()
    {
        return asistentes;
    }
    /**
     * Agregar asistentes a la charla
     * @param estudiante: estudiante
     */
    public void setAsistentes(Estudiante estudiante)
    {
        this.asistentes.add(estudiante);
    }
    /**
     * Obtener la informacion de la charla que es relevante para el administrador
     * @return String informacion de charla
     */
    public String toStringAdmin()
    {
        String hilo = "Nombre charla: "+ nombre;
        hilo += "\nLugar: " +lugar;
        hilo += "\nHora: "+hora;
        hilo += "\nFecha: "+ fecha;
        hilo += "\nDuracion (horas): " + duracion;
        String assist = "";
        for(Estudiante estudiante: asistentes)
        {
            assist += estudiante.getNombre();
        }
        hilo += "\nAsistentes: "+ assist;
        return hilo;
    }
    /**
     * Obtener la informacion de la charla que es relevante para el Estudiante
     * @return String informacion de charla
     */
    public String toStringEst()
    {
        String hilo = "Nombre charla: "+ nombre;
        hilo += "\nLugar: " +lugar;
        hilo += "\nHora: "+hora;
        hilo += "\nFecha: "+ fecha;
        hilo += "\nDuracion (horas): " + duracion+ "\n";
        return hilo;
    }
    /**
     * Obtener la duracion (horas) de la charla
     * @return int horas
     */
    public int getDuracion(){
        return duracion;
    }
    /**
     * Obtener todos los asistentes de esta charla 
     * @return String informacion de charla
     */
    public String todosAsistentes()
    {   String asistieron="";
        for(Estudiante asistente:asistentes)
        {
            asistieron+=asistente.getNombre();
        }
        return asistieron;
    }
    /**
     * Buscar un estudiante especifico dentro de los asistentes de la charla
     * @param usuario: nombre de estudiante
     * @return boolean si ya fue ingresado o no
     */
    public boolean buscarAsistente(String usuario)
    { boolean existe=false;
        for(Estudiante asistio: asistentes)
        {
            if(asistio.getNombre().equals(usuario))
            {
                existe=true;
            }
            else
            {
                existe=false;
            }
        }
        return existe;
    }
    
}