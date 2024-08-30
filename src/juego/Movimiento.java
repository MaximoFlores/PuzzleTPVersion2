package juego;

import java.awt.Point;

public enum Movimiento {
	IZQUIERDA(new Point(0, -1)),
	DERECHA(new Point(0,1)),
	ARRIBA(new Point(-1,0)),
	ABAJO(new Point(1,0));
	
	private Point valor;

	private Movimiento(Point valor) {
		this.valor = valor;
	    
	}
	public Point getDireccion() {
		return valor;
	}
	


	
	
	
}
