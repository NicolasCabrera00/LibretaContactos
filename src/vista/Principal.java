package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTable;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.plaf.synth.SynthOptionPaneUI;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Color;
import control.*;
import modelo.Conexion;
import net.proteanit.sql.DbUtils;


public class Principal {

	public static JFrame frmAdministracionDeContactos;
	private JTable listaContactos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmAdministracionDeContactos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	Connection con;
	private JTextField buscar;
	public Principal() {
//		--> Conexion a la base de datos
		String bd = "contactos";
		String url = "C:\\\\Users\\\\Nico\\\\eclipse\\\\LibretaDeContactos";
		con = Conexion.conectar(bd, url);
		JOptionPane.showMessageDialog(null, "Se ha establecido conexion");
//											<--
		
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdministracionDeContactos = new JFrame();
		frmAdministracionDeContactos.setBackground(Color.WHITE);
		frmAdministracionDeContactos.setForeground(new Color(255, 255, 255));
		frmAdministracionDeContactos.setTitle("Administracion de contactos");
		frmAdministracionDeContactos.setBounds(100, 100, 450, 391);
		frmAdministracionDeContactos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdministracionDeContactos.getContentPane().setLayout(null);
		
		JLabel lbTitulo = new JLabel("Libreta de contactos");
		lbTitulo.setFont(new Font("Microsoft YaHei", Font.PLAIN, 25));
		lbTitulo.setBounds(108, 11, 266, 33);
		frmAdministracionDeContactos.getContentPane().add(lbTitulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(66, 55, 350, 188);
		frmAdministracionDeContactos.getContentPane().add(scrollPane);
		
		listaContactos = new JTable();
		scrollPane.setViewportView(listaContactos);
		
		JButton btnAgregar = new JButton("Agregar ");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Agregar ventanaAgregar = new Agregar();
				ventanaAgregar.setVisible(true);
			}
		});
		btnAgregar.setBackground(new Color(50, 205, 50));
		btnAgregar.setBounds(67, 301, 89, 35);
		frmAdministracionDeContactos.getContentPane().add(btnAgregar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBackground(new Color(255, 215, 0));
		btnEditar.setBounds(191, 301, 104, 35);
		frmAdministracionDeContactos.getContentPane().add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(new Color(255, 0, 0));
		btnEliminar.setBounds(327, 301, 89, 35);
		frmAdministracionDeContactos.getContentPane().add(btnEliminar);
		
		JButton btnActualizar = new JButton("");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Guardo los registros de la tabla libreta
				ResultSet rs = Control.mostrarContactos(con, "libreta");
				
				//A traves de la libreria rs2xml.jar muestro los resultados de la consulta en la tabla
				listaContactos.setModel(DbUtils.resultSetToTableModel(rs));
			}
		});
		//Inserto imagen al boton
		Image img = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		btnActualizar.setIcon(new ImageIcon(img));
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 5));
		btnActualizar.setBounds(19, 55, 37, 41);
		frmAdministracionDeContactos.getContentPane().add(btnActualizar);
		
		buscar = new JTextField();
		buscar.setBounds(66, 254, 266, 35);
		frmAdministracionDeContactos.getContentPane().add(buscar);
		buscar.setColumns(10);
		
		JButton btnBuscar = new JButton();
		//Inserto imagen al boton
		Image lupa = new ImageIcon(this.getClass().getResource("/lupa.png")).getImage();
		btnBuscar.setIcon(new ImageIcon(lupa));
		btnBuscar.setBounds(355, 254, 46, 35);
		frmAdministracionDeContactos.getContentPane().add(btnBuscar);

		
//		-->{
//				Para mostrar contactos en la tabla principal
		
		//Guardo los registros de la tabla libreta
		ResultSet rs = Control.mostrarContactos(con, "libreta");
		//A traves de la libreria rs2xml.jar muestro los resultados de la consulta en la tabla
		listaContactos.setModel(DbUtils.resultSetToTableModel(rs));
	
//									<--}
	}
	
}
