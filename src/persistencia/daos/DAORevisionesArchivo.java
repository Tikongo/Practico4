package persistencia.daos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import logica.Revision;
import logica.excepciones.ExcepAccesoADatos;
import logica.valueObjects.VOFolio;
import logica.valueObjects.VORevision;
import persistencia.accesoDatos.IConexion;
import persistencia.accesoDatos.Monitor;

public class DAORevisionesArchivo implements IDAORevisiones{
	
	Monitor monitor=new Monitor();
	
	private VORevision leerVORevision(File f) throws IOException {
		VORevision voR = null;
		monitor.comienzoLectura();
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String leido = br.readLine();
			if (leido != null) {
				voR.setDescripcion(leido);
				leido = br.readLine();
			}
			if (leido != null) {
				voR.setCodFolio(leido);
				leido = br.readLine(); 
			}
			if (leido != null) {
				voR.setNumero(Integer.parseInt(leido));
				leido = br.readLine();
			}
			monitor.terminoLectura();
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		}
		return voR;		
	}

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
