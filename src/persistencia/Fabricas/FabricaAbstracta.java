package persistencia.Fabricas;

import persistencia.daos.IDAOFolios;
import persistencia.daos.IDAORevisiones;

public interface FabricaAbstracta {
	
	public IDAOFolios crearIDAOFolios ();
	public IDAORevisiones crearIDAORevisiones (String cod);

}
