/** 
@author Ana Lucia Hernandez. 17138.
Cindy Antillon. 14581.
Andrea Arguello. 17801.
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

	/**Guarda toda la informacion de una nueva charla
     * @param nombre Nombre de la charla
	 * @param salon Lugar de la charla
	 * @param hora Hora de la charla
	 * @param fecha Fecha de la charla
     */
	public Charla(String nombre, String salon, String hora, String fecha)
	{
		this.nombre = nombre;
		this.lugar = salon;
		this.hora = hora;
		this.fecha = fecha;
	}
	
	/**
	 * @return nombre
     */
	public String getNombre()
	{
		return nombre;
	}
	
	/**
	 * @return asistentes
     */
	public ArrayList<Estudiante> getAsistentes()
	{
		return asistentes;
	}
}
