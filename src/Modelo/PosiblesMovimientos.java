package Modelo;

import java.io.Serializable;

public class PosiblesMovimientos implements Serializable{
	int x, y;
	
	public PosiblesMovimientos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void mover(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
