package grafica.ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import grafica.controladores.ContBorrarFolio;

public class VentBorrarFolio {

	private JFrame ventBorrarFolio;
	private JTextField textFieldCodigo;
	private ContBorrarFolio controlador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentBorrarFolio window = new VentBorrarFolio();
					window.ventBorrarFolio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentBorrarFolio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ventBorrarFolio = new JFrame();
		ventBorrarFolio.setTitle("Expedienpress - Borrar folio");
		ventBorrarFolio.setBounds(100, 100, 450, 300);
		ventBorrarFolio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventBorrarFolio.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblTitulo = new JLabel("Ingrese el codigo del folio a borrar y haga clic en \"Borrar Folio\"");
		ventBorrarFolio.getContentPane().add(lblTitulo, "4, 2, 3, 1");
		
		JLabel lblCodigo = new JLabel("Codigo");
		ventBorrarFolio.getContentPane().add(lblCodigo, "4, 6, right, default");
		
		textFieldCodigo = new JTextField();
		ventBorrarFolio.getContentPane().add(textFieldCodigo, "6, 6, left, default");
		textFieldCodigo.setColumns(10);
		
		JButton btnBorrarFolio = new JButton("Borrar Folio");
		ventBorrarFolio.getContentPane().add(btnBorrarFolio, "6, 10, left, default");
		btnBorrarFolio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarCampoBorrarFolio();
			}
		});
		
		JButton btnVolverAPrincipal = new JButton("Volver a Principal");
		ventBorrarFolio.getContentPane().add(btnVolverAPrincipal, "6, 12, left, default");
		btnVolverAPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventBorrarFolio.dispose();
			}
		});
		
		controlador = new ContBorrarFolio(this);
	}
	
	public void mostrarResultado(String res)
	{
		JOptionPane.showMessageDialog(ventBorrarFolio, res, "Resultado", JOptionPane.INFORMATION_MESSAGE);
		//ventBorrarFolio.dispose();
	}
	
	public void mostrarError(String res)
	{
		JOptionPane.showMessageDialog(ventBorrarFolio, res, "Resultado", JOptionPane.ERROR_MESSAGE);
	}
	
	public void validarCampoBorrarFolio() {
		if (!textFieldCodigo.getText().isEmpty()) {
			controlador.borrarFolio(textFieldCodigo.getText());
		} else {
			mostrarError("Debe completar todos los campos");
		}
	}
	
	public void setVisible(boolean valor)
	{
		ventBorrarFolio.setVisible(valor);
	}
}
