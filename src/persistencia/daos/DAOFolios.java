package persistencia.daos;

import java.util.ArrayList;
import java.util.List;

import logicaPersistencia.Folio;
import logicaPersistencia.valueObjects.VOFolio;
import logicaPersistencia.valueObjects.VOFolioMaxRev;
import logicaPersistencia.valueObjects.VORevision;

public class DAOFolios {
	
	// atributos para acceso a BD
	
	public DAOFolios() {
		
		
	}
	
	public boolean member(String cod) {
		boolean esta=false;
		return esta;
	} 

	public void insert(Folio fol) {
		
	}
	
	public Folio find(String cod) {
		Folio folio=null;
		return folio;
	}
	
	public void delete (String cod) {
		
	}
	
	public List<VOFolio> listarFolios(){
		
		List<VOFolio> listaFolios = new ArrayList<>();
		return listaFolios;
	}
	
	public boolean esVacio() {
		boolean vacio=false;
		return vacio;
	}
	
	public VOFolioMaxRev folioMasRevisado() {
		VOFolioMaxRev fmr=null;
		return fmr;
	}
	
}
