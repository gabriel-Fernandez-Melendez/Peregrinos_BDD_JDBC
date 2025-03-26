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
		String insert="INSERT INTO estancia( id_parada,id_peregrino,fecha,es_vip) VALUES (?,?,?,?)";
		try {
			//esta linea puede resultar confusa(no  hay nada)
			PreparedStatement pstmt = con.conex_BDD.prepareStatement(insert);
			pstmt.setLong(1,c.getParada().getId());
			pstmt.setLong(2,p.getId());
			pstmt.setDate(3,java.sql.Date.valueOf(LocalDate.now()));
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
	
	public  Collection<Estancia> BuscarEstanciasPorFechas(LocalDate fechaini,LocalDate fechafin,Parada p){
		Connection co=null;
		List<Estancia> lista=new ArrayList<>();
		String consulta ="select * from estancia where fecha BETWEEN ? AND ? AND id_parada =?";
		if (this.con == null  ) {
			this.con = Peregrino_BDD.Conex_BDD(co);
		}

			try {
				PreparedStatement pstmt = con.conex_BDD.prepareStatement(consulta);
				pstmt.setDate(1,java.sql.Date.valueOf(fechaini));
				pstmt.setDate(2,java.sql.Date.valueOf(fechafin));
				pstmt.setLong(3, p.getId());
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
	
	//esta coleccion enviarla al peregrino que se almacenara en xml 
	public  Collection<Estancia> ListaSellado(Peregrino p,ArrayList<Parada> paradas){
		Connection co=null;
		List<Estancia> lista=new ArrayList<>();
		String consulta ="select * from sellado_de_parada";
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
	public long insertarSinID(Estancia elemento) {
		// TODO Auto-generated method stub
		return 0;
	}
	//correccion: tengo que añadir aqui el metodo de insertar la 
	//pense que este seria el mejor luegar para poner este metodo ya que como es una tabla en la base pero no una entidad la puse en el controlador que considere esta relacionado
	//correcion: no llegaba correctamente el id del peregrino, ya funciona correctamente al hacer una consulta que saca el valor del id de la base de datos y lo setea en el objeto pasado por parametro
	public boolean Sellado(Peregrino p) {
		Connection co=null;
		String id_peregrino="SELECT id FROM peregrino ORDER BY id DESC LIMIT 0, 1";
		String insert ="insert into sellado_en_parada(id_parada,id_peregrino,fecha_de_sello) values (?,?,?)";
		if (this.con == null  ) {
			this.con = Peregrino_BDD.Conex_BDD(co);
		}
		try {
			PreparedStatement pstmt = con.conex_BDD.prepareStatement(insert);
			//operacion del id del peregrino(solo si el id es nulo )
			if(p.getId()==null) {
				PreparedStatement pstmt1 = con.conex_BDD.prepareStatement(id_peregrino);
			ResultSet resultado1 = pstmt1.executeQuery();
			while(resultado1.next()) {
				long id = resultado1.getLong("id");
				p.setId(id);
			}
			}	
			pstmt.setLong(1, p.getParadas().get(0).getId());
			//Correcion: tengo que hacer la consulta para sacar el id del ultimo peregrino introducidoal hacerlo antes con solo el objeto llegaba nulo por que lo introduzco con id nulo
			pstmt.setLong(2, p.getId());
			pstmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
			
			//y seteamos el valor que salio del resultset como id
			
			int resultadoInsercion = pstmt.executeUpdate();
			if (resultadoInsercion >= 1) {
				System.out.println("se han registrado su paso por la parada");
			}
			else {
				System.out.println("hubo algun error al momento de la insercion");
			}
			Peregrino_BDD.cerrarConexion(co);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
