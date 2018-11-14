package grafica.ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import grafica.controladores.ContAgregarFolio;

public class VentAgregarFolio {

	private JFrame ventAgregarFolio;
	private JTextField textFieldCodigo;
	private JTextField textFieldCaratula;
	private ContAgregarFolio controlador;
	private JTextField textFieldPaginas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentAgregarFolio window = new VentAgregarFolio();
					window.ventAgregarFolio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentAgregarFolio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ventAgregarFolio = new JFrame();
		ventAgregarFolio.setTitle("Expedienpress - Ingresar nuevo folio");
		ventAgregarFolio.setBounds(100, 100, 450, 300);
		ventAgregarFolio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventAgregarFolio.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
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
		
		JLabel lblTitulo = new JLabel("Ingrese los datos y haga clic en \"Ingresar Folio\"");
		ventAgregarFolio.getContentPane().add(lblTitulo, "4, 2, 3, 1");
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		ventAgregarFolio.getContentPane().add(lblCdigo, "4, 6, right, default");
		
		textFieldCodigo = new JTextField();
		ventAgregarFolio.getContentPane().add(textFieldCodigo, "6, 6, left, default");
		textFieldCodigo.setColumns(10);
		
		JLabel lblCartula = new JLabel("Car\u00E1tula");
		ventAgregarFolio.getContentPane().add(lblCartula, "4, 8, right, default");
		
		textFieldCaratula = new JTextField();
		ventAgregarFolio.getContentPane().add(textFieldCaratula, "6, 8, left, default");
		textFieldCaratula.setColumns(10);
		
		JLabel lblPaginas = new JLabel("Paginas");
		ventAgregarFolio.getContentPane().add(lblPaginas, "4, 10, right, default");
		
		textFieldPaginas = new JTextField();
		ventAgregarFolio.getContentPane().add(textFieldPaginas, "6, 10, left, default");
		textFieldPaginas.setColumns(10);
		
		JButton btnIngresarFolio = new JButton("Ingresar Folio");
		ventAgregarFolio.getContentPane().add(btnIngresarFolio, "6, 12, left, default");
		btnIngresarFolio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarCamposIngresarFolio();
			}
		});
		
		JButton btnVolverAPrincipal = new JButton("Volver a Principal");
		ventAgregarFolio.getContentPane().add(btnVolverAPrincipal, "6, 14, left, default");
		btnVolverAPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventAgregarFolio.dispose();
			}
		});
		
		controlador = new ContAgregarFolio(this);
	}
	
	public void validarCamposIngresarFolio() {
		if (!textFieldCaratula.getText().isEmpty() && !textFieldCodigo.getText().isEmpty() && !textFieldPaginas.getText().isEmpty()) {
			//Llamar al metodo del controlador.
			controlador.agregarFolio(textFieldCodigo.getText(), textFieldCaratula.getText(),textFieldPaginas.getText());
		} else {
			mostrarError("Debe completar todos los campos");
		}
	}
	
	public void mostrarResultado(String res)
	{
		JOptionPane.showMessageDialog(ventAgregarFolio, res, "Resultado", JOptionPane.INFORMATION_MESSAGE);
		//ventAgregarFolio.dispose();
	}
	
	public void mostrarError(String res)
	{
		JOptionPane.showMessageDialog(ventAgregarFolio, res, "Resultado", JOptionPane.ERROR_MESSAGE);
	}
	
	public void setVisible(boolean valor)
	{
		ventAgregarFolio.setVisible(valor);
	}

}
