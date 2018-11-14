package persistencia.accesoDatos;

import java.util.Properties;
import logica.excepciones.ExcepAccesoADatos;
import persistencia.accesoDatos.*;

import java.io.*;
import java.sql.Connection;

public class PoolConexionesArchivo implements IPoolConexiones {
	
	private String[] archivoPath; 
		/*Posicion[0]=Folios
		  Posicion[1]=Revisiones*/
	private int TAM;
	private int tope;
	private int creadas;
	private Conexion arrConexiones[];
	
	public PoolConexionesArchivo() throws ExcepAccesoADatos {
		Properties prop = new Properties();
		InputStream input = null;
		archivoPath = new String[2];
		try {
			input = new FileInputStream("dbEstudioJuridico.properties");
			prop.load(input);
			archivoPath[0] = prop.getProperty("pathFolios");
			archivoPath[1] = prop.getProperty("pathRevisiones");
			TAM = Integer.parseInt(prop.getProperty("TAM"));
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExcepAccesoADatos("Error de acceso a los datos");
		}
		arrConexiones= new Conexion[TAM];
		tope = 0;
		creadas = 0;
	}
	
	public synchronized IConexion obtenerConexion(Boolean t) throws ExcepAccesoADatos {
		/* t=TRUE: ESCRITURA.
		 * t=FALSE: LECTURA.*/
		Conexion<String[]> con = null;
		if (tope > 0) {
			//Devuelvo la conexi�n que est� al final del arreglo.
			con = arrConexiones[tope-1];
			tope = tope - 1;
		} else {
			if (creadas < TAM) {
				con = (Conexion<String[]>) new Conexion(archivoPath);
				creadas = creadas + 1;
			} else {
				//A dormir.
				try {
					wait();
				} catch (InterruptedException iExc) { }
			}
		}
		return con;
	}
	
	public synchronized void liberarConexion(IConexion iC, Boolean t) {
		Conexion<String[]> conex = (Conexion<String[]>)iC;
		arrConexiones[tope] = conex;
		tope++;
		notify();
	}
}
