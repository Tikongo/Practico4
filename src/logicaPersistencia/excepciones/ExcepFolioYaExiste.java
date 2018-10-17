package logicaPersistencia.excepciones;

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
