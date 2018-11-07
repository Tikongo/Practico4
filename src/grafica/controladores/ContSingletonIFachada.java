package grafica.controladores;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.Properties;

import logica.IFachada;

public class ContSingletonIFachada {

	private static ContSingletonIFachada instancia;
	private IFachada interfazFachada;
	
	private ContSingletonIFachada() 
	{
		try
		{
			//Intento conectarme
			Properties p=new Properties();
			String nomArch="dbEstudioJuridico.properties";
			p.load(new FileInputStream(nomArch));
			String ip=p.getProperty("ipServidor");
			String puerto=p.getProperty("puertoServidor");
			String ruta="//"+ip+":"+puerto+"/fachada";
			
			//Voy a buscar el objeto remoto
			interfazFachada = (IFachada) Naming.lookup(ruta);
		}
		catch(MalformedURLException mEx)
		{
			mEx.printStackTrace();			
		}
		catch(IOException ioEx)
		{
			ioEx.printStackTrace();			
		}
		catch(NotBoundException nobEx)
		{
			nobEx.printStackTrace();
		}	
	}
	
	public static ContSingletonIFachada getInstancia()
	{
		if(instancia==null)
			instancia= new ContSingletonIFachada();

		return instancia;	
	}	
	
	public IFachada getInterfazFachada()
	{
		return interfazFachada;
	}
}
