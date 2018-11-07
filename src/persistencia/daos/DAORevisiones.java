package persistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.Folio;
import logica.Revision;
import logicaPersistencia.accesoBD.Conexion;
import logicaPersistencia.accesoBD.Consultas;
import logicaPersistencia.accesoBD.IConexion;
import logicaPersistencia.excepciones.*;
import logicaPersistencia.valueObjects.VOFolio;
import logicaPersistencia.valueObjects.VORevision;

public class DAORevisiones {
	
	//  atributos para acceso a BD
	
	private String codigoFolio;

	public DAORevisiones(String codigoFolio) {
		super();
		this.codigoFolio = codigoFolio;
	}
	
	public void insBack (IConexion icon,Revision rev) throws ExcepAccesoADatos{
		
	}
	
	public int largo (IConexion icon) throws ExcepAccesoADatos{
		int largo=0;
		Consultas consultas = new Consultas();
		Connection con= ((Conexion) icon).getConexion();
		String cantR = consultas.queryCantRevision();
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(cantR); 
		pstmt.setString(1, codigoFolio);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			largo++;
			
		}
		rs.close();	
		pstmt.close();
		}catch (SQLException e) {
			throw new ExcepAccesoADatos("Error de acceso a los datos");
		}
		return largo;
	}

	public Revision kesimo(IConexion icon,int numero) throws ExcepAccesoADatos{
		Revision rev=null;
		try {
			Consultas consultas = new Consultas();
			Connection con= ((Conexion) icon).getConexion();
			String devolerR = consultas.queryDarRevision();
			PreparedStatement pstmt = con.prepareStatement(devolerR);
			pstmt.setString(1, codigoFolio);
			pstmt.setInt(2, numero);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int num = rs.getInt("numero");
				String descripcion = rs.getString("descripcion");
				rev = new Revision(num,descripcion);
			}
			rs.close();	
			pstmt.close();
			
		}catch (SQLException e) {
			throw new ExcepAccesoADatos("Error de acceso a los datos");
		}
		return rev;
		
	}
	
    public List<VORevision> listarRevisiones(IConexion icon) throws ExcepAccesoADatos{
		
		List<VORevision> listaRevisiones = new ArrayList<>();
		try {
			Consultas consultas = new Consultas();
			Connection con= ((Conexion) icon).getConexion();
			String listarRevisiones = consultas.queryListarRevisiones();
			PreparedStatement pstmt = con.prepareStatement(listarRevisiones);
			pstmt.setString(1, codigoFolio);		
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int numero = rs.getInt("numero");
				String codigoF = rs.getString("codigoFolio");
				String desc = rs.getString("descripcion");
				VORevision revision = new VORevision(numero,desc,codigoF);
				listaRevisiones.add(revision);
			}
			rs.close();		
			pstmt.close();
			
		}catch (SQLException e) {
			throw new ExcepAccesoADatos("Error de acceso a los datos");
		}
		return listaRevisiones;
	}
    
    public void borrarRevisiones (IConexion icon) throws ExcepAccesoADatos{
    	try {
			Consultas consultas = new Consultas();
			Connection con= ((Conexion) icon).getConexion();
			String delete = consultas.queryBorrarFolioRevisiones();
			PreparedStatement pstmt = con.prepareStatement(delete);
			pstmt.setString(1, codigoFolio);
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch (SQLException e) {
			throw new ExcepAccesoADatos("Error de acceso a los datos");
		}
    }
}
