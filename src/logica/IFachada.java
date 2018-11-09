package logica;

import java.sql.SQLException;
import java.util.List;
import logicaPersistencia.excepciones.ExcepAccesoADatos;
import logicaPersistencia.excepciones.ExcepFolioNoExiste;
import logicaPersistencia.excepciones.ExcepFolioYaExiste;
import logicaPersistencia.valueObjects.*;
import logicaPersistencia.excepciones.*;
import logicaPersistencia.valueObjects.VOFolio;
import logicaPersistencia.valueObjects.VOFolioMaxRev;
import logicaPersistencia.valueObjects.VORevision;
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