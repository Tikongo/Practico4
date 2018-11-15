package logica;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import logica.excepciones.ExcepAccesoADatos;
import logica.valueObjects.ListaVORevisiones;
import logica.valueObjects.VOFolio;
import logica.valueObjects.VORevision;
import persistencia.accesoDatos.IConexion;
import persistencia.daos.*;
import persistencia.Fabricas.*;

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
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("dbEstudioJuridico.properties");
			prop.load(input);
			String metodo = prop.getProperty("metodoPersistencia").toString();
			if (metodo.equalsIgnoreCase("archivo")) {
				String nomFabrica = "persistencia.Fabricas.FabricaArchivo";
				FabricaAbstracta fabrica = (FabricaAbstracta) Class.forName(nomFabrica).newInstance();
				this.secuencia = fabrica.crearIDAORevisiones(codigo);
			}
			if (metodo.equalsIgnoreCase("mysql")) {
				String nomFabrica = "persistencia.Fabricas.FabricaMySQL";
				FabricaAbstracta fabrica = (FabricaAbstracta) Class.forName(nomFabrica).newInstance();
				this.secuencia = fabrica.crearIDAORevisiones(codigo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
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
			cant = secuencia.largo(icon);
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
	
	public ListaVORevisiones listarRevisiones(IConexion icon) throws ExcepAccesoADatos {
		ListaVORevisiones listaRevisiones=null;
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
