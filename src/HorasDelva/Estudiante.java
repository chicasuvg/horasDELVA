/** 
 * @author Ana Lucia Hernandez (17138)
 * Andrea Arguello (17801) 
 * Cindy Antillon (14581)
 * @since 04/08/2017
 * @proposito  modela la informacion del estudiante UVG que - potencialmente - asistira a una charla
 * Estudiante.java
**/
package HorasDelva;
import java.util.ArrayList;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.NotSaved;

@Entity
public class Estudiante
{
    @Id private ObjectId id; 
    private String usuario;
    private String contrasena;
    private int horasDone;
    private int horasRestantes;
    @NotSaved private ArrayList<Charla> charlasEst;
    
    public Estudiante()
    {
        charlasEst = new ArrayList<>();
    }
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
    public void addHoras()
    {   
        if(horasDone<8)
        {
            this.horasDone ++;
        }
        else 
        {
            this.horasDone = 8;
        }
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
    /**
     * 
     * @return para obtener las charlas a las que ha asistido el estudiante
     */
    public String charlasAsistente()
    {
        String infocharla = "";
        for(Charla delva : charlasEst)
        {
            infocharla += delva.toStringEst();
        }   
        return infocharla;
    }
    
}
