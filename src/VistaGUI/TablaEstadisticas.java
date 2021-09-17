package VistaGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Modelo.Jugador;

public class TablaEstadisticas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ArrayList<Jugador> jugadores;
	

	public TablaEstadisticas(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores; 
		setVisible(true);
		setTitle("Tabla de estadisticas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 604, 347);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 560, 227);
		getContentPane().add(scrollPane);

		String[] columnas = { "Jugador", "Jugados", "Victorias", "Derrotas", "Efectividad" };
		final DefaultTableModel modelo;
		modelo = new DefaultTableModel(null, columnas);


		table = new JTable(modelo) {

		};

		table.setBounds(26, 26, 463, 332);
		scrollPane.setViewportView(table);

		for (Jugador j: jugadores) {
			Object[] nuevafila = { j.getNombre(), j.partidosJugados(), j.partidosGanados(), j.partidosPerdidos(),  j.porcentajeGanados() + "%" };
			modelo.addRow(nuevafila);
		}

	}

	


}
