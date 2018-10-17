package logicaPersistencia.excepciones;

public class ExcepRevisionNoExiste extends Exception {

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
