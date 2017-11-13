package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.IntClienteCallback;

public class ImplClienteCallback extends UnicastRemoteObject implements IntClienteCallback {

	public ImplClienteCallback() throws RemoteException{
		super();
	}
	
	@Override
	public void notificar(String nombreRival) throws RemoteException {
		System.out.println(" partida aceptada por "+nombreRival);
		
	}
}
