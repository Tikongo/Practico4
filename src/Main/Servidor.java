package Main;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.net.MalformedURLException;
import logica.Fachada;

public class Servidor {
	
	public static void main(String[] args) {
		try {
			
			Properties p=new Properties();
			String nomArch="dbEstudioJuridico.properties";
			p.load(new FileInputStream(nomArch));
			String ip=p.getProperty("ipServidor");
			String puerto=p.getProperty("puertoServidor");
			int port = Integer.parseInt(puerto);
			
			//Pongo a correr el rmiregistry
			LocateRegistry.createRegistry(port);
			
			//publico el objeto remoto en dicha ip y puerto
			String ruta="//"+ip+":"+puerto+"/fachada";
			Fachada fachada=Fachada.getInstancia();
			
			Naming.rebind(ruta, fachada);
			System.out.println("Servidor en linea");
			
		} catch(RemoteException rE)	{
			rE.printStackTrace();
		} catch(MalformedURLException mEx) {
			mEx.printStackTrace();
		} catch(FileNotFoundException fnotfEx) {
			fnotfEx.printStackTrace();
		} catch(IOException ioEx) {
			ioEx.printStackTrace();
		}
	}
}

