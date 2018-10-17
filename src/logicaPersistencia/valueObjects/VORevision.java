package logicaPersistencia.valueObjects;

public class VORevision {
	
	private int numero;
	private String descripcion;
	private String codFolio;
	
	public VORevision(){
		numero = 0;
		descripcion = null;
		codFolio = null;
	}
	
	public VORevision(int num, String des, String codF){
		numero = num;
		descripcion = des;
		codFolio = codF;
	}
	
	public int getNumero(){
		return numero;
	}
	
	public String getDescripcion(){
		return descripcion;
	}
	
	public String getCodFolio(){
		return codFolio;
	}
}
