package persistencia.accesoDB;

import java.io.*;

public class ConexionArchivo implements IConexion{
	
	private FileWriter archivo;
	
	public ConexionArchivo(String nombreArchivo) throws IOException {
		try {
			archivo = new FileWriter(nombreArchivo);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}
	
	public FileWriter getConexion() {
		return archivo;
	}
}
