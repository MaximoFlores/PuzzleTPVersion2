package juego;

import java.util.Collections;
import java.awt.Point;
import java.util.ArrayList;

public class Juego {
	public static final int FIL = 4;
	public static final int COL = 4;
	protected final int[][] tablero;
	protected Point celdaVacia;
	private int filaVacia;
	private int colVacia;
	private int movimientos;

	public Juego() {
        tablero = new int[FIL][COL];
        ArrayList<Integer> numeros = new ArrayList<>();

        // Agregar números del 1 al 15 a la lista
        for (int i = 1; i <= FIL * COL - 1; i++) {
            numeros.add(i);
        }

        // Mezclar los números para generar un tablero desordenado
        Collections.shuffle(numeros);

        // Rellenar el tablero con los números mezclados, dejando la última casilla
        // vacía
        int index = 0;
        for (int f = 0; f < FIL; f++) {
            for (int c = 0; c < COL; c++) {
                if (f == FIL - 1 && c == COL - 1) {
                    tablero[f][c] = 0; // Última casilla vacía
//                    celdaVacia= new Point(f,c);
                    filaVacia = f;
                    colVacia =c;
                } else {
                    tablero[f][c] = numeros.get(index++);
                }
            }
        }
        movimientos = 0;
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

//	public void moverCelda(Movimiento direccion) {
//		int nuevaPosCol = celdaVacia.y + direccion.getDireccion().y;
//		int nuevaPosFil = celdaVacia.x + direccion.getDireccion().x;
//		if(nuevaPosCol >= 0 && nuevaPosCol < tableroTamanio() && nuevaPosFil >= 0 && nuevaPosFil < tableroTamanio()) {
//		
//			int aux = tablero[nuevaPosFil][nuevaPosCol];
//			tablero[nuevaPosFil][nuevaPosCol] = tablero[celdaVacia.x][celdaVacia.y];
//			tablero[celdaVacia.x][celdaVacia.y] = aux;
//			celdaVacia = new Point(nuevaPosFil,nuevaPosCol);
//			movimientos++;
//		}
//	}
	public void moverArriba() {
        if (filaVacia < FIL - 1) {
            tablero[filaVacia][colVacia] = tablero[filaVacia + 1][colVacia];
            tablero[filaVacia + 1][colVacia] = 0;
            filaVacia++;
            movimientos++;
        }
    }

    public void moverAbajo() {
        if (filaVacia > 0) {
            tablero[filaVacia][colVacia] = tablero[filaVacia - 1][colVacia];
            tablero[filaVacia - 1][colVacia] = 0;
            filaVacia--;
            movimientos++;
        }
    }

    public void moverDerecha() {
        if (colVacia > 0) {
            tablero[filaVacia][colVacia] = tablero[filaVacia][colVacia - 1];
            tablero[filaVacia][colVacia - 1] = 0;
            colVacia--;
            movimientos++;
        }
    }

    public void moverIzquierda() {
        if (colVacia < COL - 1) {
            tablero[filaVacia][colVacia] = tablero[filaVacia][colVacia + 1];
            tablero[filaVacia][colVacia + 1] = 0;
            colVacia++;
            movimientos++;
        }
    }
	
	private int tableroTamanio() {
		return tablero.length;
	}
	
	public int getValor(int fila, int columna) {
		return tablero[fila][columna];
	}
	
	
	
	
	
	
	
}