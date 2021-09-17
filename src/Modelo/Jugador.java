package Modelo;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Jugador implements Serializable{

	private int jugados, ganados, perdidos;
	private String nombre;
	
	
	public Jugador(String nombre) {
		this.nombre = nombre;
		jugados = 0;
		ganados = 0;
		perdidos = 0;
	}
	
	
	public void victoria() {
		ganados++;
		jugados++;
	}
	
	public void derrota() {
		perdidos++;
		jugados++;
	}
	
	public int partidosJugados() {
		return jugados;
	}
	
	public int partidosGanados() {
		return ganados;
	}
	
	public int partidosPerdidos() {
		return perdidos;
	}
	
	public String porcentajeGanados() {
		DecimalFormat df2 = new DecimalFormat("#.##");
		double porcentaje;
		if (jugados == 0) 
			porcentaje = 0;
		else
			porcentaje = ((((double) ganados/(double) jugados))*100);
		
		return df2.format(porcentaje);
		
	}
	
	public String getNombre() {
		return nombre;
	}

}
