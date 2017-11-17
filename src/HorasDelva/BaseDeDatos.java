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
        morphia.map(Administrador.class).map(Estudiante.class).map(Charla.class).map(Registro.class);
        ds = morphia.createDatastore(mongo, "HorasDelva"); // Base Datos
        query = ds.createQuery(Administrador.class);
        List<Administrador> busqueda = query.asList();
        administradores = busqueda;
        query1 = ds.createQuery(Estudiante.class);
        List<Estudiante> busqueda1 = query1.asList();
        estudiantes = busqueda1;
        query2 = ds.createQuery(Charla.class);
        charlas = query2.asList();
    }
    public boolean getEst()
    {
        return est;
    }
    public boolean getAdmin()
    {
        return admin;
    }
    public Estudiante getEstudiante()
    {
        return usuarioE;
    }
    public Administrador getAdministrador()
    {
        return usuarioA;
    }
    public ArrayList<Charla> getCharlas()
    {
        ArrayList<Charla> delvas = new ArrayList();
        query2 = ds.createQuery(Charla.class);
        charlas = query2.asList();
        for (Charla delva: charlas) { delvas.add(delva); }
        return delvas;
    }
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
            }
        }
        return existe;
    }
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
                
            }
            else{existe=false;}
        }
        return existe;
    }
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
