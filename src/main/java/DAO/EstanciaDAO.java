package DAO;

import java.sql.Connection;
import java.util.Collection;

import Modelo.Estancia;

public class EstanciaDAO implements operacionesCRUD<Estancia>{

	private static EstanciaDAO Datos_Estancia;
	public Connection con;
	
	private EstanciaDAO(Connection con){
		if(Datos_Estancia==null){
			this.con=con;
		}
	}
	
	public static EstanciaDAO Conexion_Estancia(Connection con){
		if(Datos_Estancia==null){
			Datos_Estancia= new EstanciaDAO(con);
		}
		return Datos_Estancia;
	}
	
	@Override
	public boolean insertarConID(Estancia elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long insertarSinID(Estancia elemento) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Estancia buscarPorID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Estancia> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modificar(Estancia elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Estancia elemento) {
		// TODO Auto-generated method stub
		return false;
	}

}
