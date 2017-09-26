/**
 * @author Ana Lucia Hernandez (17138)
 * Andrea Arguello (17801) 
 * Cindy Antillon (14581)
 * @since 04/08/2017
 * Registro.java
 */
package HorasDelva;

import java.util.ArrayList;

public class Registro {
    private ArrayList<Administrador> administradores;
    private ArrayList<Estudiante> estudiantes;
    private boolean acceso;
    private boolean student;
    private boolean admin;
    
    public Registro()//se tendra un usuario admistrador predeterminado y un usuario estudiante predeterminado
    {
        administradores = new ArrayList<>();
        estudiantes = new ArrayList<>();
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
    /**
     * Metodo que verifica si el usuario existe dentro de la base de datos, para poder ingresar a la plataforma de 
     * un usuario determinado.
     * @param nombreUsuario: el nombre de la cuenta
     * @param contrasena: la contrasena de la cuenta
     */
    public boolean verificacionIngreso(String nombreUsuario, String contrasena)
    {
        for(Estudiante estudiante: estudiantes)
        {
            if(nombreUsuario.equals(estudiante.getNombre()) && contrasena.equals(estudiante.getContrasena()))
            {
                acceso = true;
                student = true;
            }
        }
        for(Administrador administrador: administradores)
        {
            if(nombreUsuario.equals(administrador.getNombre()) && contrasena.equals(administrador.getContrasena()))
            {
                acceso = true;
                admin = true;
            }
        }
        return this.acceso;
    }
    public ArrayList<Administrador> usuarioAdmin()
    {
        return administradores;
    }
    public ArrayList<Estudiante> usuarioEst()
    {
        return estudiantes;
    }
    public boolean getAccesoEst()
    {
        return student;
    }
    public boolean getAccesoAdmin()
    {
        return admin;
    }
}
