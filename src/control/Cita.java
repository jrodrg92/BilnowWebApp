package control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author Javier
 *
 */
public class Cita {

	private ResultSet datos;

	private long id_Cita=0;

	private String id_Mascota=null;

	private String fecha_Cita;
	
	private ArrayList<Cita> citas_Mascota=null;
	
	private AccesoBD consulta;

	/**
	 * @param id_Mascota 
	 * 
	 */
	public Cita(String id_Mascota) {

		this.setId_Mascota(id_Mascota);	

		consulta=new AccesoBD();
		
	}
	
	/**
	 * 
	 * @return
	 */
	public long getId_Cita() {
		return id_Cita;
	}

	/**
	 * 
	 * @param l
	 */
	private void setId_Cita(long l) {
		this.id_Cita = l;
	}

	/**
	 * 
	 * @return
	 */
	public String getId_Mascota() {
		return id_Mascota;
	}

	/**
	 * 
	 * @param id_Mascota
	 */
	private void setId_Mascota(String id_Mascota) {
		this.id_Mascota = id_Mascota;
	}

	/**
	 * 
	 * @return
	 */
	public String getFecha_Cita() {
		return fecha_Cita;
	}

	/**
	 * 
	 * @param date
	 */
	private void setFecha_Cita(String fecha) {
		this.fecha_Cita = fecha;
	}
	/**
	 * 
	 * @param pos_Mascota
	 * @return 
	 */

	public String getCita(int pos_Cita) {
		// TODO Auto-generated method stub
		
		return String.valueOf(citas_Mascota.get(pos_Cita).getFecha_Cita());
		
	}
	
	/**
	 * @param hora 
	 * 
	 * 
	 */
	public void aniadirCita(java.util.Date cita_Fecha, String hora) {
		// TODO Auto-generated method stub

		consulta.aniadirCitaBD(cita_Fecha, this.getId_Mascota(),hora);

	}

	/*
	 * 
	 * 
	 */
	public void eliminarCita(int pos_Cita) {
		// TODO Auto-generated method stub
		
		consulta.eliminarCitaBD(citas_Mascota.get(pos_Cita).getId_Cita());
		
	}
	
	/**
	 * @param string 
	 * 
	 */
	public void getCitasMascota() {

		citas_Mascota= new ArrayList<Cita>();

			try {
	
				datos=consulta.getCitasMascotaBD(this.getId_Mascota());
				
				while (datos.next()) {

					Cita cita_nueva=new Cita(this.getId_Mascota());
					cita_nueva.setId_Cita(datos.getLong(1));
					cita_nueva.setFecha_Cita(datos.getString(2));
					cita_nueva.setId_Mascota(datos.getString(3));


					citas_Mascota.add(cita_nueva);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

	}
	
	/**
	 * 
	 */
	public int getNumCitas() {
		// TODO Auto-generated method stub

		int num_Citas=0;

		try {
			datos=consulta.getNumCitasBD(this.getId_Mascota());

			if(datos.next()) {
				num_Citas=datos.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return num_Citas;
		
	}

	public boolean estaLibre(String hora, Date cita_Fecha) {
		
		return consulta.estaLibreHora(cita_Fecha,hora);
			
	}

	

	
}
