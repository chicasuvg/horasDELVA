/** 
 * @author Ana Lucia Hernandez (17138)
 * Andrea Arguello (17801) 
 * Cindy Antillon (14581)
 * @since 04/08/2017
 * @proposito  modela la informacion de una charla DELVA
Charla.java
*/
package HorasDelva;
import java.util.ArrayList;

public class Charla
{
    private String nombre; //el nombre de la charla siempre ira en mayusculas
    private String lugar;
    private String hora;
    private String fecha;
    private ArrayList<Estudiante> asistentes;

    public Charla(String nombre, String salon, String hora, String fecha)
    {
            this.nombre = nombre;
            this.lugar = salon;
            this.hora = hora;
            this.fecha = fecha;
    }
    public String getNombre()
    {
            return nombre;
    }
    public ArrayList<Estudiante> getAsistentes()
    {
            return asistentes;
    }
}