package control;

import java.util.ArrayList;

/**
 * 
 * @author Javier
 *
 */
public class Carrito{
	
	private Usuario comprador=null;
	
	private ArrayList<Producto> carro_Compra=null;	
	
	/**
	 * 
	 * @param user
	 */
	public Carrito(Usuario user) {
		
		this.comprador=user;
		this.carro_Compra=new ArrayList<Producto>();
		
	}
	
	/**
	 * 
	 * @param producto
	 */
	public void aniadir_Producto(Producto producto){
		
		carro_Compra.add(producto);
		
	}
	
	/**
	 * 
	 * @param pos
	 */
	public void eliminar_Producto(int pos){
		
		carro_Compra.remove(pos);
		
	}
	
	/**
	 * 
	 * @return
	 */
	public int getTamanio() {
		
		return carro_Compra.size();
		
	}
	
	/**
	 * 
	 * @return
	 */
	public Producto[] getCarrito() {

		return carro_Compra.toArray(new Producto[carro_Compra.size()]);
	
	}
	
	/**
	 * 
	 * @return
	 */
	public int obtener_Total(){
		
		int total=0;
		
		for(int i=0;i<carro_Compra.size();i++){
			
			total=total+carro_Compra.get(i).getPrecio();
			
		}

		return total;
	}

	/**
	 * 
	 * @param i
	 * @return el producto de la posicion i.
	 */
	public Producto getProd(int i) {
		
		return carro_Compra.get(i);
		
	}
}
