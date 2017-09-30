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
    private int duracion;

    public Charla(String nombre, String salon, String hora, String fecha, int duracion)
    {
        this.nombre = nombre;
        this.lugar = salon;
        this.hora = hora;
        this.fecha = fecha;
        this.duracion = duracion;
        asistentes = new ArrayList<>();
    }
    public String getNombre()
    {
        return nombre;
    }
    public String getFecha()
    {
        return fecha;
    }
    public ArrayList<Estudiante> getAsistentes()
    {
        return asistentes;
    }
    public void setAsistentes(Estudiante estudiante)
    {
        this.asistentes.add(estudiante);
    }
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
    public String toStringEst()
    {
        String hilo = "Nombre charla: "+ nombre;
        hilo += "\nLugar: " +lugar;
        hilo += "\nHora: "+hora;
        hilo += "\nFecha: "+ fecha;
        hilo += "\nDuracion (horas): " + duracion+ "\n";
        return hilo;
    }
    public int getDuracion(){
        return duracion;
    }
    public String todosAsistentes()
    {   String asistieron="";
        for(Estudiante asistente:asistentes)
        {
            asistieron+=asistente.getNombre();
        }
        return asistieron;
    }
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