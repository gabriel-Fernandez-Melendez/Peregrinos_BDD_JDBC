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
import Modelo.Parada;
import Modelo.Usuarios;

public class ParadaDAO implements operacionesCRUD<Parada>{

	private static ParadaDAO Datos_Parada;
	public Peregrino_BDD con;
	
	private ParadaDAO(Peregrino_BDD con) {
		this.con=con;
	}
	
	public static ParadaDAO Conexion_Parada(Peregrino_BDD con) {
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

		return null;
	}

	@Override
	public Collection<Parada> buscarTodos() {
				// TODO Auto-generated method //hay que declarar esto fuera del of para cerrarlo correctamente
		Connection co=null;
		List<Parada> lista=new ArrayList<>();
		
		String consulta ="select * from parada";
		if (this.con == null  ) {
			this.con = Peregrino_BDD.Conex_BDD(co);
		}
		try {
			PreparedStatement pstmt = con.conex_BDD.prepareStatement(consulta);
			ResultSet resultado = pstmt.executeQuery();
			while(resultado.next()) {
				Parada par =new Parada();
				long id_parada = resultado.getLong("id");
				long id_cred = resultado.getLong("id_credenciales");
				String nombre_p = resultado.getNString("nombre");
				String region = resultado.getNString("region");
				String nombre_responsable = resultado.getNString("nombre_responsable");
				par.setId(id_parada);
				par.setId_credenciales(id_cred);
				par.setNombre(nombre_p);
				par.setRegion(region.charAt(0));
				par.setResponsable_parada(nombre_responsable);
				lista.add(par);
				//BORRAR ESTA LINEA AL TERMINAR LAS PRUEBAS
				System.out.println(par.toString());
			}
			Peregrino_BDD.cerrarConexion(co);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
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
