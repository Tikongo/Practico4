package persistencia.accesoDatos;

import java.sql.SQLException;
import logica.excepciones.ExcepAccesoADatos;

public interface IPoolConexiones {
	
	public IConexion obtenerConexion(Boolean t) throws ExcepAccesoADatos;
	
	public void liberarConexion(IConexion iC, Boolean t) throws ExcepAccesoADatos;
	
}
