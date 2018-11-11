package persistencia.consultas.Archivo;

import java.io.*;

import logica.excepciones.ExcepAccesoADatos;

public class lecturaEscrituraArchivo {

	public void escribirArchivo (String nomArch, String texto) throws ExcepAccesoADatos{
		try
		{ 
			FileWriter f = new FileWriter(nomArch);
			BufferedWriter b = new BufferedWriter(f);
			b.write(texto);
			b.close();
			f.close();
		}
		catch (IOException e){
			e.printStackTrace();
			throw new ExcepAccesoADatos("Error al acceder a los datos");
		}
	}
	
	public void leerArchivo (String nomArch, String texto)	throws ExcepAccesoADatos{ 
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
		catch (IOException e){ 
			e.printStackTrace();
			throw new ExcepAccesoADatos("Error al acceder a los datos");
		}
	}
}
