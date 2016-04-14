package sudoku.modelo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
/**
 * Almacen de celdas.
 * @author Félix Laguna Teno
 *
 */
public class Almacen {
	/**
	 * Lista de celdas.
	 */
	private List<Celda> celdas;
	/**
	 * Constructor del almacen.
	 * @param N int dimensión
	 */
	public Almacen(int N){
		celdas=new LinkedList<Celda>();
	}
	/**
	 * Devuelve la lista de celdas.
	 * @return lista<Celda> de celdas
	 */
	public List<Celda> obtenerCeldas(){
		return celdas;
	}
	/**
	 * Añade una celda al almacen.
	 * @param c Celda
	 */
	public void addCell(Celda c){
		celdas.add(c);
	}
	/**
	 * Comprueba si el almacen cumple las condiciones para ser correcto, tener todos los numeros distintos.
	 * @return true si es correcto, false si no
	 */
	public boolean esCorrecto(){
		List<Integer> lista=new ArrayList<Integer>();
		for (Celda c:celdas){
			lista.add(c.devolverNum());
		}
		return (lista.size()==new HashSet<>(lista).size());
	}
	/**
	 * Devuelve la lista de numeros válidos para el almacen.
	 * @return lista<Integer> de número válidos.
	 */
	public List<Integer> numeroValidos(){
		List<Integer> resultado=new LinkedList<Integer>();
		for (int i=1;i<10;++i){
			resultado.add(i);
		}
		for (Celda c:celdas){
			if (!(c.estaVacia())){
				if (resultado.contains(c.devolverNum())){
					resultado.remove((Integer)c.devolverNum());
				}
			}
		}
		return resultado;
			
	}
	@Override
	public String toString(){
		String s=new String();
		for (int i=0;i<celdas.size();++i){
			s+=celdas.get(i).toString();
		}
		return s;
	}
	/**
	 * Comprueba si un objeto esta contenido en el almacen.
	 * @param o Objeto
	 * @return true si esta contenido, false si no
	 */
	public boolean contains(Object o){
		return celdas.contains(o);
	}
}
