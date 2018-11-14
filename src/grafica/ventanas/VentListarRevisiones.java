package grafica.ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import grafica.controladores.ContListarRevisiones;
import logica.valueObjects.ListaVORevisiones;
import logica.valueObjects.VORevision;

import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;

public class VentListarRevisiones {

	private JFrame VentListarRevisiones;
	private JTextField textFieldCodFolio;
	private JButton btnListarRevisiones;
	private JTable tableRevisiones;
	private JButton btnVolverAPrincipal;
	private ContListarRevisiones controlador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentListarRevisiones window = new VentListarRevisiones();
					window.VentListarRevisiones.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentListarRevisiones() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		VentListarRevisiones = new JFrame();
		VentListarRevisiones.setTitle("Expedienpress - Listar Revisiones de un Folio");
		VentListarRevisiones.setBounds(100, 100, 450, 300);
		VentListarRevisiones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		VentListarRevisiones.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblCodDeFolio = new JLabel("Codigo de Folio");
		VentListarRevisiones.getContentPane().add(lblCodDeFolio, "4, 4, right, default");
		
		textFieldCodFolio = new JTextField();
		VentListarRevisiones.getContentPane().add(textFieldCodFolio, "6, 4, left, default");
		textFieldCodFolio.setColumns(10);
		
		tableRevisiones = new JTable();
		VentListarRevisiones.getContentPane().add(tableRevisiones, "6, 6, 2, 1, fill, fill");
		
		btnListarRevisiones = new JButton("Listar Revisiones");
		VentListarRevisiones.getContentPane().add(btnListarRevisiones, "7, 4");
		btnListarRevisiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarCampoListarRevisiones();
			}
		});
				
		btnVolverAPrincipal = new JButton("Volver a Principal");
		VentListarRevisiones.getContentPane().add(btnVolverAPrincipal, "7, 8");
		btnVolverAPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentListarRevisiones.dispose();
			}
		});
		
		controlador = new ContListarRevisiones(this);
	}
	
	public void mostrarResultado(String res)
	{
		JOptionPane.showMessageDialog(VentListarRevisiones, res, "Resultado", JOptionPane.INFORMATION_MESSAGE);
		//VentListarRevisiones.dispose();
	}
	
	public void mostrarError(String res)
	{
		JOptionPane.showMessageDialog(VentListarRevisiones, res, "Resultado", JOptionPane.ERROR_MESSAGE);
	}
	
	public void validarCampoListarRevisiones() {
		if (!textFieldCodFolio.getText().isEmpty()) {
			controlador.listarRevisiones(textFieldCodFolio.getText());
		} else {
			mostrarError("Debe completar todos los campos");
		}
	}
	
	public void armarTablaRevisiones(ListaVORevisiones lVoR) {
		if (lVoR == null) {
			mostrarError("Ningun folio tiene revisiones para mostrar");
		} else {
			DefaultTableModel modelo=new DefaultTableModel();
			modelo.addColumn("NUMERO DE REVISION");
			modelo.addColumn("DESCRIPCION");
			Object rowData[]= new Object[2];
			rowData[0] = "<html><b>" + modelo.getColumnName(0) + "</html></b>";
			rowData[1] = "<html><b>" + modelo.getColumnName(1) + "</html></b>";
			modelo.addRow(rowData);
			for (VORevision voR: lVoR.getRevisiones()) {
				rowData[0] = voR.getNumero();
				rowData[1] = voR.getDescripcion();
				modelo.addRow(rowData);
			}
			tableRevisiones.setModel(modelo);
		}
	}
	
	public void setVisible(boolean valor)
	{
		VentListarRevisiones.setVisible(valor);
	}

}
