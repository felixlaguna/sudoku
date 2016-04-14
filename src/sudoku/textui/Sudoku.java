package sudoku.textui;

import sudoku.control.Arbitro;
/**
 * Modo texto del sudoku, solo generación.
 * @author Félix Laguna Teno
 *
 */
public class Sudoku {
	/**
	 * Main.
	 * @param args String args[]
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Arbitro arbitro=new Arbitro(9,9);
		System.out.println(arbitro.obtenerTablero().toString());
		arbitro.solverRapido();
		//arbitro.rellenar();
		System.out.println(arbitro.obtenerTablero().toString());
		arbitro.terminar(3);
		System.out.println(arbitro.obtenerTablero().toString());
	}

}
