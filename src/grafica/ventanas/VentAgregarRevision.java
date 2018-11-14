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
import grafica.controladores.ContAgregarRevision;

public class VentAgregarRevision {

	private JFrame ventAgregarRevision;
	private JTextField textFieldCodigo;
	private JTextField textFieldDescripcion;
	private ContAgregarRevision controlador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentAgregarRevision window = new VentAgregarRevision();
					window.ventAgregarRevision.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentAgregarRevision() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ventAgregarRevision = new JFrame();
		ventAgregarRevision.setTitle("Expedienpress - Agregar revision a folio");
		ventAgregarRevision.setBounds(100, 100, 450, 300);
		ventAgregarRevision.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventAgregarRevision.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblTitulo = new JLabel("Ingrese la informaci\u00F3n en los campos y haga clic en \"Ingresar Revision\"");
		ventAgregarRevision.getContentPane().add(lblTitulo, "4, 2, 3, 1");
		
		JLabel lblCodigoFolio = new JLabel("Codigo Folio");
		ventAgregarRevision.getContentPane().add(lblCodigoFolio, "4, 6, right, default");
		
		textFieldCodigo = new JTextField();
		ventAgregarRevision.getContentPane().add(textFieldCodigo, "6, 6, left, default");
		textFieldCodigo.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		ventAgregarRevision.getContentPane().add(lblDescripcion, "4, 8, right, default");
		
		textFieldDescripcion = new JTextField();
		ventAgregarRevision.getContentPane().add(textFieldDescripcion, "6, 8, left, default");
		textFieldDescripcion.setColumns(10);
		
		JButton btnIngresarRevision = new JButton("Ingresar Revision");
		ventAgregarRevision.getContentPane().add(btnIngresarRevision, "6, 12, left, default");
		btnIngresarRevision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarCamposAgregarRevison();
			}
		});
		
		JButton btnVolverAPrincipal = new JButton("Volver a Principal");
		ventAgregarRevision.getContentPane().add(btnVolverAPrincipal, "6, 14, left, default");
		btnVolverAPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventAgregarRevision.dispose();
			}
		});
		
		controlador = new ContAgregarRevision(this);
	}
	
	public void validarCamposAgregarRevison() {
		if (!textFieldCodigo.getText().isEmpty() && !textFieldDescripcion.getText().isEmpty()) {
			controlador.agregarRevision(textFieldCodigo.getText(), textFieldDescripcion.getText());
		} else {
			mostrarError("Debe completar todos los campos");
		}
	}
	
	public void mostrarResultado(String res)
	{
		JOptionPane.showMessageDialog(ventAgregarRevision, res, "Resultado", JOptionPane.INFORMATION_MESSAGE);
		ventAgregarRevision.dispose();
	}
	
	public void mostrarError(String res)
	{
		JOptionPane.showMessageDialog(ventAgregarRevision, res, "Resultado", JOptionPane.ERROR_MESSAGE);
	}
	
	public void setVisible(boolean valor)
	{
		ventAgregarRevision.setVisible(valor);
	}

}
