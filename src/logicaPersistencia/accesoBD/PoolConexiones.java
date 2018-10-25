package logicaPersistencia.accesoBD;

import sistema.logica.asignaturas.Asignatura;

public class PoolConexiones implements IPoolConexiones {
	
	private String driver;
	private String url;
	private String user;
	private String pwd;
	private int nivelTran;
	private int TAM = 10;
	private int tope;
	private int creadas;
	private Conexion arrConexiones[];
	
	public PoolConexiones() {
		arrConexiones= new Conexion[TAM];
		tope = 0;
		creadas = 0;
	}
	
	public IConexion obtenerConexion(Boolean t) {
		/*HACER*/
	}
	
	public void liberarConexion(IConexion iC, Boolean t) {
		/*HACER*/
	}
}
