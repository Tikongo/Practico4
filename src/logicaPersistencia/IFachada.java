package logicaPersistencia;

import java.sql.SQLException;
import java.util.List;

import logicaPersistencia.excepciones.ExcepAccesoADatos;
import logicaPersistencia.excepciones.ExcepFolioYaExiste;
import logicaPersistencia.valueObjects.VOFolio;
import logicaPersistencia.valueObjects.VOFolioMaxRev;
import logicaPersistencia.valueObjects.VORevision;

public interface IFachada {

	void agregarFolio(VOFolio voF) throws ExcepFolioYaExiste, ExcepAccesoADatos, SQLException;

	void agregarRevision(String codF, String desc);

	void borrarFolioRevisiones(String codF)throws ExcepFolioYaExiste, ExcepAccesoADatos ,SQLException;

	String darDescripcion(String codF, int numR);

	List<VOFolio> listarFolios() throws ExcepAccesoADatos;

	List<VORevision> listarRevisiones(String codF) throws ExcepAccesoADatos;

	VOFolioMaxRev folioMasRevisado() throws ExcepAccesoADatos;

}