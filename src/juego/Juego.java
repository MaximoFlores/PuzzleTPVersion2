package juego;

import java.util.Random;
import java.awt.Point;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Juego {
	
	public static final int FIL = 4;
	public static final int COL = 4;
	protected final int[][] tablero;
	private int filaVacia;
	private int colVacia;
	private int movimientos;
	private LinkedList<Integer> ordenInv;
	private HashMap<Integer,Move> numOrden;
	protected LocalDateTime start;
	protected LocalDateTime end;

	
	public Juego() {
		tablero = new int[FIL][COL];
		ordenInv = new LinkedList<>();
		declarateNumOrden();
		
		// Rellenar el tablero con los números mezclados, dejando la última casilla
		// vacía
		int index = 1;
		for (int f = 0; f < FIL; f++) {
			for (int c = 0; c < COL; c++) {
				if (f == FIL - 1 && c == COL - 1) {
					tablero[f][c] = 0; // Última casilla vacía
					filaVacia = f;
					colVacia = c;
				} else {
					tablero[f][c] = index++;
				}
			}
		}

		mezclar();
		System.out.println(ordenInv.toString());
		limpiarOrden();
		System.out.println();
		System.out.println(ordenInv.toString());
		movimientos = 0;
	}


	public void mezclar() {
		Random random = new Random();		
		for (int i = 0; i < 30; i++) {			
			int numAzar = random.nextInt(4) + 1;
			
			while(!esPosible(numOrden.get(numAzar))) {
				numAzar = random.nextInt(4) + 1;				
			}
			moverCelda(numOrden.get(numAzar), false);
		}
	}
	private boolean esPosible(Move dir) {
		int ret1 = filaVacia+dir.getDir().x;
		int ret2 = colVacia+dir.getDir().y;
		return ret1>=0 && ret1<FIL && ret2>=0 && ret2<COL;
	}
	private void limpiarOrden() {
		
		while(true) {
			ArrayList<Integer> posEliminar = new ArrayList<Integer>();
			for (int i = 0; i < ordenInv.size() - 1; i++) {
				int actual = ordenInv.get(i);
				int siguiente = ordenInv.get(i + 1);

				if ((actual == 1 && siguiente == 2) || (actual == 2 && siguiente == 1) ||
						(actual == 3 && siguiente == 4) || (actual == 4 && siguiente == 3)) {
					posEliminar.add(i);
					posEliminar.add(i + 1);
					i++; 	           
				}

			}
			if(posEliminar.size() == 0) {
				break;
			}
			LinkedList<Integer> nuevoOrden = new LinkedList<Integer>();
			for (int i = 0; i < ordenInv.size(); i++) {
				if(!posEliminar.contains(i)) {
					nuevoOrden.add(ordenInv.get(i));
				}
			}
			ordenInv = nuevoOrden;
		}
		System.out.println(ordenInv.toString());
	}
		
    private void declarateNumOrden() {
        
        numOrden = new HashMap<>();
        numOrden.put(1,Move.LEFT);
        numOrden.put(2,Move.RIGHT);
        numOrden.put(3,Move.UP);
        numOrden.put(4,Move.DOWN);
        
    }

	public boolean partidaGanada() {
		int valor = 1;
		for (int f = 0; f < FIL; f++) {
			for (int c = 0; c < COL; c++) {
				if (f == FIL - 1 && c == COL - 1) {
					return tablero[f][c] == 0; // La última casilla debe estar vacía
				} else if (tablero[f][c] != valor++) {
					return false;
				}
			}
		}
		return true;
	}
	
	public int cantidadDeMovRealizados() {
		return movimientos;
	}
	
	
	public String nextVerification(int i, int j) {
        if (i < FIL - 1) {
            int valor1 = getValor(i + 1, j);
            if (valor1 == 0) {
                return "DOWN";
            }
        }
        if (i > 0) {
            int valor2 = getValor(i - 1, j);
            if (valor2 == 0) {
                return "UP";
            }
        }
        if (j < COL - 1) {
            int valor3 = getValor(i, j + 1);
            if (valor3 == 0) {
                return "RIGHT";
            }
        }
        if (j > 0) {
            int valor4 = getValor(i, j - 1);
            if (valor4 == 0) {
                return "LEFT";
            }
        }

        return "";
    }
	
    public void moverCelda(Move dir, boolean ayuda){       
        Point nuevaPos = new Point(filaVacia+dir.getDir().x,colVacia+dir.getDir().y);
        
        if(nuevaPos.x>=0 && nuevaPos.x<tablero.length &&
                nuevaPos.y>=0 && nuevaPos.y<tablero[0].length){       
      
        	intercambiarCeldas(filaVacia, colVacia, nuevaPos.x, nuevaPos.y);        
            filaVacia = nuevaPos.x;
            colVacia = nuevaPos.y;
            movimientos++;
            if(!ayuda){
                ordenInv.addLast(dir.getNumOrder());
            }
            else{
                ordenInv.removeLast();
            }
        }
    }


	private void intercambiarCeldas(int fila1, int col1, int fila2, int col2) {
    	int aux = tablero[fila1][col1];
    	tablero[fila1][col1] = tablero[fila2][col2]; 
    	tablero[fila2][col2] = aux;
    }

	public int getValor(int fila, int columna) {
		return tablero[fila][columna];
	}
	
	
	public void getAyuda() {
		limpiarOrden();
		if(ordenInv.size()>=1) {
			moverCelda(numOrden.get(ordenInv.getLast()), true);				
		}
		
	}
		
}