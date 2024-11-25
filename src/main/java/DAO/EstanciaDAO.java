package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import BDD.Peregrino_BDD;
import Modelo.Carnet;
import Modelo.Estancia;
import Modelo.Parada;
import Modelo.Peregrino;

public class EstanciaDAO implements operacionesCRUD<Estancia>{

	private static EstanciaDAO Datos_Estancia;
	public  Peregrino_BDD con;
	
	private EstanciaDAO(Peregrino_BDD con){
			this.con=con;
		
	}
	
	public static EstanciaDAO Conexion_Estancia(Peregrino_BDD con){
		if(Datos_Estancia==null){
			Datos_Estancia= new EstanciaDAO(con);
		}
		return Datos_Estancia;
	}
	
	@Override
	public boolean insertarConID(Estancia elemento) {
		// TODO Auto-generated method stub
		return false;
	}


	public long insertarSinID(Estancia c,Peregrino p,Carnet carnet) {
		int id =0;
		Connection co=null;
		if (this.con == null  ) {			
			this.con = Peregrino_BDD.Conex_BDD(co);
		}			
		String insert="INSERT INTO estancia( id_parada_ini, fecha_exp,distancia,n_vips) VALUES (?,?,?,?)";
		try {
			//esta linea puede resultar confusa(no  hay nada)
			PreparedStatement pstmt = con.conex_BDD.prepareStatement(insert);
			pstmt.setLong(1,c.getParada().getId());
			pstmt.setLong(2,p.getId());
			pstmt.setDate(3,java.sql.Date.valueOf(carnet.getFecha_creacion()));
			pstmt.setBoolean(4,c.isVip());
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
	public Estancia buscarPorID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Estancia> buscarTodos() {
		Connection co=null;
		List<Estancia> lista=new ArrayList<>();
		String consulta ="select * from estancia ";
		if (this.con == null  ) {
			this.con = Peregrino_BDD.Conex_BDD(co);
		}

			try {
				PreparedStatement pstmt = con.conex_BDD.prepareStatement(consulta);		
				ResultSet resultado = pstmt.executeQuery();
				while(resultado.next()) {
					Estancia estancia = new Estancia();
					long id =resultado.getLong("id");
					long id_parada =resultado.getLong("id_parada");
					long id_peregrino =resultado.getLong("id_peregrino");
					//FORMA DE PASAR DATE A LOCALDATE
					LocalDate fecha=resultado.getDate("fecha").toLocalDate();
					boolean n_vip=resultado.getBoolean("es_vip");
					estancia.setId(id);
					//el objeto parada para poder asignar el id y pasarlo a la estancia
					Parada parada=new Parada();
					parada.setId(id_parada);
					estancia.setParada(parada);
					//lo mismo para el peregrino
					Peregrino per= new Peregrino();
					per.setId(id_peregrino);
					estancia.setPeregrino(per);
					estancia.setFecha(fecha);
					estancia.setVip(n_vip);
					lista.add(estancia);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return lista;
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
	
	public  Collection<Estancia> BuscarEstanciasPorFechas(LocalDate fechaini,LocalDate fechafin){
		Connection co=null;
		List<Estancia> lista=new ArrayList<>();
		String consulta ="select * from estancia where fecha BETWEEN ? AND ?";
		if (this.con == null  ) {
			this.con = Peregrino_BDD.Conex_BDD(co);
		}

			try {
				PreparedStatement pstmt = con.conex_BDD.prepareStatement(consulta);
				pstmt.setDate(1,java.sql.Date.valueOf(fechaini));
				pstmt.setDate(2,java.sql.Date.valueOf(fechafin));
				ResultSet resultado = pstmt.executeQuery();
				while(resultado.next()) {
					Estancia estancia = new Estancia();
					long id =resultado.getLong("id");
					long id_parada =resultado.getLong("id_parada");
					long id_peregrino =resultado.getLong("id_peregrino");
					//FORMA DE PASAR DATE A LOCALDATE
					LocalDate fecha=resultado.getDate("fecha").toLocalDate();
					boolean n_vip=resultado.getBoolean("es_vip");
					estancia.setId(id);
					//el objeto parada para poder asignar el id y pasarlo a la estancia
					Parada parada=new Parada();
					parada.setId(id_parada);
					estancia.setParada(parada);
					//lo mismo para el peregrino
					Peregrino per= new Peregrino();
					per.setId(id_peregrino);
					estancia.setPeregrino(per);
					estancia.setFecha(fecha);
					estancia.setVip(n_vip);
					lista.add(estancia);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return lista;
	}

	@Override
	public long insertarSinID(Estancia elemento) {
		// TODO Auto-generated method stub
		return 0;
	}

}
