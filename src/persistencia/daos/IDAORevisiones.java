package persistencia.daos;

import java.util.List;

import logica.Revision;
import logica.excepciones.ExcepAccesoADatos;
import logica.valueObjects.ListaVORevisiones;
import logica.valueObjects.VORevision;
import persistencia.accesoDatos.IConexion;

public interface IDAORevisiones {
	
	public void insBack (IConexion icon,Revision rev) throws ExcepAccesoADatos;
	
	public int largo (IConexion icon) throws ExcepAccesoADatos;
	
	public Revision kesimo(IConexion icon,int numero) throws ExcepAccesoADatos;
	
	public ListaVORevisiones listarRevisiones(IConexion icon) throws ExcepAccesoADatos;
	
	public void borrarRevisiones (IConexion icon) throws ExcepAccesoADatos;

}
