package sudoku.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Almacen {
	private List<Celda> celdas;
	public Almacen(int N){
		celdas=new ArrayList<Celda>(9);
	}
	public List<Celda> obtenerCeldas(){
		return celdas;
	}
	public void addCell(Celda c){
		celdas.add(c);
	}
	public boolean esCorrecto(){
		List<Integer> lista=new ArrayList<Integer>();
		for (Celda c:celdas){
			lista.add(c.devolverNum());
		}
		return (lista.size()==new HashSet<>(lista).size());
	}
	public List<Celda> celdasVacias(){
		List<Celda> resultado=new ArrayList<Celda>();
		for (Celda c: celdas){
			if (c.estaVacia()){
				resultado.add(c);
			}
		}
		return resultado;
	}
	public List<Integer> numeroValidos(){
		List<Integer> resultado=new ArrayList<Integer>();
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
	public boolean contains(Object o){
		return celdas.contains(o);
	}
}
