package grafica.controladores;


import grafica.ventanas.VentDarDescripcion;
import java.rmi.RemoteException;
import logica.IFachada;
import logica.excepciones.ExcepAccesoADatos;
import logica.excepciones.ExcepFolioNoExiste;
import logica.excepciones.ExcepRevisionNoExiste;



public class ContDarDescripcion {
	
	private VentDarDescripcion ventana;
	private IFachada interfazFachada;
	
	public ContDarDescripcion(VentDarDescripcion v) {
		ventana = v;
		interfazFachada = ContSingletonIFachada.getInstancia().getInterfazFachada();
	}
	
	public String darDescripcion(String codF, int nroD) {
		String desc = null;
		try {
			desc = interfazFachada.darDescripcion(codF, nroD);
		} catch (ExcepAccesoADatos e) {
			ventana.mostrarError(e.darMensaje());
		} catch (ExcepFolioNoExiste e) {
			ventana.mostrarError(e.darMensaje());
		} catch (ExcepRevisionNoExiste e) {
			ventana.mostrarError(e.darMensaje());
		} catch (RemoteException remEx) {
			ventana.mostrarError(remEx.toString());
		} 
		return desc;
	}
}
