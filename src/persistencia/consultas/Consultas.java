package persistencia.consultas;

public class Consultas {

	public String existeFolio() {
		String query = "SELECT codigo FROM Folios WHERE codigo = (?)";
		return query;
	}
	public String queryAgregarFolio() {
		String query = "INSERT INTO Folios (codigo,caratula,paginas) values (?,?,?)";
		return query;
	}
	
	public String queryAgregarRevision() {
		String query = "INSERT INTO Revisiones (numero,codigoFolio,descripcion) values (?,?,?)";
		return query;
	}
	
	public String queryBorrarFolioRevisiones() {
		String query = "DELETE FROM Revisiones WHERE codigoFolio= (?) ";
		return query;
	}
	
	public String queryBorrarFolio() {
		String query = "DELETE FROM Folios WHERE codigo= (?) ";
		return query;
	}
	
	public String queryDarDescripcion() {
		String query = "SELECT descripcion FROM Revisiones where codigoFolio= (?) AND numero = (?)";
		return query;
	}
	
	public String queryListarFolios() {
		String query = "SELECT * FROM Folios ORDER BY codigo";
		return query;
	}
	
	public String queryListarRevisiones() {
		String query = "SELECT * FROM Revisiones where codigoFolio = (?)  ORDER BY numero";
		return query;
	}
	
	public String queryFolioMasRevisado() {
		String query = "SELECT F.codigo, f.caratula, F.paginas, count(R.numero) as TotalRev FROM Folios F, Revisiones R where F.codigo=R.codigoFolio GROUP BY F.codigo ORDER BY TotalRev DESC LIMIT 1";
		return query;
	}
	
	public String queryFindFolio() {
		String query = "SELECT * FROM Folios where codigo = (?)";
		return query;
	}
	
	public String queryDarRevision() {
		String query = "SELECT * FROM Revisiones where codigoFolio= (?) AND numero = (?)";
		return query;
	}
	
	public String queryCantRevision() {
		String query = "SELECT count(*) as TotalRev FROM Revisiones WHERE codigoFolio = (?) ";
		return query;
	}
}
