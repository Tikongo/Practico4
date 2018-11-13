package logica.valueObjects;

import java.io.Serializable;

public class VOFolio implements Serializable{
	
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
	
	public String getCaratula(){
		return caratula;
	}
	
	public int getPaginas(){
		return paginas;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setCaratula(String caratula) {
		this.caratula = caratula;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
	
}
