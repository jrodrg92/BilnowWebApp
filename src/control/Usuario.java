package control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 
 * @author Javier
 *
 */

public class Usuario {

	private ResultSet datos;

	private static String dni_usuario;

	private static String pw_usuario;

	private String nombre_usuario;

	private String tlf_Usuario;

	private String dir_usuario;

	private String email_usuario;

	private int rol_usuario;

	private Mascota[] mascotas_Usuario;

	private Mascota mascota_Control;

	private AccesoBD consulta;

	/**
	 * 
	 * @param usuario
	 * @param password
	 */
	public Usuario(String usuario) {

		consulta=new AccesoBD();

		mascota_Control=new Mascota();

		try {
			datos=consulta.getUsuaioBD(usuario);
			int i=0;
			if(datos.next()) {

				this.setDni_usuario(datos.getString(1));
				this.setNombre_usuario(datos.getString(2).concat(" "+datos.getString(3)));
				this.settlf_Usuario(datos.getString(4));
				this.setDireccion_usuario(datos.getString(5));
				this.setRol(datos.getInt(6));
				this.setPw_usuario(datos.getString(7));
				this.setEmail_usuario(datos.getString(8));

			}

			mascotas_Usuario=new Mascota[mascota_Control.getNumMascotas(this.getDni_usuario())];
			mascotas_Usuario=mascota_Control.getMascotasUsuario(this);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	private void setRol(int rol) {
		// TODO Auto-generated method stub

		this.rol_usuario=rol;

	}

	private void settlf_Usuario(String tlf) {
		// TODO Auto-generated method stub

		this.tlf_Usuario=tlf;

	}

	/**
	 * 
	 * @return
	 */
	public String getDni_usuario() {
		return dni_usuario;
	}


	/**
	 * 
	 * @param dni_usuario
	 */
	private void setDni_usuario(String dni_usuario) {
		this.dni_usuario = dni_usuario;
	}

	/**
	 * 
	 * @return
	 */
	public String getNombre_usuario() {
		return nombre_usuario;
	}

	/**
	 * 
	 * @param nombre_usuario
	 */
	private void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	/**
	 * 
	 * @return
	 */
	public String getDireccion_usuario() {
		return dir_usuario;
	}

	/**
	 * 
	 * @param direccion_usuario
	 */
	private void setDireccion_usuario(String direccion_usuario) {
		this.dir_usuario = direccion_usuario;
	}

	/**
	 * 
	 * @return
	 */
	public String getEmail_usuario() {
		return email_usuario;
	}

	/**
	 * 
	 * @param email_usuario
	 */
	private void setEmail_usuario(String email_usuario) {
		this.email_usuario = email_usuario;
	}

	/**
	 * 
	 * @param j
	 * @return
	 */
	public Mascota getMascota(int pos_Mascota) {

		return mascotas_Usuario[pos_Mascota];

	}

	public int getRol_usuario() {
		return rol_usuario;
	}

	private void setRol_usuario(int rol_usuario) {
		this.rol_usuario = rol_usuario;
	}

	public String getPw_usuario() {
		return pw_usuario;
	}

	private void setPw_usuario(String pw_usuario) {
		this.pw_usuario = pw_usuario;
	}

	public String getTlf_Usuario() {
		return tlf_Usuario;
	}

	private void setTlf_Usuario(String tlf_Usuario) {
		this.tlf_Usuario = tlf_Usuario;
	}

	public int comprobarUsuario(String id_User, String pw_User) {

		AccesoBD consulta2=new AccesoBD();
		boolean existe_User=false;
		boolean existe_Psswd=false;
		existe_User=consulta2.existeUserBD(id_User);
		
		if(existe_User==true){

			existe_Psswd=consulta2.existePasswdBD(pw_User,id_User);

			if(existe_Psswd==true) {
				return 2;
			}
			else {
				return 1;
			}
			
		}
		else {
			return 0;
		}



	}

	public void aniadir_Usuario(String dni, String nom, String ap, String tlf, String pswd, String correo,
			String dir, int rol) {

		
		AccesoBD consulta2=new AccesoBD();
		consulta2.aniadir_UsuarioBD(dni, nom, ap, tlf, pswd, correo, dir, rol);
		
	}

	public void eliminar_Usuario(String dni_usuario) {

		consulta.eliminarUsuarioBD(dni_usuario);
	}


}
