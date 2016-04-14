package sudoku.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sudoku.modelo.*;
import sudoku.control.*;

/**
 * Clase de interfaz gráfixa de la sopa de letras.
 * @author FELIX
 *
 */
public class SudokuGUI {
	/**
	 * Tamaño del tablero cuadrado.
	 */
	private static int N=9;
	/**
	 * Lista de los botones
	 */
	private final List<Cell> list = new ArrayList<Cell>();
	private static List<Celda> originales=new ArrayList<Celda>();
	/**
	 * Arbitro.
	 */
	private static Arbitro arbitro;
	private static JLabel labelPal;
	/**
	 * Lineas a introducir en la sopa de letras.
	 */
	
	/**
	 * Devuelve el boton asociado a unas coordenadas.
	 * @param r fila
	 * @param c columna
	 * @return botón de tipo Cell
	 */
	private Cell getGridButton(int r, int c) {
		int index = r * N + c;
		return list.get(index);
	}
	/**
	 * Crea cada botón.
	 * @param row fila
	 * @param col columna
	 * @return Botón creado de tipo Cell
	 */
	private Cell createGridButton(final int row, final int col) {
		final Cell b = new Cell(Color.WHITE,arbitro.obtenerTablero().obtenerCelda(row, col).obtenerString(),row,col);
		b.addActionListener(new ActionListener() {
			/**
			 * Escucha de acciones.
			 * @param e AccionEvent
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		b.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            	Celda c=arbitro.obtenerTablero().obtenerCelda(row, col);
            	 if (!originales.contains(c)){
            		 
            		 if (e.getButton() == MouseEvent.BUTTON3) {
            			 c.decrementar();
            			 
                     }else if (e.getButton() == MouseEvent.BUTTON2){
                    	 c.vaciar();
                     }else{
                    	 c.incrementar();
                    	
                     }
            		 b.setText(c.obtenerString());
				 }
              
            }
         });
		return b;
	}
	/**
	 * Crea el jpanel con el layout de botones.
	 * @return Jpanel creado.
	 */
	private JPanel createGridPanel() {
		JPanel p = new JPanel(new GridLayout(N, N));
		for (int i = 0; i < N * N; i++) {
			int row = i / N;
			int col = i % N;
			Cell gb = createGridButton(row, col);
			list.add(gb);
			p.add(gb);
		}
		return p;
	}
	/**
	 * Muestra la aplicación final.
	 */
	private void display() {
		JFrame f = new JFrame("Sudoku");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(createGridPanel());
		JPanel panel = new JPanel();
		labelPal=new JLabel("-");
		labelPal.setFont(new Font("Arial",Font.PLAIN,20));
		
		panel.add(labelPal);
		f.getContentPane().add(panel, BorderLayout.SOUTH);
		JPanel panelAr=new JPanel(new GridLayout(0,3));
		JButton sol=new JButton("Solución");
		sol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				arbitro.obtenerTablero().changeCell(originales);
				
				arbitro.solverRapido();
				clear();
				
			}
		});
		panelAr.add(sol);
		JButton reset=new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				f.dispose();
				inicializar();
				
			}
		});
		panelAr.add(reset);
		JButton check=new JButton("Comprobar");
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arbitro.correcto()){
					labelPal.setText("Correcto");
				}else{
					labelPal.setText("Incorrecto");
				}
			}
		});
		panelAr.add(check);
		f.getContentPane().add(panelAr, BorderLayout.NORTH);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		clear();
	}
	/**
	 * Main.
	 * @param args argumentos como array de strings.
	 */
	public static void main(String[] args) {
		inicializar();
	}
	/**
	 * Clase interna de cell, un tipo de botón jButton.
	 * @author FELIX
	 *
	 */
	public class Cell extends JButton {
		/**
		 * Necesaria.
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * Fila.
		 */
		private int row;
		/**
		 * Columna.
		 */
		private int col;
		/**
		 * Constructor de cada cell.
		 * @param background color de fondo
		 * @param s cadena de texto en el botón
		 * @param row fila
		 * @param col columna
		 */
		public Cell(Color background,String s,int row, int col) {
			super(s);
			this.row=row;
			this.col=col;
			setContentAreaFilled(false);
			setBorderPainted(true);
			setBackground(background);
			setOpaque(true);
			setFont(new Font("Arial",Font.PLAIN,20));

		}
		/**
		 * Devuelve el tamaño preferido.
		 */
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(70, 70);
		}
		/**
		 * Cambia de color el botón.
		 * @param color color a cambiar
		 */
		public void toColor(Color color){
			setBackground(color);
		}
		/**
		 * Devuelve la fila.
		 * @return fila
		 */
		public int getRow(){
			return row;
		}
		/**
		 * Devuelve la columna.
		 * @return columna
		 */
		public int getCol(){
			return col;
		}
	}
	/**
	 * Añade palabras al array lines.
	 */
	/**
	 * Aplica el color blanco a las celdas que no tengan palabras ganadoras o que no se estén usando.
	 */
	private void clear(){
		for (int i=0;i<N;++i){
			for (int j=0;j<N;++j){
				Cell aux = this.getGridButton(i, j);
				aux.setText(arbitro.obtenerTablero().obtenerCelda(i, j).obtenerString());
				if (originales.contains(arbitro.obtenerTablero().obtenerCelda(i,j))){
					aux.toColor(Color.LIGHT_GRAY);
					
				}
				int arriba=1;
				int abajo=1;
				int izquierda=1;
				int derecha=1;
				if (j==0){
					izquierda=5;
				}
				if ((j+1)%3==0){
					derecha=5;
				}
				if ((i)%3==0){
					arriba=5;
				}
				if (i==8){
					abajo=9;
				}
				aux.setBorder(BorderFactory.createMatteBorder(arriba, izquierda, abajo, derecha, Color.BLACK));
			}
		}
	}
	/**
	 * Inicializa los valores.
	 */
	private static void inicializar(){
		arbitro=new Arbitro(N,N);
		arbitro.solverRapido();
		arbitro.terminar(2);
		originales=new ArrayList<Celda>();
		originales.addAll(arbitro.obtenerTablero().toList());
		originales.removeAll(arbitro.celdasVacias(arbitro.obtenerTablero()));
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new SudokuGUI().display();
			}
		});
	}
}
