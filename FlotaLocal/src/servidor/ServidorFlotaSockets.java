package servidor;

import java.net.ServerSocket;

import comun.MyStreamSocket;

/**
 * Este modulo contiene la logica de aplicacion del servidor del juego Hundir la flota
 * Utiliza sockets en modo stream para llevar a cabo la comunicacion entre procesos.
 * Puede servir a varios clientes de modo concurrente lanzando una hebra para atender a cada uno de ellos.
 * Se le puede indicar el puerto del servidor en linea de ordenes.
 */


public class ServidorFlotaSockets {
   
   public static void main(String[] args) {
	   
	  // Acepta conexiones vía socket de distintos clientes.
	  // Por cada conexión establecida lanza una hebra de la clase HiloServidorFlota.
	   
	   int puertoServidor = 8034;
	   String mensaje;
	   if(args.length ==1)
		   puertoServidor = Integer.parseInt(args[0]);
	   try {
		   ServerSocket miSocketConexion = new ServerSocket(puertoServidor);
		   System.out.println("Servidor socket Listo");
		   
		   while(true) {
			   System.out.println("Esperando conexion");
			   MyStreamSocket miSocketDatos = new MyStreamSocket(miSocketConexion.accept());
			   System.out.println("Conexion Aceptada");
			   Thread hilo = new Thread(new HiloServidorFlota(miSocketDatos));
			   hilo.start();
		   }
		   
		   
	   }
	   catch (Exception ex) {
		   ex.printStackTrace();
	   }
	  // Revisad el apartado 5.5 del libro de Liu
 
	   
   } //fin main
} // fin class
