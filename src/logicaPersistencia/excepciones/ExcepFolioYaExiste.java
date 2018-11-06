package logicaPersistencia.excepciones;

import java.sql.SQLException;

public class ExcepFolioYaExiste extends Exception {
	
	private String mensaje;
	
	public ExcepFolioYaExiste(String mensaje)
	{
		this.mensaje=mensaje;
	}
	
	public String darMensaje()
	{
		return mensaje;
	}
}
