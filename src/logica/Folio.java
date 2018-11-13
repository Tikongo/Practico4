package logica;

import java.util.ArrayList;
import java.util.List;

import logica.excepciones.ExcepAccesoADatos;
import logica.valueObjects.VOFolio;
import logica.valueObjects.VORevision;
import persistencia.accesoDatos.IConexion;
import persistencia.daos.DAORevisiones;
import persistencia.daos.IDAORevisiones;

public class Folio {
	
	private String codigo;
	private String caratula;
	private int paginas;
	private IDAORevisiones secuencia;
		
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

	public boolean tieneRevision(IConexion icon,int numR) throws ExcepAccesoADatos{
		boolean tiene =false;
		Revision rev=secuencia.kesimo(icon, numR);
		if (rev!=null)
			tiene=true;
		return tiene;
	}
	
	public int cantidadRevisiones(IConexion icon) throws ExcepAccesoADatos{
		int cant=0;
		try {
			cant= secuencia.largo(icon);
		} catch (ExcepAccesoADatos e) {
			throw new ExcepAccesoADatos("Error de conexion");
		}
		return cant;
	}
	
	public void addRevision (IConexion icon,Revision rev) throws ExcepAccesoADatos{
		try {
			secuencia.insBack(icon, rev);
		} catch (ExcepAccesoADatos e) {
			throw new ExcepAccesoADatos("Error de conexion");
		}
	}
	
	public Revision obtenerRevision(IConexion icon,int numR) throws ExcepAccesoADatos {
		Revision rev=null;
		try {
			rev=secuencia.kesimo(icon, numR);
		} catch (ExcepAccesoADatos e) {
			throw new ExcepAccesoADatos("Error de conexion");
		}
		return rev; 
	}
	
	public List<VORevision> listarRevisiones(IConexion icon) throws ExcepAccesoADatos{
	
		List<VORevision> listaRevisiones=null;
		try {
			listaRevisiones = secuencia.listarRevisiones(icon);
		} catch (ExcepAccesoADatos e) {
			throw new ExcepAccesoADatos("Error de conexion");
		}
		return listaRevisiones;
	}
	
	public void borrarRevisiones(IConexion icon) throws ExcepAccesoADatos{
		try {
			secuencia.borrarRevisiones(icon);
		} catch (ExcepAccesoADatos e) {
			throw new ExcepAccesoADatos("Error de conexion");
		}
	}

	public IDAORevisiones getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(IDAORevisiones secuencia) {
		this.secuencia = secuencia;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setCaratula(String caratula) {
		this.caratula = caratula;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

}
