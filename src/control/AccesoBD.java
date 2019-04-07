package control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author Javier
 *
 */
public class AccesoBD {

	private Statement declaracion;

	private ResultSet datos;


	/**
	 * 
	 */
	public AccesoBD() {


		Conecta nuevo=new Conecta();

		Connection conexion=nuevo.getConexion();

		try {

			declaracion=conexion.createStatement();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}


	/**
	 * 
	 * @return
	 */
	public ResultSet getUsuaioBD(String id_Usuario){


		try {

			datos=declaracion.executeQuery("SELECT * FROM usuario where id_Usuario='"+id_Usuario+"';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datos;
	}


	/**
	 * 
	 * @return
	 */
	public ResultSet getProductosBD(){


		try {

			datos=declaracion.executeQuery("SELECT * FROM `productos`;");


		} catch (SQLException e) {

			e.printStackTrace();

		}

		return datos;

	}


	/**
	 * 
	 * @return
	 */
	public ResultSet getNumProductosBD() {

		try {
			datos=declaracion.executeQuery("SELECT count(*) from productos");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datos;
	}


	/**
	 * 
	 * @param id_Mascota 
	 * 
	 */
	public ResultSet getNumCitasBD(String id_Mascota) {
		// TODO Auto-generated method stub

		try {
			datos=declaracion.executeQuery("SELECT count(*) from cita where id_Mascota ='"+id_Mascota+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datos;

	}


	/**
	 * @param string 
	 * @return 
	 * 
	 */
	public ResultSet getCitasMascotaBD(String id_Mascota) {

		try {

			datos=declaracion.executeQuery("SELECT * FROM `cita` WHERE `id_Mascota` = '"+id_Mascota+"'");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datos;
	}


	/**
	 * 
	 * @param id_Usuario
	 * @return
	 */
	public ResultSet getMascotasUsuarioBD(String id_Usuario) {
		// TODO Auto-generated method stub


		try {
			datos=declaracion.executeQuery("select * from mascota where id_Duenio='"+id_Usuario+"'");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datos;
	}


	/**
	 * 
	 * @param dni_usuario 
	 * @return
	 */
	public ResultSet getNumMascotasBD(String id_usuario) {

		try {
			datos=declaracion.executeQuery("select count(*) from mascota where id_Duenio='"+id_usuario+"';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datos;
	}


	/**
	 * @param hora 
	 * 
	 * 
	 */
	public void aniadirCitaBD(java.util.Date cita_Fecha, String id_Mascota, String hora) {
		// TODO Auto-generated method stub

		try {
			declaracion.executeUpdate("INSERT INTO `cita` (`id_Cita`, `fecha_cita`, `id_Mascota`, `hora_Cita`) VALUES (NULL, '"+new java.sql.Date(cita_Fecha.getTime())+"', '"+id_Mascota+"', '"+hora+"');");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  
		}

	}


	/*
	 * 
	 * 
	 */
	public void eliminarCitaBD(long id_Cita) {
		// TODO Auto-generated method stub

		try {
			declaracion.execute("DELETE FROM `cita` WHERE`id_Cita` = '"+id_Cita+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	/**
	 * 
	 * @param id_Producto
	 */
	public void borrar_ProductoBD(String id_Producto) {

		try {
			declaracion.execute("DELETE FROM `productos` WHERE`id_Producto` = '"+id_Producto+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	/**
	 * 
	 * @param id_Usuario
	 * @param fecha
	 */
	public void aniadir_ReservaBD(String id_Usuario, Date fecha) {
		try {
			declaracion.execute("INSERT INTO `reserva` (`id_Reserva`, `id_Usuario`, `pedido_Recogido`, `fecha_Pedido`) VALUES (NULL, '"+id_Usuario+"', '0', '"+fecha+"');");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	/**
	 * 
	 * @param id_Usuarios
	 * @param fecha
	 * @return
	 */
	public int getId_ReservaBD(String id_Usuarios, Date fecha) {
		// TODO Auto-generated method stub

		int id=0;

		try {
			datos=declaracion.executeQuery("SELECT * FROM `reserva` WHERE `id_Usuario`='"+id_Usuarios+"' and `fecha_Pedido`='"+fecha+"';");

			if(datos.next()) {
				id=datos.getInt(1);
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(id);
		return id;
	}


	/**
	 * 
	 * @param id_Prod
	 * @param id_Reserva
	 */
	public void aniadirReservaProductoBD(String id_Prod, int id_Reserva) {
		// TODO Auto-generated method stub

		System.out.println(id_Prod+"+"+id_Reserva);

		try {
			declaracion.execute("INSERT INTO `productosreservados` (`cont_Reserva`, `id_Reserva`, `id_Producto`) VALUES (NULL, '"+id_Reserva+"', '"+id_Prod+"');");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	/**
	 * 
	 * @param fileName
	 */
	public static void backupBD(File fileName) {

		try {
			String rutaMySqlDump = "C:\\Archivos de programa\\MySQL\\MySQL Server 5.0\\bin\\mysqldump.exe";
			String contrasenia ="";
			String usuario = "root";
			String dataBase = "bilnow_db";

			String cad = "\"" + rutaMySqlDump + "\" --opt --password=" + contrasenia + " --user=" + usuario + " " + dataBase + " > \"" + fileName.getAbsolutePath()+".sql" +"\"\n";


			File fcopi = new File("copia_seguridad.bat");
			FileWriter fw = new FileWriter(fcopi);
			fw.write(cad, 0, cad.length());
			fw.close();
			Runtime.getRuntime().exec("copia_Seguridad.bat");


		} catch (Exception e) {
			e.printStackTrace();
		}


	}


	/**
	 * 
	 * @param dni_usuario
	 * @return
	 */
	public boolean existeUserBD(String dni_usuario) {

		boolean esta=true;

		try {
			datos=declaracion.executeQuery("Select * from `usuario` where `id_Usuario` = '"+dni_usuario+"';");

			if(datos.next()==false) {
				esta=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return esta;

	}


	/**
	 * 
	 * @param pw_usuario
	 * @param dni_usuario
	 * @return
	 */
	public boolean existePasswdBD(String pw_usuario,String dni_usuario) {

		boolean esta=true;

		try {
			datos=declaracion.executeQuery("SELECT * FROM `usuario` WHERE `id_Usuario`='"+dni_usuario+"' and `pswd_Usuario`='"+pw_usuario+"';");

			if(datos.next()==false) {
				esta=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return esta;

	}

	/**
	 * 
	 * @param dni
	 * @param nom
	 * @param ap
	 * @param tlf
	 * @param pswd
	 * @param correo
	 * @param dir
	 * @param rol
	 */
	public void aniadir_UsuarioBD(String dni, String nom, String ap, String tlf, String pswd, String correo, String dir, int rol) {
		// TODO Auto-generated method stub
		try {
			declaracion.execute("INSERT INTO `usuario` (`id_Usuario`, `nom_Usuario`, `ap_Usuario`, `tlf_Usuario`, `dir_Usuario`, `rol_Usuario`, `pswd_Usuario`,"
					+ " `email_Usuario`) VALUES ('"+dni+"', '"+nom+"', '"+ap+"', '"+tlf+"', '"+dir+"', '"+rol+"', '"+pswd+"', '"+correo+"');");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * 
	 * @param id
	 * @param nom
	 * @param esp
	 * @param raza
	 * @param capa
	 * @param decha
	 * @param sexo
	 * @param id_Duenio
	 */
	public void aniadir_MascotaBD(String id, String nom, String esp, String raza, String capa, Date fecha, int sexo, String id_Duenio) {
		// TODO Auto-generated method stub
		try {
			declaracion.execute("INSERT INTO `mascota` (`id_Mascota`, `nombre_Mascota`, `esp_Mascota`, `raza_Mascota`, `capa_Mascota`, `fecha_Nacimiento`, `sexo_Mascota`, `id_Duenio`) "
					+ "VALUES ('"+id+"', '"+nom+"', '"+esp+"', '"+raza+"', '"+capa+"', '"+new java.sql.Date(fecha.getTime())+"', '"+sexo+"', '"+id_Duenio+"');");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * 
	 * @param id
	 * @param nom
	 * @param fabricante
	 * @param fecha_Cad
	 * @param price
	 */
	public void aniadir_ProductoBD(String id, String nom, String fabricante, Date fecha_Cad, String price) {
		// TODO Auto-generated method stub
		try {
			declaracion.execute("INSERT INTO `productos` (`id_Producto`, `nom_Producto`, `fecha_Caducidad`, `fabricante_Pro`, `price_Producto`) "
					+ "VALUES ('"+id+"', '"+nom+"', '"+new java.sql.Date(fecha_Cad.getTime())+"', '"+fabricante+"', '"+price+"');");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * 
	 * @param dni_Mascota
	 * @return
	 */
	public boolean existeMascotaBD(String dni_Mascota) {

		boolean esta=true;

		try {
			datos=declaracion.executeQuery("Select * from `mascota` where `id_Mascota` = '"+dni_Mascota+"';");

			if(datos.next()==false) {
				esta=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return esta;
	}


	/**
	 * 
	 * @param id_Producto
	 * @return
	 */
	public boolean existeProductoBD(String id_Producto) {

		boolean esta=true;

		try {
			datos=declaracion.executeQuery("Select * from `productos` where `id_Producto` = '"+id_Producto+"';");

			if(datos.next()==false) {
				esta=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return esta;
	}


	/**
	 * 
	 * @param dni_Mascota
	 */
	public void eliminarMascotaBD(String dni_Mascota) {

		try {
			declaracion.execute("DELETE FROM `mascota` WHERE `id_Mascota` = '"+dni_Mascota+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	/**
	 * 
	 * @param id_Prod
	 */
	public void eliminarProductoBD(String id_Prod) {

		try {
			declaracion.execute("DELETE FROM `productos` WHERE `id_Producto` = '"+id_Prod+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void eliminarCitaMascotaBD(String dni_Mascota) {

		try {
			declaracion.execute("DELETE FROM `cita` WHERE`id_Mascota` = '"+dni_Mascota+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void eliminarUsuarioBD(String dni_usuario) {

		try {
			declaracion.execute("DELETE FROM `usuario` WHERE`id_Usuario` = '"+dni_usuario+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public boolean estaLibreHora(Date cita_Fecha, String hora) {

		boolean esta=false;

		try {
			datos=declaracion.executeQuery("Select * from `cita` where `fecha_cita` = '"+new java.sql.Date(cita_Fecha.getTime())+"' and `hora_Cita` = '"+hora+"';");

			if(datos.next()==false) {
				esta=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return esta;
	}


	public ResultSet getMascota(String id_Mascota) {
		// TODO Auto-generated method stub
		return null;
	}

}
