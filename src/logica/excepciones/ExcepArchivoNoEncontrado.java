package logica.excepciones;

import java.io.IOException;

public class ExcepArchivoNoEncontrado extends IOException{
	private String mensaje;
	
	public ExcepArchivoNoEncontrado(String mensaje)
	{
		this.mensaje=mensaje;
	}
	
	public String darMensaje()
	{
		return mensaje;
	}
}
