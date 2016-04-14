package sudoku.modelo;

/**
 * Clase de celda
 * @author F�lix Laguna Teno
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
	/**
	 * Coloca un n�mero en la celda.
	 * @param numero numero
	 */
	public void establecerNumero(int numero){
		this.numero=numero;
	}
	/**
	 * Devuelve el n�mero.
	 * @return n�mero
	 */
	public int devolverNum(){
		return numero;
	}
	/**
	 * Devuelve si la celda tiene o no una letra.
	 * @return true si est� vac�a, false si no
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
		if (numero==0){
			s+="";
		}else{
			s+=numero;
		}
		return s;
		}
	/**
	 * Vac�a la celda.
	 */
	public void vaciar(){
		numero=0;
	}
	@Override
	/**
	 * Devuelve la celda como String.
	 * @return cadena de la celda
	 */
	public String toString(){
		String s=new String();
		s+="<"+fila+"-"+numero+"-"+columna+">";
		return s;
	}
	/**
	 * Sobreescritura de clone.
	 */
	@Override
	public Celda clone(){
		Celda c=new Celda(fila,columna);
		c.establecerNumero(numero);
		return c;
	}
	/**
	 * Incrementa el n�mero de la celda.
	 */
	public void incrementar(){
		if (numero>=9){
			numero=1;
		}else{
			numero++;
		}
	}
	/**
	 * Decrementa el n�mero de la celda.
	 */
	public void decrementar(){
		if (numero<=1){
			numero=9;
		}else{
			numero--;
		}
	}
}
