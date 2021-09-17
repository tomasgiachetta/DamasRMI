package Modelo;

import java.rmi.RemoteException;
import java.util.ArrayList;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public interface IJuego extends IObservableRemoto {

	void cargaInicial() throws RemoteException;

	void cargarPartida(IJuego juego) throws RemoteException;
	
	public Tablero getTablero() throws RemoteException;
	
	public Jugador getJugadorBlancas() throws RemoteException;
	
	public Jugador getJugadorNegras() throws RemoteException;

	void agregarJugador(String nombre) throws RemoteException;

	String nombreJugador(Jugador jugador) throws RemoteException;

	String getGanador() throws RemoteException;

	String getPerdedor() throws RemoteException;

	void setPerdedor(String perdedor) throws RemoteException;

	void setGanador(String ganador) throws RemoteException;

	void asignarJugadores(String color, Jugador jugador) throws RemoteException;

	void inicializarFichas() throws RemoteException;

	void crearFichaNegra(int x, int y) throws RemoteException;

	void crearFichaBlanca(int x, int y) throws RemoteException;

	void crearMovPosible(int x, int y) throws RemoteException;

	void moverFicha(int x, int y, int x1, int y1) throws RemoteException;

	void ponerTableroVacio() throws RemoteException;

	void verificarEstado() throws RemoteException;

	boolean fichasBloqueadas() throws RemoteException;

	// VERIFICAR QUE LA POSICION DE LA FICHA QUE INGRESA EL USUARIO EXISTE
	boolean verificarFicha(int x, int y) throws RemoteException;

	// VERIFICAR QUE LA POSICION DE MOVIMIENTO QUE INGRESA EL USUARIO EXISTE
	boolean verificarCasilla(int x1, int y1) throws RemoteException;

	void seleccionarFicha(int x, int y) throws RemoteException;

	void seleccionarCasilla(int x1, int y1) throws RemoteException;

	void iniciarPartida() throws RemoteException;

	Boolean getEstado() throws RemoteException;
	
	int[][] obtenerTablero() throws RemoteException;

	char obtenerTurno() throws RemoteException;

	Jugador obtenerJugadorEnTurno() throws RemoteException;

	ArrayList<Jugador> obtenerJugadores() throws RemoteException;

	void eliminarJugador(String nombre) throws RemoteException;
}