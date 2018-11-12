package persistencia.accesoDatos;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import logica.excepciones.ExcepAccesoADatos;

public class PoolConexiones implements IPoolConexiones {
	
	private String tipoPersistencia;
	private String driver;
	private String url;
	private String user;
	private String pwd;
	private String archivoPath;
	private int nivelTran;
	private int TAM;
	private int tope;
	private int creadas;
	private Conexion arrConexiones[];
	
	
	private Conexion setTransactionIsolation (boolean t, Conexion con) {
		Conexion<Connection> conex = con;
		if (t) {
			try {
				conex.getConexion().setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				conex.getConexion().setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conex;
	}
	
	public PoolConexiones() throws ExcepAccesoADatos {
		/*Cargar tama�o de archivo de propiedades.*/
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
			Class.forName(driver);
			
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new ExcepAccesoADatos("Error de acceso a los datos");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ExcepAccesoADatos("Error de acceso a los datos");
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new ExcepAccesoADatos("Error de acceso a los datos");
				}
			}
		}
		arrConexiones = new Conexion[TAM];
		tope = 0;
		creadas = 0;
	}
	
	public synchronized IConexion obtenerConexion(Boolean t) throws ExcepAccesoADatos{
		/* t=TRUE: MODIFICACION.
		 * t=FALSE: LECTURA.*/
		Conexion<Connection> con = null;
		/*PASO 1: Ver si hay conexiones libres.*/
		try {
			if (tope > 0) {
				//Devuelvo la conexi�n que est� al final del arreglo.
				con = arrConexiones[tope-1];
				con = setTransactionIsolation(t, con);
				tope = tope - 1;
			} else {
				/*PASO 2: Ver si puedo crear una conexi�n nueva.*/
				if (creadas < TAM) {
					//Creo una nueva conexi�n y la devuelvo.
					Connection c = null;
					try {
						c = DriverManager.getConnection(url,user,pwd);
					} catch (SQLException e) {
						e.printStackTrace();
						throw new ExcepAccesoADatos("Error de Acceso a la BD");
					}
					con = (Conexion<Connection>) new Conexion(c);
					creadas = creadas + 1;
					con = setTransactionIsolation(t, con);
					con.getConexion().setAutoCommit(false);
				} else {
					/*PASO 3: Mandar a dormir al usuario.*/
					try {
						wait();
					} catch(InterruptedException iExc) { }
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ExcepAccesoADatos("Error de Acceso a la BD");
		}
		return con;
	}
	
	public synchronized void liberarConexion(IConexion iC, Boolean t) throws ExcepAccesoADatos {
		Conexion<Connection> conex = (Conexion<Connection>)iC;
		try {
			if (t) {
				conex.getConexion().commit();
			} else {
				conex.getConexion().rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ExcepAccesoADatos("Error al acceder a los datos");
		}
		arrConexiones[tope] = conex;
		tope++;
		notify();
	}
}
