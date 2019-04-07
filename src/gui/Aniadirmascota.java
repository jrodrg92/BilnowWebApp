package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import control.AccesoBD;
import control.Usuario;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Aniadirmascota extends JFrame {

	private static Aniadirmascota frame;
	private static JPanel contentPane;
	private JTextField textFieldDNIMascota;
	private JTextField textNombre;
	private JTextField textField_3;
	private JTextField textRaza;
	private JTextField textEspecie;
	private JTextField textDNIDuenio;
	private JTextField textCapa;
	private AccesoBD consulta;
	private control.Mascota mascota;
	/**
	 * Launch the application.
	 */
	public static void main(String id_Duenio) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Aniadirmascota(id_Duenio);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param string 
	 */
	public Aniadirmascota(String id_Duenio) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblAadirMascota = new JLabel("A\u00D1ADIR MASCOTA");
		lblAadirMascota.setFont(new Font("Consolas", Font.BOLD, 26));
		lblAadirMascota.setBounds(143, 22, 196, 31);
		panel.add(lblAadirMascota);
		
		JLabel lblDnimascota = new JLabel("DNI Mascota");
		lblDnimascota.setBounds(43, 77, 64, 14);
		panel.add(lblDnimascota);
		
		textFieldDNIMascota = new JTextField();
		textFieldDNIMascota.setBounds(143, 74, 162, 20);
		panel.add(textFieldDNIMascota);
		textFieldDNIMascota.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(43, 102, 46, 14);
		panel.add(lblNombre);
		
		JLabel lblCapa = new JLabel("Capa");
		lblCapa.setBounds(43, 177, 46, 14);
		panel.add(lblCapa);
		
		textNombre = new JTextField();
		textNombre.setBounds(153, 99, 116, 20);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		textEspecie = new JTextField();
		textEspecie.setBounds(153, 124, 116, 20);
		panel.add(textEspecie);
		textEspecie.setColumns(10);
		
		JLabel lblEspecie = new JLabel("Especie");
		lblEspecie.setBounds(43, 127, 46, 14);
		panel.add(lblEspecie);
		
		JLabel lblRaza = new JLabel("Raza");
		lblRaza.setBounds(43, 152, 46, 14);
		panel.add(lblRaza);
		
		JLabel lblFechanacimiento = new JLabel("Fecha de nacimiento");
		lblFechanacimiento.setBounds(43, 202, 110, 14);
		panel.add(lblFechanacimiento);
		
		JDateChooser elegir_Fecha=new JDateChooser();
		elegir_Fecha.setBounds(153, 196, 116, 20);
		panel.add(elegir_Fecha);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(43, 232, 35, 14);
		panel.add(lblSexo);
		
		JCheckBox chckbxHembra = new JCheckBox("Hembra");
		chckbxHembra.setBounds(131, 228, 64, 23);
		panel.add(chckbxHembra);
		
		JCheckBox chckbxMacho = new JCheckBox("Macho");
		chckbxMacho.setBounds(77, 228, 57, 23);
		panel.add(chckbxMacho);
		
		JLabel lblDniDuenio = new JLabel("DNI Due\u00F1o");
		lblDniDuenio.setBounds(43, 257, 64, 14);
		panel.add(lblDniDuenio);
		
		textRaza = new JTextField();
		textRaza.setColumns(10);
		textRaza.setBounds(153, 149, 116, 20);
		panel.add(textRaza);
		
		textDNIDuenio = new JTextField();
		textDNIDuenio.setColumns(10);
		textDNIDuenio.setBounds(152, 257, 116, 20);
		panel.add(textDNIDuenio);
		textDNIDuenio.setText(id_Duenio);
		
		textCapa = new JTextField();
		textCapa.setColumns(10);
		textCapa.setBounds(153, 174, 116, 20);
		panel.add(textCapa);
		
		JButton btnAniadir = new JButton("A\u00F1adir");
		btnAniadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean existe=false;
				AccesoBD consulta=new AccesoBD();
				existe=consulta.existeMascotaBD(textFieldDNIMascota.getText());
				if(existe==true) {
					
					JOptionPane.showMessageDialog(btnAniadir, "Mascota ya registrada en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
					
				}
				else {

					consulta=new AccesoBD();
					
					int sexo=0;
					
					if(chckbxMacho.isSelected()) {
						sexo=1;
					}
					
					mascota.aniadir_Mascota(textFieldDNIMascota.getText(),textNombre.getText(),textEspecie.getText(),textRaza.getText(),textCapa.getText(),elegir_Fecha.getDate(),sexo,textDNIDuenio.getText());
				
					frame.setVisible(false);
					
				}
				
			}
		});
		btnAniadir.setBounds(191, 308, 89, 23);
		panel.add(btnAniadir);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			frame.setVisible(false);
			
			}
		});
		btnCancelar.setBounds(353, 343, 89, 23);
		panel.add(btnCancelar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String id_Mascota=textFieldDNIMascota.getText();
				
				AccesoBD consulta=new AccesoBD();
				
				if(consulta.existeMascotaBD(id_Mascota)) {
					textFieldDNIMascota.setBackground(Color.RED);
				}
				else {
					textFieldDNIMascota.setBackground(Color.GREEN);
				}
			}
		});
		btnConsultar.setBounds(324, 76, 110, 23);
		panel.add(btnConsultar);
	}

}
