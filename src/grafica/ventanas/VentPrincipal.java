package grafica.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

public class VentPrincipal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentPrincipal window = new VentPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnArchivo.add(mntmSalir);
		
		JMenu mnIngresoDeDatos = new JMenu("Datos");
		menuBar.add(mnIngresoDeDatos);
		
		JMenuItem mntmAgregarFolios = new JMenuItem("Agregar Folio");
		mnIngresoDeDatos.add(mntmAgregarFolios);
		
		JMenuItem mntmBorrarFolio = new JMenuItem("Borrar Folio");
		mnIngresoDeDatos.add(mntmBorrarFolio);
		
		JMenuItem mntmAgregarRevision = new JMenuItem("Agregar Revision");
		mnIngresoDeDatos.add(mntmAgregarRevision);
		
		JMenu mnListados = new JMenu("Listados");
		menuBar.add(mnListados);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Listar Folios");
		mnListados.add(mntmNewMenuItem);
		
		JMenuItem mntmListarRevisiones = new JMenuItem("Listar Revisiones");
		mnListados.add(mntmListarRevisiones);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Ver Descripcion");
		mnListados.add(mntmNewMenuItem_1);
		
		JMenuItem mntmVerFolioMas = new JMenuItem("Ver Folio mas Revisado");
		mnListados.add(mntmVerFolioMas);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {},
			new RowSpec[] {}));
	}

}
