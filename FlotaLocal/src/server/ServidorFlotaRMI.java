package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServidorFlotaRMI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		      try{     
		         // start a security manager - this is needed if
		         // stub downloading is in use for this application.
		         System.setSecurityManager(new SecurityManager());

		         startRegistry(1099);
		         ImplServidorJuegoRMI exportedObj = new ImplServidorJuegoRMI();
		         String registryURL = "rmi://localhost:1099/barcos";
		         Naming.rebind(registryURL, exportedObj);
		         System.out.println("Server registered. Registry contains:");
		         // list names currently in the registry
		         listRegistry(registryURL);
		         System.out.println("Flota Server ready.");
		      }// end try
		      catch (Exception re) {
		         System.out.println("Exception in FlotaServer.main: " + re);
		      } // end catch
		  } // end main

		  // This method starts a RMI registry on the local host, if
		  // it doesn't already exists at the specified port number.
		   private static void startRegistry(int RMIPortNum)
		      throws RemoteException{
		      try {
		         Registry registry = 
		            LocateRegistry.getRegistry(RMIPortNum);
		         registry.list();  // This call will throw an
		         //exception if the registry does not already exist
		      } catch (RemoteException e) { 
		         // No valid registry at that port.
		         System.out.println("RMI registry cannot be located at port " + RMIPortNum);
		         LocateRegistry.createRegistry(RMIPortNum);
		         System.out.println("RMI registry created at port " + RMIPortNum);
		      }
		   } // end startRegistry

		  //This method lists the names registered with a Registry
		  private static void listRegistry(String registryURL) throws RemoteException, MalformedURLException {
		       System.out.println("Registry " + registryURL + " contains: ");
		       String [] names = Naming.list(registryURL);
		       for (int i=0; i < names.length; i++)
		          System.out.println(names[i]);
		  }
	}


