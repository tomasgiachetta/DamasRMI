package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Estadisticas implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Jugador> jugadores = new ArrayList<>();

	public Estadisticas() {

	}

	public void agregarJugador(Jugador jugador) {
		this.jugadores.add(jugador);
	}

	public ArrayList<Jugador> obtenerJugadores() {
		return jugadores;
	}

	public void partidosJugados() {

	}

	public void partidosGanados() {

	}

	public void partidosPerdidos() {

	}

	public void porcentajeGanados() {

	}

	public void eliminarJugador(String nombre) {
		int aux = -1;
		for (int i = 0; i < jugadores.size(); i++ ) {
			if (jugadores.get(i).getNombre().equals(nombre)) {
					aux = i;
			}
		}
		jugadores.remove(aux);
		
	}
}
