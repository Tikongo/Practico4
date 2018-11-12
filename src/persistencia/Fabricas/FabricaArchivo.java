package persistencia.Fabricas;

import persistencia.daos.DAOFoliosArchivo;
import persistencia.daos.IDAOFolios;

public class FabricaArchivo implements FabricaAbstracta{

	@Override
	public IDAOFolios crearIDAOFolios() {
		// TODO Auto-generated method stub
		return new DAOFoliosArchivo();
	}
}