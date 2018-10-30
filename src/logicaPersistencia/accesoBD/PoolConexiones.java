package logicaPersistencia.accesoBD;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import logicaPersistencia.excepciones.*;

public class PoolConexiones implements IPoolConexiones {
	
	private String driver;
	private String url;
	private String user;
	private String pwd;
	private int nivelTran;
	private int TAM;
	private int tope;
	private int creadas;
	private Conexion arrConexiones[];
	
	public PoolConexiones() throws ExcepAccesoADatos {
		/*Cargar tamaño de archivo de propiedades.*/
		Properties prop = new Properties();
		InputStream input = null;
		
		try {

			input = new FileInputStream("dbEstudioJuridico.properties");

			// Cargo el archivo.
			prop.load(input);

			// Cargo los valores del archivo de propiedades en cada variable.
			url = prop.getProperty("database");
			user = prop.getProperty("dbuser");
			pwd = prop.getProperty("dbpassword");
			driver = prop.getProperty("driver");
			TAM = Integer.parseInt(prop.getProperty("TAM"));
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		arrConexiones= new Conexion[TAM];
		tope = 0;
		creadas = 0;
	}
	
	public IConexion obtenerConexion(Boolean t) throws SQLException, ClassNotFoundException {
		/* t=TRUE: MODIFICACION.
		 * t=FALSE: LECTURA.*/
		Conexion con = null;
		/*PASO 1: Ver si hay conexiones libres.*/
		if (tope > 0) {
			//Devuelvo la conexión que está al final del arreglo.
			con = arrConexiones[tope-1];
			if (t) {
				con.getConexion().setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			} else {
				con.getConexion().setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			}
			tope = tope - 1;
		} else {
			/*PASO 2: Ver si puedo crear una conexión nueva.*/
			if (creadas < TAM) {
				//Creo una nueva conexión y la devuelvo.
				Class.forName(driver);
				Connection c = DriverManager.getConnection(url,user,pwd);
				con = new Conexion(c);
				creadas = creadas + 1;
				if (t) {
					con.getConexion().setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				} else {
					con.getConexion().setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
				}
			} else {
				/*PASO 3: Mandar a dormir al usuario.*/
			}
		}
		return con;
	}
	
	public void liberarConexion(IConexion iC, Boolean t) {
		/*HACER*/
	}
}
