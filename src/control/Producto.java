package control;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Javier
 *
 */
public class Producto {
	
	private ResultSet datos;

	public static int pos=0;

	private String id_Prod;

	private String nom_Prod;

	private int precio;

	private String fabrica_Prod;

	private Date fecha_Cad;

	private List<Producto> productos_Almacen=null;
	
	private AccesoBD consulta;


	/**
	 * 
	 */
	public Producto() {

		consulta=new AccesoBD();
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String getId_Prod() {
		return id_Prod;
	}
	
	/**
	 * 
	 * @param id_Prod
	 */
	private void setId_Prod(String id_Prod) {
		this.id_Prod = id_Prod;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getNom_Prod() {
		return nom_Prod;
	}
	
	/**
	 * 
	 * @param nom_Prod
	 */
	private void setNom_Prod(String nom_Prod) {
		this.nom_Prod = nom_Prod;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getPrecio() {
		return precio;
	}
		
	/**
	 * 
	 * @param precio
	 */
	private void setPrecio(int precio) {
		this.precio = precio;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFabrica_Prod() {
		return fabrica_Prod;
	}
	
	/**
	 * 
	 * @param fabrica_Prod
	 */
	private void setFabrica_Prod(String fabrica_Prod) {
		this.fabrica_Prod = fabrica_Prod;
	}
	
	/**
	 * 
	 * @return
	 */
	public Date getFecha_Cad() {
		return fecha_Cad;
	}
	
	/**
	 * 
	 * @param fecha_Cad
	 */
	private void setFecha_Cad(Date fecha_Cad) {
		this.fecha_Cad = fecha_Cad;
	}
	
	/**
	 * 
	 * @return
	 */
	public Producto[] getProductos(){

		productos_Almacen=new ArrayList<>();

		try {
		
			datos=consulta.getProductosBD();

			while(datos.next()){

				Producto n_Producto=new Producto();
				
				n_Producto.setId_Prod(datos.getString(1));
				n_Producto.setNom_Prod(datos.getString(2));
				n_Producto.setFecha_Cad(datos.getDate(3));
				n_Producto.setFabrica_Prod(datos.getString(4));
				n_Producto.setPrecio(datos.getInt(5));
				
				productos_Almacen.add(n_Producto);

			}

		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return productos_Almacen.toArray(new Producto[getNumProductos()]);

	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumProductos() {
		
		int num_Productos=0;

		try {


			datos=consulta.getNumProductosBD();

			if(datos.next()) {

				num_Productos=datos.getInt(1);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		
		}

		return num_Productos;
	}

	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Producto obtener_Producto(String id) {
		
		Producto devolver=null;
		
		for(int i=0;i<productos_Almacen.size();i++) {
				
			if(productos_Almacen.get(i).getId_Prod()==id) {
				
				devolver=productos_Almacen.get(i);
			
			}
			
		}
			
		return devolver;
	}

	public void aniadir_Producto(Producto new_Producto) {
		
	}
	
	public void borrar_Producto(String id_Producto) {
		
	}
	
}
