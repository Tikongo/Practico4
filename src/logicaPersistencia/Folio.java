package logicaPersistencia;

import java.util.ArrayList;
import java.util.List;

import logicaPersistencia.valueObjects.VOFolio;
import logicaPersistencia.valueObjects.VORevision;
import persistencia.daos.DAORevisiones;

public class Folio {
	
	private String codigo;
	private String caratula;
	private int paginas;
	private DAORevisiones secuencia;
	
	
	public Folio(String codigo, String caratula, int paginas, DAORevisiones secuencia) {
		super();
		this.codigo = codigo;
		this.caratula = caratula;
		this.paginas = paginas;
		this.secuencia = secuencia;
	}


	public String getCodigo() {
		return codigo;
	}



	public String getCaratula() {
		return caratula;
	}


	public int getPaginas() {
		return paginas;
	}


	public boolean tieneRevision(int numR) {
		boolean tiene =false;
		
		return tiene;
	}
	
	public int cantidadRevisiones() {
		int cant=0;
		return cant;
	}
	
	public void addRevision (Revision rev) {
		
	}
	
	public Revision obtenerRevision(int numR) {
		Revision rev=null;
		return rev;
	}
	
	public List<VORevision> listarRevisiones(){
	
		List<VORevision> listaRevisiones = new ArrayList<>();
		return listaRevisiones;
	}
	
	public void borrarRevisiones() {
		
	}

}
