package main;

import java.sql.Connection;

import BDD.Peregrino_BDD;
import DAO.CarnetDAO;
import DAO.CredencialesUsuarioDAO;
import DAO.EstanciaDAO;
import DAO.ParadaDAO;
import DAO.PeregrinoDAO;

public class PeregrinoPrincipal {

	public static void main(String[] args) {
	
	}
	
}


	/*
		 * //pruebas de funcionamineto del singleton 
		 * Connection con =null;
		 * 
		 * ParadaDAO par=ParadaDAO.Conexion_Parada(con); ParadaDAO
		 * par2=ParadaDAO.Conexion_Parada(con); ParadaDAO
		 * par3=ParadaDAO.Conexion_Parada(con); ParadaDAO
		 * par4=ParadaDAO.Conexion_Parada(con);
		 * 
		 * EstanciaDAO est=EstanciaDAO.Conexion_Estancia(con); EstanciaDAO
		 * est1=EstanciaDAO.Conexion_Estancia(con); EstanciaDAO
		 * est2=EstanciaDAO.Conexion_Estancia(con); EstanciaDAO
		 * est3=EstanciaDAO.Conexion_Estancia(con);
		 * 
		 * CredencialesUsuarioDAO cred
		 * =CredencialesUsuarioDAO.Conexion_CredencialesUsuario(con);
		 * CredencialesUsuarioDAO cred2
		 * =CredencialesUsuarioDAO.Conexion_CredencialesUsuario(con);
		 * CredencialesUsuarioDAO cred3
		 * =CredencialesUsuarioDAO.Conexion_CredencialesUsuario(con);
		 * CredencialesUsuarioDAO cred4
		 * =CredencialesUsuarioDAO.Conexion_CredencialesUsuario(con);
		 * 
		 * CarnetDAO car =CarnetDAO.Conexion_Peregrino(con); CarnetDAO car1
		 * =CarnetDAO.Conexion_Peregrino(con); CarnetDAO car2
		 * =CarnetDAO.Conexion_Peregrino(con); CarnetDAO car3
		 * =CarnetDAO.Conexion_Peregrino(con);
		 * 
		 * Peregrino_BDD conex=Peregrino_BDD.Conex_BDD(con); Peregrino_BDD
		 * conex1=Peregrino_BDD.Conex_BDD(con); Peregrino_BDD
		 * conex2=Peregrino_BDD.Conex_BDD(con); Peregrino_BDD
		 * conex3=Peregrino_BDD.Conex_BDD(con);
		 * 
		 * PeregrinoDAO d =PeregrinoDAO.Conexion_Peregrino(con); PeregrinoDAO d1
		 * =PeregrinoDAO.Conexion_Peregrino(con); PeregrinoDAO
		 * d2=PeregrinoDAO.Conexion_Peregrino(con); PeregrinoDAO
		 * d3=PeregrinoDAO.Conexion_Peregrino(con); //prueba del singleton
		 * System.out.println(d.hashCode()); System.out.println(d1.hashCode());
		 * System.out.println(d2.hashCode()); System.out.println(d3.hashCode());
		 * 
		 * System.out.println(conex.hashCode()); System.out.println(conex1.hashCode());
		 * System.out.println(conex2.hashCode()); System.out.println(conex3.hashCode());
		 * 
		 * System.out.println(car.hashCode()); System.out.println(car1.hashCode());
		 * System.out.println(car2.hashCode()); System.out.println(car3.hashCode());
		 * 
		 * System.out.println(cred.hashCode()); System.out.println(cred2.hashCode());
		 * System.out.println(cred3.hashCode()); System.out.println(cred4.hashCode());
		 * 
		 * System.out.println(est.hashCode()); System.out.println(est1.hashCode());
		 * System.out.println(est2.hashCode()); System.out.println(est3.hashCode());
		 * 
		 * System.out.println(par.hashCode()); System.out.println(par2.hashCode());
		 * System.out.println(par3.hashCode()); System.out.println(par4.hashCode());
		 */