package vista;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import BDD.Peregrino_BDD;
import DAO.CredencialesUsuarioDAO;
import DAO.ParadaDAO;
import DAO.PeregrinoDAO;
import Modelo.*;
import controlador.CarnetController;
import controlador.CredencialesUsuarioController;
import controlador.EstanciaController;
import controlador.ParadaController;
import controlador.PeregrinoController;
import utilidades.Utilidades;



public class Menus {
	//estos campos estaticos donde haga falta conectarse a la base de datos para acceder desde todos los metodos sin  hacer mas llamadas al mismo objeto singelizado
	public static Connection con;
	public static Peregrino_BDD peregrinosBDD=Peregrino_BDD.Conex_BDD(con);
	//hay alguno de estos metodos que tendre que meter retirn para llevar el control de las funciones de sus usuarios y almacenar los cambios en base a su id 
	public static void MenuPrincipalInvitado() {
		Peregrino peregrino=new Peregrino();//es esto necesario ? 
		boolean val =true;
		int elecc = -1;
		
					
		do {
			System.out.println("Bienvenido invitado, que desea hacer en el sistema: ");
			System.out.println("1 - registrarse como peregrino");
			System.out.println("2 - Logearse");
			System.out.println("0 - salir del programa");
			System.out.println("-------------------------");			
			
			elecc=Utilidades.LeerNumero();
			if(elecc<0 || elecc>2) {
				System.out.println("el numero no es valido, introduzca un numero valido");
				val=false;
			}	
			else {
				val=true;
			}
		} while (!val);
		val=false;
		switch (elecc) {
		case 1:
			Peregrino p=new Peregrino();
			p=PeregrinoController.NuevoPeregrino();
			PeregrinoDAO per=PeregrinoDAO.Conexion_Peregrino(peregrinosBDD);
			per.insertarSinID(p);
			break;
		case 2:
			val=false;
			do {				
				val=CredencialesUsuarioController.login_completo();
				if(val) {
					val=true;
				}
				else {
					System.out.println("esas no son unas credenciales validas, ingrese credenciales basicas");
					val=false;
				}			
			}while(!val);	
			break;
		case 0:
			System.out.println("seguro que quiere salir del programa?");
			val= Utilidades.leerBoolean();
			if(val) { //esto pa salir del programa
				System.out.println("saliendo del programa");
				System.exit(0);
			}
			else {
				val=false;
			}
		default:
			System.out.println("algo a salido mal , intentelo de nuevo");
			break;
		}
			
	}
	//prueba de push
	//hay que hacer que este metodo tenga los correspondientes menus
	public static void MenuLogin(CredencialesUsuario cred) {
		Usuarios usu = cred.getTipo_usuario();
		//el propio switch verifica que el usuario cuenta con el tipo correcto para acceder a sus funcionalidades
		switch (usu) {
		case Invitado:
			//por aqui nunca pasa , solo pasa por aqui antes de crear un  perfil, pero por orden de codig lo incluyo
			break;
		case Responsable_Parada:
			//meter el !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SELLADO
			Menu_ResponsableParada(cred);
			break;
		case Peregrino:		
			MenuPeregrino(cred);
			break;
		case Administrador_General:
			Menu_AdminGeneral(cred);
			break;			
		default:
			System.out.println("algo no ha ido como se esperaba");
			break;
		}
		
	}
	public static void MenuPeregrino(CredencialesUsuario cred) {
		boolean val =true;
		Scanner scan =new Scanner(System.in);
		int elecc = -1;
		do {
					
		do {
			System.out.println("Bienvenido invitado, que desea hacer en el sistema: ");
			System.out.println("1 - Exportar carnet XML");
			System.out.println("0 - salir");
			System.out.println("-------------------------");			
			scan.reset();			
			elecc=Utilidades.LeerNumero();
			if(elecc<0 || elecc>1) {
				System.out.println("el numero no es valido, introduzca un numero valido");
				val=false;
			}	
			else {
				val=true;
			}
		} while (!val);
		val=false;
		switch (elecc) {
		case 1:
			PeregrinoDAO per=PeregrinoDAO.Conexion_Peregrino(peregrinosBDD);
			Peregrino p = new Peregrino();
			p=per.buscarPorID(cred.getId());
			PeregrinoController.Exportar(p.getId_credenciales().getId());
			System.out.println("Quieres volveral menu principal?");
			val=Utilidades.leerBoolean();
			if(val) {
			MenuPrincipalInvitado();	
			}
			else {
				val=false;
			}	
			break;
		case 0:
			System.out.println("seguro que quiere volver al menu principal?");
			val=Utilidades.leerBoolean();
			if(val) {
				System.out.println("saliendo del programa");
				MenuPrincipalInvitado();
			}
			else {
				val=false;
			}
		default:
			System.out.println("algo a salido mal , intentelo de nuevo");
			break;
		}		
				} while (val);
	}
	
	public static void Menu_ResponsableParada(CredencialesUsuario cred) {
		boolean val =true;
		Scanner scan =new Scanner(System.in);
		int elecc = -1;
		do {
					
		do {
			System.out.println("Bienvenido invitado, que desea hacer en el sistema: ");
			System.out.println("1 - Sellar carnet de peregrino");
			System.out.println("2 - Exportar estancias por fecha");
			System.out.println("0 - salir");
			System.out.println("-------------------------");			
			scan.reset();			
			elecc=Utilidades.LeerNumero();
			if(elecc<0 || elecc>2) {
				System.out.println("el numero no es valido, introduzca un numero valido");
				val=false;
			}	
			else {
				val=true;
			}
		} while (!val);
		//dudas de si esta linea puede romper el programa
		val=false;
		switch (elecc) {
		case 1:
			//aqui va el metodo de sellado!!!!!!!!!
			//correcion: de mostrar los id de los pegrinos que estan en la Base de Datos
			Peregrino peregrino=new Peregrino();
			do {
			System.out.println("escoja el id de uno de los siguientes peregrinos");
			ArrayList<Peregrino> lista = new ArrayList<>();
			PeregrinoDAO per= PeregrinoDAO.Conexion_Peregrino(peregrinosBDD);
			lista=(ArrayList<Peregrino>) per.buscarTodos();
			int i = 1;
			for(Peregrino p : lista) {
				System.out.println(i+" - "+p.toString());
				i++;
			}
			System.out.println("escoja uno de los peregrinos de la lista ingresando su  numero de la izquierda");
			int id_peregrino=Utilidades.LeerNumero();
			if(id_peregrino>=lista.size()+1) {
				System.out.println("el numero ingresado  no es valido intentelo de nuevo");
				val=false;
			}
			else {
				System.out.println("quiere sellar el carner del peregrino: "+lista.get(id_peregrino-1));
				val=Utilidades.leerBoolean();
				peregrino = lista.get(id_peregrino-1);
			}
			} while (!val);
			CarnetController.SellarCarnet(cred,peregrino);
			System.out.println("se ha sellado el carnet");
			System.out.println("quiere volver al menu principal?");
			
			do{
				val=Utilidades.leerBoolean();
			
			if(val) {
				MenuPrincipalInvitado();	
				}
				else {
					Menu_ResponsableParada(cred); //llama de mi metodo , la condicional en el while da igual realmente
				}
			}while(val);
			break;
		case 2:
			ParadaDAO par=ParadaDAO.Conexion_Parada(peregrinosBDD);
			Parada parada = new Parada();
			parada=par.buscarPorID(cred.getId());
			EstanciaController.ExportarEstancias(parada);
			//correccion :  no le decia al usuario que si queria volver al menu principal 
			System.out.println("quiere volver al menu principal del programa?");
			val=Utilidades.leerBoolean();
			if(val) {
			MenuPrincipalInvitado();	
			}
			else {
				//llamada al mismo metodo en caso de que no quiera volver al principal que lo mande a las opcciones del menu del responsable
				Menu_ResponsableParada(cred);
				val=false;
			}		
			break;
		case 0:
			System.out.println("seguro que quiere salir del programa?");
			//meter el validador booleano
			System.out.println("quiere volver al menu principal?");
			val=Utilidades.leerBoolean();
			if(val) {
				MenuPrincipalInvitado();	
				}
				else {
					val=false;
				}
		default:
			System.out.println("algo a salido mal , intentelo de nuevo");
			break;
		}		
				} while (val);
	}
	public static void Menu_AdminGeneral(CredencialesUsuario cred) {
		boolean val =true;
		Scanner scan =new Scanner(System.in);
		int elecc = -1;
		do {
					
		do {
			System.out.println("Bienvenido Administrador general, que desea hacer en el sistema: ");
			System.out.println("1 - registrar parada");
			System.out.println("0 - salir");
			System.out.println("-------------------------");			
			scan.reset();
			
			elecc=scan.nextInt();
			if(elecc<0 || elecc>2) {
				System.out.println("el numero no es valido, introduzca un numero valido");
				val=false;
			}	
			else {
				val=true;
			}
		} while (!val);
		//dudas de si esta linea puede romper el programa
		val=false;
		switch (elecc) {
		case 1:
			//meter aqui la funcion de crear nueva parada 
			
			val=ParadaController.NuevaParada();
			if(val)System.out.println("se añadio con exito");
			System.out.println("Quieres volveral menu principal?");
			val=Utilidades.leerBoolean();
			if(val) {
			MenuPrincipalInvitado();	
			}
			else {
				//Correcion del cierre de sesion cual se indica que no se quiere salir del menu
				System.out.println("se volvera al menu principal del administrador general");
				Menu_AdminGeneral(cred);
			}		
			break;
		case 0:
			System.out.println("seguro que quiere salir del programa?");
			val=Utilidades.leerBoolean();
			if(val) {
				MenuPrincipalInvitado();	
				}
				else {
					//Correcion del cierre de sesion cual se indica que no se quiere salir del menu
					System.out.println("se volvera al menu principal del administrador general");
					Menu_AdminGeneral(cred);
				}
			break;
		default:
			System.out.println("algo a salido mal , intentelo de nuevo");
			break;
		}		
				} while (val);
	}
}
