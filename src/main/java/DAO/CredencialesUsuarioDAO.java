package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import BDD.Peregrino_BDD;
import Modelo.CredencialesUsuario;


public class CredencialesUsuarioDAO implements operacionesCRUD<CredencialesUsuario> {

	public static CredencialesUsuarioDAO Datos_CredencialesUsuario;
	public Peregrino_BDD con;
	
	private CredencialesUsuarioDAO(Peregrino_BDD con) {
		if(Datos_CredencialesUsuario==null){
			this.con=con;
		}
	}
	
	public static CredencialesUsuarioDAO Conexion_CredencialesUsuario(Peregrino_BDD con){
		if(Datos_CredencialesUsuario==null) {
			Connection c=null;
			Datos_CredencialesUsuario=new CredencialesUsuarioDAO(Peregrino_BDD.Conex_BDD(c));
		}
		return Datos_CredencialesUsuario;
	}
	//metodo para insertar las credenciales de un usuario
	@Override
	public boolean insertarConID(CredencialesUsuario c) {
		boolean val =false;
		if (this.con == null  ) {
			Connection co=null;
			this.con = Peregrino_BDD.Conex_BDD(co);
		}			
		String insert="INSERT INTO credenciales_usuario( nombre, clave, tipo_perfil) VALUES (?,?,?)";
		try {
			//esta linea puede resultar confusa
			PreparedStatement pstmt = con.con.prepareStatement(insert);
			pstmt.setString(1, c.getNombre());
			pstmt.setString(2, c.getClave());
			pstmt.setString(3, c.getTipo_usuario().getTipoDeUsuario());
			int resultadoInsercion = pstmt.executeUpdate();
			if (resultadoInsercion == 1) {
				System.out.println("se han almacenado las credenciales del usuario");
				val=true;
			}
			else {
				System.out.println("hubo algun error al momento de la insercion");
				val=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return val;
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
