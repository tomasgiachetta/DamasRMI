package Modelo;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

public class Juego extends ObservableRemoto implements Serializable, IJuego {

	private static Juego instancia;
	private Tablero tableroFichas;
	private String ganador = null, perdedor = null;
	int x, y, x1, y1;
	boolean estado;
	private Jugador jugadorBlancas = null;
	private Jugador jugadorNegras = null;
	// private ArrayList<Jugador> jugadores = new ArrayList<>();
	private Persistir persistir = new Persistir();
	private Estadisticas estadisticas = new Estadisticas();

	public static Juego getInstancia() {
		if (instancia == null) {
			instancia = new Juego();
		}
		return instancia;
	}

	private Juego() {
		tableroFichas = new Tablero();
		jugadorBlancas = null;
		jugadorNegras = null;
		try {
			cargaInicial();
		} catch (Exception e) {

		}
	}

	public Tablero getTablero() throws RemoteException {
		return tableroFichas;
	}

	public Jugador getJugadorBlancas() throws RemoteException {
		return jugadorBlancas;
	}

	public Jugador getJugadorNegras() throws RemoteException {
		return jugadorNegras;
	}

	@Override
	public void cargaInicial() throws RemoteException {
		estadisticas = (Estadisticas) persistir.cargarObjetos("Estadisticas.dat");
	}

	public void cargarPartida(IJuego juego) throws RemoteException {
		this.tableroFichas = juego.getTablero();
		this.ganador = juego.getGanador();
		this.perdedor = juego.getPerdedor();
		this.estado = juego.getEstado();
		this.jugadorBlancas = juego.getJugadorBlancas();
		this.jugadorNegras = juego.getJugadorNegras();
		if (estado)
			notificarObservadores(Eventos.ACTUALIZAR_TABLERO);
	}

	@Override
	public void agregarJugador(String nombre) throws RemoteException {
		ArrayList<Jugador> jugadores = estadisticas.obtenerJugadores();
		boolean aux = false;
		for (Jugador j : jugadores) {
			if (j.getNombre().equals(nombre)) {
				aux = true;
			}
		}
		if (aux) {
			notificarObservadores(Eventos.JUGADOR_EXISTENTE);
		} else {
			if (nombre != "") {
				Jugador j = new Jugador(nombre);
				estadisticas.agregarJugador(j);
				notificarObservadores(Eventos.JUGADOR_AGREGADO);
				persistir.guardarObjetos("Estadisticas.dat", estadisticas);
			}
		}
	}

	public void eliminarJugador(String nombre) throws RemoteException {
		estadisticas.eliminarJugador(nombre);
		persistir.guardarObjetos("Estadisticas.dat", estadisticas);
		notificarObservadores(Eventos.JUGADOR_ELIMINADO);
	}

	@Override
	public String nombreJugador(Jugador jugador) throws RemoteException {
		return jugador.getNombre();
	}

	@Override
	public String getGanador() throws RemoteException {
		return ganador;
	}

	@Override
	public String getPerdedor() throws RemoteException {
		return perdedor;
	}

	public void setPerdedor(String perdedor) throws RemoteException {
		this.perdedor = perdedor;
	}


	public void setGanador(String ganador) throws RemoteException {
		this.ganador = ganador;
	}

	@Override
	public void asignarJugadores(String color, Jugador jugador) throws RemoteException {
		ArrayList<Jugador> jugadores = estadisticas.obtenerJugadores();
		if (color.equals("blancas")) {
			if (jugadorBlancas == null) {
				for (Jugador j : jugadores) {
					if (jugador.getNombre().equals(j.getNombre())) {
						if (jugadorNegras == null) {
							jugadorBlancas = j;
							notificarObservadores(Eventos.JUGADOR_SELECCIONADO);
						} else if (!jugadorNegras.getNombre().equals(j.getNombre())) {
							jugadorBlancas = j;
							notificarObservadores(Eventos.JUGADOR_SELECCIONADO);
						} else
							notificarObservadores(Eventos.JUGADOR_YA_ASIGNADO);
					}
				}
			} else
				notificarObservadores(Eventos.COLOR_YA_ASIGNADO);
		} else if (jugadorNegras == null) {
			for (Jugador j : jugadores) {
				if (jugador.getNombre().equals(j.getNombre())) {
					if (jugadorBlancas == null) {
						jugadorNegras = j;
						notificarObservadores(Eventos.JUGADOR_SELECCIONADO);
					} else if (!jugadorBlancas.getNombre().equals(j.getNombre())) {
						jugadorNegras = j;
						notificarObservadores(Eventos.JUGADOR_SELECCIONADO);
					} else
						notificarObservadores(Eventos.JUGADOR_YA_ASIGNADO);
				}
			}
		} else
			notificarObservadores(Eventos.COLOR_YA_ASIGNADO);

	}

	@Override
	public void inicializarFichas() throws RemoteException {
		for (int j = 1; j < 9; j++) {
			if (j % 2 != 0) {
				crearFichaBlanca(2, j);
				crearFichaNegra(8, j);
				crearFichaNegra(6, j);
				crearMovPosible(4, j);

			} else {
				crearFichaBlanca(3, j);
				crearFichaBlanca(1, j);
				crearFichaNegra(7, j);
				crearMovPosible(5, j);
			}
		}
		tableroFichas.actualizarTablero();
	}

	@Override
	public void crearFichaNegra(int x, int y) throws RemoteException {
		tableroFichas.crearFichaNegra(x, y);
	}

	@Override
	public void crearFichaBlanca(int x, int y) throws RemoteException {
		tableroFichas.crearFichaBlanca(x, y);
	}

	@Override
	public void crearMovPosible(int x, int y) throws RemoteException {
		tableroFichas.crearMovPosible(x, y);
	}

	public void moverFicha(int x, int y, int x1, int y1) throws RemoteException {
		tableroFichas.moverFicha(x, y, x1, y1);
	}

	@Override
	public void ponerTableroVacio() throws RemoteException {
		tableroFichas.ponerTableroVacio();

	}

	@Override
	public void verificarEstado() throws RemoteException {
		if (!estado || fichasBloqueadas()) {
			setPerdedor(tableroFichas.turnoDe());
			if (getPerdedor() == "Negras") {
				setGanador(jugadorBlancas.getNombre());
				setPerdedor(jugadorNegras.getNombre());
				jugadorBlancas.victoria();
				jugadorNegras.derrota();
			} else {
				setGanador(jugadorNegras.getNombre());
				setPerdedor(jugadorBlancas.getNombre());
				jugadorNegras.victoria();
				jugadorBlancas.derrota();
			}

			estado = false;
			
			notificarObservadores(Eventos.ACTUALIZAR_TABLERO);
			ponerTableroVacio();
			notificarObservadores(Eventos.INFORMAR_GANADOR);

			persistir.guardarObjetos("Estadisticas.dat", estadisticas);

		}
	}

	@Override
	public boolean fichasBloqueadas() throws RemoteException {
		return !tableroFichas.posibilidadMovimiento();
	}

	// VERIFICAR QUE LA POSICION DE LA FICHA QUE INGRESA EL USUARIO EXISTE
	@Override
	public boolean verificarFicha(int x, int y) throws RemoteException {
		return tableroFichas.verificarFicha(x, y);
	}

	// VERIFICAR QUE LA POSICION DE MOVIMIENTO QUE INGRESA EL USUARIO EXISTE
	@Override
	public boolean verificarCasilla(int x1, int y1) throws RemoteException {
		return tableroFichas.verificarCasilla(x1, y1);
	}

	@Override
	public void seleccionarFicha(int x, int y) throws RemoteException {
		this.x = x;
		this.y = y;
		if (!verificarFicha(x, y)) {
			notificarObservadores(Eventos.ERROR_FICHA_SELECCIONADA);
		}
		;
	}

	@Override
	public void seleccionarCasilla(int x1, int y1) throws RemoteException {
		this.x1 = x1;
		this.y1 = y1;
		if (x1 != x && y1 != y) {
			if (verificarCasilla(x1, y1)) {
				tableroFichas.moverFicha(x, y, x1, y1);
				notificarObservadores(Eventos.ACTUALIZAR_TABLERO);
			} else
				notificarObservadores(Eventos.ERROR_CASILLA_SELECCIONADA);
		} else
			notificarObservadores(Eventos.DESELECCIONAR_FICHA);
	}

	@Override
	public void iniciarPartida() throws RemoteException {

		if (jugadorBlancas != null && jugadorNegras != null) {
			estado = true;
			notificarObservadores(Eventos.JUEGO_INICIADO);
			inicializarFichas();
			// notificarObservadores(tableroFichas.getTablero(), tableroFichas.getTurno());

			notificarObservadores(Eventos.ACTUALIZAR_TABLERO);
		} else
			notificarObservadores(Eventos.JUGADORES_INSUFICIENTES);

		/*
		 * while (estado && !fichasBloqueadas()) { notificar(Eventos.SELECCIONAR_FICHA);
		 * if (verificarFicha(x, y)) { notificar(Eventos.SELECCIONAR_CASILLA); if
		 * (verificarCasilla(x1, y1)) { moverFicha(x, y, x1, y1);
		 * notificarCambio(tableroFichas.getTablero(), tableroFichas.getTurno()); } else
		 * notificar(Eventos.ERROR_CASILLA_SELECCIONADA);
		 * 
		 * } else notificar(Eventos.ERROR_FICHA_SELECCIONADA);
		 * 
		 * }
		 * 
		 * 
		 * setPerdedor(tableroFichas.turnoDe()); if (getPerdedor() == "Negras")
		 * setGanador("Blancas"); else setGanador("Negras"); estado = false;
		 * 
		 * System.out.println("Ganan las " + getGanador());
		 */
	}

	public ArrayList<Jugador> obtenerJugadores() {
		return estadisticas.obtenerJugadores();
	}

	@Override
	public Boolean getEstado() throws RemoteException {
		return estado;
	}

	public int[][] obtenerTablero() throws RemoteException {
		return tableroFichas.getTablero();
	}

	public char obtenerTurno() throws RemoteException {
		return tableroFichas.getTurno();
	}

	public Jugador obtenerJugadorEnTurno() {
		if (tableroFichas.getTurno() == 'B')
			return jugadorBlancas;
		else
			return jugadorNegras;

	}

}
