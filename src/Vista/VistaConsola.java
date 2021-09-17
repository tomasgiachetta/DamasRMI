package Vista;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

import Modelo.Jugador;
import Controlador.*;

public class VistaConsola implements IVista{
	private final int blancas = 1, negras = 2, reinaBlanca = 3, reinaNegra = 4, PosibleMov = 5;
	Controlador controlador;
	int x, y, x1, y1;
	private Scanner teclado = new Scanner(System.in);
	private boolean fichaSeleccionada;
	private boolean enEspera = false;
	private boolean enJuego = false;
	private boolean movimientosActivados;
	
	
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
		//comenzarJuego();	
	}
	
	//private void loop() {
		
	//}
	
	
	
	private int mostrarMenu() {
        int opcion = -1;
        while (opcion != 0 && opcion != 1 && opcion != 2 && opcion != 3 && opcion != 4 && opcion != 5) {
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("--                                 Damas                                    --");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("-- 1 - Iniciar Juego                                                        --");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("-- 2 - Seleccionar Jugador                                                  --");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("-- 3 - Agregar Jugador                                                      --");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("-- 4 - Eliminar Jugador                                                     --");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("-- 5 - Mostrar estadisticas de los jugadores                                --");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("-- 0 - Salir del Juego                                                      --");
            System.out.println("------------------------------------------------------------------------------");
			opcion = teclado.nextInt();
        }
        return opcion;
    }
	
	public void menu() throws RemoteException  {
        int opcion = -1;
        while (opcion != 0) {
            opcion = mostrarMenu();
            switch (opcion) {
            case 1:
                iniciarPartida();
                break;
            case 2:
                seleccionarJugadores();
                break;    
            case 3:
            	agregarJugador();
            	break;
            case 4:
            	eliminarJugador();
            	break;
            case 5:
            	mostrarEstadisticas();
            	break;
            }
        }
        System.out.println("Juego finalizado.");

    } 

	private void mostrarEstadisticas() throws RemoteException {
		ArrayList<Jugador> jugadores = controlador.obtenerJugadores();
		for (Jugador j : jugadores) {
			System.out.println("Nombre " + j.getNombre() + ", Partidos jugados: " + j.partidosJugados() + ", Partidos ganados: " + j.partidosGanados()
					+ ", Partidos perdidos: " + j.partidosPerdidos() + ", Efectividad: " + j.porcentajeGanados() + "%");
			
		}
		
		System.out.println("Presione enter para volver al menú.");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
	}

	public void seleccionarFicha() throws RemoteException {
		System.out.println("Ingrese la fila de la ficha a seleccionar, presione enter y luego ingrese la columna.");
		Scanner sc = new Scanner(System.in);
		x = sc.nextInt();
		y = sc.nextInt();
		controlador.seleccionarFicha(x, y);
	}

	public void seleccionarCasilla() throws RemoteException {
		System.out.println(
				"Ingrese la fila de la casilla a la que quiere mover la ficha seleccionada, presione enter y luego ingrese la columna.");
		Scanner sc = new Scanner(System.in);
		x1 = sc.nextInt();
		y1 = sc.nextInt();
		controlador.seleccionarCasilla(x1, y1);
	}

	public void actualizarTablero(int[][] tablero, char turno) throws RemoteException {
		System.out.println();
		System.out.println("      1    2    3    4    5    6    7    8 ");
		for (int i = 1; i < tablero.length; i++) {
			System.out.println("    -----------------------------------------");
			System.out.print(" " + i + "  |");
			for (int j = 1; j < tablero[0].length; j++) {
				if (tablero[i][j] == blancas) {
					System.out.print(" B  |");
				} else if (tablero[i][j] == negras) {
					System.out.print(" N  |");
				} else if (tablero[i][j] == reinaBlanca) {
					System.out.print(" RB |");
				} else if (tablero[i][j] == reinaNegra) {
					System.out.print(" RN |");
				} else if (tablero[i][j] == PosibleMov) {
					System.out.print("----|");
				} else {
					System.out.print("    |");
				}
				if (controlador.estadoJuego()) {
					if (i == 4 && j == 8 && turno == 'N') {
						System.out.print(" Juegan las Negras");
					}
					if (i == 4 && j == 8 && turno == 'B')
						System.out.print(" Juegan las Blancas");
				}
			}
			
			System.out.println();
		}
		
		System.out.println("    -----------------------------------------");
		if (!controlador.estadoJuego()) {
			System.out.println("Presione enter para volver al menú.");
			Scanner sc = new Scanner(System.in);
			sc.nextLine();
		}
	}



	public void iniciarPartida() throws RemoteException {
		controlador.iniciarPartida();
		//while (controlador.estadoJuego()) { 
		//	if (!fichaSeleccionada) 
		//		seleccionarFicha();
		//	else
		//		seleccionarCasilla();
		//}
		//mostrarMenu();
	}

	
	
	public void seleccionarJugadores() throws RemoteException {
		
		System.out.println("1- Blancas.");
		System.out.println("2- Negras.");
		System.out.println("Ingrese el numero del color de fichas que quiere utilizar.");
		Scanner sc = new Scanner(System.in);
		String color = null;
		int c = -1;
		while (c != 1 && c!= 2) {
			c = sc.nextInt();
			if (c == 1) {
				color = "blancas";
			} else if (c == 2) {
				color = "negras";
			} else {
				System.out.println("Ingrese un numero correcto.");
			}	
		}
		
		ArrayList<Jugador> jugadores = controlador.obtenerJugadores();
		for (int i = 0; i < jugadores.size(); i++) {
			System.out.println(i + " - " + jugadores.get(i).getNombre());
		}
		System.out.println("Ahora ingrese el numero del jugador que quiere seleccionar.");
		
		int j = -1;
		j = sc.nextInt();
		while (j < 0 || j > jugadores.size()) {
			System.out.println("Ingrese un numero correcto.");
			j = sc.nextInt();
		}	
		asignarJugador(color, jugadores.get(j));
	}
	
	public void jugadorSeleccionado() {
		System.out.println("Jugador seleccionado.");
		System.out.println("");
		
	}
	
	
	public void agregarJugador() throws RemoteException {
		System.out.println("Ingrese el nombre del jugador o ingrese 0 para volver hacia atrás.");
		Scanner sc = new Scanner(System.in);
		String nombre = sc.nextLine();
		if (!nombre.equals("0")) {
			controlador.agregarJugador(nombre);
			ponerEnEspera();
		}
	}
	
	
	public void eliminarJugador() throws RemoteException {
		ArrayList<Jugador> jugadores = controlador.obtenerJugadores();
		
		for (int i = 0; i < jugadores.size(); i++) {
			System.out.println(i + " - " + jugadores.get(i).getNombre());
		}
		System.out.println("Ingrese el numero del jugador que quiere eliminar.");
		
		Scanner sc = new Scanner(System.in);
		int j = -1;
		j = sc.nextInt();
		
		System.out.println("Está por eliminar al jugador " + jugadores.get(j).getNombre() + ", ¿está seguro que quiere eliminarlo?");
		System.out.println("Los jugadores eliminados pierden las estadisticas guardadas.");
		System.out.println("Ingrese 1 para confirmar la eliminación, o cualquier otra cosa para cancelarla.");
		int aux = sc.nextInt();
		if (aux == 1) {
			controlador.eliminarJugador(jugadores.get(j).getNombre());
		} else {
			System.out.println("Accion cancelada.");
		}
	}
	
	public void jugadorEliminado() {
		System.out.println("Jugador eliminado correctamente.");
		System.out.println("");
		
	}
	

	public void fichaSeleccionada() throws RemoteException {
		fichaSeleccionada = true;
	}

	public void movimientoRealizado() throws RemoteException {
		fichaSeleccionada = false;
	}

	public void informarGanador(String ganador) throws RemoteException {
		fichaSeleccionada = false;
		System.out.println("    -----------------------------------------");
		System.out.println("");
		System.out.println("            Partida finalizada.");
		System.out.println("            Ganó " + ganador);
		System.out.println("");
		System.out.println("Presione enter para volver al menú");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		
	}
	
	public void errorMovimientoInvalido() throws RemoteException {
		fichaSeleccionada = false;
		System.out.println("Ingrese una posición valida para mover la ficha seleccionada");
	}

	public void errorMovimientoComer() throws RemoteException {
		System.out.println(
				"Movimiento invalido. Recuerde que los movimientos de comer son obligatorios si existe la posibilidad");
	}

	public void errorFichaSeleccionada() throws RemoteException {
		System.out.println("Ingrese una posición válida");
	}

	public void errorCasillaSeleccionada() throws RemoteException {
		fichaSeleccionada = false;
		System.out.println("Ingrese una posicion válida para mover la ficha seleccionada ([" + x + "," + y + "])");
	}
	
	public void errorColorAsignado() {
		System.out.println("El color de fichas elegido ya fue seleccionado por otro jugador.");
		try {
			seleccionarJugadores();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void errorJugadorAsignado() {
		System.out.println("El jugador elegido ya fue seleccionado por otro jugador.");
		try {
			seleccionarJugadores();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void errorJugadoresInsuficientes() {
		System.out.println("Para iniciar la partida tienen que haberse seleccionado ambos jugadores.");
	}
	
	
	public void guardarPartida() throws RemoteException {
		controlador.guardarPartida("Damas.dat");
	}
	
	public void cargarPartida() throws RemoteException {
		controlador.cargarPartida("Damas.dat");
	}
	
	public void reanudarPartida(int[][] tablero, char turno) throws RemoteException {
		while (controlador.estadoJuego()) { 
			if (!fichaSeleccionada) 
				seleccionarFicha();
			else
				seleccionarCasilla();
		}
		mostrarMenu();
	}

	@Override
	public void iniciar() {
		try {
			menu();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void crearMenu()  {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void crearTablero()  {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean estadoJuego() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void asignarJugador(String color, Jugador jugador) {
		try {
			controlador.asignarJugadores(color, jugador);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void volverAlMenu() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activarMovimientos() {
		this.movimientosActivados = true;
		
	}

	@Override
	public void desactivarMovimientos() {
		this.movimientosActivados = false;
		
	}
	
	public void loopJuego() throws RemoteException {
		while (enJuego) { 
			if (!fichaSeleccionada) 
				seleccionarFicha();
			else
				seleccionarCasilla();
		}
	}
	
	@Override
	public void juegoIniciado() {
		enJuego = true;
		try {
			loopJuego();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void juegoTerminado() {
		enJuego = false;
	}

	@Override
	public boolean getEnEspera() {
		// TODO Auto-generated method stub
		return enEspera;
	}

	@Override
	public void ponerEnEspera() {
		enEspera = true;
		
	}

	@Override
	public void sacarDeEspera() {
		enEspera = false;
	}

	@Override
	public void deseleccionarFicha() {
		// TODO Auto-generated method stub
		fichaSeleccionada = false;
	}

	@Override
	public void errorJugadorAgregado() {
		System.out.println("Ya hay un jugador agregado con ese nombre.");
		sacarDeEspera();
		
	}

	@Override
	public void jugadorAgregado() {
		System.out.println("Jugador agregado correctamente.");
		sacarDeEspera();
	}
	


		
	
	
	
	
}