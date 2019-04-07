package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.AccesoBD;
import control.Usuario;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Aniadircliente extends JFrame {

	private JPanel contentPane;
	private JTextField textDNI;
	private JTextField textNombre;
	private static Aniadircliente frame ;
	private JTextField textDireccion;
	private JTextField textCorreo;
	private JTextField textApellido;
	private JTextField textTelefono;
	private JTextField textContrasenia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Aniadircliente();
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
	 */
	public Aniadircliente() {
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

		JLabel lblAadirCliente = new JLabel("A\u00D1ADIR CLIENTE");
		lblAadirCliente.setFont(new Font("Consolas", Font.BOLD, 26));
		lblAadirCliente.setBounds(140, 23, 234, 35);
		panel.add(lblAadirCliente);

		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(70, 81, 26, 14);
		panel.add(lblDni);

		textDNI = new JTextField();
		textDNI.setBounds(146, 79, 86, 20);
		panel.add(textDNI);
		textDNI.setColumns(10);

		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setBounds(70, 181, 46, 14);
		panel.add(lblDireccin);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(70, 106, 46, 14);
		panel.add(lblNombre);

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(70, 206, 46, 14);
		panel.add(lblCorreo);

		textNombre = new JTextField();
		textNombre.setBounds(146, 103, 206, 20);
		panel.add(textNombre);
		textNombre.setColumns(10);

		textDireccion = new JTextField();
		textDireccion.setBounds(146, 178, 206, 20);
		panel.add(textDireccion);
		textDireccion.setColumns(10);

		textCorreo = new JTextField();
		textCorreo.setBounds(146, 203, 206, 20);
		panel.add(textCorreo);
		textCorreo.setColumns(10);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String id_Usuario=textDNI.getText();

				AccesoBD consulta=new AccesoBD();

				if(consulta.existeUserBD(id_Usuario)) {
					textDNI.setBackground(Color.RED);
				}
				else {
					textDNI.setBackground(Color.GREEN);
				}

			}
		});
		btnConsultar.setBounds(242, 77, 110, 23);
		panel.add(btnConsultar);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(70, 131, 46, 14);
		panel.add(lblApellido);

		textApellido = new JTextField();
		textApellido.setBounds(146, 128, 206, 20);
		panel.add(textApellido);
		textApellido.setColumns(10);

		JLabel lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setBounds(70, 156, 46, 14);
		panel.add(lblTelefono);

		textTelefono = new JTextField();
		textTelefono.setBounds(146, 153, 86, 20);
		panel.add(textTelefono);
		textTelefono.setColumns(10);

		JLabel lblContrasenia = new JLabel("Contrase\u00F1a");
		lblContrasenia.setBounds(70, 231, 64, 14);
		panel.add(lblContrasenia);

		textContrasenia = new JTextField();
		textContrasenia.setBounds(146, 228, 86, 20);
		panel.add(textContrasenia);
		textContrasenia.setColumns(10);

		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setBounds(70, 260, 46, 14);
		panel.add(lblAdmin);

		JCheckBox checkBoxAdmin = new JCheckBox("");
		checkBoxAdmin.setBounds(146, 255, 26, 23);
		panel.add(checkBoxAdmin);

		JButton btnAniadir = new JButton("A\u00F1adir");
		btnAniadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean existe=false;
				AccesoBD consulta=new AccesoBD();
				existe=consulta.existeUserBD(textDNI.getText());
				if(existe==true) {

					JOptionPane.showMessageDialog(btnAniadir, "Usuario ya registrado en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);

				}
				else {

					int rol=1;

					if(checkBoxAdmin.isSelected()) {
						rol=0;
					}

					Usuario nuevo=new Usuario();
					nuevo.aniadir_Usuario(textDNI.getText(),textNombre.getText(),textApellido.getText(),textTelefono.getText(),textContrasenia.getText(),textCorreo.getText(),textDireccion.getText(),rol);

					JButton btnagregarButton = new JButton("+");
					JButton buttoneliminar = new JButton("-");
					JButton eliminarUser=new JButton("Borrar Usuario");

					Cliente cliente_Edita=new Cliente(new Usuario(textDNI.getText()),btnagregarButton,buttoneliminar,eliminarUser);
					cliente_Edita.main();

					frame.setVisible(false);	

				}

			}
		});
		btnAniadir.setBounds(192, 299, 89, 23);
		panel.add(btnAniadir);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnCancelar.setBounds(350, 340, 89, 23);
		panel.add(btnCancelar);
	}
}
