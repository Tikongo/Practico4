package logicaPersistencia.accesoBD;

import java.sql.SQLException;

public interface IPoolConexiones {
	
	public IConexion obtenerConexion(Boolean t) throws SQLException;
	
	public void liberarConexion(IConexion iC, Boolean t);
	
}
