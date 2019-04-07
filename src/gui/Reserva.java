package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import control.AccesoBD;
import control.Carrito;
import control.Producto;
import control.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import java.awt.FlowLayout;

public class Reserva {

	private static JFrame frame;
	
	private static JPanel panel;
	
	private static JScrollPane scrollPane_1;

	private Usuario user=null;

	public static Producto[] productos_Tienda=null;

	public Carrito carro_compra=null;

	public static Producto[] carrito=null;

	private int pos_Objeto=0;
	
	private AccesoBD consulta;
	
	private JButton aniadir_Prod;

	private JButton btnEliminar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
					frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
					

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param eliminar 
	 * @param aniadir 
	 * @wbp.parser.entryPoint
	 */
	public Reserva(Usuario user, JButton aniadir, JButton eliminar) {

		aniadir_Prod=aniadir;
		
		btnEliminar=eliminar;
		
		carro_compra=new Carrito(user);
		
		consulta=new AccesoBD();

		this.user=user;

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 1046, 646);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.WHITE);

		JLabel lblCarritoDeLa = new JLabel("Carrito de la compra");
		lblCarritoDeLa.setBounds(769, 35, 153, 14);
		frame.getContentPane().add(lblCarritoDeLa);

		JButton btnRealizarPedido = new JButton("Realizar pedido");
		btnRealizarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Date fecha_Reserva=new Date();
				
				control.Reserva nueva=new control.Reserva(user.getDni_usuario(), new java.sql.Date(fecha_Reserva.getTime()), carro_compra);
				
				frame.setVisible(false);
				
			}
		});
		btnRealizarPedido.setBounds(880, 573, 112, 23);
		frame.getContentPane().add(btnRealizarPedido);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(24, 43, 600, 500);

		Producto gestionar=new Producto();

		frame.getContentPane().add(scrollPane);

		JPanel panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(null);
		
		//Crear array de todos los productos;

		carrito=new Producto[carro_compra.getTamanio()];

		productos_Tienda=new Producto[gestionar.getNumProductos()];

		productos_Tienda= gestionar.getProductos();

		if(productos_Tienda.length%3==0) {
			panel_1.setPreferredSize(new Dimension(600, 200*(productos_Tienda.length/3)));
		}
		else {
			panel_1.setPreferredSize(new Dimension(600, 200*(productos_Tienda.length/3)+200));
		}

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(648, 60, 359, 483);
		frame.getContentPane().add(scrollPane_1);

		int x=0;
		int y=0;
		int cont=0;
		for(int i=0;i<productos_Tienda.length;i++) {

			cont++;
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(x, y, 200, 200);
			panel_1.add(panel_2);
			panel_2.setLayout(null);

			JLabel lblNomprod = new JLabel(productos_Tienda[i].getId_Prod());
			lblNomprod.setBounds(30, 21, 139, 32);
			panel_2.add(lblNomprod);

			JLabel lblFabricante = new JLabel("Fabricante");
			lblFabricante.setBounds(30, 64, 80, 14);
			panel_2.add(lblFabricante);

			JLabel lblPrecio = new JLabel("precio");
			lblPrecio.setBounds(30, 101, 46, 14);
			panel_2.add(lblPrecio);		

			
			if(btnEliminar!=null) {
				
				JButton btnElim_1=new JButton("-");
				btnElim_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						AccesoBD consulta=new AccesoBD();
						consulta.eliminarProductoBD(lblNomprod.getText());
						
						frame.repaint();
						
					}
				});
				btnElim_1.setBounds(30, 166, 65, 23);
				panel_2.add(btnElim_1);
				
			}
			
			JButton btnAdd = new JButton("ADD");
			btnAdd.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					String id=lblNomprod.getText();

					Producto seleccionado = gestionar.obtener_Producto(id); 

					carro_compra.aniadir_Producto(seleccionado);

					int total=carro_compra.obtener_Total();

					pintar_Carrito();
					
					actualizar_Precio();
	
				}

				
			});

			btnAdd.setBounds(125, 166, 65, 23);
			panel_2.add(btnAdd);

			x=x+200;
			if(cont%3==0){
				x=0;
				y=y+200;
			}
		}
		
		JButton btnQuitarProducto = new JButton("Quitar Producto");
		btnQuitarProducto.setBounds(688, 573, 124, 23);
		btnQuitarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				carro_compra.eliminar_Producto(pos_Objeto);
				
				pintar_Carrito();
				
			}
						
		});	
		frame.getContentPane().add(btnQuitarProducto);
		
		if(aniadir_Prod!=null) {
		
			aniadir_Prod.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Aniadirproducto aniadirproducto=new Aniadirproducto();
					aniadirproducto.main(null);
					
				}
			});
			aniadir_Prod.setBounds(487, 557, 53, 39);
			frame.getContentPane().add(aniadir_Prod);
		}

		frame.repaint();
	}
	
	private void actualizar_Precio() {
		// TODO Auto-generated method stub
		
		JLabel lblPreciototal = new JLabel("Precio_Total");
		lblPreciototal.setBounds(880, 548, 300, 20);
		frame.getContentPane().add(lblPreciototal);

		lblPreciototal.setText(String.valueOf(carro_compra.obtener_Total()));
		
	}
	
	public void pintar_Carrito() {
		// TODO Auto-generated method stub
		
		panel = new JPanel();
		

		scrollPane_1.setViewportView(panel);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);

		panel.setPreferredSize(new Dimension(359, 68*carro_compra.getTamanio()));
		
		int pos=0;

		for(int i=0;i<carro_compra.getTamanio();i++) {

			Producto actual=carro_compra.getProd(i);

			JPanel panel_3 = new JPanel();
			panel_3.setBackground(Color.WHITE);

			panel_3.setBounds(0, pos, 359, 68);
			panel.add(panel_3);
			panel_3.setLayout(null);

			panel_3.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {

					int pos=panel_3.getY();

					if(pos!=0) {
						pos_Objeto=pos/68;
					}
					else {
						pos_Objeto=0;
					}

				}
			});

			JLabel lblNomprod_1 = new JLabel(actual.getId_Prod());
			lblNomprod_1.setBounds(36, 23, 68, 14);
			panel_3.add(lblNomprod_1);

			JLabel lblPrecio_1 = new JLabel("Precio");
			lblPrecio_1.setBounds(246, 23, 46, 14);
			panel_3.add(lblPrecio_1);

			JLabel lblUnidades = new JLabel("unidades");
			lblUnidades.setBounds(153, 23, 46, 14);
			panel_3.add(lblUnidades);

			pos=pos+68;
		}
	}
	
}
