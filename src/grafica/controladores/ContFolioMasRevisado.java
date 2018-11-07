package grafica.controladores;

import java.rmi.RemoteException;
import logicaPersistencia.IFachada;
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
		}
		return voFMR;
	}
	
	public int cantidadRevisiones(String codF) {
		int cantR = 0;
		
		return cantR;
	}
	
}
