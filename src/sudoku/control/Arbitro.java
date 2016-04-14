package sudoku.control;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
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
	/**
	 * Método estático para devolver un elemento aleatorio de una lista.
	 * @param lista lista<T>
	 * @return elemento T aleatorio
	 */
	private static <T> T aleatorio(List<T> lista){
		if (lista.size()>0){
			return lista.get(ThreadLocalRandom.current().nextInt(0,lista.size()));
		}
		return null;
	}
	/**
	 * Devuelve los numeros válidos para una celda y un tablero.
	 * @param c Celda
	 * @param tab tablero
	 * @return Lista<Integer> de numeros válidos
	 */
	private List<Integer> numerosValidos(Celda c,Tablero tab){
		List<Integer> temp1=tab.obtenerRecuadro(c).numeroValidos();
		List<Integer> temp2=tab.obtenerFila(c).numeroValidos();
		List<Integer> temp3=tab.obtenerColumna(c).numeroValidos();
		temp1.retainAll(temp2);
		temp1.retainAll(temp3);
		return temp1;
	}
	/**
	 * Solucionador 1 para el sudoku.
	 * @param tab tablero
	 * @return true si tiene solución, false si no
	 */
	private boolean solver(Tablero tab){
		List<Celda> vacias=celdasVacias(tab);

		if (vacias.isEmpty()){
			return true;
		}
		Celda c=vacias.get(0);
		List<Integer> numVal=numerosValidos(c,tab);
		for (int i:numVal){

			c.establecerNumero(i);
			if (solver(tab)){
				return true;
			}
			c.vaciar();
			System.out.println(obtenerTablero().toString());
		}
		return false;

	}
	/**
	 * Solucionador y generador de tableros aleatorios.
	 * @return true si tiene solución, false si no
	 */
	public boolean solverRapido(){
		List<Celda> vacias=celdasVacias(tablero);

		if (vacias.isEmpty()){
			return true;
		}
		Celda c=vacias.get(0);
		List<Integer> numVal=numerosValidos(c,tablero);
		Collections.shuffle(numVal);
		for (int i:numVal){

			c.establecerNumero(i);
			if (solverRapido()){
				return true;
			}
			c.vaciar();
		}
		return false;
	}
	/**
	 * Devuelve la lista de celda vacías de un tablero.
	 * @param tab tablero
	 * @return lista<Celda> de celdas
	 */
	public List<Celda> celdasVacias(Tablero tab){
		List<Celda> resultado=new LinkedList<Celda>();
		for (int i=0;i<tab.obtenerNumeroFilas();++i){
			for (int j=0;j<tab.obtenerNumeroColumnas();++j){
				Celda c=tab.obtenerCelda(i, j);
				if (c.estaVacia()){
					resultado.add(c);
				}
			}
		}
		return resultado;
	}
	/**
	 * Rellenador 1 del tablero, tiempo aproximado: 5 min.
	 */
	@SuppressWarnings("unused")
	private void rellenar(){
		List<Celda> vacias=celdasVacias(tablero);
		Celda c;
		vacias=celdasVacias(tablero);
		while (!vacias.isEmpty()){
			c=Arbitro.aleatorio(vacias);
			int num=Arbitro.aleatorio(numerosValidos(c,tablero));
			c.establecerNumero(num);
			Tablero tab=tablero.clone();
			if (!solver(tab)){
				c.vaciar();
			}

		}
	}
	/**
	 * Elimina determinadas celdas y deja el sudoku listo para la resolución.
	 * @param dificultad int que indica la dificultad, no mayor que 7
	 */
	public void terminar(int dificultad){
		List<Celda> lista=tablero.toList();
		long seed=System.nanoTime();
		Collections.shuffle(lista, new Random(seed));
		boolean bucle=true;
		while (bucle){
			if (lista.size()>0){
				Celda r=lista.get(0);
				int num=r.devolverNum();
				r.vaciar();
				if (soluciones()>dificultad+1){
					r.establecerNumero(num);
					bucle=false;
				}else{
					lista.remove(r);
				}
			}else{
				bucle=false;
			}

		}
	}
	/**
	 * Devuelve el número de soluciones del tablero.
	 * @return
	 */
	private int soluciones(){
		int max=0;
		for (int i=0;i<tablero.obtenerNumeroFilas();++i){
			for (int j=0;j<tablero.obtenerNumeroColumnas();++j){
				if (max<numerosValidos(tablero.obtenerCelda(i, j),tablero).size()){
					max=numerosValidos(tablero.obtenerCelda(i, j),tablero).size();
				}
			}
		}
		return max;
	}
	/**
	 * Comprueba si el tablero es correcto.
	 * @return true si es correcto, false si no
	 */
	public boolean correcto(){
		boolean resultado=true;
		for (int i=0;i<9;++i){
			resultado&=tablero.obtenerRecuadro(i).esCorrecto();
			resultado&=tablero.obtenerFila(i).esCorrecto();
			resultado&=tablero.obtenerColumna(i).esCorrecto();
		}
		return resultado;
	}
}
