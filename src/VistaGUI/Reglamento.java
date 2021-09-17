package VistaGUI;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Reglamento extends JFrame {

	
	
	private JLabel label1,label2,label3;
	
	private JTextArea area;
	
	private JScrollPane panel;
	
	
	public Reglamento() {
		this.setTitle("Reglas de juego");
		iniciar();
		setLayout(null);
		this.setResizable(false);
		label1.setBounds(160,0,500,50);
		getContentPane().add(label1);
		panel.setBounds(0,50,530,200);
		getContentPane().add(panel);
		setSize(530,350);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	
	public void iniciar() {
		label1 = new JLabel("Juego De Damas");
		label1.setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
		area = new JTextArea();
		area.setEditable(false);
		area.setText("Objetivo del juego\r\n" 
				+ "El juego de las damas consta de 24 peones divididos en 12 blancos y 12 negros\r\n" 
				+ "y un tablero de 64 casillas (8×8).\r\n" 
				+ "La finalidad del juego es la captura o bloqueo de todas las piezas contrarias,\r\n" 
				+ "de forma que no les sea posible realizar movimiento.\r\n"
				+ "\r\n"
				+ "Cómo jugar a las damas\r\n"  
				+ "Cada jugador controla las piezas de un color situadas al comienzo a cada lado "
				+ "del tablero.\r\n"
				+ "Empieza el juego las blancas.\r\n" 
				+ "\r\n" 
				+ "Los movimientos se hacen alternativamente, uno por jugador, en diagonal, una sola \r\n"
				+ "casilla y en sentido de avance, o sea, hacia el campo del oponente.\r\n" 
				+ "\r\n" 
				+ "Si un jugador consigue llevar una de sus fichas al lado contrario del tablero\r\n"
				+ "cambiará dicho peón por una dama o reina (dos fichas del mismo color una encima\r\n"
				+ "de otra).\r\n"
				+ "La dama o reina se mueve también en diagonal, pero puede hacerlo hacia delante y\r\n"
				+ "hacia atrás.Nunca podrá saltar por encima de sus propias piezas o dos piezas contiguas.\r\n"
				+ "\r\n"
				+ "Capturar fichas del contrario\r\n" 
				+ "Una pieza puede capturar otra si puede saltar por encima de ella siempre en dirección\r\n"
				+ "de ataque y en diagonal y caer en la casilla inmediatamente detrás de aquella.\r\n"
				+ "Además, las damas pueden capturar en cualquier dirección.\r\n"
				+ "\r\n"
				+ "La captura es obligatoria. Si una o más piezas están en situación de realizar capturas,\r\n"
				+ "será obligatorio realizar tal captura, no pudiendo optar por mover otra pieza."
				+ "\r\n"
				+ "Una vez realizada una captura, tanto un peón como una dama deben volver a capturar si \r\n"
				+ "es posible según las reglas anteriores y en el mismo turno del jugador, y así sucesivamente.\r\n"
				+ "\r\n"
				+ "Final de la partida\r\n" 
				+ "Finaliza la partida cuando un jugador abandona, se queda sin fichas o estas no tienen \r\n"
				+ "posibilidad de movimiento (bloqueo o ahogada).");
		panel = new JScrollPane(area);
	}
	


}
