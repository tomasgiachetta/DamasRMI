package VistaGUI;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Modelo.Jugador;
import Vista.IVista;
import Vista.VistaGrafica;



public class ElegirJugadores extends JFrame implements ActionListener{
	
	private Label color,jugador;
	
	private JButton boton1,boton2;
	
	private JComboBox<Object> combo1,combo2;

	private VistaGrafica vista;

	private ArrayList<Jugador> jugadores;


	public ElegirJugadores(VistaGrafica vista, ArrayList<Jugador> jugadores) {
		this.vista = vista;
		this.jugadores = jugadores;
		this.setTitle("Elegir jugador");
		iniciar();
		setLayout(null);
		this.setResizable(false);
		getContentPane().add(color);
		getContentPane().add(jugador);
		getContentPane().add(combo1);
		getContentPane().add(combo2);
		getContentPane().add(boton1);
		getContentPane().add(boton2);
		setResizable(false);
		setSize(400,150);
		setLocationRelativeTo(vista);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		if (jugadores.size() < 1) {
			boton1.setEnabled(false);
		} else {
			boton1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String color;
					if (combo1.getSelectedIndex() == 1) 
						color = "blancas";
					else
						color = "negras";
					if (combo2.getSelectedIndex() != 0 && combo1.getSelectedIndex() != 0) {
						try {
							vista.ponerEnEspera();
							vista.asignarJugador(color, jugadores.get(combo2.getSelectedIndex()-1));
							
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						dispose();
					} else
						JOptionPane.showMessageDialog(ElegirJugadores.this, "Seleccione los campos correctamente.");
					//vista.crearTablero();
					//try {
					//	vista.iniciarPartida();
					//} catch (RemoteException e1) {
						// TODO Auto-generated catch block
					//	e1.printStackTrace();
					//}
					
					
				}
				
			});
		}
	}
		
	
	public void iniciar() {
		boton1= new JButton("Elegir jugador");
		boton1.setBounds(50,70,140,30);

		
		boton2= new JButton("Salir");
		boton2.setBounds(220,70,100,30);
		boton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
			
		});
		
		color = new Label("Elija el color");
		color.setBounds(50,10,70,20);
		jugador = new Label("Elija el jugador");
		jugador.setBounds(220,10,88,20);
				
		
		combo1 = new JComboBox<>();
		combo2 = new JComboBox<>();
		combo1.addItem("");
		combo2.addItem("");
		combo1.setBounds(50,35,120,20);
		combo1.addItem("Blancas");
		combo1.addItem("Negras");
		combo2.setBounds(220,35,120,20);
		for (Jugador jugador : jugadores) {
			combo2.addItem(jugador.getNombre());
		}
	
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
