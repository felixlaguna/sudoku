package sudoku.modelo;

import java.util.ArrayList;
import java.util.List;

public class Recuadro {
	private List<Celda> celdas;
	private int tamano;
	public Recuadro(int N){
		celdas=new ArrayList<Celda>(N);
		tamano=N;
	}
	public List<Celda> obtenerCeldas(){
		return celdas;
	}
	public void addCell(Celda c){
		celdas.add(c);
	}
	public String toString(){
		String s=new String();
		int num=(int) Math.sqrt(tamano);
		for (int i=0;i<tamano;++i){
			if (i%num==0){
				System.out.println("");
			}
			System.out.println(celdas.get(i).toString());
		}
		return s;
	}
}
