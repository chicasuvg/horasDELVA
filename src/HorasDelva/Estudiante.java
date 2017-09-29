/** 
 * @author Ana Lucia Hernandez (17138)
 * Andrea Arguello (17801) 
 * Cindy Antillon (14581)
 * @since 04/08/2017
@proposito  modela la informacion del estudiante UVG que - potencialmente - asistira a una charla
Estudiante.java
*/
package HorasDelva;
import java.util.ArrayList;

public class Estudiante
{
    private String usuario;
    private String contrasena;
    private int horasDone;
    private int horasRestantes;
    private long carnet;
    private ArrayList<Charla> charlasEst;

    /**
     * 
     * @param usuario: nombre del usuario
     * @param contrasena: contrasena
     */
    public Estudiante(String usuario, String contrasena)
    {
        charlasEst = new ArrayList<>();
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.horasDone = 0;
    }
    /**
     * 
     * @return usuario
     */
    public String getNombre()
    {
            return usuario;
    }
    /**
     * 
     * @return contrasena
     */
    public String getContrasena()
    {
        return contrasena;
    }
    /**
     * 
     * @return horas cumplidas
     */
    public int getHoras()
    {
            return horasDone;
    }
    /**
     * 
     * @return charlas asistidas 
     */
    public ArrayList<Charla> getCEst()
    {
            return charlasEst;
    }
    
    /**
     * 
     * @param hora: duracion de la charla
     */
    public void addHoras(int hora)
    {   while(horasDone<8)
        {this.horasDone += hora;}
    }
    /**
     * 
     * @return horasRestantes
     */
    public int horasRestantes()
    {
        if (horasDone < 5)
        {
            horasRestantes = 5- horasDone;
        }
        else if((horasDone == 5) || horasDone>5)
        {
            horasRestantes = 0;
        }
        return horasRestantes;
    }
}