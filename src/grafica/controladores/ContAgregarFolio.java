package grafica.controladores;

import java.rmi.RemoteException;

import grafica.ventanas.VentAgregarFolio;
import logica.IFachada;
import logica.excepciones.ExcepAccesoADatos;
import logica.excepciones.ExcepFolioYaExiste;
import logica.valueObjects.VOFolio;

public class ContAgregarFolio {

	private IFachada interfazFachada;
	private VentAgregarFolio ventana;
	
	public ContAgregarFolio(VentAgregarFolio v) {
		ventana = v;
		interfazFachada = ContSingletonIFachada.getInstancia().getInterfazFachada();
	}
	
	public void agregarFolio(String cod, String car) {
		int pag = 0;
		VOFolio voF = new VOFolio(cod,car,pag);
		try {
			interfazFachada.agregarFolio(voF);
			ventana.mostrarResultado("Folio ingresado exitosamente");
		} catch (ExcepFolioYaExiste e) {
			ventana.mostrarError(e.darMensaje());
		} catch (ExcepAccesoADatos e) {
			ventana.mostrarError(e.darMensaje());
		} catch(RemoteException remEx) {
			ventana.mostrarError(remEx.toString());
		}
	}
}
