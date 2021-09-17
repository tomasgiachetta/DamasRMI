package Modelo;

import java.rmi.RemoteException;

public interface Observador {

		public void notificar(Eventos evento) throws RemoteException;
		public void notificarCambio(int [][] tablero, char turno);
		public void notificarGanador(String ganador) throws RemoteException;
		public void notificarCarga(int [][] tablero, char turno);
		//public void notificarMensaje();
	//	public void notificarError(EventosError evento);

	

}
