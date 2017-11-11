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
    private Query<Estudiante> query1;
    private Query<Charla> query2;
    private boolean admin;
    private boolean est;
    public Estudiante usuarioE;
    public Administrador usuarioA;
    
    public BaseDeDatos()
    {
        MongoClient mongo = new MongoClient();
        Morphia morphia = new Morphia();
        morphia.map(Administrador.class).map(Estudiante.class).map(Charla.class).map(Registro.class);
        ds = morphia.createDatastore(mongo, "HorasDelva");
        query = ds.createQuery(Administrador.class);
        List<Administrador> busqueda = query.asList();
        administradores = busqueda;
        query1 = ds.createQuery(Estudiante.class);
        List<Estudiante> busqueda1 = query1.asList();
        estudiantes = busqueda1;
        query2 = ds.createQuery(Charla.class);
        List<Charla> busqueda2 = query2.asList();
        charlas = busqueda2;
        
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
    public boolean crearUsuario(String tipoUsuario, String nombreUsuario, String contrasena)
    {
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
            }
        }
        return puedeIngresar;
    }
    public boolean ingresarCharla(String nombre, String salon, String time, String fecha, int duracion)
    {
        boolean existeCharla = false;
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
    
}
