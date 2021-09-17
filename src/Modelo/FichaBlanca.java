package Modelo;

import java.io.Serializable;

public class FichaBlanca extends Ficha implements Serializable{

	public FichaBlanca(int x, int y) {
		super(x, y);
		this.color = 'B';
		tipo = blancas;
	}

	
	
	@Override
	public void verificarReina() {
		if (x == 8) {
			reina = true;
			tipo = reinaBlanca;
		}
		
	}
	

}
