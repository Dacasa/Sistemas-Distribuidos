package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import tablero.Barco;
import tablero.Partida;

import common.IntServidorPartidasRMI;

public class ImplServidorPartidasRMI extends UnicastRemoteObject implements IntServidorPartidasRMI{
	/**
	 * 
	 */
	
	Partida partida;
	//private Barco barco;
	public ImplServidorPartidasRMI() throws RemoteException{
		super();
	}

	@Override
	public void nuevaPartida(int filas, int columnas, int barcos) throws RemoteException {
		partida = new Partida(filas, columnas, barcos);
		
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
