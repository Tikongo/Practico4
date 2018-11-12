package persistencia.daos;

import java.util.List;
import logica.Folio;
import logica.excepciones.ExcepAccesoADatos;
import logica.excepciones.ExcepArchivoNoEncontrado;
import logica.valueObjects.VOFolio;
import logica.valueObjects.VOFolioMaxRev;
import persistencia.accesoDatos.*;
import java.io.*;

public class DAOFoliosArchivo implements IDAOFolios{

	
	@Override
	public boolean member(IConexion icon, String cod) throws ExcepAccesoADatos {
		boolean member = false;
		File file = new File("path");
		member = file.exists();
		return member;
	}

	@Override
	public void insert(IConexion iCon, Folio fol) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		Conexion<FileWriter> c = (Conexion<FileWriter>)iCon;
		
		BufferedWriter b = new BufferedWriter(c.getConexion());
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
