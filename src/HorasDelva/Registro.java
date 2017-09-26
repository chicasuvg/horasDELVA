/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HorasDelva;

/**
 *
 * @author Ana
 */
import java.util.ArrayList;

public class Registro {
    private ArrayList<Administrador> administradores;
    private ArrayList<Estudiante> estudiantes;
    private boolean acceso;
    private boolean estudiante;
    private boolean admin;
    
    public Registro()//se tendra un usuario admistrador predeterminado y un usuario estudiante predeterminado
    {
        administradores = new ArrayList<Administrador>();
        estudiantes = new ArrayList<Estudiante>();
        String[] nombresUsuarios = {"arg17801", "ant17581"};
        String[] contrasenas = {"1234", "1234"};
        Estudiante andrea = new Estudiante(nombresUsuarios[0], contrasenas[0]);
        Administrador cindy = new Administrador(nombresUsuarios[1], contrasenas[1]);
        acceso = false;
    }
    /**
     * Metodo para crear usuarios para poder ingresar a la plataforma. El usuario tiene que ingresar el nombre de usuario
     * y la contrasena y el tipo de cuenta que quiere crear.
     * @param tipoUsuario: el tipo de cuenta que se desea crear
     * @param nombreUsuario: el nombre de la nueva cuenta
     * @param contrasena: la contrasena de la nueva cuenta
     */
    public void crearUsuario(String tipoUsuario, String nombreUsuario, String contrasena)
    {
        if(tipoUsuario.equals("Administrador"))
        {
            Administrador nuevo = new Administrador(nombreUsuario, contrasena);
            administradores.add(nuevo);
        }
        if (tipoUsuario.equals("Estudiante"))
        {
            Estudiante nuevo = new Estudiante(nombreUsuario, contrasena);
            estudiantes.add(nuevo);
        }
    }
    public void verificacionIngreso(String nombreUsuario, String contrasena)
    {
        for(Estudiante estudiante: estudiantes)
        {
            if(nombreUsuario.equals(estudiante.getNombre()) && contrasena.equals(estudiante.getContrasena()))
            {
                acceso = true;
            }
        }
        for(Administrador administrador: administradores)
        {
            if(nombreUsuario.equals(administrador.getNombre()) && contrasena.equals(administrador.getContrasena()))
            {
                acceso = true;
            }
        }
    }
    
}
