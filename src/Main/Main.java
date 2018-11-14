package Main;

import java.sql.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		try {
			//1. cargo dinamicamente el driver de MySQL/ 
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			
			/* 2. una vez cargado el driver, me conecto con el motor de la base, de forma gen�rica 
			 * porque vamos a crear la base en el pr�ximo paso.*/
			String url = "jdbc:mysql://localhost:3306/";
			Connection con = DriverManager.getConnection(url,"root","BD32018");
			
			//3. creo un PreparedStatement para crear la base de datos./ 
			String qCreateDB = "CREATE DATABASE ESTUDIOJURIDICO";
			PreparedStatement pstmtCreateDB = con.prepareStatement(qCreateDB);
			pstmtCreateDB.executeUpdate();
			pstmtCreateDB.close();
			con.close();

			/* 4. creamos las tablas de la base de datos.*/
			String url2 = "jdbc:mysql://localhost:3306/ESTUDIOJURIDICO";
			Connection con2 = DriverManager.getConnection(url2,"root","BD32018");
			
			String qCreateTableExamenes = "CREATE TABLE Folios (codigo VARCHAR(60) PRIMARY KEY, caratula VARCHAR(60), paginas INT)";
			String qCreateTableResultados = "CREATE TABLE Revisiones (numero INT, codigoFolio VARCHAR(60), descripcion VARCHAR(60), PRIMARY KEY (numero,codigoFolio), FOREIGN KEY (codigoFolio) REFERENCES Folios(codigo))";
			PreparedStatement pstmtCreateTableExamenes = con2.prepareStatement(qCreateTableExamenes);
			PreparedStatement pstmtCreateTableResultados = con2.prepareStatement(qCreateTableResultados);
			pstmtCreateTableExamenes.executeUpdate();
			pstmtCreateTableResultados.executeUpdate();
			pstmtCreateTableExamenes.close();
			pstmtCreateTableResultados.close();
			
			/* 5. insertamos la informaci�n de los examenes.*/
			String qInsertExamenes = "INSERT INTO Folios (codigo,caratula,paginas) VALUES (\"FGH-0015\",\"La comuna contra la se�ora con 38 gatos\",5),(\"BBD-1278\",\"Adolescentes descontrolados hasta las 5 AM\",2),(\"JJ-202\",\"Vecinos reclaman por heces de perro en el hall\",9),(\"CEFJ-63\",\"Vecinas rivales se tiran macetas con frecuencia\",463)";
			PreparedStatement pstmtInsertExamenes = con2.prepareStatement(qInsertExamenes);
			pstmtInsertExamenes.executeUpdate();
			pstmtInsertExamenes.close();
			
			con2.close();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

}