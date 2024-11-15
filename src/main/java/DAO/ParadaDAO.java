package DAO;

import java.util.Collection;

import Modelo.Parada;

public class ParadaDAO implements operacionesCRUD<Parada>{

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
