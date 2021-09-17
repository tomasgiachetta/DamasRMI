package Controlador;

import java.rmi.RemoteException;
import java.util.ArrayList;

import Modelo.*;
import Vista.*;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public class Controlador implements IControladorRemoto {
	// private Juego juego;
	private IJuego juego;
	private IVista vista;
	private Jugador jugador;
	// private VistaConsola vista;
	private Persistir persistir = new Persistir();
	private Jugador aux;
	private String color = null;

	/*
	 * public Controlador(Juego partida, VistaConsola vista) { miPartida = partida;
	 * this.vista= vista; partida.addObservador(this);
	 * 
	 * }
	 */

	public Controlador(IVista vista) {
		// this.juego = juego.getInstancia();
		this.vista = vista;
		//juego = new Juego();
	}

	public String getColor() {
		if (color == null)
			return "no seleccionado";
		else
			return color;
	}
	
	public String getJugador() {
		if (jugador == null) 
			return "no seleccionado";
		else
			return jugador.getNombre();
	}
	
	public boolean estadoJuego() throws RemoteException {
		return juego.getEstado();
	}

	public boolean hayFichasBloqueadas() throws RemoteException {
		return juego.fichasBloqueadas();
	}

	public boolean verificarFicha(int x, int y) throws RemoteException {
		return juego.verificarFicha(x, y);
	}

	public boolean verificarCasilla(int x1, int y1) throws RemoteException {
		return juego.verificarCasilla(x1, y1);
	}

	public void seleccionarFicha(int x, int y) throws RemoteException {
		juego.seleccionarFicha(x, y);
	}

	public void seleccionarCasilla(int x1, int y1) throws RemoteException {
		juego.seleccionarCasilla(x1, y1);
	}

	public void iniciarPartida() throws RemoteException {
		juego.iniciarPartida();
	}

	public void agregarJugador(String nombre) throws RemoteException {
		juego.agregarJugador(nombre);
	}


	public ArrayList<Jugador> obtenerJugadores() throws RemoteException {
		return juego.obtenerJugadores();
	}

	public void asignarJugadores(String color, Jugador jugador) throws RemoteException {
		this.jugador = jugador;
		this.color = color;
		juego.asignarJugadores(color, jugador);
	}
	
	public void eliminarJugador(String nombre) throws RemoteException {
		juego.eliminarJugador(nombre);
		
	}

	public void cargarPartida(String ruta) throws RemoteException {
		this.juego.cargarPartida((IJuego) this.persistir.cargarObjetos(ruta));
	}

	public void guardarPartida(String ruta) {
		this.persistir.guardarObjetos(ruta, this.juego);

	}

	public int[][] obtenerTablero() throws RemoteException {
		return juego.obtenerTablero();
	}

	public char obtenerTurno() throws RemoteException {
		return juego.obtenerTurno();
	}

	@Override
	public void actualizar(IObservableRemoto modelo, Object cambio) throws RemoteException {
		if (cambio instanceof Eventos) {
			Eventos evento = (Eventos) cambio;
			switch (evento) {
			case SELECCIONAR_FICHA:
				vista.seleccionarFicha();
				break;
			case SELECCIONAR_CASILLA:
				vista.seleccionarCasilla();
				break;
			case COLOR_YA_ASIGNADO:
				if (vista.getEnEspera()) {
					jugador = null;
					color = null;
					vista.errorColorAsignado();
				}
				break;
			case JUGADOR_YA_ASIGNADO:
				if (vista.getEnEspera()) {
					jugador = null;
					color = null;
					vista.errorJugadorAsignado();
				}
				break;
			case JUGADORES_INSUFICIENTES:
				if (vista.getEnEspera()) {
					vista.errorJugadoresInsuficientes();
					vista.sacarDeEspera();
				}
				break;
			case SELECCIONAR_JUGADORES:
				vista.seleccionarJugadores();
				break;
			case JUGADOR_SELECCIONADO:
				if (vista.getEnEspera()) {
					vista.jugadorSeleccionado();
				}
				break;
			case JUGADOR_EXISTENTE:
				if (vista.getEnEspera())
					vista.errorJugadorAgregado();
				break;
			case JUGADOR_AGREGADO:
				if (vista.getEnEspera()) 
					vista.jugadorAgregado();
				break;
			case JUGADOR_ELIMINADO:
				if (vista.getEnEspera())
					vista.jugadorEliminado();
				break;
			case ERROR_FICHA_SELECCIONADA:
				aux = juego.obtenerJugadorEnTurno();
				if (jugador.getNombre().equals(aux.getNombre()))	
					vista.errorFichaSeleccionada();
				break;
			case ERROR_CASILLA_SELECCIONADA:
				aux = juego.obtenerJugadorEnTurno();
				if (jugador.getNombre().equals(aux.getNombre()))
					vista.errorCasillaSeleccionada();
				break;
			case ERROR_MOVIMIENTO_COMER_DISPONIBLE:
				aux = juego.obtenerJugadorEnTurno();
				if (jugador.getNombre().equals(aux.getNombre()))
					vista.errorMovimientoComer();
				break;
			case ERROR_MOVIMIENTO_INVALIDO:
				aux = juego.obtenerJugadorEnTurno();
				if (jugador.getNombre().equals(aux.getNombre()))
					vista.errorMovimientoInvalido();
				break;
			case FICHA_SELECCIONADA:
				aux = juego.obtenerJugadorEnTurno();
				if (jugador.getNombre().equals(aux.getNombre()))
					vista.fichaSeleccionada();
				break;
			case DESELECCIONAR_FICHA:
				vista.deseleccionarFicha();
				break;
			case MOVIMIENTO_REALIZADO:
				aux = juego.obtenerJugadorEnTurno();
				if (jugador.getNombre().equals(aux.getNombre()))
					vista.movimientoRealizado();
				break;
			case ACTUALIZAR_TABLERO:
				vista.actualizarTablero(juego.obtenerTablero(), juego.obtenerTurno());
				break;
			case INFORMAR_GANADOR:
				vista.informarGanador(juego.getGanador());
				vista.juegoTerminado();
				break;
			case JUEGO_INICIADO:
				vista.sacarDeEspera();
				aux = juego.obtenerJugadorEnTurno();
				if (jugador.getNombre().equals(aux.getNombre()))
					vista.activarMovimientos();
				else
					vista.desactivarMovimientos();
				vista.juegoIniciado();
				vista.crearTablero();
				break;
			case CAMBIAR_TURNO:
				aux = juego.obtenerJugadorEnTurno();
				if (jugador.getNombre().equals(aux.getNombre()))
					vista.activarMovimientos();
				else
					vista.desactivarMovimientos();
				break;

			}

		}

	}
	
	public int[][] getTablero() throws RemoteException {
		return juego.obtenerTablero();
		
	}

	@Override
	public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException {
		this.juego = (IJuego) modeloRemoto;
	}



}