package grafica.controladores;

import java.rmi.RemoteException;
import logicaPersistencia.IFachada;
import logicaPersistencia.excepciones.*;
import grafica.ventanas.VentListarFolios;
import logicaPersistencia.valueObjects.VOFolio;
import logicaPersistencia.valueObjects.ListaVOFolios;

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
