/** 
@author Ana Lucia Hernandez 17138
Cindy Antillon 14581
Andrea Arguello 17801
@proposito  modela la informacion del Administrador que controla toda la informacion de las charlas DELVA
@since 04/08/2017
Administrador.java
*/
import java.util.ArrayList;

public class Administrador
{
	private String usuario;
	private String contrasena;
	protected ArrayList<Charla> charlasAdmin;
	private int index;
	protected ArrayList<Estudiante> alumnos;
	private Charla delva;
	
	/**
	 Constructor de objetos para la clase Administrador.
	 @param usuario
	 @param contrasena
	*/
	public Administrador(String usuario, String contrasena)
	{
		this.usuario = usuario;
		this.contrasena = contrasena;
	}
	/**
	 Agregar charla a las diferentes ArrayList que se crearon.
	
	 @param delva 	a agregar.
	*/
	public void agregarCharla(Charla delva)
	{
		charlasAdmin.add(delva);
		for (Estudiante estudiante: alumnos)
		{
			estudiante.getCEst().add(delva);
		}
	}
	/**
	Eliminar una charla de las que estan en la arrayList.
	
	@param busqueda 	a eliminar.
	*/
	public void eliminarCharla(String busqueda)
	{
		for (Charla delva : charlasAdmin)
			{
				if (delva.getNombre().equals(busqueda))
				{
					index = charlasAdmin.indexOf(delva);
				}
			}
		charlasAdmin.remove(index); //elimina la charla que tiene el index de la charla que quiere eliminar
	}
	/**
	 Agrega a los asistentes a las charlas.
	
	 @param bcharla
	 @param carnet
	*/
	public void agregarAsistentes(String bcharla, String carnet) 
	{ //bcharla representa el nombre de la charla a la que quiere ingresarle la asistencia
		for (Charla delva: charlasAdmin)
		{
			if (delva.getNombre().equals(bcharla))
			{
				for (Estudiante estudiante: alumnos)
				{
					if (estudiante.getNombre().contains(carnet))
					{
						estudiante.horasDone += 1; //agregarle al estudiante las horas que cumplio
						delva.getAsistentes().add(estudiante); //para agregar a la lista de asistentes al estudiante
					}
				}
			}
		}
	}
	/**
	 Charlas que se encuentran en la ArrayList.
	
	 @return charlasAdmin 	charlas de las lista de administradores.
	*/
	public ArrayList<Charla> mostrarCharlas()
	{
		return charlasAdmin;
	}
	/**
	 Alumnos que se encuentran en la ArrayList.
	
	 @return alumnos 	que estan en la lista.
	*/
	public ArrayList<Estudiante> getAlumnos()
	{
		return alumnos;
	}
	
}
