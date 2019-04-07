package control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Reserva {

	private ResultSet datos;

	private int id_Reserva;

	private String id_Usuario;

	private boolean recogido;
	
	private Date fecha_Pedido;
	
	private AccesoBD consulta;
	


	/**
	 * 
	 * @param id_Usuarios
	 * @param carro_compra
	 */
	public Reserva(String id_Usuario, Date fecha, Carrito carro_compra){

		consulta=new AccesoBD();
	
		consulta.aniadir_ReservaBD(id_Usuario,fecha);

		id_Reserva=consulta.getId_ReservaBD(id_Usuario,fecha);
		
		aniadir_Productos(carro_compra);

	}


	
	/**
	 * 
	 * @param lista_Productos
	 */
	
	private void aniadir_Productos(Carrito lista_Productos) {
		// TODO Auto-generated method stub
				
		for(int i=0;i<lista_Productos.getTamanio();i++) {
			
			consulta.aniadirReservaProductoBD(lista_Productos.getProd(i).getId_Prod(),id_Reserva);
			
			
		}
		

	}

}
