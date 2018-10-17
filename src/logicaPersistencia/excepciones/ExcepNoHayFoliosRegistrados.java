package logicaPersistencia.excepciones;

public class ExcepNoHayFoliosRegistrados extends Exception {

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
