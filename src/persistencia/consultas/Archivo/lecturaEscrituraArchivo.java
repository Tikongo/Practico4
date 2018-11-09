package persistencia.consultas.Archivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import logicaPersistencia.excepciones.ExcepArchivoNoEncontrado;

public class lecturaEscrituraArchivo {

	public void escribirArchivo (String nomArch, String texto) throws IOException{
		try
			{ 
			FileWriter f = new FileWriter(nomArch);
			BufferedWriter b = new BufferedWriter(f);
			
			b.write(texto);
			b.close();
			f.close();
		}
		catch (ExcepArchivoNoEncontrado e){
			e.printStackTrace();
			throw new ExcepArchivoNoEncontrado("Error al escribir archivo");
		}
	}
	
	public void leer (String nomArch, String texto)	throws IOException{ 
		try
			{ 
			FileReader f = new FileReader(nomArch);
			BufferedReader b = new BufferedReader(f);
			
			String resu = new String();
			String aux = b.readLine();
			while (aux != null){
				resu = resu + aux;
				aux = b.readLine();
			}
			b.close();
			f.close();
		}
		catch (ExcepArchivoNoEncontrado e){ 
			e.printStackTrace();
			throw new ExcepArchivoNoEncontrado("Error para leer archivo");
		}
	}
}
