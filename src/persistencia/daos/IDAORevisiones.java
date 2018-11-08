package persistencia.daos;

import java.util.List;

import logica.Revision;
import logicaPersistencia.accesoBD.IConexion;
import logicaPersistencia.excepciones.ExcepAccesoADatos;
import logicaPersistencia.valueObjects.VORevision;

public interface IDAORevisiones {
	
	public void insBack (IConexion icon,Revision rev) throws ExcepAccesoADatos;
	
	public int largo (IConexion icon) throws ExcepAccesoADatos;
	
	public Revision kesimo(IConexion icon,int numero) throws ExcepAccesoADatos;
	
	public List<VORevision> listarRevisiones(IConexion icon) throws ExcepAccesoADatos;
	
	public void borrarRevisiones (IConexion icon) throws ExcepAccesoADatos;

}
