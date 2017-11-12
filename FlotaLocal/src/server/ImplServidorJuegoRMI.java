package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.IntClienteCallback;
import common.IntClienteCallback;
import common.IntServidorJuegoRMI;
import common.IntServidorPartidasRMI;

public class ImplServidorJuegoRMI extends UnicastRemoteObject implements IntServidorJuegoRMI{

	IntServidorPartidasRMI servidorPartida;
	
	public ImplServidorJuegoRMI() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public IntServidorPartidasRMI nuevoServidorPartidas() throws RemoteException {
		// TODO Auto-generated method stub
		
		return new ImplServidorPartidasRMI() ;
	}

	@Override
	public boolean proponPartida(String nombreJugador, IntClienteCallback callbackClientObject) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean borraPartida(String nombreJugador) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] listaPartidas() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean aceptaPartida(String nombreJugador, String nombreRival) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
