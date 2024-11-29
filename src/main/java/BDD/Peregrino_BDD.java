package BDD;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import com.mysql.cj.jdbc.MysqlDataSource;

	//clase que se conecta con la base de datos aplicando singleton
public class Peregrino_BDD {
	
	//un campo estatico de una instancia de la propia clase y el parametro que pasamos como argumento tanto en el metodo como el constructor 
	public static Peregrino_BDD conex_PeregrinosBDD;
	public  Connection conex_BDD;
		
	//constructor privado al cual solo se accede desde el metodo publico que inicializa la instancia estatica
	private Peregrino_BDD(Connection con) {
		if(conex_PeregrinosBDD==null) {
			this.conex_BDD=con;
		}
					
	}
	
	//metodo que nos conecta a la base de datos y asigna la conexion a la instancia estatica
	public static Peregrino_BDD Conex_BDD(Connection con) {
		try {
			if (conex_PeregrinosBDD == null) {
				Properties properties = new Properties();
				MysqlDataSource m = new MysqlDataSource();
				FileInputStream fis;
				fis = new FileInputStream("src\\main\\java\\recursos\\db.properties");
				properties.load(fis);
				m.setUrl(properties.getProperty("url"));
				m.setPassword(properties.getProperty("password"));
				m.setUser(properties.getProperty("usuario"));
				fis.close();
				con = m.getConnection();
				conex_PeregrinosBDD =new Peregrino_BDD(con);
			}
			return conex_PeregrinosBDD;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conex_PeregrinosBDD;
	}
	
	//metodo para cerrar la conexion cuando sea necesario
	public static void cerrarConexion(Connection con) {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	
}
