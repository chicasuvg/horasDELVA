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
    private Estudiante usuarioEst;//usuario que actualmente esta activo (para cuando inicie sesion)
    private Administrador usuarioAdmin; //usuario que actualmente esta activo (para cuando inicie sesion)
    private ArrayList<Administrador> administradores; //lista de administradores de UVG Delvas
    private ArrayList<Estudiante> estudiantes; // lista de estudiantes de UVG
    private ArrayList<Charla> listaCharlas;
    private boolean existente;
    private boolean acceso;
    private boolean student;
    private boolean admin;
    
    public Registro()//se tendra un usuario admistrador predeterminado y un usuario estudiante predeterminado
    {
        
        listaCharlas = new ArrayList<>();
        administradores = new ArrayList<>();
        estudiantes = new ArrayList<>();
        String[] nombresUsuarios = {"ARG17801", "ANT14581"};
        String[] contrasenas = {"1234", "1234", "1234"};
        Administrador cindy = new Administrador(nombresUsuarios[1], contrasenas[1]);
        Estudiante andrea = new Estudiante(nombresUsuarios[0], contrasenas[0]);
        administradores.add(cindy);
        cindy.getAlumnos().add(andrea);
        estudiantes.add(andrea);
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
            Administrador nuevo = new Administrador(nombreUsuario.toUpperCase(), contrasena);
            nuevo.setCharlas(listaCharlas); //para que el nuevo administrador tenga acceso a todas las charlas previamente ingresadas
            administradores.add(nuevo);
            
        }
        if (tipoUsuario.equals("Estudiante"))
        {
            Estudiante nuevo = new Estudiante(nombreUsuario.toUpperCase(), contrasena);
            estudiantes.add(nuevo);
            for(Administrador admin : administradores)
            {
                admin.getAlumnos().add(nuevo);//para que todos los administradores tengan la misma lista de estudiantes.
            }
            
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
            if(estudiante.getNombre().equals(nombreUsuario) && estudiante.getContrasena().equals(contrasena))
            {
                acceso = true;
                student = true;
                admin = false;
                usuarioEst = estudiante; 
            }
        }
        for(Administrador administrador: administradores)
        {
            if(administrador.getNombre().equals(nombreUsuario) && administrador.getContrasena().equals(contrasena))
            {
                acceso = true;
                admin = true;
                student = false;
                listaCharlas = administrador.mostrarCharlas();
                usuarioAdmin = administrador;
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
    public boolean buscarEstudiante(String carnet)
    {
        boolean existe=false;
        for(Estudiante estudiante: estudiantes)
        {
            if(estudiante.getNombre().equals(carnet))
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
    public ArrayList<Charla> getCharlas()
    {
        return listaCharlas;
    }
    public boolean getAccesoEst()
    {
        return student;
    }
    public boolean getAccesoAdmin()
    {
        return admin;
    }
    public Administrador getAdministrador()
    {
        return usuarioAdmin;
    }
    public Estudiante getEstudiante()
    {
        return usuarioEst;
    }
    public boolean buscarCharla(String nombre)
    {   boolean existe=false;
        for(Charla charla: listaCharlas){
            if(charla.getNombre().equals(nombre)){
                existe=true;
            }
        }
        return existe;
    }
    public boolean buscarAsistente(String nombre, String carnet)
    { 
        boolean existe=false;
        for(Charla charla:listaCharlas)
        {
            if(charla.getNombre().equals(nombre))
            {
                existe=charla.buscarAsistente(carnet);
            }
        }
        return existe;
    }
    public boolean buscarAdmin(String usuario)
    {
        boolean existe=false;
        for(Administrador admin:administradores)
        {
            if(admin.getNombre().equals(usuario))
            {
                existe=true;
                
            }
            else{existe=false;}
        }
        return existe;
    }
   
}
