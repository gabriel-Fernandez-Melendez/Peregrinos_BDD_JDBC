package DAO;

import java.sql.Connection;
import java.util.Collection;

import Modelo.Parada;

public class ParadaDAO implements operacionesCRUD<Parada>{

	private static ParadaDAO Datos_Parada;
	public Connection con;
	
	private ParadaDAO(Connection con) {
		this.con=con;
	}
	
	public static ParadaDAO Conexion_Parada(Connection con) {
		if(Datos_Parada==null) {
			Datos_Parada=new ParadaDAO(con);
		}
		return Datos_Parada;
	}
	
	@Override
	public boolean insertarConID(Parada elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long insertarSinID(Parada elemento) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Parada buscarPorID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Parada> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modificar(Parada elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Parada elemento) {
		// TODO Auto-generated method stub
		return false;
	}

}
