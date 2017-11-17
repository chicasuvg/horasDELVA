/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HorasDelva;

/**
 *
 * @author Usuario
 */
public class CharlaException extends Exception{
    private String nombre;
    
    public CharlaException(String mensaje,String titulo)
    {
        super(mensaje);
        this.nombre=titulo;
    }
    
    public String getNombre()
    {
        return nombre;
    }
    
}
