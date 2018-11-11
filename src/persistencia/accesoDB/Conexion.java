package persistencia.accesoDB;

import java.sql.Connection;

public class Conexion<T> implements IConexion {
	
	private T con;
	
	public Conexion (T conexion) {
		con = conexion;
	}
	
	public T getConexion() {
		return con;
	}
}
