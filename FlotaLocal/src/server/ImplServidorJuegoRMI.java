package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import common.IntClienteCallback;
import common.IntServidorJuegoRMI;
import common.IntServidorPartidasRMI;

public class ImplServidorJuegoRMI extends UnicastRemoteObject implements IntServidorJuegoRMI{

	IntServidorPartidasRMI servidorPartida;
	Map<String, IntClienteCallback> partidas;
	
	public ImplServidorJuegoRMI() throws RemoteException {
		super();
		partidas = new HashMap<>();
	}

	@Override
	public IntServidorPartidasRMI nuevoServidorPartidas() throws RemoteException {
		// TODO Auto-generated method stub
		
		return new ImplServidorPartidasRMI() ;
	}

	@Override
	public boolean proponPartida(String nombreJugador, IntClienteCallback callbackClientObject) throws RemoteException {
		if(partidas.containsKey(nombreJugador)) {
			System.out.println(nombreJugador+ " ya tiene una partida creada");
			return false;
		}
		partidas.put(nombreJugador, callbackClientObject);
		System.out.println(nombreJugador+" propone partida");
		return true;
	}

	@Override
	public boolean borraPartida(String nombreJugador) throws RemoteException {
		if(partidas.remove(nombreJugador)==null) {
			System.out.println(nombreJugador+" no tiene partida");
			return false;
		}
		System.out.println(nombreJugador+" ha borrado la partida");
		return true;
	}

	@Override
	public String[] listaPartidas() throws RemoteException {
		
		String[] lis = partidas.keySet().toArray(new String[0]);
		//ArrayList<String> lis = new ArrayList<>();
		
		System.out.println("Un jugador ha pedido las partidas creadas");
		return lis;
	}

	@Override
	public boolean aceptaPartida(String nombreJugador, String nombreRival) throws RemoteException {
		System.out.println("dentro de acepta partida");
		if(partidas.containsKey(nombreRival)) {
			IntClienteCallback cli = partidas.remove(nombreRival);
			try {
				cli.notificar(nombreJugador);
			}catch(RemoteException ex) {
				System.out.println("no se ha podido aceptar la partida");
				return false;
			}
			System.out.println("Empieza la partida: "+ nombreRival+" vs "+nombreJugador);
			return true;
			
		}
		System.out.println("no hay partidas propuestas");
		return false;
	}

}
