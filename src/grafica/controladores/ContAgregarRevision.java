package grafica.controladores;

import java.rmi.RemoteException;

import logicaPersistencia.excepciones.ExcepAccesoADatos;
import logicaPersistencia.excepciones.ExcepFolioNoExiste;
import logicaPersistencia.excepciones.ExcepFolioYaExiste;
import grafica.ventanas.VentAgregarRevision;
import logica.IFachada;
import logicaPersistencia.excepciones.*;

public class ContAgregarRevision {
	
	private IFachada interfazFachada;
	private VentAgregarRevision ventana;
	
	public ContAgregarRevision(VentAgregarRevision v) {
		ventana = v;
		interfazFachada = ContSingletonIFachada.getInstancia().getInterfazFachada();
	}
	
	public void agregarRevision(String cod, String desc) {
		try {
			interfazFachada.agregarRevision(cod, desc);
			ventana.mostrarResultado("Revision ingresada exitosamente");
		} catch (ExcepFolioNoExiste e) {
			ventana.mostrarError(e.darMensaje());
		} catch (ExcepAccesoADatos e) {
			ventana.mostrarError(e.darMensaje());
		} catch (RemoteException remEx) {
			ventana.mostrarError(remEx.toString());
		}
	}
}
