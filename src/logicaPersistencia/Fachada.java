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
import logicaPersistencia.accesoBD.PoolConexiones;
import logicaPersistencia.accesoBD.PoolConexiones;
import logicaPersistencia.valueObjects.*;
import persistencia.daos.DAOFolios;

import java.sql.*;

public class Fachada {
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
	
	public void agregarFolio(VOFolio voF) throws ExcepFolioYaExiste, ExcepAccesoADatos,SQLException {
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
			String caratula = voF.caratula();
			int paginas = voF.getPaginas();
			
			//AccesoBD abd = new AccesoBD();
			//boolean existeCodigo =  abd.existeFolio(con, codigo);
			
			existeCodigo = folio.member(iCon, codigo);
			
			if(!existeCodigo) {
				//abd.agregarFolio(con,codigo,caratula,paginas);
				Folio fol = new Folio(codigo,caratula,paginas);
				folio.insert(iCon, fol);
			} else {
				msjError = "Folio ya ingresado";  
			}
			pool.liberarConexion(iCon, true);	
			
		} catch(ExcepFolioYaExiste e) {
			pool.liberarConexion(iCon, true);
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
	
	public void agregarRevision(String codF, String desc){
		
	}
	
	public void borrarFolioRevisiones(String codF){
		
	}
	
	public String darDescripcion(String codF,int numR){
		String desc = null;
		return desc;
	}
	
	public List<VOFolio> listarFolios() throws ExcepAccesoADatos{
		List<VOFolio> listaFolios = null;
		IConexion icon=null;
		String msjError="Error de Acceso a los datos";
		try {
			icon = pool.obtenerConexion (false);
			listaFolios=folio.listarFolios(icon);
			pool.liberarConexion (icon, true);
			
		}catch(Exception e) {
			if (icon != null) 
				pool.liberarConexion (icon, false);
            throw new ExcepAccesoADatos(msjError);
		}
		
		return listaFolios;
	}
	
	public List<VORevision> listarRevisiones(){
		List<VORevision> listaRevisiones = new ArrayList<>();
		return listaRevisiones;
	}
	
	public VOFolioMaxRev folioMasRevisado(){
		VOFolioMaxRev voFMR = new VOFolioMaxRev();
		return voFMR;
	}
	
}
