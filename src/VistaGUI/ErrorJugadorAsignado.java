package VistaGUI;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import Vista.VistaGrafica;

public class ErrorJugadorAsignado extends JFrame {
	private Label label;
	private JButton boton;
	
	
	public ErrorJugadorAsignado(VistaGrafica vista) {
		this.setTitle("Error");
		setSize(450, 130);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(vista);
		setLayout(null);
		this.setResizable(false);
		label = new Label("El jugador elegido ya fue seleccionado por otro jugador.");
		label.setForeground(Color.red);
		label.setBounds(40,20,400,20);
		boton = new JButton("Okey");
		boton.setBounds(163,55,100,30);
		boton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		add(label);
		add(boton);
		
		setVisible(true);
	}
}