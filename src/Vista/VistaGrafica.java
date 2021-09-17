package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import Controlador.Controlador;

import Modelo.Jugador;
import VistaGUI.AcercaDe;
import VistaGUI.ElegirJugadores;
import VistaGUI.EliminarJugador;
import VistaGUI.ErrorCasillaSeleccionada;
import VistaGUI.ErrorColorAsignado;
import VistaGUI.ErrorFichaSeleccionada;
import VistaGUI.ErrorJugadorAgregado;
import VistaGUI.ErrorJugadorAsignado;
import VistaGUI.ErrorJugadoresInsuficientes;
import VistaGUI.ErrorMovimientoComer;
import VistaGUI.ErrorMovimientoInvalido;
import VistaGUI.InformarGanador;
import VistaGUI.JugadorEliminado;
import VistaGUI.Reglamento;
import VistaGUI.TablaEstadisticas;

public class VistaGrafica extends JFrame implements ActionListener, IVista {
	private final int blancas = 1, negras = 2, reinaBlanca = 3, reinaNegra = 4, PosibleMov = 5;
	int x, y, x1, y1;
	private boolean movimientosActivados = false;
	private Controlador controlador;
	private JMenu mnOpciones;
	private JMenuBar menuGeneral;
	private JMenu mnInfo;
	private JPanel tableroGrafico;
	private JButton[][] boton = new JButton[9][9];
	private Boolean fichaSeleccionada = false;
	private JMenuItem mnAcercaDe;
	private AcercaDe acercaDe;
	private JMenuItem mnReglas;
	private JMenuItem mnVerDatos;
	private Reglamento reglamento;
	private JPanel panelPrincipal;
	private JMenuItem mnCargarPartida;
	private JMenuItem mnGuardarPartida;
	private JMenuItem mnVerJugadores;
	private JMenuItem mnVolverAlMenu;
	private ElegirJugadores elegirJugadores;
	private TablaEstadisticas tabla;
	private boolean enJuego = false;
	private boolean enEspera = false;
	private boolean jugadorElegido = false;

	private ErrorMovimientoInvalido errorMovimientoInvalido;
	private ErrorMovimientoComer errorMovimientoComer;
	private ErrorFichaSeleccionada errorFichaSeleccionada;
	private ErrorCasillaSeleccionada errorCasillaSeleccionada;
	private InformarGanador informarGanador;
	private ErrorJugadoresInsuficientes errorJugadoresInsuficientes;
	private ErrorJugadorAsignado errorJugadorAsignado;
	private ErrorColorAsignado errorColorAsignado;
	private ErrorJugadorAgregado errorJugadorAgregado;
	private EliminarJugador eliminarJugador;
	private JugadorEliminado jugadorEliminado;

	public VistaGrafica() {
		// errorMovimientoInvalido = new ErrorMovimientoInvalido();
		// errorFichaSeleccionada = new ErrorFichaSeleccionada();
		// errorCasillaSeleccionada = new ErrorCasillaSeleccionada(x, y);
		// informarGanador = new InformarGanador("hola", this);
		// errorJugadoresInsuficientes = new ErrorJugadoresInsuficientes(this) ;
		// eliminarJugador = new EliminarJugador(this);
		// jugadorEliminado = new JugadorEliminado(this);

	}

	@Override
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	// public Jugador obtenerJugador() {
	// return jugador;
	// }

	@Override
	public void iniciar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(510, 510));
		this.setLocationRelativeTo(null);
		this.setTitle("Damas");
		this.setResizable(false);

		crearMenu();
		setVisible(true);
	}

	@Override
	public void crearMenu() {
		menuGeneral = new JMenuBar();
		setJMenuBar(menuGeneral);
		mnOpciones = new JMenu("Opciones");
		menuGeneral.add(mnOpciones);
		mnInfo = new JMenu("Información");
		menuGeneral.add(mnInfo);

		mnCargarPartida = new JMenuItem("Cargar Partida");
		mnCargarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cargarPartida();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		mnGuardarPartida = new JMenuItem("Guardar partida");
		mnGuardarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				guardarPartida();

			}
		});

		mnVerJugadores = new JMenuItem("Ver estadistica de los jugadores");
		mnVerJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tabla = new TablaEstadisticas(obtenerJugadores());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		mnVolverAlMenu = new JMenuItem("Volver al menú");
		mnVolverAlMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(panelPrincipal);
				mnOpciones.remove(mnVolverAlMenu);
			}
		});

		mnOpciones.add(mnGuardarPartida);
		mnOpciones.add(mnCargarPartida);
		mnOpciones.add(mnVerJugadores);

		
		mnVerDatos = new JMenuItem("Ver jugador/color seleccionado");
		mnInfo.add(mnVerDatos);
		mnVerDatos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(VistaGrafica.this, "Color: " + controlador.getColor() + "\r\nJugador: " + controlador.getJugador());		
			}
		});
		
		
		
		mnReglas = new JMenuItem("Reglas");
		mnInfo.add(mnReglas);
		mnReglas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				reglamento = new Reglamento();

			}
		});

		mnAcercaDe = new JMenuItem("Acerca de...");
		mnInfo.add(mnAcercaDe);
		mnAcercaDe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				acercaDe = new AcercaDe();

			}

		});

		panelPrincipal = new FondoPanel();
		panelPrincipal.setLayout(null);
		setContentPane(panelPrincipal);

		
		
		JButton btnComenzar = new JButton("Comenzar Partida");

		btnComenzar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					ponerEnEspera();
					iniciarPartida();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		JButton btnElegirJugador = new JButton("Elegir Jugador");

		btnElegirJugador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!jugadorElegido)
					try {
						elegirJugadores = new ElegirJugadores(VistaGrafica.this, obtenerJugadores());
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				else
					JOptionPane.showMessageDialog(VistaGrafica.this, "Ya se seleccionó un jugador.");
			}		
		});

		JButton btnAgregarJugador = new JButton("Agregar jugador");

		btnAgregarJugador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					ponerEnEspera();
					agregarJugador();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		JButton btnEliminarJugador = new JButton("Eliminar jugador");

		btnEliminarJugador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ponerEnEspera();
				eliminarJugador = new EliminarJugador(VistaGrafica.this);
			}
		});
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);

			}

		});


		btnComenzar.setBounds(5, 120, 160, 30);
		btnComenzar.setIcon(new ImageIcon("./Imagenes/botonComenzarPartida.png"));
		panelPrincipal.add(btnComenzar);
		btnElegirJugador.setBounds(5, 160, 160, 30);
		btnElegirJugador.setIcon(new ImageIcon("./Imagenes/botonElegirJugador.png"));
		panelPrincipal.add(btnElegirJugador);
		btnAgregarJugador.setBounds(5, 200, 160, 30);
		btnAgregarJugador.setIcon(new ImageIcon("./Imagenes/botonAgregarJugador.png"));
		panelPrincipal.add(btnAgregarJugador);
		btnEliminarJugador.setBounds(5, 240, 160, 30);
		btnEliminarJugador.setIcon(new ImageIcon("./Imagenes/botonEliminarJugador.png"));
		panelPrincipal.add(btnEliminarJugador);
		btnSalir.setBounds(5, 280, 160, 30);
		btnSalir.setIcon(new ImageIcon("./Imagenes/botonSalir.png"));
		panelPrincipal.add(btnSalir);

	}

	class FondoPanel extends JPanel {
		private Image imagen;

		@Override
		public void paint(Graphics g) {
			imagen = new ImageIcon(getClass().getResource("/Imagenes/damas.png")).getImage();
			g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
			setOpaque(false);
			super.paint(g);
		}
	}

	@Override
	public void asignarJugador(String color, Jugador jugador) throws RemoteException {
		try {
			controlador.asignarJugadores(color, jugador);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actualizarTablero(int[][] tablero, char turno) {
		if (enJuego) {
			for (int i = 1; i < 9; i++) {
				for (int j = 1; j < 9; j++) {
					if (tablero[i][j] == blancas) {
						boton[i][j].setIcon(new ImageIcon("./Imagenes/FichaB.jpg"));
					} else if (tablero[i][j] == negras) {
						boton[i][j].setIcon(new ImageIcon("./Imagenes/FichaN.jpg"));
					} else if (tablero[i][j] == reinaBlanca) {
						boton[i][j].setIcon(new ImageIcon("./Imagenes/FichaBR.jpg"));
					} else if (tablero[i][j] == reinaNegra) {
						boton[i][j].setIcon(new ImageIcon("./Imagenes/FichaNR.jpg"));
					} else if (tablero[i][j] == PosibleMov) {
						boton[i][j].setIcon(new ImageIcon("./Imagenes/EspacionNegro.png"));
					} else {
						boton[i][j].setIcon(new ImageIcon("./Imagenes/EspacionBlanco.png"));
					}
				}
			}
		}

		SwingUtilities.updateComponentTreeUI(this);
	}

	@Override
	public void iniciarPartida() throws RemoteException {
		controlador.iniciarPartida();
	}

	@Override
	public void crearTablero() {

		tableroGrafico = new JPanel();
		tableroGrafico.setLayout(new GridLayout(8, 8));

		for (int i = 1; i < 9; i++) {
			final Integer iAux = new Integer(i);
			for (int j = 1; j < 9; j++) {
				final Integer jAux = new Integer(j);
				boton[i][j] = new JButton();
				boton[i][j].setBackground(Color.WHITE);
				boton[i][j].setBorderPainted(false);
				boton[i][j].setContentAreaFilled(false);
				boton[i][j].setOpaque(false);
				boton[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (movimientosActivados && enJuego) {
							if (!fichaSeleccionada) {
								x = iAux;
								y = jAux;
								try {
									seleccionarFicha();
								} catch (RemoteException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {
								x1 = iAux;
								y1 = jAux;
								try {
									seleccionarCasilla();
								} catch (RemoteException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						} else if (enJuego)
							JOptionPane.showMessageDialog(tableroGrafico, "No es su turno.");
					}
				});
				tableroGrafico.add(boton[i][j]);
			}
		}
		setContentPane(tableroGrafico);
		tableroGrafico.setVisible(true);

		SwingUtilities.updateComponentTreeUI(this);

	}

	@Override
	public boolean estadoJuego() throws RemoteException {
		return controlador.estadoJuego();
	}

	public ArrayList<Jugador> obtenerJugadores() throws RemoteException {
		return controlador.obtenerJugadores();
	}

	@Override
	public void seleccionarJugadores() throws RemoteException {
		elegirJugadores = new ElegirJugadores(VistaGrafica.this, obtenerJugadores());
	}

	@Override
	public void agregarJugador() throws RemoteException {
		String nombre = null;
		nombre = JOptionPane.showInputDialog(null, "Nombre del jugador", "Agregar jugador",
				JOptionPane.INFORMATION_MESSAGE);
		if (nombre != null)
			controlador.agregarJugador(nombre);

	}

	public void eliminarJugador(Jugador jugador) throws RemoteException {
		controlador.eliminarJugador(jugador.getNombre());

	}

	public void jugadorSeleccionado() {
		sacarDeEspera();
		jugadorElegido = true;

	}

	public void jugadorAgregado() {
		sacarDeEspera();
	}

	public void jugadorEliminado() {
		sacarDeEspera();
		jugadorEliminado = new JugadorEliminado(this);
	}

	public void volverAlMenu() throws RemoteException {
		setContentPane(panelPrincipal);
	}

	public void agregarBotonVolverAlMenu() {
		mnOpciones.add(mnVolverAlMenu);
	}

	public boolean getEnEspera() {
		return enEspera;
	}

	public void ponerEnEspera() {
		enEspera = true;
	}

	public void sacarDeEspera() {
		enEspera = false;
	}

	@Override
	public void informarGanador(String ganador) throws RemoteException {
		informarGanador = new InformarGanador(ganador, this);
		fichaSeleccionada = false;
		mnOpciones.remove(mnVolverAlMenu);
		
		// int dialogButton = JOptionPane.showConfirmDialog(null,"Ganó " + ganador + "
		// \r\nQuiere volver al menú?", "Partida finalizada",
		// JOptionPane.YES_NO_OPTION);
		// if (dialogButton == JOptionPane.YES_OPTION) {
		// setContentPane(panelPrincipal);
		// controlador.ponerTableroVacio();
		// } else {
		// mnOpciones.add(mnVolverAlMenu);
		// }
	}

	public void errorJugadoresInsuficientes() {
		errorJugadoresInsuficientes = new ErrorJugadoresInsuficientes(this);

	}

	public void errorJugadorAgregado() {
		errorJugadorAgregado = new ErrorJugadorAgregado(this);
	}

	@Override
	public void errorMovimientoInvalido() throws RemoteException {
		fichaSeleccionada = false;
		errorMovimientoInvalido = new ErrorMovimientoInvalido(this);
		/// JOptionPane.showMessageDialog(null, "Ingrese una posición valida para mover
		/// la ficha seleccionada");
	}

	@Override
	public void errorMovimientoComer() throws RemoteException {
		errorMovimientoComer = new ErrorMovimientoComer(this);
		// JOptionPane.showMessageDialog(null,
		// "Movimiento invalido. Recuerde que los movimientos de comer son obligatorios
		// si existe la posibilidad");
	}

	@Override
	public void errorFichaSeleccionada() throws RemoteException {
		errorFichaSeleccionada = new ErrorFichaSeleccionada(this);
		// JOptionPane.showMessageDialog(null, "Ingrese una posición válida");
	}

	@Override
	public void errorCasillaSeleccionada() throws RemoteException {
		fichaSeleccionada = false;
		errorCasillaSeleccionada = new ErrorCasillaSeleccionada(x, y, this);
		// JOptionPane.showMessageDialog(null, "Ingrese una posicion válida para mover
		// la ficha seleccionada ([" + x + "," + y + "])");
	}

	public void errorColorAsignado() {
		errorColorAsignado = new ErrorColorAsignado(this);
		sacarDeEspera();
	}

	public void errorJugadorAsignado() {
		errorJugadorAsignado = new ErrorJugadorAsignado(this);
		sacarDeEspera();

	}

	@Override
	public void seleccionarFicha() throws RemoteException {
		controlador.seleccionarFicha(x, y);

	}

	@Override
	public void seleccionarCasilla() throws RemoteException {
		controlador.seleccionarCasilla(x1, y1);
	}

	@Override
	public void fichaSeleccionada() {
		fichaSeleccionada = true;
	}

	public void deseleccionarFicha() {
		fichaSeleccionada = false;
	}

	@Override
	public void movimientoRealizado() {
		fichaSeleccionada = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cargarPartida() throws RemoteException {
		controlador.cargarPartida("Damas.dat");
	}

	@Override
	public void guardarPartida() {
		controlador.guardarPartida("Damas.dat");
	}

	@Override
	public void reanudarPartida(int[][] tablero, char turno) {
		crearTablero();
		actualizarTablero(tablero, turno);
	}

	@Override
	public void activarMovimientos() {
		this.movimientosActivados = true;

	}

	@Override
	public void desactivarMovimientos() {
		this.movimientosActivados = false;

	}

	public void juegoIniciado() {
		enJuego = true;
	}

	public void juegoTerminado() {
		enJuego = false;
		movimientosActivados = false;
	}

}
