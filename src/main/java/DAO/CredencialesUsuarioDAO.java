package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import BDD.Peregrino_BDD;
import Modelo.CredencialesUsuario;
import Modelo.Usuarios;


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
		Connection co=null;
		if (this.con == null  ) {			
			this.con = Peregrino_BDD.Conex_BDD(co);
		}			
		String insert="INSERT INTO credenciales_usuario( nombre, clave, tipo_perfil) VALUES (?,?,?)";
		try {
			//esta linea puede resultar confusa
			PreparedStatement pstmt = con.conex_BDD.prepareStatement(insert);
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
			Peregrino_BDD.cerrarConexion(co);
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
		//hay que declarar esto fuera del of para cerrarlo correctamente
		Connection co=null;
		List<CredencialesUsuario> lista=new ArrayList<>();
		
		String consulta ="select * from credenciales_usuario";
		if (this.con == null  ) {
			
			this.con = Peregrino_BDD.Conex_BDD(co);
		}
		try {
			PreparedStatement pstmt = con.conex_BDD.prepareStatement(consulta);
			ResultSet resultado = pstmt.executeQuery();
			while(resultado.next()) {
				CredencialesUsuario cred =new CredencialesUsuario();
				long id = resultado.getLong("id");
				String nombre = resultado.getNString("nombre");
				String clave = resultado.getNString("clave");
				String perfil = resultado.getNString("tipo_perfil");
				for(Usuarios u:Usuarios.values()) {
					if(perfil.equalsIgnoreCase(u.getTipoDeUsuario())) {
						cred.setTipo_usuario(u);
					}
				}
				cred.setId(id);
				cred.setNombre(nombre);
				cred.setClave(clave);				
				lista.add(cred);
				//BORRAR ESTA LINEA AL TERMINAR LAS PRUEBAS
				System.out.println(cred.toString());
			}
			Peregrino_BDD.cerrarConexion(co);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
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
