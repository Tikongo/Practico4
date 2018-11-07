package logicaPersistencia.excepciones;

import java.sql.SQLException;


public class ExcepFolioNoExiste extends SQLException {
	
	private String mensaje;
	
	public ExcepFolioNoExiste(String mensaje)
	{
		this.mensaje=mensaje;
	}
	
	public String darMensaje()
	{
		return mensaje;
	}
}
