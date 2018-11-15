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
		String codigo = null;
		String caratula = null;
		int paginas = 0;
		monitor.comienzoLectura();
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			codigo = br.readLine();
			if (codigo != null) {
				caratula = br.readLine();
				if (caratula != null) {
					paginas = Integer.parseInt(br.readLine());
				}
			}
			voF = new VOFolio(codigo,caratula,paginas);
			monitor.terminoLectura();
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		}
		return voF;
	}
	
	@Override
	public boolean member(IConexion iCon, String cod) throws ExcepAccesoADatos {
		Conexion<String[]> con = (Conexion<String[]>)iCon;
		boolean member = false;
		String path = con.getConexion()[0];
		String filename = cod+".txt";
		File f = new File(path,filename);
		monitor.comienzoLectura();
		member = f.exists();
		monitor.terminoLectura();
		return member;
	}

	@Override
	public void insert(IConexion iCon, Folio fol) throws ExcepAccesoADatos {
		monitor.comienzoEscritura();
		try {
			Conexion<String[]> con = (Conexion<String[]>)iCon;
			String filename = fol.getCodigo()+".txt";
			File f = new File(con.getConexion()[0],filename);
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
		String leido = null;
		monitor.comienzoLectura();
		try {
			Conexion<String[]> con = (Conexion<String[]>)iCon;
			String filename = cod+".txt";
			String codigo = null;
			String caratula = null;
			int paginas = 0;
			File f = new File(con.getConexion()[0],filename);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			codigo = br.readLine();
			if (codigo != null) {
				caratula = br.readLine();
				if (caratula != null) {
					paginas = Integer.parseInt(br.readLine());
				}
			}
			folio = new Folio(codigo,caratula,paginas);
			br.close();
			fr.close();
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
	
	@Override
	public void delete(IConexion iCon, String cod) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		monitor.comienzoEscritura();
		Conexion<String[]> con = (Conexion<String[]>)iCon;
		String filename = cod+".txt";
		//String fullPath = "C:"+File.separator+"Folios"+File.separator+"folio-a-borrar.txt";
		File f = new File(con.getConexion()[0],filename);
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
			Conexion<String[]> con = (Conexion<String[]>)iCon;
			File f = new File(con.getConexion()[0]);
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
		Conexion<String[]> con = (Conexion<String[]>)iCon;
		File f = new File(con.getConexion()[0]);
		if (f.isDirectory()) {
			if (f.list().length == 0)
				vacio = true;
		}
		return vacio;
	}

	@Override
	public VOFolioMaxRev folioMasRevisado(IConexion iCon) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		int cantRev = 0;
		DAORevisionesArchivo daoRev = null;
		VOFolio voF = null;
		VOFolio voFFinal = null;
		VOFolioMaxRev folioMaxRev = null;
		monitor.comienzoLectura();
		try {
			Conexion<String[]> con = (Conexion<String[]>)iCon;
			File dirFolio = new File(con.getConexion()[0]);
			//File dirRev = new File(con.getConexion()[1]);
			for (final File arch : dirFolio.listFiles()) {
				if (arch.isFile()) {
					voF = leerVOFolio(arch);
					daoRev = new DAORevisionesArchivo(voF.getCodigo());
					if (daoRev.largo(con) > cantRev) {
						cantRev = daoRev.largo(con);
						voFFinal = voF;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExcepAccesoADatos("Error de acceso a los datos");
		}
		if (voFFinal != null) {
			folioMaxRev = new VOFolioMaxRev(voFFinal.getCodigo(),voFFinal.getCaratula(),voFFinal.getPaginas(),cantRev);
		}
		return folioMaxRev;
	}

}
