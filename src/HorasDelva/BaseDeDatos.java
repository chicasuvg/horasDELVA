/**
 *  @author Ana Lucia Hernandez (17137)
 *      Andrea Arguello (17138)
 *  @since 20/10/2017
 *  Clase para modelas la base de datos que guardará todo sobre lo sus usuarios y las DELVAs
 **/
package HorasDelva;

import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.List;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

public class BaseDeDatos {
    private Datastore ds;
    private List<Administrador> administradores;
    private List<Estudiante> estudiantes;
    private List<Charla> charlas;
    private Query<Administrador> query;
    private Query<Charla> query2;
    private Query<Estudiante> query1;
    private boolean admin;
    private boolean est;
    private Estudiante usuarioE;
    private Administrador usuarioA;
    
    public BaseDeDatos()
    {
        MongoClient mongo = new MongoClient();
        Morphia morphia = new Morphia();
        morphia.map(Administrador.class).map(Estudiante.class).map(Charla.class);
        ds = morphia.createDatastore(mongo, "HorasDelva"); // Base Datos
        query = ds.createQuery(Administrador.class);
        List<Administrador> busqueda = query.asList();
        administradores = busqueda;
        query1 = ds.createQuery(Estudiante.class);
        List<Estudiante> busqueda1 = query1.asList();
        estudiantes = busqueda1;
        query2 = ds.createQuery(Charla.class);
        charlas = query2.asList();
        admin = false;
        est = false;
    }
    /**
     * Para que no abra las ventanas del estudiantes si ya se ingreso como administrador
     * @return boolean: est de si es un estudiante el que está ingresando
     */
    public boolean getEst()
    {
        return est;
    }
    /**
     * Para que no abra las ventanas del administrador si ya se ingreso como estudiante
     * @return boolean: est de si es un administrador el que está ingresando
     */
    public boolean getAdmin()
    {
        return admin;
    }
    /**
     * Para obtener el usuario que actualmente se está utilizando
     * @return Estudiante usuarioE
     */
    public Estudiante getEstudiante()
    {
        return usuarioE;
    }
    /**
     * Para que oculte la ventanita de funcionalidades del estudiante
     */
    public void setEst()
    {
        est = false;
    }
    /**
     * Para que oculte la ventanita de funcionalidades del administrador
     */
    public void setAdmin()
    {
        admin = false;
    }
    /**
     * Para obtener el usuario que actualmente se está usando
     * @return Administrador usuarioA
     */
    public Administrador getAdministrador()
    {
        return usuarioA;
    }
    /**
     * Para obtener todas las charlas que se han ingresado en la base de datos. 
     * @return ArrayList delvas;
     */
    public ArrayList<Charla> getCharlas()
    {
        ArrayList<Charla> delvas = new ArrayList();
        query2 = ds.createQuery(Charla.class);
        charlas = query2.asList();
        for (Charla delva: charlas) { delvas.add(delva); }
        return delvas;
    }
    /**
     * Para crear un nuevo usuario e ingresarlo en la base de datos
     * @param tipoUsuario: para saber si es un admin o un estudiante el que quiere ingresar
     * @param nombreUsuario: el nombre de usuario nuevo
     * @param contrasena: la contraseña nueva
     * @return boolean si ya existe el usuario o no
     */
    public boolean crearUsuario(String tipoUsuario, String nombreUsuario, String contrasena)
    {
        query = ds.createQuery(Administrador.class);
        List<Administrador> busqueda = query.asList();
        administradores = busqueda;
        query1 = ds.createQuery(Estudiante.class);
        List<Estudiante> busqueda1 = query1.asList();
        estudiantes = busqueda1;
        boolean existeUsuario = false;
        for (Administrador admin : administradores)
        {
            if (admin.getNombre().equals(nombreUsuario))
            {
                existeUsuario = true;
            }
        }
        for (Estudiante est :estudiantes)
        {
            if (est.getNombre().equals(nombreUsuario))
            {
                existeUsuario = true;
            }
        }
        if(tipoUsuario.equals("Administrador"))
        {
            Administrador nuevo = new Administrador(nombreUsuario.toUpperCase(), contrasena);
            if (!administradores.isEmpty())
            {
                ArrayList<Charla> charlas = administradores.get(0).mostrarCharlas();
                nuevo.setCharlas(charlas);
            } //para que el nuevo administrador tenga acceso a todas las charlas previamente ingresadas
            ds.save(nuevo);
            
        }
        if (tipoUsuario.equals("Estudiante"))
        {
            Estudiante nuevo = new Estudiante(nombreUsuario.toUpperCase(), contrasena);
            ds.save(nuevo);
        }
        return existeUsuario;
    }
    /**
     * Para verificar si un usuario ya creado está ingresando su información correctamente
     * @param nombreUsuario: el nombre de usuario que se está ingresando 
     * @param contrasena: la contraseña que se quiere ingresar
     * @return boolean para si el usuario puede ingresar al programa o no
     */
    public boolean verificacionIngreso(String nombreUsuario, String contrasena)
    {
        boolean puedeIngresar = false;
        query = ds.createQuery(Administrador.class);
        List<Administrador> busqueda = query.asList();
        administradores = busqueda;
        query1 = ds.createQuery(Estudiante.class);
        List<Estudiante> busqueda1 = query1.asList();
        estudiantes = busqueda1;
        query2 = ds.createQuery(Charla.class);
        List<Charla> busqueda2 = query2.asList();
        charlas = busqueda2;
        
        ArrayList<Charla> charlasA = new ArrayList<>();
        for(Estudiante estudiante: estudiantes)
        {
            if(estudiante.getNombre().equals(nombreUsuario) && estudiante.getContrasena().equals(contrasena))
            {
                puedeIngresar = true;
                est = true;
                usuarioE = estudiante;
            }
        }
        for(Administrador administrador: administradores)
        {
            if(administrador.getNombre().equals(nombreUsuario) && administrador.getContrasena().equals(contrasena))
            {
                puedeIngresar = true;
                admin = true;
                usuarioA = administrador;
                for(Charla charl: charlas) {charlasA.add(charl);}
                usuarioA.setCharlas(charlasA);
                for (Estudiante stud : estudiantes) {usuarioA.setAlumnos(stud);}
            }
        }
        return puedeIngresar;
    }
    /**
     * Crear una nueva charla
     * @param nombre: nombre de charla
     * @param salon: lugar de charla
     * @param time: hora de la charla
     * @param fecha: fecha en que ocurrirá
     * @param duracion: cuánto dura la charla
     * @return boolean de si la charla ya existe o no
     */
    public boolean ingresarCharla(String nombre, String salon, String time, String fecha, int duracion)
    {
        boolean existeCharla = false;
        Query<Charla>query2 = ds.createQuery(Charla.class);
        List<Charla> busqueda2 = query2.asList();
        charlas = busqueda2;
        for (Charla delva : charlas)
        {
            if (delva.getNombre().equals(nombre))
            {
                existeCharla = true;
            }
        }
        if (existeCharla == false)
        {
            Charla delva = new Charla(nombre, salon, time, fecha, duracion);
            usuarioA.agregarCharla(delva);
            ds.save(delva);
            UpdateOperations upd = ds.createUpdateOperations(Administrador.class).set("charlasAdmin", usuarioA.mostrarCharlas());
            ds.update(query, upd,false);        
        }
        return existeCharla;
        
    }
    /**
     * Para buscar si un estudiante existe en la base de datos
     * @param carnet: nombre del usuario estudiante
     * @return boolean de si existe el estudiante o no
     */
    public boolean buscarEstudiante(String carnet)
    {
        query1 = ds.createQuery(Estudiante.class);
        List<Estudiante> busqueda1 = query1.asList();
        estudiantes = busqueda1;
        boolean existe=false;
        for(Estudiante estudiante: estudiantes)
        {
            if(estudiante.getNombre().equals(carnet))
            {
                existe=true;
                return existe;
            }
            
        }
        return existe;
    }
    /**
     * Para saber si una charla existe o no
     * @param nombre: nombre de la charla a buscar
     * @return boolean existe
     */
    public boolean buscarCharla(String nombre)
    {   
        boolean existe=false;
        Query<Charla>query2 = ds.createQuery(Charla.class);
        List<Charla> busqueda2 = query2.asList();
        charlas = busqueda2;
        
        for(Charla charla: charlas)
        {
            if(charla.getNombre().equals(nombre))
            {
                existe=true;
            }
        }
        return existe;
    }
    /**
     * Buscar si ya se ha ingresado un estudiante como asistente en una charla específica
     * @param nombre: nombre de la charla
     * @param carnet: usuario del estudiante
     * @return boolean existe
     */
    public boolean buscarAsistente(String nombre, String carnet)
    { 
        boolean existe=false;
       Query<Charla> query2 = ds.createQuery(Charla.class);
        List<Charla> busqueda2 = query2.asList();
        charlas = busqueda2;
        
        for(Charla charla:charlas)
        {
            if(charla.getNombre().equals(nombre))
            {
                existe=charla.buscarAsistente(carnet);
            }
        }
        return existe;
    }
    /**
     * Para buscar si un administrador existe en la base de datos
     * @param usuario: nombre del usuario administrador
     * @return boolean de si existe el administrador o no
     */
    public boolean buscarAdmin(String usuario)
    {
        query = ds.createQuery(Administrador.class);
        List<Administrador> busqueda = query.asList();
        administradores = busqueda;
        boolean existe=false;
        for(Administrador admin:administradores)
        {
            if(admin.getNombre().equals(usuario))
            {
                existe=true;
                return existe;
                
            }
            else
            {
                existe=false;
            }
        }
        return existe;
    }
    /**
     * buscar la fecha en la que ocurrirá una charla
     * @param nombre: nombre de la charla
     * @return String fecha de la charla
     */
    public String fechaCharla(String nombre)
    {
        Query<Charla>query2 = ds.createQuery(Charla.class);
        List<Charla> busqueda2 = query2.asList();
        charlas = busqueda2;
        String fecha="";
        for(Charla charla: charlas)
        {
            if(charla.getNombre().equals(nombre))
            {
                fecha=charla.getFecha();
            }
        }
        return fecha;
    }
    /**
     * Para eliminar una charla de la base de datos
     * @param nombre: nombre de la charla a eliminar
     */
    public void eliminarCharla(String nombre)
    {
        ArrayList<Charla> eliminar = usuarioA.eliminarCharla(nombre);
        query = ds.createQuery(Administrador.class);
        query1 = ds.createQuery(Estudiante.class);
        UpdateOperations upd = ds.createUpdateOperations(Estudiante.class).set("charlasEst", eliminar);
        ds.update(query1, upd,false);
        UpdateOperations update = ds.createUpdateOperations(Administrador.class).set("charlasAdmin", eliminar);
        ds.update(query, update,false);
        Query<Charla> queryDelete =  ds.createQuery(Charla.class).filter("nombre", nombre);
        ds.delete(queryDelete);
    }
    /**
     * Agregar estudiantes como asistentes a una charla
     * @param nombrec: nombre de la charla
     * @param carnet: carnet del estudiante
     */
    public void agregarAsistentes(String nombrec, String carnet)
    {
        ArrayList<Charla> charlitas = usuarioA.mostrarCharlas();
        query = ds.createQuery(Administrador.class);
        query1 = ds.createQuery(Estudiante.class).field("usuario").equal(carnet);
        Query<Charla> query2 = ds.createQuery(Charla.class).field("nombre").equal(nombrec);
        List<Estudiante> alumnos = query1.asList();
        for (Charla delva : charlitas)
        {
            if (delva.getNombre().equals(nombrec))
            {
                for(Estudiante alumno : alumnos)
                {
                    if(alumno.getNombre().equals(carnet))
                    {
                        
                        UpdateOperations update = ds.createUpdateOperations(Charla.class).set("asistentes", usuarioA.agregarAsistentes(nombrec, carnet)); 
                        ds.update(query2, update, false);
                        UpdateOperations upd1 = ds.createUpdateOperations(Estudiante.class).add("charlasEst", delva);
                        ds.update(query1, upd1,false);  
                    }
                }
                
            }
        }
    }
}
