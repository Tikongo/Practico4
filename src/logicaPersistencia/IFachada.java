package logicaPersistencia;

import java.sql.SQLException;
import java.util.List;

import logicaPersistencia.excepciones.ExcepAccesoADatos;
import logicaPersistencia.excepciones.ExcepFolioNoExiste;
import logicaPersistencia.excepciones.ExcepFolioYaExiste;
import logicaPersistencia.excepciones.ExcepNoHayFoliosRegistrados;
import logicaPersistencia.valueObjects.VOFolio;
import logicaPersistencia.valueObjects.VOFolioMaxRev;
import logicaPersistencia.valueObjects.VORevision;

public interface IFachada {

	void agregarFolio(VOFolio voF) throws ExcepFolioYaExiste, ExcepAccesoADatos, SQLException;

	void agregarRevision(VOFolio voF,VORevision voR) throws ExcepFolioNoExiste, ExcepNoHayFoliosRegistrados, SQLException;

	void borrarFolioRevisiones(String codF)throws ExcepFolioYaExiste, ExcepAccesoADatos ,SQLException;

	String darDescripcion(String codF, int numR);

	List<VOFolio> listarFolios() throws ExcepAccesoADatos;

	List<VORevision> listarRevisiones(String codF) throws ExcepAccesoADatos;

	VOFolioMaxRev folioMasRevisado() throws ExcepAccesoADatos;

}