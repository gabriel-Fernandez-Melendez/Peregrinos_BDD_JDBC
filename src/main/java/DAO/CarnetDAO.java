package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

import BDD.Peregrino_BDD;
import Modelo.Carnet;
import Modelo.CredencialesUsuario;
import Modelo.Parada;
import Modelo.Peregrino;

public class CarnetDAO implements operacionesCRUD<Carnet>{

	public static CarnetDAO Datos_Carnet;
	public Peregrino_BDD con;
	
	private CarnetDAO(Peregrino_BDD con) {
		this.con=con;
	}
	
	public static CarnetDAO Conexion_Peregrino(Peregrino_BDD con) {
		if(Datos_Carnet==null) {
			Datos_Carnet=new CarnetDAO(con);
		}
		return Datos_Carnet;
	}
	@Override
	public boolean insertarConID(Carnet c) {
		boolean val =false;
		Connection co=null;
		if (this.con == null  ) {			
			this.con = Peregrino_BDD.Conex_BDD(co);
		}			
		String insert="INSERT INTO carnet( id_parada_ini, fecha_exp,distancia,n_vips) VALUES (?,?,?,?)";
		try {
			//esta linea puede resultar confusa(no  hay nada)
			PreparedStatement pstmt = con.conex_BDD.prepareStatement(insert);
			pstmt.setLong(1,c.getParada().getId());
			pstmt.setDate(2, java.sql.Date.valueOf(c.getFecha_creacion()));
			pstmt.setDouble(3, c.getDistancia());
			pstmt.setInt(4,c.getN_vips());
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
	public long insertarSinID(Carnet c) {
		int id =0;
		Connection co=null;
		if (this.con == null  ) {			
			this.con = Peregrino_BDD.Conex_BDD(co);
		}			
		String insert="INSERT INTO carnet( id_parada_ini, fecha_exp,distancia,n_vips) VALUES (?,?,?,?)";
		try {
			//esta linea puede resultar confusa(no  hay nada)
			PreparedStatement pstmt = con.conex_BDD.prepareStatement(insert);
			pstmt.setLong(1,c.getParada().getId());
			pstmt.setDate(2, java.sql.Date.valueOf(c.getFecha_creacion()));
			pstmt.setDouble(3, c.getDistancia());
			pstmt.setInt(4,c.getN_vips());
			int resultadoInsercion = pstmt.executeUpdate();
			if (resultadoInsercion >= 1) {
				id=resultadoInsercion;
				System.out.println("se han almacenado las credenciales del usuario");
			}
			else {
				System.out.println("hubo algun error al momento de la insercion");
			}
			Peregrino_BDD.cerrarConexion(co);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return id;
	}

	@Override
	public Carnet buscarPorID(long id) {
		Carnet c=new Carnet();
		Connection co=null;
		if (this.con == null  ) {			
			this.con = Peregrino_BDD.Conex_BDD(co);
		}
		String consulta="SELECT * FROM carnet WHERE id =?";
		PreparedStatement pstmt;
		try {
			pstmt = con.conex_BDD.prepareStatement(consulta);
			pstmt.setLong(1, id);
			ResultSet resultado = pstmt.executeQuery();
			while (resultado.next()) {
				long id_carnet =resultado.getLong("id");
				long id_parada =resultado.getLong("id_parada_ini");
				//forma de cojer localdate
				LocalDate fecha =resultado.getDate("fecha_exp").toLocalDate();
				double distancia=resultado.getDouble("distancia");
				int vip=resultado.getInt("n_vips");
				c.setId(id_carnet);
				Parada p =new Parada();
				p.setId(id_parada);
				c.setParada(p);
				c.setFecha_creacion(fecha);
				c.setDistancia(distancia);
				c.setN_vips(vip);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
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
