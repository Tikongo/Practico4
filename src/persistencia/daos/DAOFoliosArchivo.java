package persistencia.daos;


import java.util.List;

import logica.Folio;
import logicaPersistencia.accesoBD.IConexion;
import logicaPersistencia.excepciones.ExcepAccesoADatos;
import logicaPersistencia.excepciones.ExcepArchivoNoEncontrado;
import logicaPersistencia.valueObjects.VOFolio;
import logicaPersistencia.valueObjects.VOFolioMaxRev;


public class DAOFoliosArchivo implements IDAOFolios{

	
	@Override
	public boolean member(IConexion icon, String cod) throws ExcepAccesoADatos {
		
		return false;
	}

	@Override
	public void insert(IConexion icon, Folio fol) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Folio find(IConexion icon, String cod) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(IConexion icon, String cod) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<VOFolio> listarFolios(IConexion icon) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean esVacio(IConexion icon) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public VOFolioMaxRev folioMasRevisado(IConexion icon) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		return null;
	}

}
