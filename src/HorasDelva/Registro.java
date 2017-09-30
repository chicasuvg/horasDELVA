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
    /**
     * Para obtener la lista de administradores
     * @return Array de las administradores
     */
    public ArrayList<Administrador> usuarioAdmin()
    {
        return administradores;
    }
    /**
     * Para obtener la lista de estudiantes de UVG
     * @return Array de las estudiantes
     */
    public ArrayList<Estudiante> usuarioEst()
    {
        return estudiantes;
    }
    /**
     * Para poder buscar si un estudiante ya existe en la base de datos (para que no se creen dos iguales)
     * @param carnet: nombre de usuario del estudiante
     * @return boolean de si ya existe o no
     */
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
    /**
     * Para obtener la lista de charlas que todos los administradores tienen 
     * @return Array de las charlas
     */
    public ArrayList<Charla> getCharlas()
    {
        return listaCharlas;
    }
    /**
     * Para saber si lo que ingreso el usuario son los datos correctos para poder "iniciar sesion" de un usuario tipo estudiante
     * @return boolean si puede entrar o no
     */
    public boolean getAccesoEst()
    {
        return student;
    }
    /**
     * Para saber si lo que ingreso el usuario son los datos correctos para poder "iniciar sesion" de un usuario tipo administrador
     * @return boolean si puede entrar o no
     */
    public boolean getAccesoAdmin()
    {
        return admin;
    }
    /**
     * Obtener el objeto Administrador que actualmente se esta utilizando
     * @return Administrador
     */
    public Administrador getAdministrador()
    {
        return usuarioAdmin;
    }
    /**
     * Obtener el objeto Estudiante que actualmente se esta utilizando
     * @return Estudiante
     */
    public Estudiante getEstudiante()
    {
        return usuarioEst;
    }
    /**
     * Buscar si la charla ya existe en la Base de datos (para no crear dos iguales)
     * @param nombre: nombre de la charla
     * @return boolean si existe o no
     */
    public boolean buscarCharla(String nombre)
    {   boolean existe=false;
        for(Charla charla: listaCharlas){
            if(charla.getNombre().equals(nombre)){
                existe=true;
            }
        }
        return existe;
    }
    /**
     * Buscar si un estudiante ya se ha ingresado antes (para no ingresar el mismo estudiante dos veces en la misma charla)
     * @param nombre: nombre de la charla
     * @param carnet: nombre de usuario del estudiante
     * @return boolean
     */
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
    /**
     * Buscar si un administrador ya se ha creado antes (para no crear dos iguales)
     * @param usuario: nombre de usuario
     * @return boolean
     */
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
