package logica.excepciones;

import java.sql.SQLException;

public class ExcepFolioSinRevisiones extends SQLException{
	
	private String mensaje;
	
	public ExcepFolioSinRevisiones(String m) {
		this.mensaje = m;
	}
	
	public String darMensaje() {
		return this.mensaje;
	}
	
}
