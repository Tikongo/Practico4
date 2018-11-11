package logica.valueObjects;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaVOFolios implements Serializable {
	
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
