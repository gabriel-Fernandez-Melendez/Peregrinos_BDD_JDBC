package controlador;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import BDD.Peregrino_BDD;
import DAO.CredencialesUsuarioDAO;
import DAO.ParadaDAO;
import Modelo.CredencialesUsuario;
import Modelo.Parada;
import Modelo.Usuarios;
import utilidades.Utilidades;



public class ParadaController {

	public  static Connection con=null;
	private static Peregrino_BDD conex;
	
	//imagino que el metodo de validar el usuario es el mismo  que la entraga anterior por que comvierto el string en enum
	//de momento n o pego  aqui
	public static boolean validar_usuario_Administrador(CredencialesUsuario cred) {
		boolean val =true;
		if(cred.getTipo_usuario()==Modelo.Usuarios.Administrador_General) {
			System.out.println("es usted el administrador: "+cred.getNombre());
				val=true;		
		}
		else {
			System.out.println("usted no es un administrador de parada ,ingrese las credenciales de un administrador");
			val=false;
		}
		return val;
	}
	
	//este metodo solo sera accesible para el administrador general , asi que dentro del mismo  no hay que controlarlo
		public static boolean NuevaParada() {
			//la declaracion de las dao al principio de la funcion para que sea accesible fuera del do while donde se recojen datos
			CredencialesUsuarioDAO credenciales=CredencialesUsuarioDAO.Conexion_CredencialesUsuario(conex);
			ParadaDAO par=ParadaDAO.Conexion_Parada(conex);
			boolean val= false;
			Parada parada =new Parada();
			CredencialesUsuario responsable = new CredencialesUsuario();
			//creacion del scaner para usar en el nombre de la parada y del encargado de la parada
			Scanner scan=new Scanner(System.in);
			do {	
			System.out.println("Usted esta creando una  nueva parada");	
			//Correcion: debe poder aceptar espacios en blanco asi que no hare uso del metodo que hay en utils si no directamente del Scanner
			System.out.println("inserte el nombre de la parada");
			String nombre_parada = scan.nextLine(); //correcion del campo para que acepte espacios en blanco
			if(nombre_parada==null) {
				System.out.println("ningun campo puede estar vacio durante el proceso, intentelo de nuevo");
				val=false;
				break;
			}
			parada.setNombre(nombre_parada);
			System.out.println("inserte el caracter de la region donde esta la parada");
			String regionString = Utilidades.LeerCadena();		
			if(regionString.length()>1) {
				System.out.println("la region no puede tener mas de un caracter , intentelo de nuevo");
				val=false;
				break;
			}
			char region=regionString.charAt(0);
			parada.setRegion(region);
			System.out.println("introduzca el nombre del encargado de parada");
			String nombre_completo = scan.nextLine(); //correcion del campo para que acepte espacios en blanco
			if(nombre_completo==null) {
				System.out.println("ningun campo puede estar vacio durante el proceso, intentelo de nuevo");
				val=false;
				break;
			}
			parada.setResponsable_parada(nombre_completo);
			System.out.println("ahora introduzca las credenciales del que sera responsable de la parada");
			System.out.println("introduzca el nombre del login: ");
			String nombre_responsable = Utilidades.LeerCadena();
			if(nombre_responsable==null) {
				System.out.println("ningun campo puede estar vacio durante el proceso, intentelo de nuevo");
				val=false;
				break;
			}
			responsable.setNombre(nombre_responsable);
			System.out.println("introduzca la contraseña: ");
			String contraseña_responsable = Utilidades.LeerCadena();
			if(contraseña_responsable==null) {
				System.out.println("ningun campo puede estar vacio durante el proceso, intentelo de nuevo");
				val=false;
				break;
			}
			 responsable.setClave(contraseña_responsable);						
			//validamos el usuario
			 try {
				Peregrino_BDD.Conex_BDD(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 Collection<CredencialesUsuario> usuarios =new ArrayList<CredencialesUsuario>();
			 Collection<Parada> paradas =new ArrayList<Parada>();
			 
			usuarios =credenciales.buscarTodos();
			val=CredencialesUsuarioController.ValidarCredencialesNuevas(usuarios, responsable);
			if(val) {
				System.out.println("las credenciales introducidas son validas");
			}
			paradas=par.buscarTodos();
			val=ValidadorParadas(parada, paradas);
			if(val) {
				System.out.println("la parada introducida es valida");
			}
			} while (!val);
			//lo esporto a los ficheros desde el propio metodo(Meter las DAO)
			if(val) {
				
		
			System.out.println("se van a exportar los datos introducidos a la base de datos");
			responsable.setTipo_usuario(Usuarios.Responsable_Parada);
			long num=credenciales.insertarSinID(responsable);
			//una nueva coleccion por que la otra esta dentro del bucle y quiero las credenciales que acaban de entrar
			Collection<CredencialesUsuario> usuarios =new ArrayList<CredencialesUsuario>();
			usuarios=credenciales.buscarTodos();
			for(CredencialesUsuario c:usuarios) {
				if(responsable.getNombre().equalsIgnoreCase(c.getNombre())&&responsable.getClave().equalsIgnoreCase(c.getClave())) {
					responsable.setId(c.getId());
					System.out.println("se asigno el id");
				}
			}
			parada.setId_credenciales(responsable.getId());
			long num2=par.InsertarParada(parada,responsable);
			System.out.println(num);
			System.out.println(num2);
			}	
			return true;
				
		}
		
		public static boolean ValidadorParadas(Parada p,Collection<Parada> paradas) {
			boolean val=false;
			for(Parada par:paradas) {
				if(par.getNombre().equalsIgnoreCase(p.getNombre())&&par.getRegion()==p.getRegion()) {
					System.out.println("los datos de esta parada ya estan registrados");
					val=false;
					break;
				}
				else {
					val=true;
				}
				}
			return val;
		}
}
