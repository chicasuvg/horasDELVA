/** 
 * @author Ana Lucia Hernandez 17138
 * Cindy Antillon 14581
 * Andrea Arguello 17801
 * @proposito  Tira una excepcion FechaException si la fecha aun no ha pasado
 * @since 10/11/2017
 * FechaFutura.java
*/
package HorasDelva;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class fechaFutura {    
    
    /**
     * 
     * @param newfecha fecha de la charla
     * @throws FechaException si es futura
     */
    public static void fechaFutura(String newfecha) throws FechaException
    {
        Date ahora= new Date();
        SimpleDateFormat fecha= new SimpleDateFormat("dd/MM/yyyy");
        String hoy=(fecha.format(ahora));
        String[] partesHoy=hoy.split("/");
        String[] partesFecha=newfecha.split("/");
        FechaException excepcion;
        
        if((Double.parseDouble(partesHoy[2])<Double.parseDouble(partesFecha[2])) || (Double.parseDouble(partesHoy[2])>=Double.parseDouble(partesFecha[2]) && Double.parseDouble(partesHoy[1])<Double.parseDouble(partesFecha[1])) || (Double.parseDouble(partesHoy[2])>=Double.parseDouble(partesFecha[2]) && Double.parseDouble(partesHoy[1])>=Double.parseDouble(partesFecha[1]) && Double.parseDouble(partesHoy[0])<Double.parseDouble(partesFecha[0]))){
             String alerta= "Esta fecha no ha pasado. Esta accion no puede efectuarse.";
             excepcion= new FechaException(alerta);
             throw excepcion;
             
        }
        
    }
    
}
