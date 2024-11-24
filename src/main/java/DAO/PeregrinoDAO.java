package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import BDD.Peregrino_BDD;
import Modelo.Carnet;
import Modelo.CredencialesUsuario;
import Modelo.Peregrino;

//esta clase tiene singleton
public class PeregrinoDAO implements operacionesCRUD<Peregrino>{
	//tengo que hacer le metodo peregrino por que lo almaceno en credenciales y no en peregrino 
	private static PeregrinoDAO Datos_Peregrino;
	public Peregrino_BDD con;
	
	private PeregrinoDAO(Peregrino_BDD con) {
		this.con=con;
	}
	
	public static PeregrinoDAO Conexion_Peregrino(Peregrino_BDD con) {	
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
	//select max(id) from carnet
	//hay que contenplar sacar esto de la base de datos
	@Override
	public long insertarSinID(Peregrino p) {
		int valor=0;
		boolean val =false;
		Connection co=null;
		if (this.con == null  ) {			
			this.con = Peregrino_BDD.Conex_BDD(co);
		}
		String id_Carnet="SELECT id FROM carnet ORDER BY id DESC LIMIT 0, 1";
		String id_credenciales="SELECT id FROM credenciales_usuario ORDER BY id DESC LIMIT 0, 1";
		String insert="INSERT INTO peregrino(id_credenciales,id_carnet,nombre,nacionalidad) VALUES(?,?,?,?)";
		try {
			PreparedStatement pstmt1 = con.conex_BDD.prepareStatement(id_Carnet);
			PreparedStatement pstmt2 = con.conex_BDD.prepareStatement(id_credenciales);
			ResultSet resultado1 = pstmt1.executeQuery();
			ResultSet resultado2 = pstmt2.executeQuery();
			//hay que extraer los datos de las de credenciales y del carnet recien incluidos y añadirlos ap peregrino
			while(resultado1.next()) {
				long id = resultado1.getLong("id");
				Carnet car =new Carnet();
				car.setId(id);
				p.setCarnet_peregrino(car);
			}
			while(resultado2.next()) {
				long id = resultado2.getLong("id");
				CredencialesUsuario cred =new CredencialesUsuario();
				cred.setId(id);
				p.setId_credenciales(cred);
			}
			
			
			PreparedStatement pstmt = con.conex_BDD.prepareStatement(insert);
			pstmt.setLong(1,p.getCarnet_peregrino().getId());
			pstmt.setLong(2,p.getId_credenciales().getId());
			pstmt.setString(3, p.getNombre());
			pstmt.setString(4, p.getNacionalidad());
			int resultadoInsercion = pstmt.executeUpdate();
			valor=resultadoInsercion;
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
		return valor;
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
