package logicaPersistencia.excepciones;

import java.sql.SQLException;

public class ExcepPersistencia extends SQLException {
		
		private String mensaje;
		
		public ExcepPersistencia(String mensaje)
		{
			this.mensaje=mensaje;
		}
		
		public String darMensaje()
		{
			return mensaje;
		}
}
