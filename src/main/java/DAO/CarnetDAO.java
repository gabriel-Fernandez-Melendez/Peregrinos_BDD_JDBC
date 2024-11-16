package DAO;

import java.sql.Connection;
import java.util.Collection;

import Modelo.Carnet;

public class CarnetDAO implements operacionesCRUD<Carnet>{

	public static CarnetDAO Datos_Carnet;
	public Connection con;
	
	private CarnetDAO(Connection con) {
		this.con=con;
	}
	
	public static CarnetDAO Conexion_Peregrino(Connection con) {
		if(Datos_Carnet==null) {
			Datos_Carnet=new CarnetDAO(con);
		}
		return Datos_Carnet;
	}
	@Override
	public boolean insertarConID(Carnet elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long insertarSinID(Carnet elemento) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Carnet buscarPorID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Carnet> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modificar(Carnet elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Carnet elemento) {
		// TODO Auto-generated method stub
		return false;
	}

}
