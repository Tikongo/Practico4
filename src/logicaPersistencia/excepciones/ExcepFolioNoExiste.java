package logicaPersistencia.excepciones;

public class ExcepFolioNoExiste extends Exception {
	
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
