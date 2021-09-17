package Ejecutable;

import java.awt.EventQueue;
import java.rmi.RemoteException;

import Controlador.Controlador;
import Modelo.Juego;
import Vista.IVista;
import Vista.VistaConsola;
import Vista.VistaGrafica;


public class Aplication {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//VistaConsola vista = new VistaConsola();
				//Juego juego = new Juego();
				IVista vista = new VistaGrafica();
				Controlador controlador = new Controlador(vista);
				vista.setControlador(controlador);
				vista.iniciar();
			//	VistaGrafica vista2 = new VistaGrafica(controlador);
				try {
					//vista.comenzarJuego();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});
	}
}