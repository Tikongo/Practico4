package logicaPersistencia.accesoBD;

import logicaPersistencia.valueObjects.*;

public class AccesoBD {
	
	public void agregarFolio() {
		
	}
	
	public void agregarRevision() {
		
	}
	
	public void borrarFolioRevisiones() {
		
	}
	
	public String darDescripcion() {
		String desc = null;
		return desc;
	}
	
	public ListaVOFolios listarFolios() {
		ListaVOFolios listaVoF = new ListaVOFolios();
		return listaVoF;
	}
	
	public ListaVORevisiones listarRevisiones() {
		ListaVORevisiones listaVoRev = new ListaVORevisiones();
		return listaVoRev;
	}
	
	public VOFolio folioMasRevisado() {
		VOFolio folioMasRev = new VOFolio();
		return folioMasRev;
	}
}
