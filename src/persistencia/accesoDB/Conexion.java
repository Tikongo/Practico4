package persistencia.accesoDB;

import java.sql.Connection;

public class Conexion implements IConexion {
	
	private Connection con;
	
	public Conexion (Connection c) {
		con = c;
	}
	
	public Connection getConexion() {
		return con;
	}
}
