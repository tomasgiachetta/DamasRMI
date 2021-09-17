package Modelo;

import java.io.Serializable;

public class FichaNegra extends Ficha implements Serializable{

	public FichaNegra(int x, int y) {
		super(x, y);
		this.color = 'N';
		tipo = negras;
	}



	@Override
	public void verificarReina() {
		if (x == 1) {
			reina = true;
			tipo = reinaNegra;
		}
		
	}
	
	

}
