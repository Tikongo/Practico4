package persistencia.accesoDatos;

import java.sql.Connection;

public interface IConexion<T> {
	
	public T getConexion();
	
}
