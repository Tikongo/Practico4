package persistencia.daos;

import java.util.List;

import logica.Folio;
import logica.excepciones.ExcepAccesoADatos;
import logica.valueObjects.VOFolio;
import logica.valueObjects.VOFolioMaxRev;
import persistencia.accesoDatos.IConexion;

public interface IDAOFolios {

	public boolean member(IConexion icon, String cod) throws ExcepAccesoADatos;
	
	public void insert(IConexion icon, Folio fol) throws ExcepAccesoADatos;
	
	public Folio find(IConexion icon, String cod) throws ExcepAccesoADatos;
	
	public void delete (IConexion icon, String cod) throws ExcepAccesoADatos;
	
	public List<VOFolio> listarFolios(IConexion icon) throws ExcepAccesoADatos;
	
	public boolean esVacio(IConexion icon) throws ExcepAccesoADatos;
	
	public VOFolioMaxRev folioMasRevisado(IConexion icon) throws ExcepAccesoADatos;
	
}
