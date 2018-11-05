package grafica.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class VentAgregarFolio {

	private JFrame ventAgregarFolio;
	

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
		ventAgregarFolio.setBounds(100, 100, 450, 300);
		ventAgregarFolio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void mostrarResultado(String res)
	{
		JOptionPane.showMessageDialog(ventAgregarFolio, res, "Resultado", JOptionPane.INFORMATION_MESSAGE);
		ventAgregarFolio.dispose();
	}
	
	public void mostrarError(String res)
	{
		JOptionPane.showMessageDialog(ventAgregarFolio, res, "Resultado", JOptionPane.ERROR_MESSAGE);
	}

}
