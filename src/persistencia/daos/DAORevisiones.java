package persistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.Folio;
import logica.Revision;
import logicaPersistencia.accesoBD.IConexion;
import logicaPersistencia.excepciones.*;
import logicaPersistencia.valueObjects.VOFolio;
import logicaPersistencia.valueObjects.VORevision;
import persistencia.consultas.Consultas;

public class DAORevisiones implements IDAORevisiones{
	
	//  atributos para acceso a BD
	
	private String codigoFolio;

	public DAORevisiones(String codigoFolio) {
		super();
		this.codigoFolio = codigoFolio;
	}
	@Override
	public void insBack (IConexion icon,Revision rev) throws ExcepAccesoADatos{
		try {
			Consultas consultas = new Consultas();
			Connection con= (icon).getConexion();
			String insert = consultas.queryAgregarRevision();
			PreparedStatement pstmt = con.prepareStatement(insert);
			pstmt.setInt(1, rev.getNumero());
			pstmt.setString(2, codigoFolio);
			pstmt.setString(3, rev.getDescripcion());
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch (SQLException e) {
			throw new ExcepAccesoADatos("Error de acceso a los datos");
		}
	}
	@Override
	public int largo (IConexion icon) throws ExcepAccesoADatos{
		int largo=0;
		Consultas consultas = new Consultas();
		Connection con= (icon).getConexion();
		String cantR = consultas.queryCantRevision();
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(cantR); 
		pstmt.setString(1, codigoFolio);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			largo++;
			
		}
		largo=largo+1;
		rs.close();	
		pstmt.close();
		}catch (SQLException e) {
			throw new ExcepAccesoADatos("Error de acceso a los datos");
		}
		return largo;
	}
	@Override
	public Revision kesimo(IConexion icon,int numero) throws ExcepAccesoADatos{
		Revision rev=null;
		try {
			Consultas consultas = new Consultas();
			Connection con= (icon).getConexion();
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
	@Override
    public List<VORevision> listarRevisiones(IConexion icon) throws ExcepAccesoADatos{
		
		List<VORevision> listaRevisiones = new ArrayList<>();
		try {
			Consultas consultas = new Consultas();
			Connection con= (icon).getConexion();
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
	@Override
    public void borrarRevisiones (IConexion icon) throws ExcepAccesoADatos{
    	try {
			Consultas consultas = new Consultas();
			Connection con= (icon).getConexion();
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
