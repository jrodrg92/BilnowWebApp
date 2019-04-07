package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import control.Mascota;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

public class Cita {

	private static JFrame frame;

	private static Mascota mascota_Cita=null;
	
	private final String[] horarios= {"8:00",
									  "9:00",
									  "10:00",
									  "11:00",
									  "12:00",
									  "13:00",
									  "16:00",
									  "17:00",
									  "18:00",
									  "19:00",
									  "20:00"};

	private int hora_Seleccionada;
	
	public static JButton btnEditarCita=null;
	
	private control.Cita control_cita=null;
	
	private JPanel panel__Horas;
	
	private Date cita_Fecha;
	
	/**
	 * Launch the application.
	 */
	public static void main(Mascota mascota, JButton boton_editar) {
		
		if(boton_editar!=null) {
			btnEditarCita=boton_editar;
		}
		
		EventQueue.invokeLater(new Runnable() {
			

			public void run() {
				try {
					mascota_Cita=mascota;
					
					Cita window = new Cita();
					frame.setVisible(true);			
					frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
					frame.getContentPane().setBackground(Color.WHITE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Cita() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 928, 645);
		frame.getContentPane().setLayout(null);

		JCalendar calendario=new JCalendar();	
		calendario.getDayChooser().addPropertyChangeListener(
	                new java.beans.PropertyChangeListener() {
	 
	                    @Override
	                    public void propertyChange(java.beans.PropertyChangeEvent evt) {
	                        if (evt.getPropertyName().compareTo("day") == 0) {
	                        	 
	                             cita_Fecha=calendario.getDate();
	                             pintar_Horas();
	                        }
	                    }
	                });
		calendario.setBounds(461, 67, 420, 250);
		frame.getContentPane().add(calendario);
		
		JLabel lblInformacinMascota = new JLabel("Informaci\u00F3n Mascota");
		lblInformacinMascota.setBounds(24, 26, 384, 26);
		frame.getContentPane().add(lblInformacinMascota);
		cita_Fecha=new Date();
		
		/**
		 * 
		 */
		JButton btnGuardarCita = new JButton("Guardar Cita");
		btnGuardarCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				control_cita=new control.Cita(mascota_Cita.getDni_Mascota());
				
				String hora= horarios[hora_Seleccionada];
				
				int fecha = calendario.getCalendar().get(java.util.Calendar.DATE);
				
				cita_Fecha.setDate(fecha);
							
				//variables para la consulta prepararlas
				
				control_cita.aniadirCita(cita_Fecha,hora);
					
				frame.setVisible(false);
			}
		});
		
		
		btnGuardarCita.setBounds(740, 529, 121, 23);
		frame.getContentPane().add(btnGuardarCita);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(461, 366, 420, 132);
		frame.getContentPane().add(scrollPane);
		
		panel__Horas = new JPanel();
		scrollPane.setViewportView(panel__Horas);
		
		panel__Horas.setPreferredSize(new Dimension(418,horarios.length*31));
		panel__Horas.setLayout(null);
		
		if(btnEditarCita!=null){
			btnEditarCita.setBounds(499, 529, 153, 26);
			frame.getContentPane().add(btnEditarCita);
			btnEditarCita.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					
				
				}
			});
			
		}
		
		pintar_Horas();
	
	}

	public void pintar_Horas() {
		int pos_Lab=0;
		boolean hora_Libre=true;
		for (int i = 0; i < horarios.length; i++) {
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(0, pos_Lab, 418, 31);
			panel__Horas.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel(horarios[i]);
			lblNewLabel_1.setBounds(27, 11, 46, 14);
			panel_1.add(lblNewLabel_1);
			
			panel_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {

					int pos=panel_1.getY();

					if(pos!=0) {
						hora_Seleccionada=pos/31;
					}
					else {
						hora_Seleccionada=0;
					}
					
				}
				
			});
			
			
			control.Cita control2=new control.Cita(null);
			hora_Libre=control2.estaLibre(horarios[i],cita_Fecha);
			if(hora_Libre==true) {
				panel_1.setBackground(Color.GREEN);
			}
			else {
				panel_1.setBackground(Color.RED);
			}

			pos_Lab+=31;
		}
	}

}

