package logica;

import java.util.ArrayList;
import java.util.List;

import logicaPersistencia.accesoBD.IConexion;
import logicaPersistencia.excepciones.ExcepPersistencia;
import logicaPersistencia.valueObjects.VOFolio;
import logicaPersistencia.valueObjects.VORevision;
import persistencia.daos.DAORevisiones;

public class Folio {
	
	private String codigo;
	private String caratula;
	private int paginas;
	private DAORevisiones secuencia;
		
	public Folio(String codigo, String caratula, int paginas) {
		super();
		this.codigo = codigo;
		this.caratula = caratula;
		this.paginas = paginas;
		this.secuencia = new DAORevisiones(codigo);
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

	public boolean tieneRevision(IConexion icon,int numR) throws ExcepPersistencia{
		boolean tiene =false;
		Revision rev=secuencia.kesimo(icon, numR);
		if (rev!=null)
			tiene=true;
		return tiene;
	}
	
	public int cantidadRevisiones(IConexion icon) throws ExcepPersistencia{
		int cant=0;
		try {
			cant= secuencia.largo(icon);
		} catch (ExcepPersistencia e) {
			throw new ExcepPersistencia("Error de conexion");
		}
		return cant;
	}
	
	public void addRevision (IConexion icon,Revision rev) throws ExcepPersistencia{
		try {
			secuencia.insBack(icon, rev);
		} catch (ExcepPersistencia e) {
			throw new ExcepPersistencia("Error de conexion");
		}
	}
	
	public Revision obtenerRevision(IConexion icon,int numR) throws ExcepPersistencia {
		Revision rev=null;
		try {
			rev=secuencia.kesimo(icon, numR);
		} catch (ExcepPersistencia e) {
			throw new ExcepPersistencia("Error de conexion");
		}
		return rev; 
	}
	
	public List<VORevision> listarRevisiones(IConexion icon) throws ExcepPersistencia{
	
		List<VORevision> listaRevisiones=null;
		try {
			listaRevisiones = secuencia.listarRevisiones(icon);
		} catch (ExcepPersistencia e) {
			throw new ExcepPersistencia("Error de conexion");
		}
		return listaRevisiones;
	}
	
	public void borrarRevisiones(IConexion icon) throws ExcepPersistencia{
		try {
			secuencia.borrarRevisiones(icon);
		} catch (ExcepPersistencia e) {
			throw new ExcepPersistencia("Error de conexion");
		}
	}

}
