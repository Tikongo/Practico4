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
import javax.swing.JTextArea;
import grafica.controladores.ContDarDescripcion;

public class VentDarDescripcion {

	private JFrame ventDarDescripcion;
	private JTextField textFieldCodFolio;
	private JTextField textFieldNroDescripcion;
	private ContDarDescripcion controlador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentDarDescripcion window = new VentDarDescripcion();
					window.ventDarDescripcion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentDarDescripcion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ventDarDescripcion = new JFrame();
		ventDarDescripcion.setTitle("Expedienpress - Buscar descripcion de revision");
		ventDarDescripcion.setBounds(100, 100, 450, 300);
		ventDarDescripcion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventDarDescripcion.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
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
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblTitulo = new JLabel("Ingrese los datos en los campos y haga clic en \"Buscar Descripcion\"");
		ventDarDescripcion.getContentPane().add(lblTitulo, "4, 2, 3, 1");
		
		JLabel lblCodigoDeFolio = new JLabel("Codigo de Folio");
		ventDarDescripcion.getContentPane().add(lblCodigoDeFolio, "4, 4, right, default");
		
		textFieldCodFolio = new JTextField();
		ventDarDescripcion.getContentPane().add(textFieldCodFolio, "6, 4, left, default");
		textFieldCodFolio.setColumns(10);
		
		JLabel lblNroDeDescripcion = new JLabel("Nro. de Descripcion");
		ventDarDescripcion.getContentPane().add(lblNroDeDescripcion, "4, 6, right, default");
		
		textFieldNroDescripcion = new JTextField();
		ventDarDescripcion.getContentPane().add(textFieldNroDescripcion, "6, 6, left, default");
		textFieldNroDescripcion.setColumns(10);
		
		JTextArea textAreaDescripcion = new JTextArea();
		ventDarDescripcion.getContentPane().add(textAreaDescripcion, "6, 8, fill, fill");
		
		JButton btnBuscarDescripcion = new JButton("Buscar Descripcion");
		ventDarDescripcion.getContentPane().add(btnBuscarDescripcion, "4, 10");
		btnBuscarDescripcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String desc = verificarCamposDarDescripcion();
				textAreaDescripcion.setText(desc);
			}
		});
		
		JButton btnVolverAPrincipal = new JButton("Volver a Principal");
		ventDarDescripcion.getContentPane().add(btnVolverAPrincipal, "4, 14");
		btnVolverAPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventDarDescripcion.dispose();
			}
		});
		
		controlador = new ContDarDescripcion(this);
	}
	
	public void mostrarResultado(String res)
	{
		JOptionPane.showMessageDialog(ventDarDescripcion, res, "Resultado", JOptionPane.INFORMATION_MESSAGE);
		ventDarDescripcion.dispose();
	}
	
	public void mostrarError(String res)
	{
		JOptionPane.showMessageDialog(ventDarDescripcion, res, "Resultado", JOptionPane.ERROR_MESSAGE);
	}
	
	public String verificarCamposDarDescripcion() {
		String desc = null;
		if (!textFieldCodFolio.getText().isEmpty() && !textFieldNroDescripcion.getText().isEmpty()) {
			int nroD = Integer.parseInt(textFieldNroDescripcion.getText());
			desc = controlador.darDescripcion(textFieldCodFolio.getText(), nroD);
		} else {
			mostrarError("Debe completar todos los campos");
		}
		return desc;
	}
	
	public void setVisible(boolean valor)
	{
		ventDarDescripcion.setVisible(valor);
	}

}
