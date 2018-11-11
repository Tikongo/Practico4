package grafica.controladores;

import java.rmi.RemoteException;

import grafica.ventanas.VentListarFolios;
import logica.IFachada;
import logica.excepciones.ExcepAccesoADatos;
import logica.excepciones.ExcepNoHayFoliosRegistrados;
import logica.valueObjects.ListaVOFolios;
import logica.valueObjects.VOFolio;

public class ContListarFolios {
	
	private IFachada interfazFachada;
	private VentListarFolios ventana;
	
	public ContListarFolios(VentListarFolios v) {
		ventana = v;
		interfazFachada = ContSingletonIFachada.getInstancia().getInterfazFachada();
	}
	
	public void listarFolios() {
		ListaVOFolios lVoF = new ListaVOFolios();
		try {
			lVoF = interfazFachada.listarFolios();
			ventana.armarTablaFolios(lVoF);
		} catch (ExcepAccesoADatos e) {
			ventana.mostrarError(e.darMensaje());
		} catch (ExcepNoHayFoliosRegistrados e) {
			ventana.mostrarError(e.darMensaje());
		} catch (RemoteException remEx) {
			ventana.mostrarError(remEx.toString());
		}
	}
}
