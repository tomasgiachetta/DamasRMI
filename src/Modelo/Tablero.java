package Modelo;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Tablero implements Serializable{
	private int[][] tablero;
	private char turno;
	private final int blancas = 1, negras = 2, reinaBlanca = 3, reinaNegra = 4, PosibleMov = 5;
	private ArrayList<Ficha> fichas = new ArrayList<>();
	private ArrayList<PosiblesMovimientos> movsPosibles = new ArrayList<>();
	private Reglas reglas;
	
	
	public Tablero() {
		tablero = new int[9][9];
		turno = 'N';
		reglas = new Reglas(this);
	}
	
	public char getTurno() {
		return turno;
	}
	
	public void setTablero(int[][] tablero) {
		this.tablero = tablero;
	}
	
	public int[][] getTablero() {
		return tablero;
	}
	
	public String turnoDe() {
		if (this.turno == 'N')
			return "Negras";
		else
			return "Blancas";
	}
	
	// CAMBIAR TURNO EN MOVIMIENTOS NORMALES
	public void cambiarTurno() {
		if (this.turno == 'N')
			this.turno = 'B';
		else
			this.turno = 'N';
	}
	
	// CAMBIAR TURNO LUEGO DE COMER UNA FICHA
		public void cambiarTurno(int x, int y) {
			int propia, reina, contraria, reinaContraria;
			boolean cambiar = true;
			if (tablero[x][y] == reinaBlanca || tablero[x][y] == blancas) {
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

			if (tablero[x][y] == reina) {
				if ((x - 2) >= 1 && (y + 2) <= 8) {
					if (tablero[x - 1][y + 1] == reinaContraria || tablero[x - 1][y + 1] == contraria) {
						if (tablero[x - 2][y + 2] == PosibleMov)
							cambiar = false;
					}
				}
				if ((x + 2) <= 8 && (y + 2) <= 8) {
					if (tablero[x + 1][y + 1] == reinaContraria || tablero[x + 1][y + 1] == contraria) {
						if (tablero[x + 2][y + 2] == PosibleMov)
							cambiar = false;
					}
				}
				if ((x + 2) <= 8 && (y - 2) >= 1) {
					if (tablero[x + 1][y - 1] == reinaContraria || tablero[x + 1][y - 1] == contraria) {
						if (tablero[x + 2][y - 2] == PosibleMov)
							cambiar = false;
					}
				}
				if ((x - 2) >= 1 && (y - 2) >= 1) {
					if (tablero[x - 1][y - 1] == reinaContraria || tablero[x - 1][y - 1] == contraria) {
						if (tablero[x - 2][y - 2] == PosibleMov)
							cambiar = false;
					}
				}
			} else if (propia == negras) {
				if ((x - 2) >= 1 && (y + 2) <= 8) {
					if (tablero[x - 1][y + 1] == reinaContraria || tablero[x - 1][y + 1] == contraria) {
						if (tablero[x - 2][y + 2] == PosibleMov)
							cambiar = false;
					}
				}
				if ((x - 2) >= 1 && (y - 2) >= 1) {
					if (tablero[x - 1][y - 1] == reinaContraria || tablero[x - 1][y - 1] == contraria) {
						if (tablero[x - 2][y - 2] == PosibleMov)
							cambiar = false;
					}
				}
			} else {
				if ((x + 2) <= 8 && (y + 2) <= 8) {
					if (tablero[x + 1][y + 1] == reinaContraria || tablero[x + 1][y + 1] == contraria) {
						if (tablero[x + 2][y + 2] == PosibleMov)
							cambiar = false;
					}
				}
				if ((x + 2) <= 8 && (y - 2) >= 1) {
					if (tablero[x + 1][y - 1] == reinaContraria || tablero[x + 1][y - 1] == contraria) {
						if (tablero[x + 2][y - 2] == PosibleMov)
							cambiar = false;
					}
				}
			}
			if (cambiar) {
				if (propia == negras)
					this.turno = 'B';
				else
					this.turno = 'N';

			}

		}

	
	public boolean posibilidadDeComer() {
		return reglas.posibilidadDeComer();
	}
	
	public boolean posibilidadMovimiento() {
		return reglas.posibilidadDeMovimiento();
	}

	public boolean verificarFicha(int x, int y) throws RemoteException {
		if (x > 0 && x < 9 && y > 0 && y < 9) {
			int aux;
			int aux1;
			if (turno == 'N') {
				aux = negras;
				aux1 = reinaNegra;
			} else {
				aux = blancas;
				aux1 = reinaBlanca;
			}

			if (tablero[x][y] == aux || tablero[x][y] == aux1) {
				Juego.getInstancia().notificarObservadores(Eventos.FICHA_SELECCIONADA);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean verificarCasilla(int x1, int y1) {
		if (y1 > 0 && x1 > 0 && y1 < 9 && x1 < 9) {
			if (tablero[x1][y1] == PosibleMov)
				return true;
			else
				return false;
		} else
			return false;
	}

	public void crearFichaBlanca(int x, int y) {
		fichas.add(new FichaBlanca(x, y));
		
	}

	public void crearFichaNegra(int x, int y) {
		fichas.add(new FichaNegra(x, y));
		
	}

	public void crearMovPosible(int x, int y) {
		movsPosibles.add(new PosiblesMovimientos(x, y));
		
	}

	public void actualizarTablero() {
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				tablero[i][j] = 0;
		for (PosiblesMovimientos movPosible : movsPosibles) {
			ubicarMovPosible(movPosible);
		}
		for (Ficha ficha : fichas) {
			ubicarFicha(ficha);
		}
	}

	public void ubicarMovPosible(PosiblesMovimientos movPosible) {

		tablero[movPosible.getX()][movPosible.getY()] = PosibleMov;
	}
	
	public void ubicarFicha(Ficha ficha) {
		tablero[ficha.getX()][ficha.getY()] = ficha.getTipo();
	}

	public void ponerTableroVacio() {

		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				tablero[i][j] = 0;
		movsPosibles.clear();
		fichas.clear();
		cambiarTurno();
		
	}

	public void moverFicha(int x, int y, int x1, int y1) throws RemoteException {
		boolean movimientoRealizado = false;
		boolean puedeComer;
		int propia, reina, contraria, reinaContraria;
		if (turno == 'B') {
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

		puedeComer = posibilidadDeComer();

		if (tablero[x][y] == reina) {
			if (x1 == x + 1 && !puedeComer) {
				if (y1 == y - 1 || y1 == y + 1) {
					actualizarMovPosible(x1, y1, x, y);
					actualizarFicha(x, y, x1, y1);
					actualizarTablero();
					cambiarTurno();
					movimientoRealizado = true;
				}
			} else if (x1 == x + 2) {
				if (y1 == y + 2) {
					if (tablero[x + 1][y + 1] == contraria || tablero[x + 1][y + 1] == reinaContraria) {
						actualizarMovPosible(x1, y1, x, y);
						actualizarFicha(x, y, x1, y1);
						eliminarFicha(x + 1, y + 1);
						crearMovPosible(x + 1, y + 1);
						actualizarTablero();
						cambiarTurno(x1, y1);
						movimientoRealizado = true;
					}
				} else if (y1 == y - 2) {
					if (tablero[x + 1][y - 1] == contraria || tablero[x + 1][y - 1] == reinaContraria) {
						actualizarMovPosible(x1, y1, x, y);
						actualizarFicha(x, y, x1, y1);
						eliminarFicha(x + 1, y - 1);
						crearMovPosible(x + 1, y - 1);
						actualizarTablero();
						cambiarTurno(x1, y1);
						movimientoRealizado = true;
					}
				}
			} else if (x1 == x - 1 && !puedeComer) {
				if (y1 == y - 1 || y1 == y + 1) {
					actualizarMovPosible(x1, y1, x, y);
					actualizarFicha(x, y, x1, y1);
					actualizarTablero();
					cambiarTurno();
					movimientoRealizado = true;
				}
			} else if (x1 == x - 2) {
				if (y1 == y + 2) {
					if (tablero[x - 1][y + 1] == contraria || tablero[x - 1][y + 1] == reinaContraria) {
						actualizarMovPosible(x1, y1, x, y);
						actualizarFicha(x, y, x1, y1);
						eliminarFicha(x - 1, y + 1);
						crearMovPosible(x - 1, y + 1);
						actualizarTablero();
						cambiarTurno(x1, y1);
						movimientoRealizado = true;
					}
				} else if (y1 == y - 2) {
					if (tablero[x - 1][y - 1] == contraria || tablero[x - 1][y - 1] == reinaContraria) {
						actualizarMovPosible(x1, y1, x, y);
						actualizarFicha(x, y, x1, y1);
						eliminarFicha(x - 1, y - 1);
						crearMovPosible(x - 1, y - 1);
						actualizarTablero();
						cambiarTurno(x1, y1);
						movimientoRealizado = true;
					}
				}
			}

			if (puedeComer && !movimientoRealizado)
				Juego.getInstancia().notificarObservadores(Eventos.ERROR_MOVIMIENTO_COMER_DISPONIBLE);

		} else if (propia == negras) {
			if (x1 == x - 1 && !puedeComer) {
				if (y1 == y - 1 || y1 == y + 1) {
					actualizarMovPosible(x1, y1, x, y);
					actualizarFicha(x, y, x1, y1);
					actualizarTablero();
					cambiarTurno();
					movimientoRealizado = true;
				}
			} else if (x1 == x - 2) {
				if (y1 == y + 2) {
					if (tablero[x - 1][y + 1] == contraria || tablero[x - 1][y + 1] == reinaContraria) {
						actualizarMovPosible(x1, y1, x, y);
						actualizarFicha(x, y, x1, y1);
						eliminarFicha(x - 1, y + 1);
						crearMovPosible(x - 1, y + 1);
						actualizarTablero();
						cambiarTurno(x1, y1);
						movimientoRealizado = true;
					}
				} else if (y1 == y - 2) {
					if (tablero[x - 1][y - 1] == contraria || tablero[x - 1][y - 1] == reinaContraria) {
						actualizarMovPosible(x1, y1, x, y);
						actualizarFicha(x, y, x1, y1);
						eliminarFicha(x - 1, y - 1);
						crearMovPosible(x - 1, y - 1);
						actualizarTablero();
						cambiarTurno(x1, y1);
						movimientoRealizado = true;
					}
				}
			}

			if (puedeComer && !movimientoRealizado)
				Juego.getInstancia().notificarObservadores(Eventos.ERROR_MOVIMIENTO_COMER_DISPONIBLE);

		} else if (propia == blancas) {
			if (x1 == x + 1 && !puedeComer) {
				if (y1 == y - 1 || y1 == y + 1) {
					actualizarMovPosible(x1, y1, x, y);
					actualizarFicha(x, y, x1, y1);
					actualizarTablero();
					cambiarTurno();
					movimientoRealizado = true;
				}
			} else if (x1 == x + 2) {
				if (y1 == y + 2) {
					if (tablero[x + 1][y + 1] == contraria || tablero[x + 1][y + 1] == reinaContraria) {
						actualizarMovPosible(x1, y1, x, y);
						actualizarFicha(x, y, x1, y1);
						eliminarFicha(x + 1, y + 1);
						crearMovPosible(x + 1, y + 1);
						actualizarTablero();
						cambiarTurno(x1, y1);
						movimientoRealizado = true;
					}
				} else if (y1 == y - 2) {
					if (tablero[x + 1][y - 1] == contraria || tablero[x + 1][y - 1] == reinaContraria) {
						actualizarMovPosible(x1, y1, x, y);
						actualizarFicha(x, y, x1, y1);
						eliminarFicha(x + 1, y - 1);
						crearMovPosible(x + 1, y - 1);
						actualizarTablero();
						cambiarTurno(x1, y1);
						movimientoRealizado = true;
					}
				}
			}

			if (puedeComer && !movimientoRealizado)
				Juego.getInstancia().notificarObservadores(Eventos.ERROR_MOVIMIENTO_COMER_DISPONIBLE);

		}
		if (!puedeComer && !movimientoRealizado)
			Juego.getInstancia().notificarObservadores(Eventos.ERROR_MOVIMIENTO_INVALIDO);

		else {
			Juego.getInstancia().notificarObservadores(Eventos.MOVIMIENTO_REALIZADO);
			Juego.getInstancia().notificarObservadores(Eventos.CAMBIAR_TURNO);
		}
		actualizarTablero();
		Juego.getInstancia().verificarEstado();

		
	}
	
	public void eliminarFicha(int x, int y) throws RemoteException {
		boolean eliminada = false;
		int i = 0;
		while (!eliminada) {
			if (fichas.get(i).getX() == x && fichas.get(i).getY() == y) {
				fichas.remove(i);
				eliminada = true;
			}
			i++;
		}
	}
	
	public void actualizarMovPosible(int x, int y, int x1, int y1) throws RemoteException {
		boolean realizado = false;
		while (!realizado) {
			for (PosiblesMovimientos mov : movsPosibles)
				if (mov.getX() == x && mov.getY() == y) {
					mov.mover(x1, y1);
					realizado = true;
				}
		}
	}
	

	public void actualizarFicha(int x, int y, int x1, int y1) throws RemoteException {
		boolean realizado = false;
		while (!realizado) {
			for (Ficha ficha : fichas)
				if (ficha.getX() == x && ficha.getY() == y) {
					ficha.mover(x1, y1);
					ficha.verificarReina();
					realizado = true;
				}
		}
	}
	

}
