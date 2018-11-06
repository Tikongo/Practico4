package grafica.controladores;

import java.rmi.RemoteException;
import logicaPersistencia.IFachada;
import logicaPersistencia.excepciones.ExcepAccesoADatos;
import logicaPersistencia.excepciones.ExcepFolioNoExiste;
import grafica.ventanas.VentBorrarFolio;

public class ContBorrarFolio {

	private IFachada interfazFachada;
	private VentBorrarFolio ventana;
	
	public ContBorrarFolio(VentBorrarFolio v) {
		ventana = v;
		interfazFachada = ContSingletonIFachada.getInstancia().getInterfazFachada();
	}
	
	public void borrarFolio(String cod) {
		try {
			interfazFachada.borrarFolioRevisiones(cod);
			ventana.mostrarResultado("Folio borrado");
		} catch (ExcepFolioNoExiste e) {
			ventana.mostrarError(e.darMensaje());
		} catch (ExcepAccesoADatos e) {
			ventana.mostrarError(e.darMensaje());
		}
	}
	
}