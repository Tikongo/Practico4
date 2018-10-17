package logicaPersistencia.valueObjects;

public class VOFolio {
	
	private String codigo;
	private String caratula;
	private int paginas;
	
	
	public VOFolio(){
		codigo = null;
		caratula = null;
		paginas = 0;
	}

	public VOFolio(String cod, String car, int pag){
		codigo = cod;
		caratula = car;
		paginas = pag;
	}
	
	public String getCodigo(){
		return codigo;
	}
	
	public String caratula(){
		return caratula;
	}
	
	public int getPaginas(){
		return paginas;
	}
	
}
