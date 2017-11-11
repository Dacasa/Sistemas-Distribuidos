package server;

import java.rmi.RemoteException;
import tablero.Barco;
import tablero.Partida;

import common.IntServidorPartidasRMI;

public class ImplServidorPartidasRMI implements IntServidorPartidasRMI{
	private Partida partida;
	//private Barco barco;

	@Override
	public void nuevaPartida(int filas, int columnas) throws RemoteException {
		this.partida = new Partida(filas, columnas, 6);
		
	}

	@Override
	public int pruebaCasilla(int fila, int columna) throws RemoteException {
		// TODO Auto-generated method stub
		return partida.pruebaCasilla(fila, columna);
	}

	@Override
	public String getBarco(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return partida.getBarco(id);
	}

	@Override
	public String[] getSolucion() throws RemoteException {
		// TODO Auto-generated method stub
		return partida.getSolucion();
	}

}
