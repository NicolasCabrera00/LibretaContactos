package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Editar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textTelefono;
	public static String contacto = "";
	public static Editar frame = new Editar();
		/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				frame.setVisible(true);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	Connection con = null;
	
	public Editar() {
		
//		boolean continuar = true;
//
//		while (continuar) {
//			String msj = JOptionPane.showInputDialog("Ingrese el nombre del contacto que desea editar");
//			if (msj.isEmpty()) {
//				JOptionPane.showMessageDialog(null, "El numero ingresado no existe. Vuelva a intentarlo");
//				continuar = true;
//			} else {
//				continuar = false;
//				contacto = msj;
//			}
//			
//		}
		
		setTitle("Editar");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 269, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(94, 24, 66, 14);
		contentPane.add(lblNewLabel);
		
		textNombre = new JTextField();
		textNombre.setText(contacto);
		textNombre.setBounds(74, 49, 86, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Telefono");
		lblNewLabel_1.setBounds(94, 80, 79, 14);
		contentPane.add(lblNewLabel_1);
		textTelefono = new JTextField();
		textTelefono.setBounds(74, 105, 86, 20);
//		textTelefono.setText(telefono);
		contentPane.add(textTelefono);
		textTelefono.setColumns(10);

		
		//Para confirmar los nuevos cambios
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.setBounds(61, 154, 112, 23);
		contentPane.add(btnNewButton);
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(74, 217, 89, 23);
		contentPane.add(btnNewButton_1);
	}

}
