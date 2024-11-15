package DAO;

import java.util.Collection;

import Modelo.CredencialesUsuario;

public class CredencialesUsuarioDAO implements operacionesCRUD<CredencialesUsuario> {

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
