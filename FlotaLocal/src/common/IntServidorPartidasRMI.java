package common;

import java.rmi.*;

public interface IntServidorPartidasRMI extends Remote{
	public void nuevaPartida(int filas, int columnas, int barcos) throws RemoteException;
	public int pruebaCasilla(int fila, int columna) throws RemoteException;
	public String getBarco(int id) throws RemoteException;
	public String[] getSolucion() throws RemoteException;

}
