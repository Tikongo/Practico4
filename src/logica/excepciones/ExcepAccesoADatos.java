package logica.excepciones;

import java.sql.SQLException;

public class ExcepAccesoADatos extends SQLException {
	
	private String mensaje;
	
	public ExcepAccesoADatos(String mensaje)
	{
		this.mensaje=mensaje;
	}
	
	public String darMensaje()
	{
		return mensaje;
	}
}
