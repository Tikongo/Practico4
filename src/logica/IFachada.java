package logica;

import java.sql.SQLException;
import java.util.List;
import logicaPersistencia.excepciones.ExcepAccesoADatos;
import logicaPersistencia.excepciones.ExcepFolioNoExiste;
import logicaPersistencia.excepciones.ExcepFolioYaExiste;
import logicaPersistencia.valueObjects.*;
import logicaPersistencia.excepciones.ExcepNoHayFoliosRegistrados;
import logicaPersistencia.excepciones.ExcepPersistencia;
import logicaPersistencia.valueObjects.VOFolio;
import logicaPersistencia.valueObjects.VOFolioMaxRev;
import logicaPersistencia.valueObjects.VORevision;

public interface IFachada {

	void agregarFolio(VOFolio voF) throws ExcepFolioYaExiste, ExcepAccesoADatos,ExcepPersistencia;

	public void agregarRevision(String codF,String descripcion) throws ExcepFolioNoExiste, ExcepNoHayFoliosRegistrados, ExcepFolioYaExiste, ExcepAccesoADatos;
	
	void borrarFolioRevisiones(String codF)throws ExcepFolioYaExiste, ExcepAccesoADatos, ExcepPersistencia;

	String darDescripcion(String codF, int numR);

	ListaVOFolios listarFolios() throws ExcepAccesoADatos;

	ListaVORevisiones listarRevisiones(String codF) throws ExcepAccesoADatos;

	VOFolioMaxRev folioMasRevisado() throws ExcepAccesoADatos;

}