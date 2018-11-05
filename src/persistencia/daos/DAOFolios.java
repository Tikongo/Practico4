package persistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.ldap.UnsolicitedNotificationListener;



import logicaPersistencia.Folio;
import logicaPersistencia.accesoBD.Conexion;
import logicaPersistencia.accesoBD.Consultas;
import logicaPersistencia.accesoBD.IConexion;
import logicaPersistencia.accesoBD.IPoolConexiones;
import logicaPersistencia.excepciones.ExcepFolioYaExiste;
import logicaPersistencia.excepciones.ExcepPersistencia;
import logicaPersistencia.valueObjects.VOFolio;
import logicaPersistencia.valueObjects.VOFolioMaxRev;
import logicaPersistencia.valueObjects.VORevision;

public class DAOFolios {
	
	// atributos para acceso a BD
	
	public DAOFolios() {
		
		
	}
	
	//Verifico si existe el folio
	public boolean member(IConexion icon, String cod) throws ExcepPersistencia{
		boolean esta=false;
		try {
			Consultas consultas = new Consultas();
			String query = consultas.existeFolio();
			Connection con= ((Conexion) icon).getConexion();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, cod);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				esta = true;
			}
			rs.close();
			pstmt.close();
			
		}catch (SQLException e) {
			throw new ExcepPersistencia("Error de conexion");
		}
		return esta;
	} 
	
	//Insertar un Folio
	public void insert(IConexion icon, Folio fol) throws ExcepPersistencia{
		try {
			Consultas consultas = new Consultas();
			Connection con= ((Conexion) icon).getConexion();
			String insert = consultas.queryAgregarFolio();
			PreparedStatement pstmt = con.prepareStatement(insert);
			pstmt.setString(1, fol.getCodigo());
			pstmt.setString(2, fol.getCaratula());
			pstmt.setInt(3, fol.getPaginas());
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch (SQLException e) {
			throw new ExcepPersistencia("Error de conexion");
		}
	}
	
	//Devuelvo el Folio
	public Folio find(IConexion icon, String cod) throws ExcepPersistencia {
		Folio unFolio=null;
		try {
			Consultas consultas = new Consultas();
			Connection con= ((Conexion) icon).getConexion();
			String devolerF = consultas.queryFindFolio();
			PreparedStatement pstmt = con.prepareStatement(devolerF);
			pstmt.setString(1, cod);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				String codigo = rs.getString("codigo");
				String caratula = rs.getString("caratula");
				int paginas = rs.getInt("paginas");
				unFolio = new Folio(codigo, caratula, paginas);
			}
			rs.close();		
			pstmt.close();
			
		}catch (SQLException e) {
			throw new ExcepPersistencia("Error de conexion");
		}
		return unFolio;
	}
	
	//Eliminar Folio
	public void delete (IConexion icon, String cod) throws ExcepPersistencia{
			try {
				Consultas consultas = new Consultas();
				Connection con= ((Conexion) icon).getConexion();
				String delete = consultas.queryBorrarFolio();
				PreparedStatement pstmt = con.prepareStatement(delete);
				pstmt.setString(1, cod);
				pstmt.executeUpdate();
				pstmt.close();
				
			}catch (SQLException e) {
				throw new ExcepPersistencia("Error de conexion");
			}
	}
	
	//Listar todos los folios
	public List<VOFolio> listarFolios(IConexion icon) throws ExcepPersistencia{
		
		List<VOFolio> listaFolios = new ArrayList<>();
		
		try {
			Consultas consultas = new Consultas();
			Connection con= ((Conexion) icon).getConexion();
			String listarFolios = consultas.queryListarFolios();
			PreparedStatement pstmt = con.prepareStatement(listarFolios);
					
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String codigo = rs.getString("codigo");
				String caratula = rs.getString("caratula");
				int paginas = rs.getInt("paginas");
				VOFolio unFolio = new VOFolio(codigo, caratula, paginas);
				listaFolios.add(unFolio);
			}
			rs.close();		
			pstmt.close();
			
		}catch (SQLException e) {
			throw new ExcepPersistencia("Error de conexion");
		}
		return listaFolios;
	}
	
	//Verifico si hay al menos un folio
	public boolean esVacio(IConexion icon) throws ExcepPersistencia{
		boolean existe=false;
		try {
			Consultas consultas = new Consultas();
			Connection con= ((Conexion) icon).getConexion();
			String listarFolios = consultas.queryListarFolios();
			PreparedStatement pstmt = con.prepareStatement(listarFolios);
					
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				existe=true;
			
		}catch (SQLException e) {
			throw new ExcepPersistencia("Error de conexion");
		}
		return existe;
	}
	
	//Devuelvo folio con mas revisiones
	public VOFolioMaxRev folioMasRevisado(IConexion icon) throws ExcepPersistencia {
		VOFolioMaxRev unFolio=null;
		try {
			Consultas consultas = new Consultas();
			Connection con= ((Conexion) icon).getConexion();
			String folioMaxRev = consultas.queryFolioMasRevisado();
			PreparedStatement pstmt = con.prepareStatement(folioMaxRev);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				String codigo = rs.getString("codigo");
				String caratula = rs.getString("caratula");
				int paginas = rs.getInt("paginas");
				int cantRev = rs.getInt("cantRevisiones");
				unFolio = new VOFolioMaxRev(codigo, caratula, paginas,cantRev);
			}
					
			pstmt.close();
			
		}catch (SQLException e) {
			throw new ExcepPersistencia("Error de conexion");
		}
		return unFolio;
	}
	
}
