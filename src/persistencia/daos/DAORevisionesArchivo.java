package persistencia.daos;

import java.util.List;

import logica.Revision;
import logicaPersistencia.accesoBD.IConexion;
import logicaPersistencia.excepciones.ExcepAccesoADatos;
import logicaPersistencia.valueObjects.VORevision;

public class DAORevisionesArchivo implements IDAORevisiones{

	@Override
	public void insBack(IConexion icon, Revision rev) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int largo(IConexion icon) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Revision kesimo(IConexion icon, int numero) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VORevision> listarRevisiones(IConexion icon) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void borrarRevisiones(IConexion icon) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		
	}

}
