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
	public static <T> T aleatorio(List<T> lista){
		if (lista.size()>0){
			return lista.get(ThreadLocalRandom.current().nextInt(0,lista.size()));
		}
		return null;
	}
	public List<Integer> numerosValidos(Celda c,Tablero tab){
		List<Integer> temp1=tab.obtenerRecuadro(c).numeroValidos();
		List<Integer> temp2=tab.obtenerFila(c).numeroValidos();
		List<Integer> temp3=tab.obtenerColumna(c).numeroValidos();
		temp1.retainAll(temp2);
		temp1.retainAll(temp3);
		return temp1;
	}
	public boolean solver(Tablero tab){
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

	public void rellenar(){
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
	public int soluciones(){
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
