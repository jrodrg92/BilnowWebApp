package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * Para esta explicacion buscar sobre
 * la clase forname, para lo que se utiliza
 * 
 * @author Javier
 *
 */
public class Conecta {

	private static String driver = "com.mysql.jdbc.Driver";
	
	private static String linea_Conectar = "jdbc:mysql://localhost:3306/bilnow_db?useSSL=false";
	
	private static String usuario = "root";
	
	private static String passwd = "";
	
	private Connection conection = null;
	
	/**
	 * 
	 */
	public Conecta() {
		
		try {
			
			Class.forName(driver);
			
			conection=DriverManager.getConnection(linea_Conectar,usuario,passwd);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public Connection getConexion() {
		
		return conection;
		
	}
	
}
