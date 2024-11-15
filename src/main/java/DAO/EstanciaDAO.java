package DAO;

import java.util.Collection;

import Modelo.Estancia;

public class EstanciaDAO implements operacionesCRUD<Estancia>{

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
