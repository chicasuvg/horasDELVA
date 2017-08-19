/** 
@author Ana Lucia Hernandez. 17138.
@proposito  modela la informacion de una charla DELVA
@since 04/08/2017
Charla.java
*/

import java.util.ArrayList;

public class Charla
{
	private String nombre;
	private String lugar;
	private String hora;
	private String fecha;
	private ArrayList<Estudiante> asistentes;

	public Charla(String nombre, String salon, String hora, String fecha){
		this.nombre = nombre;
		this.lugar = salon;
		this.hora = hora;
		this.fecha = fecha;
		asistentes = new ArrayList<Estudiante>();
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public ArrayList<Estudiante> getAsistentes(){
		return asistentes;
	}
	
}
