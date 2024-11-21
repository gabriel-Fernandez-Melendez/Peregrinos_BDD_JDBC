package controlador;

import java.util.Collection;

import Modelo.CredencialesUsuario;

public class CredencialesUsuarioController {

	public static boolean ValidarCredenciales(Collection<CredencialesUsuario> lista,CredencialesUsuario cred) {
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
}
