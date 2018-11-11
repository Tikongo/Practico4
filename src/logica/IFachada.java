package logica;

import java.sql.SQLException;
import java.util.List;

import logica.excepciones.ExcepAccesoADatos;
import logica.excepciones.ExcepFolioNoExiste;
import logica.excepciones.ExcepFolioSinRevisiones;
import logica.excepciones.ExcepFolioYaExiste;
import logica.excepciones.ExcepNoHayFoliosRegistrados;
import logica.excepciones.ExcepRevisionNoExiste;
import logica.valueObjects.ListaVOFolios;
import logica.valueObjects.ListaVORevisiones;
import logica.valueObjects.VOFolio;
import logica.valueObjects.VOFolioMaxRev;
import logica.valueObjects.VORevision;
import logicaPersistencia.valueObjects.*;
import logicaPersistencia.excepciones.*;

import java.rmi.RemoteException;

public interface IFachada {

	void agregarFolio(VOFolio voF) throws ExcepFolioYaExiste, ExcepAccesoADatos, RemoteException;

	public void agregarRevision(String codF,String descripcion) throws ExcepFolioNoExiste, ExcepAccesoADatos, RemoteException;
	
	void borrarFolioRevisiones(String codF)throws ExcepFolioNoExiste, ExcepAccesoADatos, RemoteException;

	String darDescripcion(String codF, int numR) throws ExcepAccesoADatos, RemoteException, ExcepFolioNoExiste, ExcepRevisionNoExiste;

	ListaVOFolios listarFolios() throws ExcepAccesoADatos, RemoteException, ExcepNoHayFoliosRegistrados;

	ListaVORevisiones listarRevisiones(String codF) throws ExcepAccesoADatos, RemoteException, ExcepFolioNoExiste, ExcepFolioSinRevisiones;

	VOFolioMaxRev folioMasRevisado() throws ExcepAccesoADatos, RemoteException;

}