package logicaPersistencia;

import java.sql.SQLException;
import java.util.List;
import logicaPersistencia.excepciones.ExcepAccesoADatos;
import logicaPersistencia.excepciones.ExcepFolioNoExiste;
import logicaPersistencia.excepciones.ExcepFolioYaExiste;
<<<<<<< HEAD
import logicaPersistencia.valueObjects.*;
=======
import logicaPersistencia.excepciones.ExcepNoHayFoliosRegistrados;
import logicaPersistencia.valueObjects.VOFolio;
import logicaPersistencia.valueObjects.VOFolioMaxRev;
import logicaPersistencia.valueObjects.VORevision;
>>>>>>> ee7e7f853dff7f001b78c2aa6ba26861d2e23e10

public interface IFachada {

	void agregarFolio(VOFolio voF) throws ExcepFolioYaExiste, ExcepAccesoADatos, SQLException;

	void agregarRevision(VOFolio voF,VORevision voR) throws ExcepFolioNoExiste, ExcepNoHayFoliosRegistrados, SQLException;

	void borrarFolioRevisiones(String codF)throws ExcepFolioYaExiste, ExcepAccesoADatos ,SQLException;

	String darDescripcion(String codF, int numR);

	ListaVOFolios listarFolios() throws ExcepAccesoADatos;

	ListaVORevisiones listarRevisiones(String codF) throws ExcepAccesoADatos;

	VOFolioMaxRev folioMasRevisado() throws ExcepAccesoADatos;

}