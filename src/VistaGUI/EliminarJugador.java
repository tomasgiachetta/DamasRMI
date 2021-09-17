package VistaGUI;

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
import Vista.VistaGrafica;

public class EliminarJugador extends JFrame {

	private Label label;

	private JButton boton1;

	private JComboBox<Object> combo;

	private VistaGrafica vista;

	private ArrayList<Jugador> jugadores;

	private JButton boton2;

	public EliminarJugador(VistaGrafica vista) {
		this.vista = vista;
		this.setTitle("Eliminar jugador");
		setLayout(null);

		setResizable(false);
		setSize(400, 150);
		setLocationRelativeTo(vista);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		combo = new JComboBox<>();

		combo.setBounds(120, 35, 120, 20);
		combo.addItem("");

		try {
			jugadores = vista.obtenerJugadores();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Jugador jugador : jugadores) {
			combo.addItem(jugador.getNombre());
		}
		add(combo);

		label = new Label("Elija el jugador que desea eliminar.");
		label.setBounds(90, 10, 250, 20);
		add(label);

		boton1 = new JButton("Eliminar jugador");
		boton1.setBounds(50, 70, 140, 30);

		boton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (combo.getSelectedIndex() != 0) {
					int dialogButton = JOptionPane.showConfirmDialog(null,
							"¿Desea eliminar al jugador seleccionado?"
									+ " \r\nLos jugadores eliminados perderan las estadisticas",
							"Eliminar Jugador", JOptionPane.YES_NO_OPTION);
					if (dialogButton == JOptionPane.YES_OPTION) {
						try {
							vista.eliminarJugador(jugadores.get(combo.getSelectedIndex() - 1));
							dispose();
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else
					JOptionPane.showMessageDialog(EliminarJugador.this, "Seleccione un jugador.");
			}

		});

		add(boton1);

		boton2 = new JButton("Salir");
		boton2.setBounds(220, 70, 100, 30);
		boton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}

		});

		add(boton2);

	}

}
