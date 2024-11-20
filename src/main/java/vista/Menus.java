package vista;

import java.util.Scanner;

import Modelo.*;
import utilidades.Utilidades;



public class Menus {
	public static void MenuPrincipalInvitado() {
		Peregrino peregrino=new Peregrino();//es esto necesario ? 
		boolean val =true;
		int elecc = -1;
		Scanner scan =new Scanner(System.in);
		do {
					
		do {
			System.out.println("Bienvenido invitado, que desea hacer en el sistema: ");
			System.out.println("1 - registrarse como peregrino");
			System.out.println("2 - Logearse");
			System.out.println("0 - salir del programa");
			System.out.println("-------------------------");			
			
			elecc=scan.nextInt();
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
			
			//meter la llamada al nuevo peregrino
			break;
		case 2:
			//meter la llamada al login
			break;
		case 0:
			System.out.println("seguro que quiere salir del programa?");
			val= Utilidades.leerBoolean();
			if(val) {
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
				} while (val);
	}
	//hay que hacer que este metodo tenga los correspondientes menus
	public static void MenuLogin(CredencialesUsuario cred,Peregrino p) {
		Usuarios usu = cred.getTipo_usuario();
		//el propio switch verifica que el usuario cuenta con el tipo correcto para acceder a sus funcionalidades
		switch (usu) {
		case Invitado:
			//por aqui nunca pasa , solo pasa por aqui antes de crear un  perfil, pero por orden de codig lo incluyo
			break;
		case Responsable_Parada:
			//Menu_ResponsableParada();
			break;
		case Peregrino:
			//MenuPeregrino(p);
			break;
		case Administrador_General:
			//Menu_AdminGeneral();
			break;			
		default:
			System.out.println("algo no ha ido como se esperaba");
			break;
		}
	}
}
