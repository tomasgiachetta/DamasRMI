package Modelo;

public class Reglas {

	private final int blancas = 1, negras = 2, reinaBlanca = 3, reinaNegra = 4, PosibleMov = 5;
	private Tablero tableroFichas;

	public Reglas(Tablero tablero) {
		this.tableroFichas = tablero;
	}


	public boolean posibilidadDeComer() {
		boolean comer = false;
		int[][] tablero = tableroFichas.getTablero();
		int propia, reina, contraria, reinaContraria;
		if (tableroFichas.getTurno() == 'B') {
			contraria = negras;
			reinaContraria = reinaNegra;
			propia = blancas;
			reina = reinaBlanca;
		} else {
			reinaContraria = reinaBlanca;
			contraria = blancas;
			propia = negras;
			reina = reinaNegra;
		}
		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {
				if (tablero[i][j] == propia) {
					if (propia == negras) {
						if ((i - 2) >= 1 && (j + 2) <= 8) {
							if (tablero[i - 1][j + 1] == reinaContraria || tablero[i - 1][j + 1] == contraria) {
								if (tablero[i - 2][j + 2] == PosibleMov)
									comer = true;
							}
						}
						if ((i - 2) >= 1 && (j - 2) >= 1) {
							if (tablero[i - 1][j - 1] == reinaContraria || tablero[i - 1][j - 1] == contraria) {
								if (tablero[i - 2][j - 2] == PosibleMov)
									comer = true;
							}
						}
					} else if (propia == blancas) {
						if ((i + 2) <= 8 && (j + 2) <= 8) {
							if (tablero[i + 1][j + 1] == reinaContraria || tablero[i + 1][j + 1] == contraria) {
								if (tablero[i + 2][j + 2] == PosibleMov)
									comer = true;
							}
						}
						if ((i + 2) <= 8 && (j - 2) >= 1) {
							if (tablero[i + 1][j - 1] == reinaContraria || tablero[i + 1][j - 1] == contraria) {
								if (tablero[i + 2][j - 2] == PosibleMov)
									comer = true;
							}
						}
					}
				} else if (tablero[i][j] == reina) {
					if ((i - 2) >= 1 && (j + 2) <= 8) {
						if (tablero[i - 1][j + 1] == reinaContraria || tablero[i - 1][j + 1] == contraria) {
							if (tablero[i - 2][j + 2] == PosibleMov)
								comer = true;
						}
					} else if ((i - 2) >= 1 && (j - 2) >= 1) {
						if (tablero[i - 1][j - 1] == reinaContraria || tablero[i - 1][j - 1] == contraria) {
							if (tablero[i - 2][j - 2] == PosibleMov)
								comer = true;
						}
					} else if ((i + 2) <= 8 && (j + 2) <= 8) {
						if (tablero[i + 1][j + 1] == reinaContraria || tablero[i + 1][j + 1] == contraria) {
							if (tablero[i + 2][j + 2] == PosibleMov)
								comer = true;
						}
					}
					if ((i + 2) <= 8 && (j - 2) >= 1) {
						if (tablero[i + 1][j - 1] == reinaContraria || tablero[i + 1][j - 1] == contraria) {
							if (tablero[i + 2][j - 2] == PosibleMov)
								comer = true;
						}
					}
				}
			}

		}
		return comer;
	}

	public boolean posibilidadDeMovimiento() {
		boolean movimiento = false;
		int propia, reina;
		int[][] tablero = tableroFichas.getTablero();
		if (tableroFichas.getTurno() == 'B') {
			propia = blancas;
			reina = reinaBlanca;
		} else {
			propia = negras;
			reina = reinaNegra;
		}

		if (posibilidadDeComer()) {
			movimiento = true;
		} else {
			for (int i = 1; i < 9; i++) {
				for (int j = 1; j < 9; j++) {
					if (tablero[i][j] == propia) {
						if (propia == negras) {
							if ((i - 1) >= 1 && (j + 1) <= 8) {
								if (tablero[i - 1][j + 1] == PosibleMov)
									movimiento = true;
							}
							if ((i - 1) >= 1 && (j - 1) >= 1) {
								if (tablero[i - 1][j - 1] == PosibleMov)
									movimiento = true;
							}
						}
						if (propia == blancas) {
							if ((i + 1) <= 8 && (j + 1) <= 8) {
								if (tablero[i + 1][j + 1] == PosibleMov)
									movimiento = true;
							}
							if ((i + 1) <= 8 && (j - 1) >= 1) {
								if (tablero[i + 1][j - 1] == PosibleMov)
									movimiento = true;
							}
						}

					} else if (tablero[i][j] == reina) {
						if ((i - 1) >= 1 && (j + 1) <= 8) {
							if (tablero[i - 1][j + 1] == PosibleMov)
								movimiento = true;
						}
						if ((i - 1) >= 1 && (j - 1) >= 1) {
							if (tablero[i - 1][j - 1] == PosibleMov)
								movimiento = true;
						}
						if ((i + 1) <= 8 && (j + 1) <= 8) {
							if (tablero[i + 1][j + 1] == PosibleMov)
								movimiento = true;
						}
						if ((i + 1) <= 8 && (j - 1) >= 1) {
							if (tablero[i + 1][j - 1] == PosibleMov)
								movimiento = true;
						}
					}
				}
			}
		}

		return movimiento;
	}
}
