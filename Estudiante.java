/** 
@author Ana Lucia Hernandez. 17138.
Cindy Antillon. 14581.
Andrea Arguello. 17801.
@proposito  modela la informacion del estudiante UVG que - potencialmente - asistira a una charla
@since 04/08/2017
Estudiante.java
*/

import java.util.ArrayList;

public class Estudiante
{
	private String usuario;
	private String contrasena;
	protected int horasDone;
	private long carnet;
	private ArrayList<Charla> charlasEst;
	
	/**
	 Constructor para la clase de Estudiante
	
	 @param usuario
	 @param constrasena
	*/

	public Estudiante(String usuario, String contrasena)
	{
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.horasDone = 0;
	}
	/**
	consultar el usuario
	@return usuario
	*/
	public String getNombre()
	{
		return usuario;
	}
	/**
	consultar las horas delva hechas
	@return horasDone
	*/
	public int getHoras()
	{
		return horasDone;
	}
	/**
	consultar las charlas que se encuentran en el ArrayList de los estudiantes
	@return charlasEst
	*/
	public ArrayList<Charla> getCEst()
	{
		return charlasEst;
	}
}
