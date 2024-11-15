package DAO;

import java.sql.Connection;
import java.util.Collection;

import BDD.Peregrino_BDD;
import Modelo.Peregrino;

//esta clase tiene singleton
public class PeregrinoDAO implements operacionesCRUD<Peregrino>{

	private static PeregrinoDAO Datos_Peregrino;
	public Connection con;
	
	private PeregrinoDAO(Connection con) {
		this.con=con;
	}
	
	public static PeregrinoDAO Conexion_Peregrino(Connection con) {	
		if(Datos_Peregrino==null) {
			Datos_Peregrino= new PeregrinoDAO(con);
		}
		return Datos_Peregrino;
	}
	@Override
	public boolean insertarConID(Peregrino elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long insertarSinID(Peregrino elemento) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Peregrino buscarPorID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Peregrino> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modificar(Peregrino elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Peregrino elemento) {
		// TODO Auto-generated method stub
		return false;
	}

}
