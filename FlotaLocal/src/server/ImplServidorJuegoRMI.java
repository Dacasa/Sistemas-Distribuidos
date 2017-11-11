package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.IntCallbackCliente;
import common.IntServidorJuegoRMI;
import common.IntServidorPartidasRMI;

public class ImplServidorJuegoRMI extends UnicastRemoteObject implements IntServidorJuegoRMI{

	@Override
	public IntServidorPartidasRMI nuevoServidorPartidas() throws RemoteException {
		// TODO Auto-generated method stub
		try {
			String registryURL = "rmi://localhost:1099/barcos";
			IntServidorPartidasRMI partida = (IntServidorPartidasRMI)Naming.lookup(registryURL);
			return partida;
		}catch (Exception e){
			System.out.println("Exception: Error al crear la partida");
		}
		return null ;
	}

	@Override
	public boolean proponPartida(String nombreJugador, IntCallbackCliente callbackClientObject) throws RemoteException {
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
