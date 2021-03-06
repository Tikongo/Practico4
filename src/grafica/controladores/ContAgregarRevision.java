package grafica.controladores;

import java.rmi.RemoteException;
import grafica.ventanas.VentAgregarRevision;
import logica.IFachada;
import logica.excepciones.ExcepAccesoADatos;
import logica.excepciones.ExcepFolioNoExiste;
import logica.excepciones.ExcepFolioYaExiste;

public class ContAgregarRevision {
	
	private IFachada interfazFachada;
	private VentAgregarRevision ventana;
	
	public ContAgregarRevision(VentAgregarRevision v) {
		ventana = v;
		interfazFachada = ContSingletonIFachada.getInstancia().getInterfazFachada();
	}
	
	public void agregarRevision(String cod, String desc) {
		try {
			interfazFachada.agregarRevision(cod,desc);
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