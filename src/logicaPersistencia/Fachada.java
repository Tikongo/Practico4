package logicaPersistencia;

import java.util.List;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import logicaPersistencia.valueObjects.*;
import java.sql.*;

public class Fachada {
	//linea de prueba
	private String driverBD;
	private String urlBD;
	private String userBD;
	private String pwdBD;
	
	public Fachada(){
		/*cargar valores desde archivo de propiedades.*/
		
	}
	
	public void agregarFolio(VOFolio voF){
		
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
