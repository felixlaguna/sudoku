package sudoku.modelo;

import java.util.ArrayList;
import java.util.List;

public class Tablero {

	/**
	 * Lista de lista de celdas (matriz).
	 */
	private List<List<Celda>> celdas;
	/**
	 * Lista de recuadros.
	 */
	private List<Almacen> recuadros;
	/**
	 * Lista de filas.
	 */
	private List<Almacen> filass;
	/**
	 * Lista de columnas.
	 */
	private List<Almacen> columnass;
	/**
	 * Constructor del tablero.
	 * @param filas filas
	 * @param columnas columnas
	 */
	public Tablero(int filas,int columnas){
		celdas=new ArrayList<List<Celda>>(filas);
		recuadros=new ArrayList<Almacen>(filas);
		filass=new ArrayList<Almacen>(filas);
		columnass=new ArrayList<Almacen>(columnas);
		for (int i=0;i<9;++i){
			recuadros.add(new Almacen(9));
			filass.add(new Almacen(9));
			columnass.add(new Almacen(9));
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
				filass.get(i).addCell(c);
				columnass.get(j).addCell(c);
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
	public Almacen obtenerRecuadro(int x){
		return recuadros.get(x);
	}
	public Almacen obtenerRecuadro(Celda c){
		Almacen almacen=null;
		for (Almacen a: recuadros){
			if (a.contains(c)){
				almacen=a;
			}
		}
		return almacen;
		
	}
	public Almacen obtenerFila(int x){
		return filass.get(x);
	}
	public Almacen obtenerFila(Celda c){
		Almacen almacen=null;
		for (Almacen a: filass){
			if (a.contains(c)){
				almacen=a;
			}
		}
		return almacen;
		
	}
	public Almacen obtenerColumna(int y){
		return columnass.get(y);
		
	}
	public Almacen obtenerColumna(Celda c){
		Almacen almacen=null;
		for (Almacen a: columnass){
			if (a.contains(c)){
				almacen=a;
			}
		}
		return almacen;
	}
	private List<Almacen> obtenerrecuadros(){
		return this.recuadros;
	}
	private List<Almacen> obtenerfilas(){
		return this.filass;
	}
	private List<Almacen> obtenercolumnas(){
		return this.columnass;
	}
	private void colocarrecuadros(List<Almacen> a){
		this.recuadros=a;
	}
	private void colocarfilas(List<Almacen> a){
		this.filass=a;
	}
	private void colocarcolumnas(List<Almacen> a){
		this.columnass=a;
	}
	private void colocarceldas(List<List<Celda>> celdas){
		this.celdas=celdas;
	}
	public Tablero clone(){
		Tablero resultado=new Tablero(this.obtenerNumeroFilas(),this.obtenerNumeroColumnas());
		List<List<Celda>> a=new ArrayList<List<Celda>>();
		List<Almacen> r=new ArrayList<Almacen>();
		List<Almacen> f=new ArrayList<Almacen>();
		List<Almacen> col=new ArrayList<Almacen>();
		for (int i=0;i<9;++i){
			r.add(new Almacen(9));
			f.add(new Almacen(9));
			col.add(new Almacen(9));
		}
		int aux = 0;
		int aux2=0;
		for (int i=0;i<celdas.size();++i){
			a.add(new ArrayList<Celda>());
			for (int j=0;j<celdas.get(0).size();++j){
				a.get(i).add(obtenerCelda(i,j).clone());
				aux=i/3;
				aux2=j/3;
				int index=aux*3+aux2;
				Celda c=a.get(i).get(j);
				r.get(index).addCell(c);
				f.get(i).addCell(c);
				col.get(j).addCell(c);
			}
		}
		resultado.colocarceldas(a);
		resultado.colocarcolumnas(col);
		resultado.colocarfilas(f);
		resultado.colocarrecuadros(r);
		return resultado;
	}
	public List<Celda> toList(){
		List<Celda> lista= new ArrayList<Celda>();
		for (int i=0;i<this.obtenerNumeroFilas();++i){
			for (int j=0;j<this.obtenerNumeroColumnas();++j){
				lista.add(obtenerCelda(i,j));
			}
		}
		return lista;
	}
	public void changeCell(List<Celda> lista){
		for (int i=0;i<this.obtenerNumeroFilas();++i){
			for (int j=0;j<this.obtenerNumeroColumnas();++j){
				if (!lista.contains(obtenerCelda(i,j))){
					obtenerCelda(i,j).vaciar();
				}
				
			}
		}
		
	}
	
	
}
