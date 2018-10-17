package logicaPersistencia.valueObjects;

public class VOFolioMaxRev extends VOFolio {
	
	private int cantRevisiones;
	
	public VOFolioMaxRev (){
		super();
		cantRevisiones = 0;
	}
	
	public VOFolioMaxRev (String cod, String car, int pag, int cantRev){
		super(cod,car,pag);
		cantRevisiones = cantRev;
	}
	
	public int getCantRevisiones(){
		return cantRevisiones;
	}
	
}
