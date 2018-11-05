package persistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logicaPersistencia.Revision;
import logicaPersistencia.accesoBD.Conexion;
import logicaPersistencia.accesoBD.Consultas;
import logicaPersistencia.accesoBD.IConexion;
import logicaPersistencia.excepciones.ExcepPersistencia;
import logicaPersistencia.valueObjects.VOFolio;
import logicaPersistencia.valueObjects.VORevision;

public class DAORevisiones {
	
	//  atributos para acceso a BD
	
	private String codigoFolio;

	public DAORevisiones(String codigoFolio) {
		super();
		this.codigoFolio = codigoFolio;
	}
	
	public void insBack (IConexion icon,Revision rev) throws ExcepPersistencia{
		
	}
	
	public int largo (IConexion icon) throws ExcepPersistencia{
		int largo=0;
		return largo;
	}

	public Revision kesimo(IConexion icon,int numero) throws ExcepPersistencia{
		Revision rev=null;
		return rev;
		
	}
	
    public List<VORevision> listarRevisiones(IConexion icon) throws ExcepPersistencia{
		
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
			throw new ExcepPersistencia("Error de conexion");
		}
		return listaRevisiones;
	}
    
    public void borrarRevisiones (IConexion icon) throws ExcepPersistencia{
    	try {
			Consultas consultas = new Consultas();
			Connection con= ((Conexion) icon).getConexion();
			String delete = consultas.queryBorrarFolioRevisiones();
			PreparedStatement pstmt = con.prepareStatement(delete);
			pstmt.setString(1, codigoFolio);
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch (SQLException e) {
			throw new ExcepPersistencia("Error de conexion");
		}
    }
}
