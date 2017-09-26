/** 
@author Ana Lucia Hernandez 17138
Cindy Antillon 14581
Andrea Arguello 17801
@proposito clase que hace toda la interaccion con el usuario. Clase Principal.
@since 04/08/2017
PProyecto.java
*/
import static java.lang.System.*;
import java.util.ArrayList;
import java.util.Scanner;


public class PProyecto
{
	public static void main(String[] args)
	{
		Estudiante estudiante;
		Administrador administrador;
		Charla delva; 
		ArrayList<Estudiante> estudiantes;
		int mas;

		estudiantes = new ArrayList<Estudiante>();

		Scanner teclado = new Scanner(System.in);
		out.println("Hola! Bienvenido a tu servidor de Horas Delva. ");
		int control =0;
		while (control ==0)
		{
			out.println("Seleccione el tipo de usuario: \n\t1. Crear un nuevo usuario. \n\t2. Administrador.\n\t3. Estudiante. \n\t4. Salir");
			int accion = teclado.nextInt();
			teclado.nextLine();
			switch(accion)
			{
				case 1: 
					out.println("Que tipo de usuario desea crear? \n 1. Administrador \n 2. Estudiante");
					int crear = teclado.nextInt();
					teclado.nextLine();
					switch (crear)
					{
						case 1: 
							out.println("Ingrese el nombre de usuario: (ejemplo: lop90811)");
							String usuario = teclado.nextLine();
							out.println("Ingrese la contrasena: ");
							String contrasena = teclado.nextLine();
							estudiante = new Estudiante(usuario, contrasena);
							estudiantes.add(estudiante);
							break;
						case 2:
							out.println("Ingrese el nombre de usuario: (ejemplo: lop90811)");
							String usuarioa = teclado.nextLine();
							out.println("Ingrese la contrasena: ");
							String contrasenaa = teclado.nextLine();
							administrador = new Administrador(usuarioa, contrasenaa);
							administrador.alumnos = estudiantes;
							break;

					}
					out.println("\nDesea hacer algo mas? \n 1. Si \n 2. No."); // esta opcion es para que el usuario decida si quiere
					//hacer algo mas en el programa o si se quiere salir
					mas = teclado.nextInt();
					if (mas == 1) {control = 0;}
					if (mas == 2) {control = 1;}
					break;
				case 2:
					out.println("Ingrese su nombre de usuario: ");
					String usuarioA = teclado.nextLine();
					out.println("Ingrese su contrasena: ");
					String contrasenA = teclado.nextLine();
					administrador = new Administrador(usuarioA, contrasenA); // crea un nuevo objeto administrador
					out.println("Bienvenido, Administrador.");
					int c =0;
					while (c==0)
					{
						out.println("Que desea hacer?");
						out.println("\t1. Agregar una charla. \n\t2. Borrar charla.\n\t3. Ver charlas. \n\t4. Ingresar asistencia a charlas existentes. \n\t5. Cerrar sesion.");
						int opciona = teclado.nextInt();
						teclado.nextLine();
						switch(opciona)
						{
							case 1:
								out.println("Ingrese el nombre de la charla: ");
								String nombrec = teclado.nextLine();
								out.println("Ingrese la fecha de la charla: ");
								String fecha = teclado.nextLine();
								out.println("Ingrese la hora de la charla: ");
								String hora = teclado.nextLine();
								out.println("Ingrese el lugar (salon) de la charla: ");
								String salon = teclado.nextLine();
								delva = new Charla(nombrec, fecha, hora, salon);
								administrador.agregarCharla(delva);
								out.println("\nDesea hacer algo mas? \n 1. Si \n 2. No."); // esta opcion es para que el usuario decida si quiere
								//hacer algo mas en el programa o si se quiere salir
								mas = teclado.nextInt();
								if (mas == 1) {c = 0;}
								if (mas == 2) {c = 1;}
								break;
							case 2:
								out.println("Ingrese el nombre de la charla que desea borrar: ");
								String busqueda = teclado.nextLine();
								administrador.eliminarCharla(busqueda);
								out.println("\nDesea hacer algo mas? \n 1. Si \n 2. No."); // esta opcion es para que el usuario decida si quiere
								//hacer algo mas en el programa o si se quiere salir
								mas = teclado.nextInt();
								if (mas == 1) {c = 0;}
								if (mas == 2) {c = 1;}
								break;
							case 3:
								ArrayList<Charla> charlas = administrador.mostrarCharlas();
								out.println(charlas);
								out.println("\nDesea hacer algo mas? \n 1. Si \n 2. No."); // esta opcion es para que el usuario decida si quiere
								//hacer algo mas en el programa o si se quiere salir
								mas = teclado.nextInt();
								if (mas == 1) {c = 0;}
								if (mas == 2) {c = 1;}
								break;
							case 4: 
								out.println("Ingrese el nombre de la charla a la que desea agregar la asistencia: ");
								String bcharla = teclado.nextLine();
								int ciclo = 0;
								out.println("Ingrese los carnets de los estudiantes que asistieron: \n *********** CUANDO TERMINE DE INGRESAR LOS CARNETS, INGRESE 0. ***********");
								while (ciclo ==0);
								{
									String carnet = teclado.nextLine();
									if (!carnet.equals("0"))
									{
										administrador.agregarAsistentes(bcharla, carnet);
									}
									else
									{
										ciclo =1;
									}
								}
								out.println("\nDesea hacer algo mas? \n 1. Si \n 2. No."); // esta opcion es para que el usuario decida si quiere
								//hacer algo mas en el programa o si se quiere salir
								mas = teclado.nextInt();
								if (mas == 1) {c = 0;}
								if (mas == 2) {c = 1;}
								break;
							case 5:
								c=1;
								break;
						}
					}
					break;
				case 3: 
					out.println("Ingrese su nombre de usuario: ");
					String usuarioE = teclado.nextLine();
					out.println("Ingrese su contrasena: ");
					String contrasenaE = teclado.nextLine();
					estudiante = new Estudiante(usuarioE, contrasenaE);
					out.println("Bienvenido, Estudiante.");
					int c2 = 0;
					while (c2==0)
					{
						out.println("Que desea hacer?");
						out.println("\t1. Ver mis horas cumplidas. \n\t2. Ver futuras charlas. \n\t3. Cerrar sesion.");
						int opcione = teclado.nextInt();
						teclado.nextLine();
						switch(opcione)
						{
							case 1:
								int horas = estudiante.getHoras();
								out.println("Las horas DELVA que usted ha cumplido son: "+horas);
								out.println("\nDesea hacer algo mas? \n 1. Si \n 2. No."); // esta opcion es para que el usuario decida si quiere
								//hacer algo mas en el programa o si se quiere salir
								mas = teclado.nextInt();
								if (mas == 1) {c2 = 0;}
								if (mas == 2) {c2 = 1;}

								break;
							case 2:
								ArrayList<Charla> fcharlas = estudiante.getCEst();
								out.println("Las futuras charlas son: \n" + fcharlas);
								out.println("\nDesea hacer algo mas? \n 1. Si \n 2. No."); // esta opcion es para que el usuario decida si quiere
								//hacer algo mas en el programa o si se quiere salir
								mas = teclado.nextInt();
								if (mas == 1) {c2 = 0;}
								if (mas == 2) {c2 = 1;}
								break;
							case 3:
								c2 =1;
								break;
						}
					}
					break;
				case 4:
					control=1;
					break;
			}	
		}
	}
}
