package persistencia.accesoDatos;

import java.util.Properties;
import logica.excepciones.ExcepAccesoADatos;
import persistencia.accesoDatos.*;

import java.io.*;
import java.sql.Connection;

public class PoolConexionesArchivo implements IPoolConexiones {
	
	private String archivoPath;
	private int TAM;
	private int tope;
	private int creadas;
	private Conexion arrConexiones[];
	
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
		arrConexiones= new Conexion[TAM];
		tope = 0;
		creadas = 0;
	}
	
	public synchronized IConexion obtenerConexion(Boolean t) throws ExcepAccesoADatos{
		/* t=TRUE: ESCRITURA.
		 * t=FALSE: LECTURA.*/
		Conexion<FileWriter> con = null;
		if (tope > 0) {
			//Devuelvo la conexi�n que est� al final del arreglo.
			con = arrConexiones[tope-1];
			tope = tope - 1;
		} else {
			if (creadas < TAM) {
				FileWriter f = null;
				con = (Conexion<FileWriter>) new Conexion(f);
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
		Conexion<FileWriter> conex = (Conexion<FileWriter>)iC;
		arrConexiones[tope] = conex;
		tope++;
		notify();
	}
}
