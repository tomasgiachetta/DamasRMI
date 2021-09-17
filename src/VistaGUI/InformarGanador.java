package VistaGUI;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;

import Vista.IVista;
import Vista.VistaGrafica;

public class InformarGanador extends JFrame {
	private Label label;
	private Label label2;
	private JButton boton;
	private JButton boton2;
	
	
	public InformarGanador(String ganador, VistaGrafica vista) {
		this.setTitle("Juego finalizado");
		setSize(270, 130);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(vista);
		setLayout(null);
		this.setResizable(false);
		label = new Label("Ganó " + ganador);
		label.setBounds(30,15,400,15);
		label2 = new Label("Quiere volver al menú?");
		label2.setBounds(30,30,400,15);
		boton = new JButton("Sí");
		boton.setBounds(30,55,80,25);
		boton2 = new JButton("No");
		boton2.setBounds(130,55,80,25);
		boton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					vista.volverAlMenu();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				
			}
		});
		boton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				vista.agregarBotonVolverAlMenu();
				dispose();
				
			}
		});
		
		
		
		add(label);
		add(label2);
		add(boton);
		add(boton2);
		
		
		setVisible(true);
	}

}