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

public class BaseDeDatos {
    private Datastore ds;
    private List<Administrador> administradores;
    private List<Estudiante> estudiantes;
    
    public BaseDeDatos()
    {
        MongoClient mongo = new MongoClient();
        Morphia morphia = new Morphia();
        morphia.map(Administrador.class).map(Estudiante.class).map(Charla.class).map(Registro.class);
        ds = morphia.createDatastore(mongo, "HorasDelva");
    }
    public void crearUsuario(String tipoUsuario, String nombreUsuario, String contrasena)
    {
        Query<Administrador> query = ds.createQuery(Administrador.class);
        List<Administrador> busqueda = query.asList();
        administradores = busqueda;
        
        if(tipoUsuario.equals("Administrador"))
        {
            Administrador nuevo = new Administrador(nombreUsuario.toUpperCase(), contrasena);
            //nuevo.setCharlas(); //para que el nuevo administrador tenga acceso a todas las charlas previamente ingresadas
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
    
}
