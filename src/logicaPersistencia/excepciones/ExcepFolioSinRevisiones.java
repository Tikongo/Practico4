package logicaPersistencia.excepciones;

public class ExcepFolioSinRevisiones extends Exception{
	
	private String mensaje;
	
	public ExcepFolioSinRevisiones(String m) {
		this.mensaje = m;
	}
	
	public String darMensaje() {
		return this.mensaje;
	}
	
}
