package logica.excepciones;

import java.sql.SQLException;

public class ExcepRevisionNoExiste extends SQLException {

	private String mensaje;
	
	public ExcepRevisionNoExiste(String mensaje)
	{
		this.mensaje=mensaje;
	}
	
	public String darMensaje()
	{
		return mensaje;
	}
}
