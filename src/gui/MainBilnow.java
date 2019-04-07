package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.*;

import javax.imageio.IIOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.Conecta;
import control.Usuario;

import java.awt.Font;
import javax.swing.JTextField;

public class MainBilnow extends JFrame {

	private JPanel contentPane;
	private JTextField campo_Usuario;
	private JTextField campo_Password;
	public Connection connection=null;
	public static java.sql.Statement command;	
	private static ResultSet datos;
	private int cont_error=0;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainBilnow frame = new MainBilnow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainBilnow() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 475);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Acceder");
		btnNewButton.setBounds(112, 395, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBackground(Color.WHITE);
		lblFondo.setBounds(0, 11, 326, 256);
		lblFondo.setIcon(new ImageIcon("iconos/logo.gif"));
		contentPane.add(lblFondo);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblUsuario.setBounds(43, 298, 60, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblContrasea.setBounds(43, 342, 85, 14);
		contentPane.add(lblContrasea);
		
		campo_Usuario = new JTextField();
		campo_Usuario.setToolTipText("");
		campo_Usuario.setBounds(147, 297, 86, 20);
		contentPane.add(campo_Usuario);
		campo_Usuario.setColumns(10);
		
		campo_Password = new JTextField();
		campo_Password.setBounds(147, 341, 86, 20);
		contentPane.add(campo_Password);
		campo_Password.setColumns(10);
		this.setLocationRelativeTo(null);
		
		JButton btnAcceder = new JButton("Acceder");
		btnAcceder.setBounds(449, 124, 192, 44);
		contentPane.getRootPane().add(btnAcceder);
		
		Conecta conectar=new Conecta();
		connection=conectar.getConexion();
		
		try {
			command=connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			command=connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				//En esta parte del programa haremos la consulta en la base de datos , para ver si el dni que hemos intrducido es de un administrador o de un cliente.

				Usuario user_control=new Usuario();
				
				int control_error=user_control.comprobarUsuario(campo_Usuario.getText(),campo_Password.getText());
				
				if(control_error==0) {
					
					JOptionPane.showMessageDialog(btnAcceder, "Usuario no registrado en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
					
				}
				else if(control_error==1) {
					cont_error+=1;
					if(cont_error==5) {
						System.exit(0);
					}
					int intentos=5-cont_error;
					JOptionPane.showMessageDialog(btnAcceder, "Contraseña incorrecta numero de intentos:"+intentos+"", "Error", JOptionPane.ERROR_MESSAGE);
					
				}
				else if(control_error==2) {
					
					Usuario user=new Usuario(campo_Usuario.getText());
					
					if(campo_Usuario.getText().equals(user.getDni_usuario())){
						
						if(campo_Password.getText().equals(user.getPw_usuario())){
							
							if(user.getRol_usuario()==0) {
								Administrador nA=new Administrador();
								nA.main(null);
							}else {
								Cliente nC=new Cliente(user,null,null,null);
								nC.main();
							}
							
						}
					
					}
					
				}
					
			}
		});
		
	}
}
