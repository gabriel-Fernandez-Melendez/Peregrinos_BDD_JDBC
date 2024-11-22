package controlador;

import java.util.Collection;

import Modelo.CredencialesUsuario;

public class CredencialesUsuarioController {

	public static boolean ValidarCredencialesNuevas(Collection<CredencialesUsuario> lista,CredencialesUsuario cred) {
		boolean val=false;
		for(CredencialesUsuario u:lista) {
			if(u.getNombre().equalsIgnoreCase(cred.getNombre())&&u.getClave().equalsIgnoreCase(cred.getClave())){
				System.out.println("estas credenciales ya existen, ingrese otras credenciales");
				val =false;
				break;
			}
			else {
				val =true;
			}
		}			
		return val;
	}
	
	//creo que con esto  tengo todo el login 
	public static boolean ValidarCredencialesLogin(Collection<CredencialesUsuario> lista,CredencialesUsuario cred) {
		boolean val=false;
		for(CredencialesUsuario u:lista) {
			if(u.getNombre().equalsIgnoreCase(cred.getNombre())&&u.getClave().equalsIgnoreCase(cred.getClave())){
				System.out.println("bienvenido: "+cred.getNombre()+ "es usted un: "+cred.getTipo_usuario().toString());
				val =true;
				break;
			}
			else {
				val =true;
			}
		}			
		return val;
	}
}
