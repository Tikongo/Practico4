package logicaPersistencia.valueObjects;

import java.util.ArrayList;
import logicaPersistencia.valueObjects.*;

public class ListaVORevisiones {
	
	private ArrayList<VORevision> revisiones;

	//Constructor.
	public ListaVORevisiones()
	{	 
		revisiones = new ArrayList<VORevision>(); 
	}
	
	public void insert(VORevision voR) 
	{
		revisiones.add(voR);
	}
	
	public ArrayList<VORevision> getRevisiones()
	{
		return revisiones;
	}
	
	public boolean esVacia() {
		return revisiones.isEmpty();
	}
	
	public int largo() {
		return revisiones.size();
	}
}
