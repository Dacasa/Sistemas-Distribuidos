package tablero;


import java.io.IOException;
import java.net.SocketException;


/**
 * Clase ejecutada por cada hebra encargada de servir a un cliente del juego Hundir la flota.
 * El metodo run contiene la logica para gestionar una sesion con un cliente.
 */

 // Revisar el apartado 5.5. del libro de Liu

class HiloServidorFlota implements Runnable {
   MyStreamSocket myDataSocket;
   private Partida partida = null;

	/**
	 * Construye el objeto a ejecutar por la hebra para servir a un cliente
	 * @param	myDataSocket	socket stream para comunicarse con el cliente
	 */
   HiloServidorFlota(MyStreamSocket myDataSocket) {
      // Por implementar
	   this.myDataSocket = myDataSocket;
   }
 
   /**
	* Gestiona una sesion con un cliente	
   */
   public void run( ) {
	  // IMPLEMENTADO
      boolean done = false;
      int operacion = 0;
      // ...
      try {
         while (!done) {
        	 // Recibe una peticion del cliente
        	 String[] mensaje = myDataSocket.receiveMessage().split("#");
        	 // Extrae la operación y los argumentos
        	 operacion = Integer.parseInt(mensaje[0]);
                      
             switch (operacion) {
             case 0:  // fin de conexión con el cliente
            	 done = true;
            	 myDataSocket.close();
            	 System.out.println("Conexion terminada");
            	 break;

             case 1: { // Crea nueva partida
            	 partida = new Partida(Integer.parseInt(mensaje[1]),Integer.parseInt(mensaje[2]),Integer.parseInt(mensaje[3]));
            	 break;
             }             
             case 2: { // Prueba una casilla y devuelve el resultado al cliente
            	 int resultado = partida.pruebaCasilla(Integer.parseInt(mensaje[1]),Integer.parseInt(mensaje[2]));
            	 myDataSocket.sendMessage(String.valueOf(resultado));
                 break;
             }
             case 3: { // Obtiene los datos de un barco y se los devuelve al cliente
            	 String resultado = partida.getBarco(Integer.parseInt(mensaje[1]));
            	 myDataSocket.sendMessage(resultado);
                 break;
             }
             case 4: { // Devuelve al cliente la solucion en forma de vector de cadenas
        	   // Primero envia el numero de barco
            	 String[] solucion = partida.getSolucion();
            	 myDataSocket.sendMessage(String.valueOf(solucion.length));
               // Despues envia una cadena por cada barco
            	 for(String barco: solucion){
            		 myDataSocket.sendMessage(barco);
            	 }
               break;
             }
         } // fin switch
       } // fin while   
     } // fin try
     catch (Exception ex) {
        System.out.println("Exception caught in thread: " + ex);
     } // fin catch
   } //fin run
   
} //fin class 
