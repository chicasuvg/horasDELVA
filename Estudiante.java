/** 
@author Ana Lucia Hernandez. 17138.
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


	public Estudiante(String usuario, String contrasena)
	{
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.horasDone = 0;
	}
	public String getNombre()
	{
		return usuario;
	}
	public int getHoras()
	{
		return horasDone;
	}
	public ArrayList<Charla> getCEst()
	{
		return charlasEst;
	}
}