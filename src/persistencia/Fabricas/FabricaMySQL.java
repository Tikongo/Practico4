package persistencia.Fabricas;

import persistencia.daos.DAOFolios;
import persistencia.daos.IDAOFolios;

public class FabricaMySQL implements FabricaAbstracta{

	@Override
	public IDAOFolios crearIDAOFolios() {
		// TODO Auto-generated method stub
		return new DAOFolios();
	}

	
	
}
