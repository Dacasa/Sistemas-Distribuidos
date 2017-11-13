package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IntClienteCallback extends Remote{
	
	void notificar(String nombreRival) throws RemoteException;
	
}
