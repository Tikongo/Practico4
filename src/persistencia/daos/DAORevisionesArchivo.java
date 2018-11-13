package persistencia.daos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import logica.Revision;
import logica.excepciones.ExcepAccesoADatos;
import logica.valueObjects.ListaVOFolios;
import logica.valueObjects.ListaVORevisiones;
import logica.valueObjects.VOFolio;
import logica.valueObjects.VORevision;
import persistencia.accesoDatos.Conexion;
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
	public ListaVORevisiones listarRevisiones(IConexion icon) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		ListaVORevisiones listaRevisiones = new ListaVORevisiones();
		VORevision voR = null;
		monitor.comienzoLectura();
		try {
			Conexion<String> con = (Conexion<String>)icon;
			File f = new File(con.getConexion());
			for (final File arch : f.listFiles()) {
				if (arch.isFile()) {
					voR = leerVORevision(arch);
					listaRevisiones.insert(voR);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExcepAccesoADatos("Error al acceder a los datos");
		}
		return listaRevisiones;
	}

	@Override
	public void borrarRevisiones(IConexion icon) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		
	}

}
