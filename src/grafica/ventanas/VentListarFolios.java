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
import grafica.controladores.ContListarFolios;
import logicaPersistencia.valueObjects.ListaVOFolios;
import logicaPersistencia.valueObjects.VOFolio;

public class VentListarFolios {

	private JFrame ventListarFolios;
	private JTable tableFolios;
	private ContListarFolios controlador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentListarFolios window = new VentListarFolios();
					window.ventListarFolios.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentListarFolios() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ventListarFolios = new JFrame();
		ventListarFolios.setTitle("Expedienpress - Listar folios");
		ventListarFolios.setBounds(100, 100, 450, 300);
		ventListarFolios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventListarFolios.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		tableFolios = new JTable();
		ventListarFolios.getContentPane().add(tableFolios, "8, 4, fill, fill");
		
		JButton btnListarFolios = new JButton("Listar Folios");
		ventListarFolios.getContentPane().add(btnListarFolios, "4, 4, default, top");
		btnListarFolios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.listarFolios();
			}
		});
		
		
		JButton btnVolverAPrincipal = new JButton("Volver a Principal");
		ventListarFolios.getContentPane().add(btnVolverAPrincipal, "4, 8");
		btnVolverAPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventListarFolios.dispose();
			}
		});
		
		controlador = new ContListarFolios(this);
	}
	
	public void mostrarResultado(String res)
	{
		JOptionPane.showMessageDialog(ventListarFolios, res, "Resultado", JOptionPane.INFORMATION_MESSAGE);
		ventListarFolios.dispose();
	}
	
	public void mostrarError(String res)
	{
		JOptionPane.showMessageDialog(ventListarFolios, res, "Resultado", JOptionPane.ERROR_MESSAGE);
	}
	
	public void armarTablaFolios(ListaVOFolios lVoF) {
		DefaultTableModel modelo=new DefaultTableModel();
		modelo.addColumn("CODIGO");
		modelo.addColumn("CARATULA");
		modelo.addColumn("NRO PAGINAS");
		Object rowData[]= new Object[3];
		for(VOFolio voF: lVoF.getFolios()) {
			rowData[0] = voF.getCodigo();
			rowData[1] = voF.getCaratula();
			rowData[2] = voF.getPaginas();
		}
		tableFolios.setModel(modelo);
	}

}
