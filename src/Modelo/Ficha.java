package Modelo;

import java.io.Serializable;

public abstract class Ficha implements Serializable{
	protected int x, y;
	protected char color;
	protected int tipo;
	protected final int blancas = 1, negras = 2, reinaBlanca = 3, reinaNegra = 4, PosibleMov = 5;
	protected boolean reina;
	
	public Ficha(int x, int y) {
		this.x = x;
		this.y = y;
		reina = false;
	}
	
	//public int getColor() {
	//	return color;
	//}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public int getTipo() {
		return tipo;
	}
	
	public void mover(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void verificarReina();
	
}
