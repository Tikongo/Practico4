package logicaPersistencia.valueObjects;

import java.util.ArrayList;
import valueObjects.VOFolio;
import accesoBD.Examen;

public class ListaVOFolios {
	
	private ArrayList<VOFolio> folios;

	//Constructor.
	public ListaVOFolios()
	{	 
		folios = new ArrayList<VOFolio>(); 
	}
	
	public void insert(VOFolio voF) 
	{
		folios.add(voF);
	}
	
	public ArrayList<VOFolio> getFolios()
	{
		return folios;
	}
	
	public boolean esVacia() {
		return folios.isEmpty();
	}
	
	public int largo() {
		return folios.size();
	}
}
