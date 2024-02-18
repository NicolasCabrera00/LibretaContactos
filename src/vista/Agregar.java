package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.JTextField;
import javax.management.RuntimeOperationsException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import control.*;
import modelo.*;

public class Agregar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agregar frame = new Agregar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	Connection con = null;

	public Agregar() {
//		--> Conexion a la base de datos
		String bd = "contactos";
		String url = "C:\\Users\\Nico\\eclipse\\LibretaDeContactos";
		con = Conexion.conectar(bd, url);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Agregar");
		setBounds(100, 100, 602, 203);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titulo = new JLabel("Agregar un contacto");
		titulo.setBackground(new Color(0, 0, 255));
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titulo.setBounds(164, 11, 188, 38);
		contentPane.add(titulo);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(10, 85, 156, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(209, 85, 156, 20);
		contentPane.add(txtTelefono);
		
		JButton btnEnviar = new JButton("Agregar");
		btnEnviar.addActionListener(new ActionListener() {

			
			//Al hacer click en Agregar
			public void actionPerformed(ActionEvent e) {
		
				//Guardo los datos ingresados por el usuario
				String nombre = txtNombre.getText();
				String telefono_in = txtTelefono.getText();
				//Guardo la tabla donde se esta trabajando
				String tabla = "libreta";
				
				try 
				{
				
					//Si los datos ingresados por el usuario son validos
					
					//Paso el texto telefono_in a int 
					int telefono = Integer.parseInt(telefono_in);
					//Creo un nuevo contacto con los parametros	
					Contacto contacto = new Contacto(nombre, telefono);
				
					//Filtro posibles errores en la base de datos, asegurando un formato valido para el registro
					if(Control.formatoValido(contacto, con, tabla)) {
	
						    //Si ya existe un contacto registrado con el nombre ingresado 
						 	if (Control.mismoNombre(contacto, con, tabla)) {
							
								int opcion_in =JOptionPane.showConfirmDialog(null, "Ya existe un contacto registrado con ese nombre. ¿Guardar de todas formas?");
								
								//Si la opcion seleccionada es "si"
								if(opcion_in == 0) {
									Control.agregarContacto(con, contacto, tabla);
									JOptionPane.showMessageDialog(null, "Contacto agregado con exito");
								}
						 	}
							//Si no hay datos repetidos y se cumple el formato, agrego el contacto en la bd 	
						 	else{
								Control.agregarContacto(con, contacto, tabla);
								JOptionPane.showMessageDialog(null, "Contacto agregado con exito");
						 	}
						
					}else {
						throw new Exception();
					}
			} catch (Exception e2) {
				//Si hay algun error al crear el contacto, lo informo
				JOptionPane.showMessageDialog(null, "Se ha ingresado un formato no valido. Revise la opcion 'ayuda' ", "Error", JOptionPane.ERROR_MESSAGE);

			}
				
			}


		});
		btnEnviar.setBackground(new Color(0, 255, 0));
		btnEnviar.setBounds(393, 84, 89, 23);
		contentPane.add(btnEnviar);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(42, 60, 119, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTelefono.setBounds(230, 60, 115, 14);
		contentPane.add(lblTelefono);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setBackground(new Color(255, 69, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
			}
		});
		btnNewButton.setBounds(492, 84, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnGuia = new JButton("Ayuda");
		btnGuia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ayuda ayuda = new Ayuda();
				ayuda.setVisible(true);
			}
		});
		
		btnGuia.setBackground(new Color(255, 235, 205));
		btnGuia.setBounds(10, 134, 71, 23);
		contentPane.add(btnGuia);
	}
}
