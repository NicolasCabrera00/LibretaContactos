package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Ayuda extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ayuda frame = new Ayuda();
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
	public Ayuda() {
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 658, 217);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtAyuda = new JTextArea();
		txtAyuda.setEditable(false);
		txtAyuda.setText("Para agregar un contacto tenga en cuenta los siguientes requisitos\r\n-El telefono:\r\n\tDebe estar compuesto unicamente por numeros\r\n\tNo puede ingresar un numero ya registrado\r\n-El nombre:\r\n\tNo se admite registrar un nombre compuesto unicamente por numeros\r\n\tNo se puede registrar un nombre con caracteres especiales ");
		txtAyuda.setBounds(10, 11, 622, 156);
		contentPane.add(txtAyuda);
	}
}
