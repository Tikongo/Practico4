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
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import logicaPersistencia.valueObjects.VOFolioMaxRev;
import grafica.controladores.ContFolioMasRevisado;

public class VentFolioMasRevisado {

	private JFrame ventFolioMasRevisado;
	private JTable tableFolioMasRevisado;
	private ContFolioMasRevisado controlador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentFolioMasRevisado window = new VentFolioMasRevisado();
					window.ventFolioMasRevisado.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentFolioMasRevisado() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ventFolioMasRevisado = new JFrame();
		ventFolioMasRevisado.setTitle("Expedienpress - Ver Folio mas Revisado");
		ventFolioMasRevisado.setBounds(100, 100, 450, 300);
		ventFolioMasRevisado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventFolioMasRevisado.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
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
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		tableFolioMasRevisado = new JTable();
		ventFolioMasRevisado.getContentPane().add(tableFolioMasRevisado, "4, 6, 3, 1, fill, fill");
		
		JButton btnVerFolioMas = new JButton("Ver Folio mas Revisado");
		ventFolioMasRevisado.getContentPane().add(btnVerFolioMas, "4, 4");
		btnVerFolioMas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VOFolioMaxRev voFMR = controlador.folioMasRevisado();
				armarTablaFolioMasRevisado(voFMR);
			}
		});
		
		JButton btnVolverAPrincipal = new JButton("Volver a Principal");
		ventFolioMasRevisado.getContentPane().add(btnVolverAPrincipal, "4, 8");
		btnVolverAPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventFolioMasRevisado.dispose();
			}
		});
		
		controlador = new ContFolioMasRevisado(this);
	}
	
	public void mostrarResultado(String res)
	{
		JOptionPane.showMessageDialog(ventFolioMasRevisado, res, "Resultado", JOptionPane.INFORMATION_MESSAGE);
		ventFolioMasRevisado.dispose();
	}
	
	public void mostrarError(String res)
	{
		JOptionPane.showMessageDialog(ventFolioMasRevisado, res, "Resultado", JOptionPane.ERROR_MESSAGE);
	}
	
	public void armarTablaFolioMasRevisado(VOFolioMaxRev voFMR) {
		DefaultTableModel modelo=new DefaultTableModel();
		modelo.addColumn("CODIGO");
		modelo.addColumn("CARATULA");
		modelo.addColumn("NRO PAGINAS");
		modelo.addColumn("CANT REVISIONES");
		Object rowData[]= new Object[4];
		rowData[0] = voFMR.getCodigo();
		rowData[1] = voFMR.getCaratula();
		rowData[2] = voFMR.getPaginas();
		rowData[3] = voFMR.getCantRevisiones();
		tableFolioMasRevisado.setModel(modelo);
	}

}
