package sudoku.textui;

import sudoku.control.Arbitro;

public class Sudoku {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Arbitro arbitro=new Arbitro(9,9);
		System.out.println(arbitro.obtenerTablero().toString());
		arbitro.rellenar();
		System.out.println(arbitro.obtenerTablero().toString());
		arbitro.terminar(0);
		System.out.println(arbitro.obtenerTablero().toString());
	}

}
