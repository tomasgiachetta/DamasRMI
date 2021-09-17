package VistaGUI;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import Vista.VistaGrafica;

public class JugadorEliminado extends JFrame {
	private Label label;
	private JButton boton;
	
	
	public JugadorEliminado(VistaGrafica vista) {
		this.setTitle("Jugador eliminado");
		setSize(270, 130);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(vista);
		setLayout(null);
		this.setResizable(false);
		label = new Label("Jugador eliminado correctamente");
		label.setForeground(Color.red);
		label.setBounds(40,20,400,20);
		boton = new JButton("Okey");
		boton.setBounds(73,55,100,30);
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