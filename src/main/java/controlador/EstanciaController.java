package controlador;

import java.sql.Connection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import BDD.Peregrino_BDD;
import DAO.EstanciaDAO;
import Modelo.Estancia;
import Modelo.Parada;

public class EstanciaController {

	public static Connection con;
	public static Peregrino_BDD peregrinosBDD = Peregrino_BDD.Conex_BDD(con);

	public static void ExportarEstancias(Parada p) {
		boolean val =false;
		Scanner scan = new Scanner(System.in);
		String fecha1;
		String fecha2;
		try {
			//correccion : meti esto en un do while para que no terminara de forma improvista si metia mal la fecha
			do {			
			System.out.println("introduzca la fecha de inicio con el formato 'dd-mm-yyyy'");
			fecha1 = scan.nextLine();
			Date date1 = (Date) new SimpleDateFormat("dd-MM-yyyy").parse(fecha1);
			// stackoverflow never lies, esta linea es como pasar al local date
			LocalDate fecha_ini = LocalDate.ofInstant(date1.toInstant(), ZoneId.systemDefault());
			System.out.println("introduzca la fecha de fin con el formato 'dd-mm-yyyy'");
			fecha2 = scan.nextLine();
			Date date2 = (Date) new SimpleDateFormat("dd-MM-yyyy").parse(fecha2);
			LocalDate fecha_fin = LocalDate.ofInstant(date2.toInstant(), ZoneId.systemDefault());
			EstanciaDAO est = EstanciaDAO.Conexion_Estancia(peregrinosBDD);
			Collection<Estancia> lista = new ArrayList<>();
			if(fecha_ini.isAfter(fecha_fin)) {
				System.out.println("su fecha no es valida");
				val =false;
			}
			lista = est.BuscarEstanciasPorFechas(fecha_ini, fecha_fin, p);
			for (Estancia e : lista) {
				System.out.println("Estacia : " + e.toString());
			}
			val =true;
			if (lista.isEmpty()) {
				System.out.println("no hubo estancias durante estas fechas!");
				val =true;
			}
			} while (!val);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
