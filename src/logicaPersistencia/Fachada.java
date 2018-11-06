package logicaPersistencia;

import java.util.List;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import logicaPersistencia.accesoBD.AccesoBD;
import logicaPersistencia.accesoBD.IConexion;
import logicaPersistencia.excepciones.ExcepAccesoADatos;
import logicaPersistencia.excepciones.ExcepFolioNoExiste;
import logicaPersistencia.excepciones.ExcepFolioYaExiste;
import logicaPersistencia.excepciones.ExcepNoHayFoliosRegistrados;
import logicaPersistencia.excepciones.ExcepPersistencia;
import logicaPersistencia.excepciones.ExcepRevisionNoExiste;
import logicaPersistencia.accesoBD.PoolConexiones;
import logicaPersistencia.accesoBD.PoolConexiones;
import logicaPersistencia.valueObjects.*;
import persistencia.daos.DAOFolios;

import java.sql.*;

public class Fachada implements IFachada {
	//linea de prueba
	//private String driverBD;
	//private String urlBD;
	//private String userBD;
	//private String pwdBD;
	//private Connection con;
	private PoolConexiones pool;
	private DAOFolios folio;

	public Fachada(){
		/*cargar valores desde archivo de propiedades.*/	
	}
	
	/* (non-Javadoc)
	 * @see logicaPersistencia.IFachada#agregarFolio(logicaPersistencia.valueObjects.VOFolio)
	 */
	@Override
	public void agregarFolio(VOFolio voF) throws ExcepFolioYaExiste, ExcepAccesoADatos ,SQLException{
		IConexion iCon=null;
		String msjError="";
		boolean existeCodigo=false;
		boolean errorPersistencia=false;
		try {
			/*con = DriverManager.getConnection(urlBD, userBD, pwdBD);
			  con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			  con.setAutoCommit(false);*/
			iCon = pool.obtenerConexion(true);
			String codigo = voF.getCodigo();
			String caratula = voF.getCaratula();
			int paginas = voF.getPaginas();
			
			//AccesoBD abd = new AccesoBD();
			//boolean existeCodigo =  abd.existeFolio(con, codigo);
			
			existeCodigo = folio.member(iCon, codigo);
			
			if(!existeCodigo) {
				Folio fol = new Folio(codigo,caratula,paginas);
				folio.insert(iCon, fol);
			} else {
				msjError = "Folio ya ingresado";  
			}
			pool.liberarConexion(iCon, true);	
			
		} catch (ExcepFolioYaExiste e) {
			pool.liberarConexion(iCon, false);
			msjError = "El folio que se intenta ingresar ya estï¿½ registrado";
		} catch (ExcepAccesoADatos e) {
			pool.liberarConexion(iCon, false);
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
	public void agregarRevision(VOFolio voF,VORevision voR) throws ExcepFolioNoExiste, ExcepNoHayFoliosRegistrados, SQLException {
		IConexion iCon=null;
		String msjError="";
		boolean existeCodigo=false;
		boolean errorPersistencia=false;
		try {
			/*con = DriverManager.getConnection(urlBD, userBD, pwdBD);
			  con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			  con.setAutoCommit(false);*/
			iCon = pool.obtenerConexion(true);
			int numero = voR.getNumero();
			String descripcion = voR.getDescripcion();
			String codFolio = voR.getCodFolio();
			
			//AccesoBD abd = new AccesoBD();
			//boolean existeCodigo =  abd.existeFolio(con, codigo);
			
			existeCodigo = folio.member(iCon, codFolio);
			
			if(existeCodigo) {
				Revision rev = new Revision(numero+1, descripcion);
				Folio unFolio = folio.find(iCon, codFolio);
				unFolio.addRevision(iCon, rev);
			} else {
				msjError = "No existe Folio";  
			}
			pool.liberarConexion(iCon, true);	
			
		} catch (ExcepFolioYaExiste e) {
			pool.liberarConexion(iCon, false);
			msjError = "El folio que se intenta ingresar ya estï¿½ registrado";
		} catch (ExcepAccesoADatos e) {
			pool.liberarConexion(iCon, false);
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
	 * @see logicaPersistencia.IFachada#borrarFolioRevisiones(java.lang.String)
	 */
	@Override   //precondicion que el folio con ese coÌ�digo esteÌ� registrado.
	public void borrarFolioRevisiones(String codF)throws ExcepFolioYaExiste, ExcepAccesoADatos ,SQLException{
		IConexion icon=null;
		String msjError="";
		boolean existeCodigo=false;
		boolean errorPersistencia=false;
		try {
			icon = pool.obtenerConexion(true);
			existeCodigo = folio.member(icon, codF);
			if(existeCodigo) {
				Folio fol = folio.find(icon, codF);
				fol.borrarRevisiones(icon);
				folio.delete(icon, codF);
			} else {
				msjError = "Folio no registrado";  
			}
			pool.liberarConexion(icon, true);	
			
		} catch (ExcepFolioYaExiste e) {
			pool.liberarConexion(icon, false);
			msjError = "El folio que se intenta buscar no estï¿½ registrado";
		} catch (ExcepAccesoADatos e) {
			pool.liberarConexion(icon, false);
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
	 * @see logicaPersistencia.IFachada#darDescripcion(java.lang.String, int)
	 */
	@Override   //precondicion que el folio exista y tenga una revisioÌ�n con ese nuÌ�mero
	public String darDescripcion(String codF,int numR){
		String desc = null;
		return desc;
	}
	
	/* (non-Javadoc)
	 * @see logicaPersistencia.IFachada#listarFolios()
	 */
	@Override
	public ListaVOFolios listarFolios() throws ExcepAccesoADatos{
		ListaVOFolios listaFolios = null;
		IConexion icon=null;
		String msjError="Error de Acceso a los datos";
		try {
			icon = pool.obtenerConexion (true);
			listaFolios=folio.listarFolios(icon);
			pool.liberarConexion (icon, true);
			
		}catch(Exception e) {
			if (icon != null) 
				pool.liberarConexion (icon, false);
            throw new ExcepAccesoADatos(msjError);
		}
		
		return listaFolios;
	}
	
	/* (non-Javadoc)
	 * @see logicaPersistencia.IFachada#listarRevisiones()
	 */
	@Override   //precondicion que el folio este registrado.
	public ListaVORevisiones listarRevisiones(String codF) throws ExcepAccesoADatos{
		ListaVORevisiones listaRevisiones = null;
		IConexion icon=null;
		String msjError="Error de Acceso a los datos";
		try {
			icon = pool.obtenerConexion (true);
			Folio fol=folio.find(icon, codF);
			listaRevisiones=fol.listarRevisiones(icon);
			pool.liberarConexion (icon, true);
			
		}catch(Exception e) {
			if (icon != null) 
				pool.liberarConexion (icon, false);
            throw new ExcepAccesoADatos(msjError);
		}
		return listaRevisiones;
	}
	
	/* (non-Javadoc)
	 * @see logicaPersistencia.IFachada#folioMasRevisado()
	 */
	@Override  //precondicion existe al menos un folio.
	public VOFolioMaxRev folioMasRevisado() throws ExcepAccesoADatos {
		VOFolioMaxRev voFMR = null;
		IConexion icon=null;
		String msjError="Error de Acceso a los datos";
		try {
			icon = pool.obtenerConexion (true);
			voFMR=folio.folioMasRevisado(icon);
			pool.liberarConexion (icon, true);
			
		}catch(Exception e) {
			if (icon != null) 
				pool.liberarConexion (icon, false);
            throw new ExcepAccesoADatos(msjError);
		}
		return voFMR;
	}
	
}
