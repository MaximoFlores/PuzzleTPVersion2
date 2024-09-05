package juego;

import java.util.Collections;
import java.util.List;
import java.util.Random;
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
	private static ArrayList<Integer> orden;

	public Juego() {
		tablero = new int[FIL][COL];
		ArrayList<Integer> numeros = new ArrayList<>();
		orden = new ArrayList<>();


		// Agregar números del 1 al 15 a la lista
		for (int i = 1; i <= FIL * COL - 1; i++) {
			numeros.add(i);
		}

//        Collections.shuffle(numeros);

		// Rellenar el tablero con los números mezclados, dejando la última casilla
		// vacía
		int index = 0;
		for (int f = 0; f < FIL; f++) {
			for (int c = 0; c < COL; c++) {
				if (f == FIL - 1 && c == COL - 1) {
					tablero[f][c] = 0; // Última casilla vacía
//                    celdaVacia= new Point(f,c);
					filaVacia = f;
					colVacia = c;
				} else {
					tablero[f][c] = numeros.get(index++);
				}
			}
		}

		// Mezclar los números para generar un tablero desordenado
		mezclar();

		movimientos = 0;
	}

	public void mezclar() {
		for (int i = 0; i < 100; i++) {
			Random random = new Random();

			int numAzar = random.nextInt(4) + 1;
			
			System.out.println(numAzar);
			switch (numAzar) {
			case 1 -> moverArriba();
			case 2 -> moverAbajo();
			case 3 -> moverIzquierda();
			case 4 -> moverDerecha();
			}

		}
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
		
		if(orden.size()!=0) {
			if(orden.get(orden.size()-1)!=2){
				orden.add(1);
			}
		}else {
		orden.add(1);
		}
		}
	}

	public void moverAbajo() {
		if (filaVacia > 0) {
			tablero[filaVacia][colVacia] = tablero[filaVacia - 1][colVacia];
			tablero[filaVacia - 1][colVacia] = 0;
			filaVacia--;
			movimientos++;
		
		if(orden.size()!=0) {
			if(orden.get(orden.size()-1)!=1){
				orden.add(2);
			}
			}
		else {
			orden.add(2);
		}
		
		}
	}

	public void moverDerecha() {
		if (colVacia > 0) {
			tablero[filaVacia][colVacia] = tablero[filaVacia][colVacia - 1];
			tablero[filaVacia][colVacia - 1] = 0;
			colVacia--;
			movimientos++;
		
		if(orden.size()!=0) {
			if(orden.get(orden.size()-1)!=3){
				orden.add(4);
			}
		}else {
			orden.add(4);
		}
		}
		
	
	}

	public void moverIzquierda() {
		if (colVacia < COL - 1) {
			tablero[filaVacia][colVacia] = tablero[filaVacia][colVacia + 1];
			tablero[filaVacia][colVacia + 1] = 0;
			colVacia++;
			movimientos++;
		
		if(orden.size()!=0) {
			if(orden.get(orden.size()-1)!=4){
				orden.add(3);
			}	
		}
		else {
			orden.add(3);
		}
		}
		
	}

	private int tableroTamanio() {
		return tablero.length;
	}

	public int getValor(int fila, int columna) {
		return tablero[fila][columna];
	}
	
	public void getAyuda() {
		if(orden.size()>=1) {
		switch (orden.get(orden.size()-1)) {
		case 1 -> moverAbajo(); 
		case 2 -> moverArriba();
		case 3 -> moverDerecha();
		case 4 -> moverIzquierda() ;
		}
		System.out.println(orden);
		System.out.println(orden.get(orden.size()-1));
		orden.remove(orden.size()-1);
		orden.remove(orden.size()-1);
		

	}
	}
}