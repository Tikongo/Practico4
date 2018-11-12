package persistencia.accesoDatos;

public class Monitor {
	
	private int cantLectores;
	private boolean escribiendo;
	
	public Monitor() {
		cantLectores=0;
		escribiendo=false;
	}
	
	public synchronized void comienzoLectura() {
		while(escribiendo) {
			try {
				this.wait();
			} catch(InterruptedException iExc)
			{	}
		}
		cantLectores++;
	}
	
	public synchronized void terminoLectura() {
		cantLectores--;
		if(cantLectores == 0) {
			notify();
		}
	}
	
	public synchronized void comienzoEscritura() {
		while(cantLectores > 0 || escribiendo) {
			try {
				wait();
			} catch(InterruptedException iExc)
			{	}
		}
	}
	
	public synchronized void terminoEscritura() {
		escribiendo=false;
		notify();
	}
}
