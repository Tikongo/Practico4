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
	public boolean member(IConexion iCon, String cod) throws ExcepAccesoADatos {
		Conexion<PrintWriter> con = (Conexion<PrintWriter>)iCon;
		
		boolean member = false;
		
		return member;
	}

	@Override
	public void insert(IConexion iCon, Folio fol) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		Conexion<FileWriter> c = (Conexion<FileWriter>)iCon;
		//FileWriter f = new FileWriter
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
