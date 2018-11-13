package persistencia.daos;

import java.util.List;
import logica.Folio;
import logica.excepciones.ExcepAccesoADatos;
import logica.excepciones.ExcepArchivoNoEncontrado;
import logica.valueObjects.*;
import persistencia.accesoDatos.*;

import java.io.*;

public class DAOFoliosArchivo implements IDAOFolios{
	
	Monitor monitor=new Monitor();
	
	private VOFolio leerVOFolio(File f) throws IOException {
		VOFolio voF = null;
		monitor.comienzoLectura();
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String leido = br.readLine();
			if (leido != null) {
				voF.setCodigo(leido);
				leido = br.readLine();
			}
			if (leido != null) {
				voF.setCaratula(leido);
				leido = br.readLine(); 
			}
			if (leido != null) {
				voF.setPaginas(Integer.parseInt(leido));
				leido = br.readLine();
			}
			monitor.terminoLectura();
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		}
		return voF;		
	}
	
	@Override
	public boolean member(IConexion iCon, String cod) throws ExcepAccesoADatos {
		Conexion<String> con = (Conexion<String>)iCon;
		boolean member = false;
		File f = new File(con.getConexion(),cod);
		monitor.comienzoLectura();
		member = f.exists();
		monitor.terminoLectura();
		return member;
	}

	@Override
	public void insert(IConexion iCon, Folio fol) throws ExcepAccesoADatos {
		monitor.comienzoEscritura();
		try {
			Conexion<String> con = (Conexion<String>)iCon;
			String filename = fol.getCodigo()+".txt";
			File f = new File(con.getConexion(),filename);
			PrintWriter pw = new PrintWriter(f);
			pw.println(fol.getCodigo());
			pw.println(fol.getCaratula());
			pw.println(Integer.toString(fol.getPaginas()));
			pw.close();
			monitor.terminoEscritura();
		} catch (FileNotFoundException e) {
			monitor.terminoEscritura();
			e.printStackTrace();
			throw new ExcepAccesoADatos("Error de acceso a los datos");
		}
	}

	@Override
	public Folio find(IConexion iCon, String cod) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		Folio folio = null;
		monitor.comienzoLectura();
		try {
			Conexion<String> con = (Conexion<String>)iCon;
			String filename = cod+".txt";
			File f = new File(con.getConexion(),filename);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String leido = br.readLine();
			if (leido != null) {
				folio.setCodigo(leido);
				leido = br.readLine();
			}
			if (leido != null) {
				folio.setCaratula(leido);
				leido = br.readLine(); 
			}
			if (leido != null) {
				folio.setPaginas(Integer.parseInt(leido));
				leido = br.readLine();
			}
			monitor.terminoLectura();
		} catch (FileNotFoundException e) {
			monitor.terminoLectura();
			e.printStackTrace();
			throw new ExcepAccesoADatos("Error al acceder a los datos");
		} catch (IOException e) {
			monitor.terminoLectura();
			e.printStackTrace();
			throw new ExcepAccesoADatos("Error al acceder a los datos");
		}
		return folio;
	}
	
	//private void deleteRevisiones ()
	
	@Override
	public void delete(IConexion iCon, String cod) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		monitor.comienzoEscritura();
		Conexion<String> con = (Conexion<String>)iCon;
		String filename = cod+".txt";
		File f = new File(con.getConexion(),filename);
		//ANTES DE BORRAR SE DEBEN BORRAR TODAS SUS REVISIONES.
		boolean borrado = f.delete();
		if (!borrado) {
			throw new ExcepAccesoADatos("No se pudo eliminar el archivo");
		}
		monitor.terminoEscritura();
	}

	@Override
	public ListaVOFolios listarFolios(IConexion iCon) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		ListaVOFolios listaFolios = new ListaVOFolios();
		VOFolio voF = null;
		monitor.comienzoLectura();
		try {
			Conexion<String> con = (Conexion<String>)iCon;
			File f = new File(con.getConexion());
			for (final File arch : f.listFiles()) {
				if (arch.isFile()) {
					voF = leerVOFolio(arch);
					listaFolios.insert(voF);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExcepAccesoADatos("Error al acceder a los datos");
		}
		return listaFolios;
	}

	@Override
	public boolean esVacio(IConexion iCon) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		boolean vacio = false;
		Conexion<String> con = (Conexion<String>)iCon;
		File f = new File(con.getConexion());
		if (f.isDirectory()) {
			if (f.list().length == 0)
				vacio = true;
		}
		return vacio;
	}

	@Override
	public VOFolioMaxRev folioMasRevisado(IConexion icon) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		return null;
	}

}
