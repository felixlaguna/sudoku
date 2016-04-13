package sudoku.control;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import sudoku.modelo.Celda;
import sudoku.modelo.Tablero;


/**
 * Arbitro de la sopa de letras.
 * @author FELIX
 *
 */
public class Arbitro {
	/**
	 * Tablero.
	 */
	private Tablero tablero;
	/**
	 * Constructor del árbitro.
	 * @param filas filas
	 * @param columnas columnas
	 */
	public Arbitro(int filas, int columnas){
		tablero=new Tablero(filas,columnas);
	}
	/**
	 * Devuelve si una palarba cabe en una celda, en un sentido.
	 * @param palabra palabra
	 * @param celda celda
	 * @param sentido sentido
	 * @return true si cabe, false si no
	 */
	
	/**
	 * Devuelve el tablero.
	 * @return tablero
	 */
	public Tablero obtenerTablero(){
		return tablero;
	}
	public void colocar(int fila, int columna,int num){
		tablero.obtenerCelda(fila, columna).establecerNumero(num);
	}
	public void colocar(Celda c,int num){
		if (tablero.estaEnTablero(c)){
			c.establecerNumero(num);
		}
	}
	public static <T> T aleatorio(List<T> lista){
		if (lista.size()>0){
			return lista.get(ThreadLocalRandom.current().nextInt(0,lista.size()));
		}
		return null;
	}
	public List<Integer> numerosValidos(Celda c){
		List<Integer> temp1=tablero.obtenerRecuadro(c).numeroValidos();
		List<Integer> temp2=tablero.obtenerFila(c).numeroValidos();
		List<Integer> temp3=tablero.obtenerColumna(c).numeroValidos();
		temp1.retainAll(temp2);
		temp1.retainAll(temp3);
		return temp1;
	}
	public void rellenar(){
		List<Celda> vacias=celdasVacias();
		while (!vacias.isEmpty()){
			Celda c=Arbitro.aleatorio(vacias);
			
			if (numerosValidos(c).isEmpty()){
				System.out.println("Error en celda"+c.toString());
				List<Integer> temp=numerosValidos(c);
			}
			c.establecerNumero(Arbitro.aleatorio(numerosValidos(c)));
			vacias.remove(c);
			System.out.println(tablero.toString());
		}
		int a=0;
	}
	public List<Celda> celdasVacias(){
		List<Celda> resultado=new LinkedList<Celda>();
		for (int i=0;i<tablero.obtenerNumeroFilas();++i){
			for (int j=0;j<tablero.obtenerNumeroColumnas();++j){
				Celda c=tablero.obtenerCelda(i, j);
				if (c.estaVacia()){
					resultado.add(c);
				}
			}
		}
		return resultado;
	}
	
}
