package main;

import java.sql.Connection;

import BDD.Peregrino_BDD;
import DAO.PeregrinoDAO;

public class PeregrinoPrincipal {

	public static void main(String[] args) {
		Connection con =null;
		 con=Peregrino_BDD.Conex_BDD(con);
		PeregrinoDAO d =PeregrinoDAO.Conexion_Peregrino(con);
		PeregrinoDAO d1 =PeregrinoDAO.Conexion_Peregrino(con);
		PeregrinoDAO d2=PeregrinoDAO.Conexion_Peregrino(con);
		PeregrinoDAO d3=PeregrinoDAO.Conexion_Peregrino(con);
		//prueba del singleton
		System.out.println(d.hashCode());
		System.out.println(d1.hashCode());
		System.out.println(d2.hashCode());
		System.out.println(d3.hashCode());
	}
	
}
