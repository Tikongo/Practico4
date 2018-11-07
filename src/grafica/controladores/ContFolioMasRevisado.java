package grafica.controladores;

import java.rmi.RemoteException;
import logica.IFachada;
import logicaPersistencia.excepciones.*;
import grafica.ventanas.VentFolioMasRevisado;
import logicaPersistencia.valueObjects.VOFolioMaxRev;

public class ContFolioMasRevisado {
	
	private VentFolioMasRevisado ventana;
	private IFachada interfazFachada;
	
	public ContFolioMasRevisado(VentFolioMasRevisado v) {
		ventana = v;
		interfazFachada = ContSingletonIFachada.getInstancia().getInterfazFachada();
	}
	
	public VOFolioMaxRev folioMasRevisado() {
		VOFolioMaxRev voFMR = null;
		try {
			voFMR = interfazFachada.folioMasRevisado();
		} catch (ExcepAccesoADatos e) {
			ventana.mostrarError(e.darMensaje());
		} catch (RemoteException remEx) {
			ventana.mostrarError(remEx.toString());
		}
		return voFMR;
	}
	
}
