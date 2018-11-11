package persistencia.accesoDB;

import java.util.Properties;
import logica.excepciones.ExcepAccesoADatos;
import java.io.*;
import persistencia.accesoDB.*;

public class PoolConexionesArchivo implements IPoolConexiones {
	
	private String archivoPath;
	private int TAM;
	private int tope;
	private int creadas;
	private ConexionArchivo arrConexiones[];
	
	public PoolConexionesArchivo() throws ExcepAccesoADatos {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("dbEstudioJuridico.properties");
			prop.load(input);
			archivoPath = prop.getProperty("path");
			TAM = Integer.parseInt(prop.getProperty("TAM"));
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExcepAccesoADatos("Error de acceso a los datos");
		}
		arrConexiones= new ConexionArchivo[TAM];
		tope = 0;
		creadas = 0;
	}
	
	public synchronized IConexion obtenerConexion(boolean t) throws ExcepAccesoADatos{
		/* t=TRUE: ESCRITURA.
		 * t=FALSE: LECTURA.*/
		
	}
	
	public synchronized void liberarConexion(IConexion iC, Boolean t) {
		
	}
}
