package persistencia.Fabricas;

import persistencia.daos.DAOFolios;
import persistencia.daos.DAORevisiones;
import persistencia.daos.IDAOFolios;
import persistencia.daos.IDAORevisiones;

public class FabricaMySQL implements FabricaAbstracta{

	@Override
	public IDAOFolios crearIDAOFolios() {
		// TODO Auto-generated method stub
		return new DAOFolios();
	}

	@Override
	public IDAORevisiones crearIDAORevisiones(String cod) {
		return new DAORevisiones(cod);
	}
	
}
