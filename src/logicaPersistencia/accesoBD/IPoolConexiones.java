package logicaPersistencia.accesoBD;

import java.sql.SQLException;

import logicaPersistencia.excepciones.ExcepAccesoADatos;

public interface IPoolConexiones {
	
	public IConexion obtenerConexion(Boolean t) throws ExcepAccesoADatos;
	
	public void liberarConexion(IConexion iC, Boolean t);
	
}
