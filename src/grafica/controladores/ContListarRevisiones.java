package grafica.controladores;

import java.rmi.RemoteException;
import logicaPersistencia.IFachada;
import logicaPersistencia.excepciones.*;
import grafica.ventanas.VentListarRevisiones;
import logicaPersistencia.valueObjects.VORevision;
import logicaPersistencia.valueObjects.ListaVORevisiones;

public class ContListarRevisiones {
	
	private IFachada interfazFachada;
	private VentListarRevisiones ventana;
	
	public ContListarRevisiones(VentListarRevisiones v) {
		ventana = v;
		interfazFachada = ContSingletonIFachada.getInstancia().getInterfazFachada();
	}
	
	public void listarRevisiones(String codF) {
		ListaVORevisiones lVoR = new ListaVORevisiones();
		try {
			lVoR = interfazFachada.listarRevisiones(codF);
			ventana.armarTablaRevisiones(lVoR);
		} catch (ExcepFolioNoExiste e) {
			ventana.mostrarError(e.darMensaje());
		} catch (ExcepFolioSinRevisiones e) {
			ventana.mostrarError(e.darMensaje());
		} catch (ExcepAccesoADatos e) {
			ventana.mostrarError(e.darMensaje());
		} catch (RemoteException remEx) {
			ventana.mostrarError(remEx.toString());
		}
	}
}
