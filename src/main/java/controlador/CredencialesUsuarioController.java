package controlador;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;

import BDD.Peregrino_BDD;
import DAO.CredencialesUsuarioDAO;
import Modelo.CredencialesUsuario;
import utilidades.Utilidades;
import vista.Menus;

public class CredencialesUsuarioController {

	public static Connection con;
	public static Peregrino_BDD peregrinosBDD=Peregrino_BDD.Conex_BDD(con);
	
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
	
	//creo que con esto  tengo todo el login (controlar esto en un do while en el futuro cuando se incluyan los menus)
	public static CredencialesUsuario ValidarCredencialesLogin(Collection<CredencialesUsuario> lista,CredencialesUsuario cred) {
		CredencialesUsuario credenciales_completas=new CredencialesUsuario();
		boolean val=false;
		for(CredencialesUsuario u:lista) {
			if(u.getNombre().equalsIgnoreCase(cred.getNombre())&&u.getClave().equalsIgnoreCase(cred.getClave())){
				credenciales_completas=u;
				System.out.println("bienvenido: "+credenciales_completas.getNombre()+ "es usted un: "+credenciales_completas.getTipo_usuario().toString());
				val =true;
				break;
			}
			else {
				System.out.println("no validas");
				val =true;
			}
		}			
		return credenciales_completas;
	}
	
	public static CredencialesUsuario Login() {
		CredencialesUsuario cred =new CredencialesUsuario();
		System.out.println("introduzca si nombre de login");
		String nombre = Utilidades.LeerCadena();
		System.out.println("introduzca su contraseña");
		String clave = Utilidades.LeerCadena();
		cred.setNombre(nombre);
		cred.setClave(clave);
		return cred;
	}
	
	public static boolean login_completo() {
		boolean val=false;
		Collection<CredencialesUsuario> lista=new ArrayList<CredencialesUsuario>();
		CredencialesUsuario cred=new CredencialesUsuario();
		CredencialesUsuarioDAO datos_cred=CredencialesUsuarioDAO.Conexion_CredencialesUsuario(peregrinosBDD);
		lista=datos_cred.buscarTodos();
		cred=CredencialesUsuarioController.Login();
		cred=CredencialesUsuarioController.ValidarCredencialesLogin(lista, cred);
		if(cred.getTipo_usuario()!=null) {
			System.out.println("bienvenido: "+cred.getNombre()+" accedera como: "+cred.getTipo_usuario().getTipoDeUsuario());
			//importante llamada al dato
			Menus.MenuLogin(cred);
			val =true;
			}
		else {
			System.out.println("no son validas intente de nuevo");
		}
		return val;
	}
}
