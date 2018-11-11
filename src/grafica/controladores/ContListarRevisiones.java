package grafica.controladores;

import java.rmi.RemoteException;

import grafica.ventanas.VentListarRevisiones;
import logica.IFachada;
import logica.excepciones.ExcepAccesoADatos;
import logica.excepciones.ExcepFolioNoExiste;
import logica.excepciones.ExcepFolioSinRevisiones;
import logica.valueObjects.ListaVORevisiones;
import logica.valueObjects.VORevision;

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
