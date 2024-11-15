package BDD;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import com.mysql.cj.jdbc.MysqlDataSource;


public class Peregrino_BDD {
	
	public static Peregrino_BDD conex_PeregrinosBDD;
	public  Connection con;
		
	private Peregrino_BDD(Connection con) {
		if(con==null) {
			this.con=con;
		}
					
	}
	
	public static Connection Conex_BDD(Connection con) {
		try {
			if (con == null) {
				Properties properties = new Properties();
				MysqlDataSource m = new MysqlDataSource();
				FileInputStream fis;
				fis = new FileInputStream("src\\main\\java\\recursos\\db.properties");
				// cargamos la información del fichero properties
				properties.load(fis);
				// asignamos al origen de datos las propiedades leidas del fichero properties
				m.setUrl(properties.getProperty("url"));
				m.setPassword(properties.getProperty("password"));
				m.setUser(properties.getProperty("usuario")); // obtememos la conexion
				fis.close();
				con = m.getConnection();
			}
			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
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
