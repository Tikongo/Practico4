package logicaPersistencia.accesoBD;

public class Consultas {

	public String existeFolio() {
		String query = "SELECT codigo FROM Folios WHERE codigo = ?";
		return query;
	}
	public String queryAgregarFolio() {
		String query = "INSERT INTO Folios (codigo,caratula,pagina) values (?,?,?)";
		return query;
	}
	
	public String queryAgregarRevision() {
		String query = "INSERT INTO Revisiones (numero,codigoFolio,descripcion) values (?,?,?)";
		return query;
	}
	
	public String queryBorrarFolioRevisiones() {
		String query = "DELETE FROM Revisiones WHERE codigoFolio= ? ";
		return query;
	}
	
	public String queryDarDescripcion() {
		String query = "SELECT descripcion FROM Revisiones where codigoFolio= ? AND numero = ?";
		return query;
	}
	
	public String queryListarFolios() {
		String query = "SELECT * FROM Folios ORDER BY codigo";
		return query;
	}
	
	public String queryListarRevisiones() {
		String query = "SELECT * FROM Revisiones where codigoFolio = ?  ORDER BY numero";
		return query;
	}
	
	public String queryFolioMasRevisado() {
		String query = "SELECT * FROM Folios f,Revisiones r where f.codigo=r.codigoFolio GROUP BY r.numero ORDER BY COUNT (r.numero) DESC LIMIT 1 ";
		return query;
	}
	
	public String queryFindFolio() {
		String query = "SELECT * FROM Folios where codigo = ?";
		return query;
	}
}
