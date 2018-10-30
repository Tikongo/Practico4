package persistencia.daos;

import java.util.ArrayList;
import java.util.List;

import logicaPersistencia.Revision;
import logicaPersistencia.valueObjects.VOFolio;
import logicaPersistencia.valueObjects.VORevision;

public class DAORevisiones {
	
	//  atributos para acceso a BD
	
	private String codigoFolio;

	public DAORevisiones(String codigoFolio) {
		super();
		this.codigoFolio = codigoFolio;
	}
	
	public void insBack (Revision rev) {
		
	}
	
	public int largo () {
		int largo=0;
		return largo;
	}

	public Revision kesimo(int numero){
		Revision rev=null;
		return rev;
		
	}
	
    public List<VORevision> listarRevisiones(){
		
		List<VORevision> listaRevisiones = new ArrayList<>();
		return listaRevisiones;
	}
    
    public void borrarRevisiones () {
    	
    }
}
