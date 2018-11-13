package logica;

import java.util.List;
import java.util.Properties;
import logica.excepciones.*;
import logica.valueObjects.*;
import java.io.*;
import java.util.ArrayList;
import persistencia.accesoDatos.IConexion;
import persistencia.accesoDatos.IPoolConexiones;
import persistencia.daos.*;
import persistencia.Fabricas.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

public class Fachada extends UnicastRemoteObject implements IFachada {
	
	private IPoolConexiones ipool;
	private IDAOFolios folios;
	private static final long serialVersionUID = 1L;
	private static Fachada instanciaFachada;

	public Fachada() throws RemoteException {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("dbEstudioJuridico.properties");
			String poolConcreto = prop.getProperty("nombrePool");
			ipool = (IPoolConexiones) Class.forName(poolConcreto).newInstance();
			String nomFabrica = prop.getProperty("persistencia");
			FabricaAbstracta fabrica = (FabricaAbstracta) Class.forName(nomFabrica).newInstance();
			folios = fabrica.crearIDAOFolios();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Fachada getInstancia() {
		if(instanciaFachada==null)
		{
			try {
				instanciaFachada= new Fachada();
			} catch(RemoteException remEx) {
				remEx.printStackTrace();
			}
		}		
		return instanciaFachada;
	}
	
	/* (non-Javadoc)
	 * @see logicaPersistencia.IFachada#agregarFolio(logicaPersistencia.valueObjects.VOFolio)
	 */
	@Override
	public void agregarFolio(VOFolio voF) throws ExcepFolioYaExiste,ExcepAccesoADatos, RemoteException{
		IConexion iCon = null;
		String msjError="";
		boolean existeCodigo=false;
		boolean errorPersistencia=false;
		
			try {
				iCon = ipool.obtenerConexion(true);
				String codigo = voF.getCodigo();
				String caratula = voF.getCaratula();
				int paginas = voF.getPaginas();
				
				//AccesoBD abd = new AccesoBD();
				//boolean existeCodigo =  abd.existeFolio(con, codigo);
				
				existeCodigo = folios.member(iCon, codigo);
				if(!existeCodigo) {
					Folio fol = new Folio(codigo,caratula,paginas);
					folios.insert(iCon, fol);
				} else {
					msjError = "Folio ya ingresado";
				}
				ipool.liberarConexion(iCon, true);	
			}  catch (ExcepAccesoADatos e) {
				ipool.liberarConexion(iCon, false);
				errorPersistencia=true;
				msjError = "Error de Acceso a los datos";
			}
			finally {
				if (existeCodigo) { 
					throw new ExcepFolioYaExiste(msjError);
				}
				if (errorPersistencia) {
					throw new ExcepAccesoADatos(msjError);
				}
			}
		}
	
	/* (non-Javadoc)
	 * @see logicaPersistencia.IFachada#agregarRevision(java.lang.String, java.lang.String)
	 */
	@Override
	/*REVISAR!!!*/
	public void agregarRevision(String codF,String descripcion) throws ExcepFolioNoExiste,ExcepAccesoADatos, RemoteException {
		IConexion iCon=null;
		String msjError="";
		boolean existeCodigo=false;
		boolean errorPersistencia=false;
		try {
			/*con = DriverManager.getConnection(urlBD, userBD, pwdBD);
			  con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			  con.setAutoCommit(false);*/
			iCon = ipool.obtenerConexion(true);
			
			existeCodigo = folios.member(iCon, codF);
			
			if(existeCodigo) {
				Folio fol=folios.find(iCon, codF);
				Revision rev = new Revision(fol.cantidadRevisiones(iCon), descripcion);
				fol.addRevision(iCon, rev);
			} else {
				msjError = "No existe Folio";  
			}
			ipool.liberarConexion(iCon, true);	
			
		} catch (ExcepAccesoADatos e) {
			ipool.liberarConexion(iCon, false);
			errorPersistencia=true;
			msjError = "Error de Acceso a los datos";
		} finally {
			if (!existeCodigo) { 
				throw new ExcepFolioNoExiste(msjError);
			}
			if (errorPersistencia) {
				throw new ExcepAccesoADatos(msjError);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see logicaPersistencia.IFachada#borrarFolioRevisiones(java.lang.String)
	 */
	@Override   //precondicion que el folio con ese coÌ�digo esteÌ� registrado.
	public void borrarFolioRevisiones(String codF)throws ExcepAccesoADatos, ExcepFolioNoExiste, RemoteException {
		IConexion icon=null;
		String msjError="";
		boolean existeCodigo=false;
		boolean errorPersistencia=false;
		try {
			icon = ipool.obtenerConexion(true);
			existeCodigo = folios.member(icon, codF);
			if(existeCodigo) {
				Folio fol = folios.find(icon, codF);
				fol.borrarRevisiones(icon);
				folios.delete(icon, codF);
			} else {
				msjError = "Folio no registrado";  
			}
			ipool.liberarConexion(icon, true);	
			
		} catch (ExcepAccesoADatos e) {
			ipool.liberarConexion(icon, false);
			errorPersistencia=true;
			msjError = "Error de Acceso a los datos";
		}
		finally {
			if (!existeCodigo) { 
				throw new ExcepFolioNoExiste(msjError);
			}
			if (errorPersistencia) {
				throw new ExcepAccesoADatos(msjError);
			}
		}
		
	}
	
	/* (non-Javadoc)
	 * @see logicaPersistencia.IFachada#darDescripcion(java.lang.String, int)
	 */
	@Override   //precondicion que el folio exista y tenga una revisioÌ�n con ese nuÌ�mero
	public String darDescripcion(String codF,int numR) throws ExcepAccesoADatos, RemoteException, ExcepFolioNoExiste, ExcepRevisionNoExiste{
		String desc = null;
		IConexion icon=null;
		String msjError="Error de Acceso a los datos";
		try {
			icon = ipool.obtenerConexion (true);
			Folio fol=folios.find(icon, codF);
			Revision rev=fol.obtenerRevision(icon, numR);
			desc=rev.getDescripcion();
			ipool.liberarConexion (icon, true);
		}catch(ExcepAccesoADatos e) {
			if (icon != null) 
				ipool.liberarConexion (icon, false);
            throw new ExcepAccesoADatos(msjError);
		}
		return desc;
	}
	
	/* (non-Javadoc)
	 * @see logicaPersistencia.IFachada#listarFolios()
	 */
	@Override
	public ListaVOFolios listarFolios() throws ExcepAccesoADatos, RemoteException, ExcepNoHayFoliosRegistrados{
		ListaVOFolios listaFolios = null;
		IConexion icon=null;
		String msjError="Error de Acceso a los datos";
		try {
			icon = ipool.obtenerConexion (true);
			listaFolios=(ListaVOFolios) folios.listarFolios(icon);
			ipool.liberarConexion (icon, true);
			
		}catch(ExcepAccesoADatos e) {
			if (icon != null) 
				ipool.liberarConexion (icon, false);
            throw new ExcepAccesoADatos(msjError);
		}
		
		return listaFolios;
	}
	
	/* (non-Javadoc)
	 * @see logicaPersistencia.IFachada#listarRevisiones()
	 */
	@Override   //precondicion que el folio este registrado.
	public ListaVORevisiones listarRevisiones(String codF) throws ExcepAccesoADatos, RemoteException, ExcepFolioNoExiste, ExcepFolioSinRevisiones{
		ListaVORevisiones listaRevisiones = null;
		IConexion icon=null;
		String msjError="Error de Acceso a los datos";
		try {
			icon = ipool.obtenerConexion (true);
			Folio fol=folios.find(icon, codF);
			listaRevisiones=(ListaVORevisiones) fol.listarRevisiones(icon);
			ipool.liberarConexion (icon, true);
			
		}catch(ExcepAccesoADatos e) {
			if (icon != null) 
				ipool.liberarConexion (icon, false);
            throw new ExcepAccesoADatos(msjError);
		}
		return listaRevisiones;
	}
	
	/* (non-Javadoc)
	 * @see logicaPersistencia.IFachada#folioMasRevisado()
	 */
	@Override  //precondicion existe al menos un folio.
	public VOFolioMaxRev folioMasRevisado() throws ExcepAccesoADatos, RemoteException {
		VOFolioMaxRev voFMR = null;
		IConexion icon=null;
		String msjError="Error de Acceso a los datos";
		try {
			icon = ipool.obtenerConexion (true);
			voFMR=folios.folioMasRevisado(icon);
			ipool.liberarConexion (icon, true);
			
		}catch(ExcepAccesoADatos e) {
			if (icon != null) 
				ipool.liberarConexion (icon, false);
            throw new ExcepAccesoADatos(msjError);
		}
		return voFMR;
	}
	
}
