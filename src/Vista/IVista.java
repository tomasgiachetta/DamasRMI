package Vista;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import Controlador.Controlador;
import Modelo.Eventos;
import Modelo.Jugador;

public interface IVista {
	
	void iniciar();
	
	void crearMenu();
	
	void asignarJugador(String color, Jugador jugador) throws RemoteException;

	void actualizarTablero(int[][] tablero, char turno) throws RemoteException;

	void iniciarPartida() throws RemoteException;

	void crearTablero();
	
	void volverAlMenu() throws RemoteException;

	boolean estadoJuego() throws RemoteException;

	void seleccionarJugadores() throws RemoteException;

	void agregarJugador() throws RemoteException;

	void informarGanador(String ganador) throws RemoteException;

	void errorMovimientoInvalido() throws RemoteException;

	void errorMovimientoComer() throws RemoteException;

	void errorFichaSeleccionada() throws RemoteException;

	void errorCasillaSeleccionada() throws RemoteException;

	void seleccionarFicha() throws RemoteException;

	void seleccionarCasilla() throws RemoteException;

	void fichaSeleccionada() throws RemoteException;

	void movimientoRealizado() throws RemoteException;

	boolean getEnEspera();
	
	void ponerEnEspera();
	
	void sacarDeEspera();
	
	void cargarPartida() throws RemoteException;

	void guardarPartida() throws RemoteException;

	void reanudarPartida(int[][] tablero, char turno) throws RemoteException;
	
	void setControlador(Controlador controlador);
	
	void activarMovimientos();
	
	void desactivarMovimientos();
	
	void juegoIniciado();
	
	void juegoTerminado();

	void errorColorAsignado();

	void errorJugadorAsignado();

	void errorJugadoresInsuficientes();

	void deseleccionarFicha();

	void errorJugadorAgregado();

	void jugadorAgregado();

	void jugadorEliminado();

	void jugadorSeleccionado();

}