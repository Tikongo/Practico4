package persistencia.daos;

import java.util.List;
import logica.Folio;
import logica.excepciones.ExcepAccesoADatos;
import logica.excepciones.ExcepArchivoNoEncontrado;
import logica.valueObjects.*;
import persistencia.accesoDatos.*;
import java.io.*;

public class DAOFoliosArchivo implements IDAOFolios{
	
	@Override
	public boolean member(IConexion iCon, String cod) throws ExcepAccesoADatos {
		Conexion<String> con = (Conexion<String>)iCon;
		boolean member = false;
		File f = new File(con.getConexion(),cod);
		member = f.exists();
		return member;
	}

	@Override
	public void insert(IConexion iCon, Folio fol) throws ExcepAccesoADatos {
		try {
			Conexion<String> con = (Conexion<String>)iCon;
			String filename = fol.getCodigo()+".txt";
			File f = new File(con.getConexion(),filename);
			PrintWriter pw = new PrintWriter(f);
			pw.println(fol.getCodigo());
			pw.println(fol.getCaratula());
			pw.println(Integer.toString(fol.getPaginas()));
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ExcepAccesoADatos("Error de acceso a los datos");
		}
	}

	@Override
	public Folio find(IConexion icon, String cod) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		Folio folio = null;
		
		return folio;
	}

	@Override
	public void delete(IConexion icon, String cod) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ListaVOFolios listarFolios(IConexion icon) throws ExcepAccesoADatos {
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
