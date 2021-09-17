package VistaGUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import Vista.IVista;
import Vista.VistaGrafica;

public class ErrorMovimientoComer extends JFrame {

	private Label label;
	private JButton boton;
	
	
	public ErrorMovimientoComer(VistaGrafica vista) {
		this.setTitle("Movimiento Invalido");
		setSize(480, 130);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(vista);
		setLayout(null);
		this.setResizable(false);
		label = new Label("Movimiento invalido. Recuerde que los movimientos de comer son obligatorios");
		label.setForeground(Color.red);
		label.setBounds(13,20,440,20);
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
