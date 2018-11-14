package Main;

import grafica.controladores.ContSingletonIFachada;
import grafica.ventanas.VentPrincipal;
import logica.IFachada;

public class Cliente {

	private IFachada interfazFachada;
	private ContSingletonIFachada singleton;
	
	public static void main(String[] args)
	{
				
			//Llamar ventana
			VentPrincipal vPrinc = new VentPrincipal();
			vPrinc.setVisible(true);
					
		}
}
