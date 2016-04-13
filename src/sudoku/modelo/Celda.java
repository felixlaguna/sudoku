package sudoku.modelo;
/**
 * Clase de celda.
 * @author FELIX
 *
 */
public class Celda {
	/**
	 * Numero.
	 */
	private int numero;
	/**
	 * Fila.
	 */
	private int fila;
	/**
	 * Columna.
	 */
	private int columna;
	/**
	 * Constructor de celda.
	 * @param fila fila
	 * @param columna columna
	 */
	public Celda(int fila, int columna){
		this.fila=fila;
		this.columna=columna;
	}
	
	public void establecerNumero(int numero){
		this.numero=numero;
	}
	public int devolverNum(){
		return numero;
	}
	/**
	 * Devuelve si la celda tiene o no una letra.
	 * @return true si está vacía, false si no
	 */
	public boolean estaVacia(){
		return numero==0;
	}
	/**
	 * Consulta la fila de la celda.
	 * @return fila
	 */
	public int obtenerFila(){
		return fila;
	}
	/**
	 * Consulta la columna de la celda.
	 * @return columna
	 */
	public int obtenerColumna(){
		return columna;
	}
	/**
	 * Devuelve la letra como string.
	 * @return String de la letra
	 */
	public String obtenerString(){
		String s=new String();
		s+=numero;
		return s;
		}
	public void vaciar(){
		numero=0;
	}
	@Override
	public String toString(){
		String s=new String();
		s+="<"+fila+"-"+numero+"-"+columna+">";
		return s;
	}
}
