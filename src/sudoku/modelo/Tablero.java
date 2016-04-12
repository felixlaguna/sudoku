package sudoku.modelo;

import java.util.ArrayList;
import java.util.List;

public class Tablero {

	/**
	 * Lista de lista de celdas (matriz).
	 */
	private List<List<Celda>> celdas;
	/**
	 * Lista de recuadros
	 */
	private List<Recuadro> recuadros;
	/**
	 * Constructor del tablero.
	 * @param filas filas
	 * @param columnas columnas
	 */
	public Tablero(int filas,int columnas){
		celdas=new ArrayList<List<Celda>>(filas);
		recuadros=new ArrayList<Recuadro>(9);
		for (int i=0;i<9;++i){
			recuadros.add(new Recuadro(9));
		}
		int aux = 0;
		int aux2=0;
		for (int i=0;i<filas;++i){
			celdas.add(i,new ArrayList<Celda>(columnas));
			for (int j=0;j<columnas;++j){
				celdas.get(i).add(j,new Celda(i,j));
				aux=i/3;
				aux2=j/3;
				int index=aux*3+aux2;
				Celda c=celdas.get(i).get(j);
				recuadros.get(index).addCell(c);
			}
		}
		int a=0;
		a=2*a;
	}
	/**
	 * Devuelve si la celda está en el tablero.
	 * @param x fila
	 * @param y columna
	 * @return true si está, false si no
	 */
	public boolean estaEnTablero(int x, int y){
		boolean aux=(x>=0 && x < obtenerNumeroFilas() 
				&& y>=0 && y < obtenerNumeroColumnas());
		return aux;
	}
	/**
	 * Comprueba si una celda esta en el tablero.
	 * @param celda celda
	 * @return true si esta en el tablero, false si no
	 */
	public boolean estaEnTablero(Celda celda){
		return celdas.get(celda.obtenerFila()).contains(celda);
	}
	/**
	 * Consulta el número de filas.
	 * @return filas
	 */
	public int obtenerNumeroFilas(){
		return celdas.size();
	}
	/**
	 * Consulta el número de columnas.
	 * @return columnas
	 */
	public int obtenerNumeroColumnas(){
		return celdas.get(0).size();
	}
	/**
	 * Devuelve la celda en x fila e y columna del tablero.
	 * @param x fila
	 * @param y columna
	 * @return celda o null
	 */
	public Celda obtenerCelda(int x, int y){
		if (estaEnTablero(x,y)){
			return celdas.get(x).get(y);
		}else{
			return null;
		}
	}
	/**
	 * ToString.
	 */
	@Override
	public String toString(){
		String s="";
		for(int i=0;i<obtenerNumeroFilas();++i){
			s+="\t";
			for(int j=0;j<obtenerNumeroColumnas();++j){
				if (obtenerCelda(i, j).estaVacia()){
					s+="- ";
				}else{
					s+=obtenerCelda(i, j).devolverNum()+" ";
				}
			}
			s+="\n";
			
		}
		s+="\n";
		return s;
	}
}
