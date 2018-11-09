package persistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logica.Folio;
import logicaPersistencia.accesoBD.IConexion;

import logicaPersistencia.excepciones.ExcepAccesoADatos;
import logicaPersistencia.valueObjects.VOFolio;
import logicaPersistencia.valueObjects.VOFolioMaxRev;
import logicaPersistencia.valueObjects.VORevision;

public class DAOFolios implements IDAOFolios{
	
	// atributos para acceso a BD
	
	public DAOFolios() {
		
		
	}
	
	//Verifico si existe el folio
	@Override
	public boolean member(IConexion icon, String cod) throws ExcepAccesoADatos{
		boolean esta=false;
		try {
			persistencia.consultas.Consultas consultas = new persistencia.consultas.Consultas();
			String query = consultas.existeFolio();
			Connection con= (icon).getConexion();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, cod);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				esta = true; 
			}
			rs.close();
			pstmt.close();
			
		}catch (SQLException e) {
			throw new ExcepAccesoADatos("Error de acceso a los datos");
		}
		return esta;
	} 
	
	//Insertar un Folio
	@Override
	public void insert(IConexion icon, Folio fol) throws ExcepAccesoADatos{
		try {
			persistencia.consultas.Consultas consultas = new persistencia.consultas.Consultas();
			Connection con= (icon).getConexion();
			String insert = consultas.queryAgregarFolio();
			PreparedStatement pstmt = con.prepareStatement(insert);
			pstmt.setString(1, fol.getCodigo());
			pstmt.setString(2, fol.getCaratula());
			pstmt.setInt(3, fol.getPaginas());
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch (SQLException e) {
			throw new ExcepAccesoADatos("Error de acceso a los datos");
		}
	}
	
	//Devuelvo el Folio
	@Override
	public Folio find(IConexion icon, String cod) throws ExcepAccesoADatos {
		Folio unFolio=null;
		try {
			persistencia.consultas.Consultas consultas = new persistencia.consultas.Consultas();
			Connection con= (icon).getConexion();
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
			throw new ExcepAccesoADatos("Error de acceso a los datos");
		}
		return unFolio;
	}
	
	//Eliminar Folio
	@Override
	public void delete (IConexion icon, String cod) throws ExcepAccesoADatos {
			try {
				persistencia.consultas.Consultas consultas = new persistencia.consultas.Consultas();
				Connection con= (icon).getConexion();
				String delete = consultas.queryBorrarFolio();
				PreparedStatement pstmt = con.prepareStatement(delete);
				pstmt.setString(1, cod);
				pstmt.executeUpdate();
				pstmt.close();
				
			}catch (SQLException e) {
				throw new ExcepAccesoADatos("Error de acceso a los datos");
			}
	}
	
	//Listar todos los folios
	@Override
	public List<VOFolio> listarFolios(IConexion icon) throws ExcepAccesoADatos{
		
		List<VOFolio> listaFolios = new ArrayList<>();
		
		try {
			persistencia.consultas.Consultas consultas = new persistencia.consultas.Consultas();
			Connection con= (icon).getConexion();
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
			throw new ExcepAccesoADatos("Error de acceso a los datos");
		}
		return listaFolios;
	}
	
	//Verifico si hay al menos un folio
	@Override
	public boolean esVacio(IConexion icon) throws ExcepAccesoADatos{
		boolean existe=false;
		try {
			persistencia.consultas.Consultas consultas = new persistencia.consultas.Consultas();
			Connection con= (icon).getConexion();
			String listarFolios = consultas.queryListarFolios();
			PreparedStatement pstmt = con.prepareStatement(listarFolios);
					
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				existe=true;
			
		}catch (SQLException e) {
			throw new ExcepAccesoADatos("Error de acceso a los datos");
		}
		return existe;
	}
	
	//Devuelvo folio con mas revisiones
	@Override
	public VOFolioMaxRev folioMasRevisado(IConexion icon) throws ExcepAccesoADatos {
		VOFolioMaxRev unFolio=null;
		try {
			persistencia.consultas.Consultas consultas = new persistencia.consultas.Consultas();
			Connection con= (icon).getConexion();
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
			rs.close();			
			pstmt.close();
			
		}catch (SQLException e) {
			throw new ExcepAccesoADatos("Error de acceso a los datos");
		}
		return unFolio;
	}
	
}
