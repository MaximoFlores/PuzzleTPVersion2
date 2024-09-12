package juego;

import java.awt.Point;

public enum Move {
    LEFT(new Point(0,1),2),
    RIGHT(new Point(0,-1),1),
    UP(new Point(1,0),4),
    DOWN(new Point(-1,0),3);
    
    private final Point punto;
    private final int numOrder;
    
    private Move(Point punto, int numOrder ){
        this.punto = punto;
        this.numOrder = numOrder;
    }
    public Point getDir() {
	return punto;
    }
    public int getNumOrder(){
        return this.numOrder;
    }
}
	


