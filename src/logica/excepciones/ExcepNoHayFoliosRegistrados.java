package logica.excepciones;

import java.sql.SQLException;

public class ExcepNoHayFoliosRegistrados extends SQLException {

	private String mensaje;
	
	public ExcepNoHayFoliosRegistrados(String mensaje)
	{
		this.mensaje=mensaje;
	}
	
	public String darMensaje()
	{
		return mensaje;
	}
}
