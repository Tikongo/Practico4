package persistencia.daos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;
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
	
	private String codigoFolio;
	Monitor monitor=new Monitor();
	private String indice;
		
	public DAORevisionesArchivo(String cod) {
		codigoFolio = cod;
		Properties prop = new Properties();
		InputStream input = null;
		String filename = "indiceRev-"+cod+".txt";
		try {
			input = new FileInputStream("dbEstudioJuridico.properties");
			prop.load(input);
			indice = prop.getProperty("pathRevisiones")+filename;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
	
	private void actualizarIndice(String filename) {
		monitor.comienzoEscritura();
		try {
			File f = new File(indice);
			PrintWriter pw = new PrintWriter(f);
			pw.println(filename);
			pw.close();
			monitor.terminoEscritura();
		} catch (IOException e) {
			e.printStackTrace();
			monitor.terminoEscritura();
		}
	}
	
	@Override
	public void insBack(IConexion iCon, Revision rev) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		String nuevoInd = Integer.toString(rev.getNumero());
		monitor.comienzoEscritura();
		try {
			Conexion<String[]> con = (Conexion<String[]>)iCon;
			String filename = nuevoInd+"-"+codigoFolio+".txt";
			File f = new File(con.getConexion()[1],filename);
			PrintWriter pw = new PrintWriter(f);
			pw.println(nuevoInd);
			pw.println(rev.getDescripcion());
			pw.println(codigoFolio);
			pw.close();
			monitor.terminoEscritura();
			actualizarIndice(filename);
			//actualizarIndice();
		} catch (IOException e) {
			e.printStackTrace();
			monitor.terminoEscritura();
			throw new ExcepAccesoADatos("Error al acceder a los datos");
		}
	}

	@Override
	public int largo(IConexion iCon) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		monitor.comienzoLectura();
		String leido = null;
		int largo = 0;
		try {
			FileReader fr = new FileReader(indice);
			BufferedReader br = new BufferedReader(fr);
			leido = br.readLine();
			while (leido != null) {
				largo++;
				leido = br.readLine();
			}
			monitor.terminoLectura();
		} catch (IOException e) {
			e.printStackTrace();
			monitor.terminoLectura();
			throw new ExcepAccesoADatos("Error al acceder a los datos");
		}
		return largo;
	}

	@Override
	public Revision kesimo(IConexion iCon, int numero) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		Revision rev = null;
		String leido = null;
		int pos = 0;
		monitor.comienzoLectura();
		try {
			Conexion<String[]> con = (Conexion<String[]>)iCon;
			FileReader fr = new FileReader(indice);
			BufferedReader br = new BufferedReader(fr);
			leido = br.readLine();
			while (leido!=null && Integer.parseInt(leido.substring(0,1))!=numero) {
				leido = br.readLine();
			}
			String filename = leido+".txt";
			File f = new File(con.getConexion()[1],filename);
			VORevision voR = leerVORevision(f);
			rev = new Revision(voR.getNumero(),voR.getDescripcion());
			monitor.terminoLectura();
		} catch (IOException e) {
			e.printStackTrace();
			monitor.terminoLectura();
			throw new ExcepAccesoADatos("Error al acceder a los datos");
		}
		return rev;
	}

	@Override
	public ListaVORevisiones listarRevisiones(IConexion iCon) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		ListaVORevisiones listaRevisiones = new ListaVORevisiones();
		VORevision voR = null;
		monitor.comienzoLectura();
		try {
			Conexion<String[]> con = (Conexion<String[]>)iCon;
			String sufijo = codigoFolio+".txt";
			File f = new File(con.getConexion()[1]);
			for (final File arch : f.listFiles()) {
				if (arch.isFile() && arch.getName().endsWith(sufijo)) {
					voR = leerVORevision(arch);
					listaRevisiones.insert(voR);
				}
			}
			monitor.terminoLectura();
		} catch (IOException e) {
			e.printStackTrace();
			monitor.terminoLectura();
			throw new ExcepAccesoADatos("Error al acceder a los datos");
		}
		return listaRevisiones;
	}

	@Override
	public void borrarRevisiones(IConexion iCon) throws ExcepAccesoADatos {
		// TODO Auto-generated method stub
		boolean borradoExitoso = true;
		monitor.comienzoEscritura();
		Conexion<String[]> con = (Conexion<String[]>)iCon;
		String sufijo = codigoFolio+".txt";
		File f = new File(con.getConexion()[1]);
		for (final File arch : f.listFiles()) {
			if (arch.isFile() && arch.getName().endsWith(sufijo) && borradoExitoso) {
				borradoExitoso = arch.delete();
			} else {
				throw new ExcepAccesoADatos("Error al acceder a los datos");
			}		
		}
		monitor.terminoEscritura();
	}

}
