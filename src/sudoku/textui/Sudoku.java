package sudoku.textui;

import sudoku.control.Arbitro;
import sudoku.modelo.Tablero;

public class Sudoku {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Arbitro arbitro=new Arbitro(9,9);
		System.out.println(arbitro.obtenerTablero().toString());
		arbitro.rellenar();
		System.out.println(arbitro.obtenerTablero().toString());
	}

}
