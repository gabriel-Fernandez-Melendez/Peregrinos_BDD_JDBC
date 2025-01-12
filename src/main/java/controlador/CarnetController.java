package controlador;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import BDD.Peregrino_BDD;
import DAO.CarnetDAO;
import DAO.EstanciaDAO;
import DAO.ParadaDAO;
import DAO.PeregrinoDAO;
import Modelo.Carnet;
import Modelo.CredencialesUsuario;
import Modelo.Estancia;
import Modelo.Parada;
import Modelo.Peregrino;
import utilidades.Utilidades;


public class CarnetController {
	
	//campos necesarios para la coneccion de los DAO a lo largo de algunos metodos incluidor en esta clase
	public static Connection con;
	public static Peregrino_BDD peregrinosBDD=Peregrino_BDD.Conex_BDD(con);
	
	//todos los metodos de esta clase son estaticos para usarlos sin crear estancias de la clase
	
	//el nuevo carnet de momento  no hace falta por que meteremos dentro de las DAO
	public static Carnet NuevoCarnet(Peregrino p) {
		Carnet ret =new Carnet();
		ret.setId(0L);//esta a 0 pero es autocalculado en la base de datos, este dato es irrelevante para la creacion del objeto
		ret.setParada(p.getParadas().get(0));
		ret.setFecha_creacion(LocalDate.now());
		//el carnet inicia con los valores de distancia y de vip a cero
		ret.setDistancia(0.0f);
		ret.setN_vips(0);
		return ret;
	}
	
	//metodo encargado de sellar el carnet en la tabla intermedia que existe entre las entidades
	public static void SellarCarnet(CredencialesUsuario cred,Peregrino per) {
		boolean vip=false;
		boolean val=false;
		//guardamos las credenciales para buscar la parada
		Parada parada_aux=new Parada();
		//todos los objetos de conexiones necesarias para llenar los datos de la tabla
		PeregrinoDAO par =PeregrinoDAO.Conexion_Peregrino(peregrinosBDD);
		CarnetDAO car=CarnetDAO.Conexion_Peregrino(peregrinosBDD);
		EstanciaDAO est=EstanciaDAO.Conexion_Estancia(peregrinosBDD);
		ParadaDAO parada=ParadaDAO.Conexion_Parada(peregrinosBDD);		
		Peregrino p = new Peregrino();	
		p=par.buscarPorID(per.getId());
		parada_aux=parada.buscarPorID(cred.getId());
		List<Parada> lista=new ArrayList<>();
		lista.add(parada_aux);
		System.out.println("quiere hacer una estancia en la parada?");
		val=Utilidades.leerBoolean();
		if(val) {
		System.out.println("quiere que su estancia sea vip?");
		val=Utilidades.leerBoolean();
		if(val) {
			vip=true;
			System.out.println("perfecto, ya esta almacenado ,hasta la proxima!");
			//esta solo se ejecuta si quiere  una estancia el usuario
			Estancia estancia=new Estancia();
			estancia.setParada(parada_aux);
			Carnet carnet=new Carnet();
			carnet=car.buscarPorID(p.getCarnet_peregrino().getId());
			est.insertarSinID(estancia,p,carnet);
		}
		}
		//crear el objeto para almacer carnets y estancias
		//le ponemos al peregrino la parada		
		p.setParadas(lista);
		car.UpdateCarnet(vip,p.getCarnet_peregrino().getId());
		est.Sellado(p);
		}
}
