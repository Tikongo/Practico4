package persistencia.Fabricas;

import persistencia.daos.DAOFoliosArchivo;
import persistencia.daos.DAORevisionesArchivo;
import persistencia.daos.IDAOFolios;
import persistencia.daos.IDAORevisiones;

public class FabricaArchivo implements FabricaAbstracta{

	@Override
	public IDAOFolios crearIDAOFolios() {
		// TODO Auto-generated method stub
		return new DAOFoliosArchivo();
	}
	
	@Override
	public IDAORevisiones crearIDAORevisiones(String cod) {
		return new DAORevisionesArchivo(cod);
	}
	
}