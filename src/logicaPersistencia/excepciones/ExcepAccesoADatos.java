package logicaPersistencia.excepciones;

public class ExcepAccesoADatos extends Exception {
	
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
