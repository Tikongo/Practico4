package grafica.ventanas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentPrincipal {

	private JFrame ventPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentPrincipal window = new VentPrincipal();
					window.ventPrincipal.setVisible(true);
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
		ventPrincipal = new JFrame();
		ventPrincipal.setResizable(false);
		ventPrincipal.setTitle("Expedienpress - Principal");
		ventPrincipal.setBounds(100, 100, 584, 304);
		ventPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ventPrincipal.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				JFrame frame = (JFrame)e.getSource();
				int result = JOptionPane.showConfirmDialog(frame,"¿Está seguro que desea cerrar Expedienpress?","Cerrar Expedienpress",JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}  
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		ventPrincipal.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnArchivo.add(mntmSalir);
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == mntmSalir) {
					int result = JOptionPane.showConfirmDialog(ventPrincipal,"¿Está seguro que desea cerrar Expedienpress?","Cerrar Expedienpress",JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						ventPrincipal.dispose();
					}
				}
			}
		});	
		
		/*MENU INGRESO DE DATOS*/
		JMenu mnIngresoDeDatos = new JMenu("Datos");
		menuBar.add(mnIngresoDeDatos);
		
		JMenuItem mntmAgregarFolios = new JMenuItem("Agregar Folio");
		mnIngresoDeDatos.add(mntmAgregarFolios);
		mntmAgregarFolios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentAgregarFolio ventAgregarFolio = new VentAgregarFolio();
				ventAgregarFolio.setVisible(true);
			}
		});
		
		JMenuItem mntmBorrarFolio = new JMenuItem("Borrar Folio");
		mnIngresoDeDatos.add(mntmBorrarFolio);
		mntmBorrarFolio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentBorrarFolio ventBorrarFolio = new VentBorrarFolio();
				ventBorrarFolio.setVisible(true);
			}
		});
		
		JMenuItem mntmAgregarRevision = new JMenuItem("Agregar Revision");
		mnIngresoDeDatos.add(mntmAgregarRevision);
		mntmAgregarRevision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentAgregarRevision ventAgregarRevision = new VentAgregarRevision();
				ventAgregarRevision.setVisible(true);
			}
		});
		
		JMenu mnListados = new JMenu("Listados");
		menuBar.add(mnListados);
		
		JMenuItem mntmListarFolios = new JMenuItem("Listar Folios");
		mnListados.add(mntmListarFolios);
		mntmListarFolios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentListarFolios ventListarFolios = new VentListarFolios();
				ventListarFolios.setVisible(true);
			}
		});
		
		JMenuItem mntmListarRevisiones = new JMenuItem("Listar Revisiones");
		mnListados.add(mntmListarRevisiones);
		mntmListarRevisiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentListarRevisiones ventListarRevisiones = new VentListarRevisiones();
				ventListarRevisiones.setVisible(true);
			}
		});
		
		JMenuItem mntmDarDescripcion = new JMenuItem("Ver Descripcion");
		mnListados.add(mntmDarDescripcion);
		mntmDarDescripcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentDarDescripcion ventDarDescripcion = new VentDarDescripcion();
				ventDarDescripcion.setVisible(true);
			}
		});
		
		JMenuItem mntmVerFolioMas = new JMenuItem("Ver Folio mas Revisado");
		mnListados.add(mntmVerFolioMas);
		ventPrincipal.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
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
				FormFactory.DEFAULT_ROWSPEC,}));
		mntmVerFolioMas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentFolioMasRevisado ventFolioMasRevisado = new VentFolioMasRevisado();
				ventFolioMasRevisado.setVisible(true);
			}
		});
		
		JLabel lblExpedienpress = new JLabel("EXPEDIENPRESS");
		lblExpedienpress.setFont(new Font("Agency FB", Font.BOLD, 55));
		ventPrincipal.getContentPane().add(lblExpedienpress, "6, 6, center, default");
		
		JLabel lblSoftwareParaEstudio = new JLabel("Software para Estudios Jur\u00EDdicos");
		lblSoftwareParaEstudio.setFont(new Font("Agency FB", Font.PLAIN, 24));
		ventPrincipal.getContentPane().add(lblSoftwareParaEstudio, "6, 8, center, default");
	}
	
	public void setVisible(boolean valor)
	{
		ventPrincipal.setVisible(valor);
	}

}
