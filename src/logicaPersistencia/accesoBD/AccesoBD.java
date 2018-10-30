package logicaPersistencia.accesoBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import logicaPersistencia.excepciones.ExcepAccesoADatos;
import logicaPersistencia.valueObjects.*;

public class AccesoBD {
	
	public void agregarFolio(Connection con,String codigo,String caratula,int paginas) {
		
	}
	
	public boolean existeFolio(Connection con, String codigo ) throws ExcepAccesoADatos{
		boolean existe= false;
		try {
			Consultas consultas = new Consultas();
			String query = consultas.existeFolio();
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setString(1, codigo);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				existe=true;
			rs.close();
			pstmt.close();
			
		}catch(SQLException e) {
			throw new ExcepAccesoADatos("Error de Acceso a la BD");
		}
			
		
		return existe;
	}
	
	public void agregarRevision() {
		
	}
	
	public void borrarFolioRevisiones() {
		
	}
	
	public String darDescripcion() {
		String desc = null;
		return desc;
	}
	
	public ListaVOFolios listarFolios() {
		ListaVOFolios listaVoF = new ListaVOFolios();
		return listaVoF;
	}
	
	public ListaVORevisiones listarRevisiones() {
		ListaVORevisiones listaVoRev = new ListaVORevisiones();
		return listaVoRev;
	}
	
	public VOFolio folioMasRevisado() {
		VOFolio folioMasRev = new VOFolio();
		return folioMasRev;
	}
}
