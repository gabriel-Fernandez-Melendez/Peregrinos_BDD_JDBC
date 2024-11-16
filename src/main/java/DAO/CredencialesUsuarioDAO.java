package DAO;

import java.sql.Connection;
import java.util.Collection;

import Modelo.CredencialesUsuario;

public class CredencialesUsuarioDAO implements operacionesCRUD<CredencialesUsuario> {

	public static CredencialesUsuarioDAO Datos_CredencialesUsuario;
	public Connection con;
	
	private CredencialesUsuarioDAO(Connection con) {
		if(Datos_CredencialesUsuario==null){
			this.con=con;
		}
	}
	
	public static CredencialesUsuarioDAO Conexion_CredencialesUsuario(Connection con){
		if(Datos_CredencialesUsuario==null) {
			Datos_CredencialesUsuario=new CredencialesUsuarioDAO(con);
		}
		return Datos_CredencialesUsuario;
	}
	@Override
	public boolean insertarConID(CredencialesUsuario elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long insertarSinID(CredencialesUsuario elemento) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CredencialesUsuario buscarPorID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<CredencialesUsuario> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modificar(CredencialesUsuario elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(CredencialesUsuario elemento) {
		// TODO Auto-generated method stub
		return false;
	}

}
