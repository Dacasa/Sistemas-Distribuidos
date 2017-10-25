package common;

import java.rmi.*;

public interface IntServidorPartidasRMI extends Remote{
	public void nuevaPartida(int filas, int columnas) throws java.rmi.RemoteException;
	public int pruebaCasilla(int fila, int columna) throws java.rmi.RemoteException;
	public String getBarco(int id) throws java.rmi.RemoteException;
	public String[] getSolucion() throws java.rmi.RemoteException;

}
