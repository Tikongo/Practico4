package logicaPersistencia;

import java.util.List;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


import logicaPersistencia.accesoBD.AccesoBD;
import logicaPersistencia.excepciones.ExcepAccesoADatos;
import logicaPersistencia.excepciones.ExcepFolioNoExiste;
import logicaPersistencia.excepciones.ExcepFolioYaExiste;

import logicaPersistencia.accesoBD.PoolConexiones;

import logicaPersistencia.valueObjects.*;
//import persistencia.daos.DAOFolios;

import java.sql.*;

public class Fachada {
	//linea de prueba
	private String driverBD;
	private String urlBD;
	private String userBD;
	private String pwdBD;

	private Connection con;

	private PoolConexiones pool;
	private DAOFolios folio;
	

	public Fachada(){
		/*cargar valores desde archivo de propiedades.*/
		
	}
	
	public void agregarFolio(VOFolio voF) throws ExcepFolioYaExiste, ExcepAccesoADatos,SQLException{
		try {
			con = DriverManager.getConnection(urlBD, userBD, pwdBD);
			con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			con.setAutoCommit(false);
			
			String codigo=voF.getCodigo();
			String caratula=voF.caratula();
			int paginas=voF.getPaginas();
			
			AccesoBD abd = new AccesoBD();
			boolean existeCodigo =  abd.existeFolio(con, codigo);
			
			if(!existeCodigo) {
				abd.agregarFolio(con,codigo,caratula,paginas);
			}else {
				//msjError = "Folio ya ingresado";
				
			}
				
			con.commit();
			
			
		}catch(ExcepFolioYaExiste e){
			con.rollback();
			/*
			errorPersistencia =  true;
			msjError = "Error de Acceso a la BD";*/
			
		}
		finally {
			con.close();
			
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
	
	public List<VOFolio> listarFolios(){
		List<VOFolio> listaFolios = new ArrayList<>();
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
