package VistaGUI;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Vista.VistaGrafica;




public class AcercaDe extends JFrame {

	
	private JLabel label1,label2,label3,label4;
	
	private JTextArea area;
	
	private JScrollPane panel;
	
	public AcercaDe() {
		this.setTitle("Acerca de...");
		iniciar();
		alinear();
		setSize(300,250);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		this.setResizable(false);
	}
	
	public void alinear() {
		setLayout(null);
		label1.setBounds(65,0,200,50);
		getContentPane().add(label1);
		label2.setBounds(210,0,1000,400);
		getContentPane().add(label2);
		label3.setBounds(10,0,500,200);
		getContentPane().add(label3);
		label4.setBounds(10,0,500,250);
		getContentPane().add(label4);
	}
	
	public void iniciar() {
		label1 = new JLabel("Juego De Damas");
		label1.setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
		label2 = new JLabel("Version 1.0");
		label2.setFont(new Font("Arial", Font.CENTER_BASELINE, 13));
		label3 = new JLabel("Hecho por: ");
		label3.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
		label4 = new JLabel("Tomás Giachetta");
		label4.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
	}
	
}
