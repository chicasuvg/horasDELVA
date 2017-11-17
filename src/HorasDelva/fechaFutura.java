/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HorasDelva;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class fechaFutura {    
    
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
